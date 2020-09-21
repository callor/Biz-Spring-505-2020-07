package com.biz.book.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.biz.book.config.NaverSecret;

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
@Service
public class NaverService {

	// 도서명을 매개변수로 받아서 queryURL을 생성
	public String queryURL(String bookName) {
		
		String queryURL = NaverSecret.NAVER_BOOK_JSON;
		// url?query=자바
		queryURL += String.format("?query=%s",bookName);
		
		// 한번에 조회할 데이터 개수를 지정할수 있다
		queryURL += "&display=10"; 
		return queryURL;
	}
	
	// queryURL을 naver API에게 보내고 데이터를 수신하는 method
	public String getNaverBook(String queryURL) {
		
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
			
			BufferedReader buffer = null;
			InputStreamReader is = null;
			String reader = new String();
			
			if(resCode == 200) {
				// naver가 정상적으로 응답을 할것이다
				is = new InputStreamReader(httpConn.getInputStream());
				
				// InputStreamReader와 BufferedReader를 파이프로 연결
				buffer = new BufferedReader(is);
				StringBuffer sBuffer = new StringBuffer();
				while(true) {
					reader = buffer.readLine();
					if(reader == null) break;
					sBuffer.append(reader);
				}
				return sBuffer.toString();
			
			} else {
				// naver가 거부를 할것이다.
				is = new InputStreamReader(httpConn.getErrorStream());
				buffer = new BufferedReader(is);
				StringBuffer eBuffer = new StringBuffer();
				while(true) {
					reader = buffer.readLine();
					if(reader == null) break;
					eBuffer.append(reader);
				}
				return eBuffer.toString();
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
}
