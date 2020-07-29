package com.biz.hello;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.xml.crypto.Data;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		Date date = new Date(System.currentTimeMillis());
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);	
		model.addAttribute("serverTime", formattedDate );
		return "home";
	}
	@RequestMapping(value="/home2")
	public String home2(Model model) {
		return "home2";		
	}
	@RequestMapping(value="/home3")
	public String home3(Model model) {
		
		model.addAttribute("your","너, 홍길동");
		return "home3";		
	}
	@RequestMapping(value="/home4")
	public String home4(Model model){
		
		model.addAttribute("your1","홍길동");
		model.addAttribute("your2","이몽룡");
		return "home4";		
	}
	@RequestMapping(value="/home5")
	public String home5(Model model) {
		model.addAttribute("friend","성춘향");
		return "home5";		
	}
		
}
