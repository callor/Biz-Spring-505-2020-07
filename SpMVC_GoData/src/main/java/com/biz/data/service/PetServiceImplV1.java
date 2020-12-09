package com.biz.data.service;

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

import com.biz.data.config.DataGoConfig;
import com.biz.data.config.model.pet.GoPetVO;
import com.biz.data.config.model.pet.RfcOpenAPI;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PetServiceImplV1 implements PetService {

	public List<GoPetVO> getHosp(String cat, String search) {
		log.debug("{}, {}",cat,search);
		String queryString = DataGoConfig.PET_URL;
		queryString += "/getDongMulHospital";
		queryString += "?ServiceKey=" + DataGoConfig.SEVICE_KEY;
		queryString += "&pageNo=1";
		queryString += "&numOfRows=100";
		try {
			if(!search.isEmpty()) {
				if(cat.equalsIgnoreCase("HOSP")) {
					queryString += "&dongName=" 
							+ URLEncoder.encode(search,"UTF-8");
				} else {
					queryString += "&address=" 
							+ URLEncoder.encode(search,"UTF-8");
				}
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// springframework.http 패키지의 클래스
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections
				.singletonList(MediaType.APPLICATION_XML));

		HttpEntity<String> entity 
			= new HttpEntity<String>("parameters",headers);
		
		RestTemplate restTemp = new RestTemplate();
		URI restURI = null;
		
		ResponseEntity<RfcOpenAPI> result = null;
		try {
			restURI = new URI(queryString);
			result 
				= restTemp.exchange(
						restURI, 
						HttpMethod.GET,
						entity,
						RfcOpenAPI.class);
			
			List<GoPetVO> petList = result.getBody().body.data.list;
			return petList;

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}
	
}
