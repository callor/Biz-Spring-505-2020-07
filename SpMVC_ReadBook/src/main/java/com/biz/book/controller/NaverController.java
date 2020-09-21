package com.biz.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.book.model.BookVO;
import com.biz.book.service.NaverService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/naver")
public class NaverController {

	@Autowired
	private NaverService naverService;
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String search(String search_text) {
		log.debug("search");
		return "naver";
	}
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String search(String category,String search_text,Model model) {

		log.debug(search_text);
		log.debug(category);
		
		String queryURL 
			= naverService.queryURL(category,search_text.trim());
		
		String jsonString = naverService.getNaverBook(queryURL);
		List<BookVO> bookList = naverService.getJsonObject(jsonString);
		log.debug(bookList.toString());
		model.addAttribute("NAVERS",bookList);
		return "naver";
	
	}
}
