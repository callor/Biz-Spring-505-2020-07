package com.biz.book.exec;

import java.util.Map;

public class EnvList {

	public static void main(String[] args) {
		
		Map<String, String> envList ;
		// 컴퓨터시스템의 환경변수 들을 map 객체로 추출하는 method
		envList = System.getenv();
		System.out.println("BIZ.COM : " + envList.get("BIZ.COM"));
		System.out.println("BIZ.NET : " + envList.get("BIZ.NET"));
		
	}
	
}
