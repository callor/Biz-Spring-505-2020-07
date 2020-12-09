package com.biz.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BisDestVO {

	// ARRIVE_LIST	
	private String ARRIVE;		//	레코드 구분
	private String LINE_ID;		//	노선 ID
	private String LINE_NAME;	//	노선 명
	private String BUS_ID;		//	버스 ID
	private String METRO_FLAG;	//	광역 노선 구분
	private String CURR_STOP_ID;//	현재 정류소 ID
	private String BUSSTOP_NAME;//	현재 정류소 명칭 (국문)
	private String REMAIN_MIN;	//	도착 예정 시간
	private String REMAIN_STOP;	//	남은 정류소 개수
	private String DIR_START;	//	기점명
	private String DIR_END;		//	종점명
	private String LOW_BUS;		//	저상버스
	private String ENG_BUSSTOP_NAME;//	현재 정류소 위치 (영문)
	private String ARRIVE_FLAG;//	곧도착 FLAG
	private String LINE_KIND;//	노선 구분

}
