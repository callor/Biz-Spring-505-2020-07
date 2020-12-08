package com.biz.data.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.biz.data.config.DataGoConfig;
import com.biz.data.model.BisStationData;
import com.biz.data.model.BisStationList;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BisServiceImplV1 {

	
	public List<BisStationData> getStation() {

		
		// 공공DB로부터 데이터를 수집하는 용도의 클래스
		RestTemplate restTemp = new RestTemplate();
		ResponseEntity<BisStationList> resList = null;

//		restTemp.getInterceptors().add(new ClientHttpRequestInterceptor() {
//			
//			@Override
//			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
//					throws IOException {
//	            ClientHttpResponse response = execution.execute(request,body);
//	            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
//	            return response;
//			}
//		});
		
		restTemp.getInterceptors().add((request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request,body);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response;
        });
		
		String apiURI = DataGoConfig.BIS_URL;
		apiURI += "?ServiceKey=" + DataGoConfig.SEVICE_KEY;

		// springframework.http 패키지의 클래스
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Collections
//				.singletonList(MediaType.APPLICATION_JSON));
//
//		HttpEntity<String> entity 
//			= new HttpEntity<String>("parameters",headers);
		
		try {

			URI bisURI = new URI(apiURI);
			resList = restTemp.exchange(bisURI, 
					HttpMethod.GET, null, BisStationList.class);
			log.debug(resList.getBody().STATION_LIST.toString());
			return resList.getBody().STATION_LIST;

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getString() {

		// 공공DB로부터 데이터를 수집하는 용도의 클래스
		RestTemplate restTemp = new RestTemplate();
		ResponseEntity<String> resList = null;

		String apiURI = DataGoConfig.BIS_URL;
		apiURI += "?serviceKey=" + DataGoConfig.SEVICE_KEY;

		HttpHeaders headers = new HttpHeaders();
	    // headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		
		try {
			URI bisURI = new URI(apiURI);
			resList = restTemp.exchange(bisURI, HttpMethod.GET, entity, String.class);

			 log.debug(resList.getBody());
			// return resList.getBody();
			return resList.getBody();

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	
}
