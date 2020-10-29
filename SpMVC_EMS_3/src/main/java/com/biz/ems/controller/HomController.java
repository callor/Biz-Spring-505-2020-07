package com.biz.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/home")
public class HomController {

	@RequestMapping(value="/",method=RequestMethod.GET)
	public String home() {
		return "home";
	}
	
}
