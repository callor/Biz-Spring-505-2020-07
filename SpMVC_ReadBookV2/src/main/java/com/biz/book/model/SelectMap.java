package com.biz.book.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SelectMap {

	/*
	 * final 로 설정된 필드변수는 생성자에서 반드시 생성(초기화)를 해야 한다.
	 */
	public static final Map<String,String> CAT_MAP;
	public static final Map<String,String> FLAG_MAP;

	/*
	 * static final 로 선언된 CAT_MAP을 초기화 시키는 코드
	 * 각각 항목에 item을 추가하기
	 */
	static {
		Map<String, String> tempMap = new HashMap<String, String>();
		tempMap.put("CAT_THINK", "생각하기");
		tempMap.put("CAT_WORK", "일");
		tempMap.put("CAT_PROMISE", "약속");
		tempMap.put("CAT_ETC", "기타");
		CAT_MAP = Collections.unmodifiableMap(tempMap);
		
		
		tempMap = new HashMap<String,String>();
		tempMap.put("FLAG_TOP", "중요");
		tempMap.put("FLAG_MID", "보통");
		tempMap.put("FLAG_LOW", "일반");
		FLAG_MAP = Collections.unmodifiableMap(tempMap); 
		
	}
	
	
}
