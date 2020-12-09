package com.biz.data.config.model.pet;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@XmlRootElement(name="list")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GoPetVO {

	private String apiSid;		//	데이터번호
	private String apiDongName;	//	동물병원의 이름
	private String apiNewAddress;//	완산구 백제대로 218 (중화산동2가)	동물병원의 도로명주소
	private String apiOldAddress;//	255		전라북도 전주시 완산구 중화산동2가 142-11	동물병원의 도로명주소
	private String apiTel;//	255	필수	063-285-7975	동물병원의 연락처
	private String apiLat;//	255	필수	35.812316	동물병원의 위도좌표(WGS84)
	private String apiLng;//	255	필수	127.123623	동물병원의 경도좌표(WGS84)
	private String apiRegDate;//	255	필수	2016-11-30	데이터작성 기준일자
	
}
