package com.biz.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/member")
public class MemberController {

	// logout.jsp 파일을 보여주기 위한 URL Mapping
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout() {
		return "member/logout";
	}
	
	
}
