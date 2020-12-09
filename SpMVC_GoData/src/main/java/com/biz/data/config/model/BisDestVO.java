package com.biz.data.config.model;

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

	public String ARRIVE;		//	레코드 구분
	public String SHORT_LINE_NAME; // 
	public String LINE_ID;		//	노선 ID
	public String LINE_NAME;	//	노선 명
	public String BUS_ID;		//	버스 ID
	public String METRO_FLAG;	//	광역 노선 구분
	public String CURR_STOP_ID;//	현재 정류소 ID
	public String BUSSTOP_NAME;//	현재 정류소 명칭 (국문)
	public String REMAIN_MIN;	//	도착 예정 시간
	public String REMAIN_STOP;	//	남은 정류소 개수
	public String DIR_START;	//	기점명
	public String DIR_END;		//	종점명
	public String LOW_BUS;		//	저상버스
	public String ENG_BUSSTOP_NAME;//	현재 정류소 위치 (영문)
	public String ARRIVE_FLAG;//	곧도착 FLAG
	public String LINE_KIND;//	노선 구분

}
