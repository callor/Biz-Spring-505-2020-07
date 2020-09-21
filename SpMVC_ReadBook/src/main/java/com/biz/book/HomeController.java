package com.biz.book;

import java.util.List;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.book.model.BookVO;
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
	public List<BookVO> naver(String book_name) {
		
		String queryURL = naverService.queryURL("BOOK",book_name);
		String resString = naverService.getNaverBook(queryURL);
		List<BookVO> bookList = naverService.getJsonObject(resString);
		return bookList;
	}
	
}
