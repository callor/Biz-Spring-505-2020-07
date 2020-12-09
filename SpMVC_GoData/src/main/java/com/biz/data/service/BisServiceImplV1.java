package com.callor.data.service;

<<<<<<< HEAD:SpMVC_GoData/src/main/java/com/callor/data/service/BisServiceImplV1.java
import java.io.IOException;
=======
import java.io.UnsupportedEncodingException;
>>>>>>> 097dbe2c03d4f463c1cda3baf7880b1f008e2a33:SpMVC_GoData/src/main/java/com/biz/data/service/BisServiceImplV1.java
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

<<<<<<< HEAD:SpMVC_GoData/src/main/java/com/callor/data/service/BisServiceImplV1.java
import com.callor.data.config.DataGoConfig;
import com.callor.data.model.BisStationData;
import com.callor.data.model.BisStationList;
=======
import com.biz.data.config.DataGoConfig;
import com.biz.data.model.BisArriveList;
import com.biz.data.model.BisDestVO;
import com.biz.data.model.BisStationData;
import com.biz.data.model.BisStationList;
>>>>>>> 097dbe2c03d4f463c1cda3baf7880b1f008e2a33:SpMVC_GoData/src/main/java/com/biz/data/service/BisServiceImplV1.java

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
<<<<<<< HEAD:SpMVC_GoData/src/main/java/com/callor/data/service/BisServiceImplV1.java
public class BisServiceImplV1 implements BisService {

=======
public class BisServiceImplV1 implements BisService{
>>>>>>> 097dbe2c03d4f463c1cda3baf7880b1f008e2a33:SpMVC_GoData/src/main/java/com/biz/data/service/BisServiceImplV1.java
	
	public List<BisStationData> getStation() {
		
		// 공공DB로부터 데이터를 수집하는 용도의 클래스
		RestTemplate restTemp = new RestTemplate();
		ResponseEntity<BisStationList> resList = null;

<<<<<<< HEAD:SpMVC_GoData/src/main/java/com/callor/data/service/BisServiceImplV1.java

		restTemp.getInterceptors().add(new ClientHttpRequestInterceptor() {
			@Override
			public ClientHttpResponse intercept(HttpRequest request,byte[] body,ClientHttpRequestExecution execution) throws IOException {
	            ClientHttpResponse response = execution.execute(request,body);
	            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
	            return response;
			}
		});
		
//		restTemp.getInterceptors().add((request, body, execution) -> {
//            ClientHttpResponse response = execution.execute(request,body);
//            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
//            return response;
//        });
		
=======
>>>>>>> 097dbe2c03d4f463c1cda3baf7880b1f008e2a33:SpMVC_GoData/src/main/java/com/biz/data/service/BisServiceImplV1.java
		String apiURI = DataGoConfig.BIS_URL;
		apiURI += "?ServiceKey=" + DataGoConfig.SEVICE_KEY;
		

// 		별도로 클래스를 만들어 interceptor를 설정하는 방법
//		request,body, excution등을 주입해주어야 해서 매우 복잡하다		
//		RestTempInterceptor rtInter = new RestTempInterceptor();
//		restTemp.getInterceptors().add(
//				rtInter.intercept(request, body, execution)
//		)
		
		/**
		 * ClientHttpRequestInterceptor interface를 사용하여
		 * 익명클래스를 선언하기
		 */
//		restTemp.getInterceptors().add(new ClientHttpRequestInterceptor() {
//			@Override
//			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
//					throws IOException {
//
//				ClientHttpResponse response = execution.execute(request, body);
//				response
//					.getHeaders()
//					.setContentType(MediaType.APPLICATION_JSON);
//				return response;
//			}
//		});
		
		// java Lambda 형식으로 구현한 Interceptor
		// java Lambda 구현을 위해서는 Interface가 필수적으로 요구된다
		restTemp.getInterceptors().add((request,body,execution)->{
			ClientHttpResponse response = execution.execute(request, body);
			response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
			return response;
		});
		
		try {

			URI bisURI = new URI(apiURI);
			resList = restTemp.exchange(bisURI, 
<<<<<<< HEAD:SpMVC_GoData/src/main/java/com/callor/data/service/BisServiceImplV1.java
					HttpMethod.GET, null, BisStationList.class);
			log.debug(resList.getBody().toString());
=======
					HttpMethod.GET, 
					null, 
					BisStationList.class);
			
			// log.debug(resList.getBody().STATION_LIST.toString());
>>>>>>> 097dbe2c03d4f463c1cda3baf7880b1f008e2a33:SpMVC_GoData/src/main/java/com/biz/data/service/BisServiceImplV1.java
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
		ResponseEntity<String> resString = null;

		String apiURI = DataGoConfig.BIS_URL;
		apiURI += "?serviceKey=" + DataGoConfig.SEVICE_KEY;
		
		try {
			URI bisURI = new URI(apiURI);
			resString = restTemp.exchange(bisURI, 
					HttpMethod.GET, 
					null, 
					String.class);

			 log.debug(resString.getBody());
			 return resString.getBody();

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BisDestVO> busstop(String station) {

		String queryString = DataGoConfig.BIS_DEST_URL;
		queryString += "?ServiceKey=" + DataGoConfig.SEVICE_KEY;
		try {
			queryString += "&BUSSTOP_ID=" + URLEncoder.encode(station,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		RestTemplate restTemp = new RestTemplate();
		ResponseEntity<BisArriveList> bisArrList;
		URI apiURI = null;
		
		restTemp.getInterceptors().add((request,body,execution)->{
			ClientHttpResponse response = execution.execute(request, body);
			response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
			return response;
		});

		try {
			apiURI = new URI(queryString);
			bisArrList = restTemp.exchange(
					apiURI, HttpMethod.GET,
					null,
					BisArriveList.class);
			
			log.debug(bisArrList.getBody().RESULT.get("RESULT_CODE"));
			log.debug(bisArrList.getBody().BUSSTOP_LIST.toString());
			 return bisArrList.getBody().BUSSTOP_LIST;
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String busstopString(String station) {

		String queryString = DataGoConfig.BIS_DEST_URL;
		queryString += "?ServiceKey=" + DataGoConfig.SEVICE_KEY;
		try {
			queryString += "&BUSSTOP_ID=" + URLEncoder.encode(station,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		RestTemplate restTemp = new RestTemplate();
		ResponseEntity<String> bisArrList;
		URI apiURI = null;
		
		restTemp.getInterceptors().add((request,body,execution)->{
			ClientHttpResponse response = execution.execute(request, body);
			response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
			return response;
		});

		try {
			apiURI = new URI(queryString);
			bisArrList = restTemp.exchange(
					apiURI, HttpMethod.GET,
					null,
					String.class);
			
			log.debug(bisArrList.getBody().toString());
			return bisArrList.getBody();
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	
}
