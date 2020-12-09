package com.biz.data.config;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * RestTemplate 을 사용하여 OpenAPI데이터를 가져올때
 * HttpHeader에 APLLICATION_JSON, APLLICATION_XML형식을
 * 지정했음에도 불구하고
 * OpenAPI 데이터를 제공하는 곳에서
 * application/json, application/xml 형식으로 보내야 하는데
 * text/html, text/json, text/xml 형식으로 데이터를 보내는 경우
 * VO클래스에 데이터를 mapping하지 못해서 오류가 발생한다.
 * 
 * RestTemplate에 interceptor를 설정하여
 * 강제로 데이터를 변환시키도록 하기 위한 클래스 선언
 * ClientHttpRequestInterceptor 상속받아서
 * method를 구현해주어야 한다.
 */
public class RestTempInterceptor implements ClientHttpRequestInterceptor{

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {

		ClientHttpResponse response 
			= execution.execute(request, body);
		response
		.getHeaders()
		.setContentType(MediaType.APPLICATION_JSON);
		
		return response;
	}
	
}