package com.biz.book.service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.biz.book.config.NaverSecret;
import com.biz.book.model.BookVO;
import com.biz.book.model.Book_XML_Parent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("naverServiceV2_XML")
public class NaverServiceImplV2_XML extends NaverServiceImplV1 {
	
	public String queryURL(String category, String bookName) {

		String queryURL = NaverSecret.NAVER_BOOK_XML;
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

	
	
	// NaverServiceV1의 3개의 method를 모두 상속받아서
	// 여기에 아무런 코드가 없어도 프로젝트가 정상 수행이 되고 있다.
	
	// 3의 method 중에서 getNaverList(jsonString) method만 upgrade하겠다
	@Override
	public List<BookVO> getNaverList(String queryURL) {
		
		// queryURL(요청주소포함된) 문자열을 URI 객체로 만들어
		// http 프로토콜에서 사용할 수 있도록 만드는 클래스
		// 기존에 URL 클래스가 있으나, 새로운 기능을 수행하기 위해서
		// 별도로 URI 클래스를 만들어 놨으며
		// 많은 기능들이 추가되어 있다.
		// RestTamplate를 사용하기 위해서는 
		// queryURL 문자열을 URL 객체가 아닌 URI 객체로 만들어야 한다.
		URI restURI = null;
		
		/*
		 * springframework에서 외부 API를 사용하여 데이터를 가져올때
		 * 기존에는 JSON(XML) 형식으로 가저오고, 
		 * 여러가지 외부 라이브러리를 사용하여 객체로 parsing하는 과정을
		 * 복잡하게 만들어야 했다.
		 * 
		 * spring에서 외부 API를 사용하여 데이터를 가져올일이 점점 많아 지면서
		 * RestTempate라는 클래스를 새로 만들어
		 * framework 프로젝트에서 사용할수 있도록 만들어 두었다.
		 * 
		 * HttpHeader, HttpEntity, ResponseEntity 객체만 잘 작성하면
		 * 외부 API에 요청하고, 응답받은 데이터를 parsing 하는 절차를
		 * 대신 수행 해준다.
		 */
		RestTemplate restTemp = new RestTemplate();
		
		try {
			restURI = new URI(queryURL);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Naver-Client-Id",NaverSecret.NAVER_CLIENT_ID);
		headers.set("X-Naver-Client-Secret",NaverSecret.NAVER_CLIENT_SECRET);

		headers
		.setAccept(Collections.singletonList(MediaType.APPLICATION_XML ));
		
		HttpEntity<String> entity = new HttpEntity<String>("parameter",headers);
		ResponseEntity<Book_XML_Parent> bookList = null;
		
		bookList
			= restTemp.exchange(restURI, 
					HttpMethod.GET,
					entity,
					Book_XML_Parent.class);
		log.debug(bookList.toString());
		return bookList.getBody().channel.item;
	
	}

}
