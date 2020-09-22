package com.biz.book.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.biz.book.config.NaverSecret;
import com.biz.book.model.BookVO;

import lombok.extern.slf4j.Slf4j;

/*
 * naver API 통하여 도서명을 보내고
 * 그 결과를 JSON형태로 수신하여 Parsing 처리를 수행하는 서비스 클래스
 * naver가 server가 되고 ReadBook 프로젝트가 client가 된다.
 * 1. naver API 에 보낼 쿼리 문자열이 포함된 URL을 생성
 * 2. naver API 에서 보내온 문자열을 JSON 객체로 변환
 * 3. parsing을 수행하고
 * 4. BookVO에 담고
 * 5. List<BookVO>에 담기 
 */
@Slf4j
@Service("naverServiceV1")
public class NaverServiceImplV1 implements NaverService<BookVO> {

	// 도서명을 매개변수로 받아서 queryURL을 생성
	public String queryURL(String category, String bookName) {
		
		log.debug(category);
		String queryURL = NaverSecret.NAVER_BOOK_XML;
		if(category.equalsIgnoreCase("NEWS")) {
			queryURL = NaverSecret.NAVER_NEWS_JSON;
		} else if ( category.equalsIgnoreCase("MOVIE")) {
			queryURL = NaverSecret.NAVER_MOVIE_JSON;
		}
		
		String encodeText = null;
		try {
			//한글 검색을 위해서 검색어를 UTF로 encoding 처리해주어야 한다.
			encodeText = URLEncoder.encode(bookName.trim(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// url?query=자바
		queryURL += String.format("?query=%s",encodeText);
		
		// 한번에 조회할 데이터 개수를 지정할수 있다
		queryURL += "&display=10"; 
		return queryURL;
	}
	
	// queryURL을 naver API에게 보내고 데이터를 수신하는 method
	private String getNaverBook(String queryURL) {
		
		// queryURL 문자열을 http 프로토콜을 통해서 전송하기 위한
		// 형식으로 만드는 클래스
		URL url = null;
		
		// http 프로토콜을 사용하여 데이터 주고받는 Helper Class
		HttpURLConnection httpConn = null;
		
		try {
			url = new URL(queryURL);
			httpConn = (HttpURLConnection) url.openConnection();
			
			httpConn.setRequestMethod("GET");
			
			// http 프로토콜에 X-Naver-Client-Id 라는 변수로 
			// Client id값 저장(심기)
			httpConn.setRequestProperty("X-Naver-Client-Id", 
										NaverSecret.NAVER_CLIENT_ID);
			httpConn.setRequestProperty("X-Naver-Client-Secret", 
										NaverSecret.NAVER_CLIENT_SECRET);
			
			// 내가 URL을만들고, GET으로 요청을 하면서 
			// Property에 Client Id와 Client Secret를 저장하여 보냈는데
			// 나에게 응답을 해줄래 ?
			int resCode = httpConn.getResponseCode();
			/*
			 * HTTP 프로토콜에서 response Code
			 * 200 : Server가 요청(Request)를 정상적으로 수신하고 
			 * 		response 할 데이터를 준비중이다
			 * 3xx : 요청은 정상적으로 수신했으나 보낼 데이터가 없거나
			 * 		이미 보냈기 때문에 다시 보내지 않겠다
			 * 302 : redirect 했다
			 * 304 : 직전에 보낸 데이터이가 변화가 없으니 그대로 사용해라
			 * 
			 * 4xx : 요청정보가 문제가 있다
			 * 
			 * 404 : 요청주소(URL,URI)가 서버에서 처리할 end point 가 없다 
			 * 405 : 요청주소는 있으나 method(GET, POST)가 지정되지 않았다
			 * 		브라이저 주소창에 
			 * 		http://localhost:8080/book/input 라고 request를 했는데
			 * 		서버에 @RequestMapping(value="/input",method=POST)만 있을때
			 * 
			 * 		form의 action="/input" method=POST 로 지정된 데이터를
			 * 		submit 했는데
			 * 		서버에 @RequestMapping(value="/input",method=GET)만 있을때
			 * 403 : 요청권한이 없다. 인증이 잘못되었거나 ROLL(역할)이 잘못 되었다
			 * 400 : form에 데이터를 입력하고 서버로 전송했을때
			 * 		Controller 의 매개변수 차원에서 문제가 발생했을때
			 * 		<input name="age"> 라는 input box에 값을 입력하지 않고 
			 * 			submit했는데
			 * 		public String input(int age) 라고 Contoller method의 매개변수로
			 * 			설정해 두었을때
			 * 
			 * 		spring의 Dispatcher는 age 변수에 담긴 값을 int 형으로 형변환을
			 * 			시도한다
			 * 			이때, input box에 아무런 값이 없으면
			 * 			age = Integer.valueOf("") 의 코드가 실행되는 것과 같다
			 * 			이럴때 내부에서 exception이 발생하고
			 * 			res code 로 400을 보낸다
			 *  
			 *  500 : Server Internal Error
			 *  	사용자의 요청을 처리하는 과정에서
			 *  	Controller, Service, Dao, 등등 코드를 실행하는 도중
			 *  	어떠한 원인으로 exception이 발생했을때 
			 *  
			 *  	오류메시지를 제일 하단부터 거꾸로 검토해 나가자
			 *  	Error Stack 메시지는 발생된 순서가 역순으로 나타나기 때문
			 *  
			 */
			
			BufferedReader buffer = null;
			InputStreamReader is = null;

			if(resCode == 200) {
				// naver가 정상적으로 응답을 할것이다
				is = new InputStreamReader(httpConn.getInputStream());
			} else {
				is = new InputStreamReader(httpConn.getErrorStream());
			}
				
			// InputStreamReader와 BufferedReader를 파이프로 연결
			buffer = new BufferedReader(is);
			StringBuffer sBuffer = new StringBuffer();
			
			// naver가 보낸 payload(response data)를 한개의 문자열로
			// 통합하여 수신한다.
			// String sBuffer = "";
			String reader = new String();
			while(  (reader=buffer.readLine()) != null  ) {
				sBuffer.append(reader);
				// sBuffer += reader;
			}
			
//			while(true) {
//				reader = buffer.readLine();
//				if(reader == null) break;
//				sBuffer.append(reader);
//			}
			buffer.close();
			// sBuffer에 append한 문자열을 한개의 문자열로 변환하여 return
			return sBuffer.toString();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	// jsonString을 parsing 하여 Object(VO 등등)으로 바꾸는 기능
	private List<BookVO> getJsonObject(String jsonString ) {
		
		List<BookVO> bookList = new ArrayList<BookVO>();
		
		/*
		 * JSON* 클래스를 사용하여 JSON 문자열을 객체로 변환하기
		 * 1. JSONParser를 사용하여 JSONObject로 변환
		 * 2. JSONObject에서 "items"를 기준으로 잘라서 JSONArray로 변환
		 * 3. JSONArray를 for문으로 반복하면서 각 요소를 다시 JSONObject로 변환
		 * 4. 요소로 변환된 JSONObject에서 각각의 항목을 추출하여 VO에 담기
		 * 5. VO를 List에 add() 하기
		 */
		JSONParser jParser = new JSONParser();
		try {
			// JSONParser도구를 사용하여 JSON형태의 문자열을
			// JSONObject(객체)로 변환하기
			JSONObject jObject = (JSONObject) jParser.parse(jsonString);
			JSONArray jArray = (JSONArray) jObject.get("items");
			
			int size = jArray.size();
			for(int i = 0 ; i < size ; i++) {
				JSONObject jo = (JSONObject) jArray.get(i);
				
				// bookVO = new BookVO(title,link,image,autho,price..);
				/*
				BookVO bookVO = new BookVO();
				bookVO.setTitle(jo.get("title").toString());
				bookVO.setImage(jo.get("image").toString());
				bookVO.setLink(jo.get("link").toString());
				*/
				/*
				 * VO @Builder 를 설정하므로써
				 * VO객체를 생성할때 Builder 패턴을 사용할수 있다.
				 * GoF 패턴 중 생성자 패턴 중 1가지
				 */
				String descript = "";
				if(jo.get("director") != null) {
					descript += String.format("감독: %s <br>", jo.get("director").toString());
				}
				if(jo.get("actor") != null) {
					descript += String.format("출연: %s <br>", jo.get("actor").toString());
				}
				if(jo.get("userRating") != null) {
					descript += String.format("평점: %s <br>", jo.get("userRating").toString());
				}

				/*
				 * 빌드패턴을 사용하여 booKVO 객체 생성
				 * 일반적인 VO 객체를 생성하고 데이터를 Setting 하는 방법
				 * 1. 비어있는 VO객체를 생성 : vo = new VO();
				 * 	  setter method를 사용하여 데이터를 입력하는 방법
				 * 2. 생성자에 값을 설정하고 VO객체를 생성 
				 * 		: vo = new VO(값1,값2,값3...)
				 * 	  생성자에 값을 설정(주입)하고 VO객체를 생성하는 방법은
				 * 	  가. 데이터의 순서가 완전히 개발자 책임이다
				 *			혹여 순서가 바뀐채로 VO객체가 생성되면
				 *    		이후에 발생하는 모든 문제를 개발자가 책임져야 한다.
				 *    나. 일부 데이터만 사용하여 객체를 생성하려면
				 *    		생성자를 필요한 매개변수만 있는 상태로 
				 *    		또다시 만들어야 한다.
				 *    	vo = new VO(값1, 값2) : VO(String 값1, String 값2)
				 *      vo = new VO(값1, 값2, 값3) : VO(String 값1, String 값2, String 값3)
				 *      많은 생성자의 중복 선언이 발생할 수 있다
				 *      
				 * 3. Builder(Build) 패턴을 사용한 객체 생성
				 * 		VO = VO.builder()
				 * 				.변수1(값1)
				 * 				.변수2(값2)
				 * 				.변수3(값3)
				 * 				.build();
				 * 		가. 생성자를 통해서 객체를 필요할때 즉시 생성한다.
				 * 		나. 생성자에 매개변수를 주입하는 방식인데
				 * 			여기서는 필요한 데이터만 매개변수로 주입할수 있다.
				 * 			모든 변수를 생성자에 주입할 필요가 없다.
				 * 		다. 매개변수를 주입할때 
				 * 			set*() 와 같은 method를 사용하지 않고
				 * 			매개변수의 이름을 통해 직접 설정할 수 있다
				 * 		라. 객체를 생성할때 객체 chaining 코딩을 사용할수 있다
				 * 			객체.변수1().변수2().변수3().변수4().변수5()
				 */
				BookVO bookVO = BookVO.builder()
						.title(jo.get("title").toString())
						.image(jo.get("image") == null 
								? "noImage" 
								: jo.get("image").toString())
						.link(jo.get("link").toString())
						.description(jo.get("description") == null
								? descript
								: jo.get("description").toString())
						.build();
				bookList.add(bookVO);
			}
			return bookList;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public String getNaverSearch(String queryURL) {
		// TODO Auto-generated method stub
		return this.getNaverBook(queryURL);
	}

	@Override
	public List<BookVO> getNaverList(String jSonString) {
		// TODO Auto-generated method stub
		return this.getJsonObject(jSonString);
	}
	
	
	
	
}
