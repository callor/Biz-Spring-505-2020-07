package com.biz.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.book.model.BookVO;
import com.biz.book.service.NaverService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/naver")
public class NaverController {

	@Autowired
	@Qualifier("naverServiceV1")
	private NaverService<BookVO> nServiceV1;

	@Autowired
	@Qualifier("naverServiceV2_XML")
	private NaverService<BookVO> nServiceV2;
	
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
			= nServiceV2.queryURL(category,search_text.trim());
		
		// String jsonString = naverService.getNaverSearch(queryURL);
		// V1에서 사용되는 method
		// List<BookVO> bookList = naverService.getNaverList(jsonString);
		
		List<BookVO> bookList = nServiceV2.getNaverList(queryURL);
		
		log.debug(bookList.toString());
		model.addAttribute("NAVERS",bookList);
		return "naver";
	
	}
	
	@ResponseBody
	@RequestMapping(value="/api", method=RequestMethod.POST, 
			produces = "application/xml;charset=utf8")
	public String naver(String book_name) {
		
		String queryURL = nServiceV1.queryURL("BOOK",book_name);
		String retString = nServiceV1.getNaverSearch(queryURL);
		return retString;
				
//		List<BookVO> bookList = nServiceV1.getNaverList(jsonString);
//		return bookList;
	}

	
}
