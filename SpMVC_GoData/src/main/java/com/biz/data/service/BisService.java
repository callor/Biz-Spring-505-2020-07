package com.biz.data.service;

import java.util.List;

import com.biz.data.config.model.BisDestVO;
import com.biz.data.config.model.BisStationData;

public interface BisService {

	// 데이터를 VO에 담아서 추출
	public List<BisStationData> getStation();
	
	// 문자열 형태로 수신하여 테스트 용도
	public String getString();

	public List<BisDestVO> busstop(String string);

	public String busstopString(String string);

}
