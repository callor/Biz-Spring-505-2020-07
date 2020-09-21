package com.biz.book;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.book.service.NaverService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private NaverService naverService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	
	@ResponseBody
	@RequestMapping(value="/book",
			method=RequestMethod.POST,
			produces = "application/json;charset=utf8")
	public String naver(String book_name) {
		
		
		String queryURL = naverService.queryURL(book_name);
		String resString = naverService.getNaverBook(queryURL);
		
		return resString;
	}
	
	
}
