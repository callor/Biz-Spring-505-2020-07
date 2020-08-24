package com.biz.shop.controller;

import java.util.Scanner;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
 * 
 * RestController
 * 표준 Controller는 return 문자열을 가지고 view 파일을 읽고
 * rendering을 수행한 후 web client에 HTML 코드로 response를 수행한다.
 * 그에 반해 RestController는 
 * view 가 없이 데이터를 직접 web client에 전송할때 사용하는 Controller
 * 
 * 표준 Controller에서는 특정 method를 지정하여 
 * view가 아닌 데이터를 직접 web client로 전송할때 
 * method에 @ResponseBody를 설정해준다.
 * 
 * RestController는 모든 
 * method에 @ResponseBody가 설정된 것과 똑같은 효과를 낸다
 * 
 * RequestMapping을 api로 시작한 이유
 * API(Application Programmerble Interface)는
 * 서로다른 서버와 서버간, 서로다른 서비스와 서비스간에
 * 데이터를 주고받은 방식을 일컷는 용어
 */
@RestController
@RequestMapping(value="/api/product")
public class ProductRestController {
	
	private Scanner scan;
	public ProductRestController() {
		scan = new Scanner(System.in); 
	}
	
	@RequestMapping(value="/keyinput",method=RequestMethod.GET)
	public String keyinput( ) {
		System.out.print("키를 입력하세요~~~~");
		String strIn = scan.nextLine();
		return strIn;
	}
}




