package com.biz.shop.exec;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptExec {

	public static void main(String[] args) {
		
		// 평문을 일방향 암호화를 하는 클래스 도구
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		
		String nation = "Korea";
		String encNation = bcrypt.encode(nation);
		
		for(int i = 0 ; i < 10 ; i ++) {
			encNation = bcrypt.encode(nation);
			System.out.printf("%s : %s\n",nation,encNation);
		}
	}
}





