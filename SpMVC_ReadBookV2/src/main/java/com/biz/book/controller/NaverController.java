package com.biz.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		return "naver/naver-list";
	}
	
	/*
	 * @RequestParam() 
	 * Controller의 매개변수를 재 정의의 하는 용도
	 * 만약 client에서 보내는 변수 이름과 Controller에서 사용하는 변수 이름을
	 * 	다르게 하고 싶으면
	 * 	@RequestParam(name="변수") String 내이름
	 * 
	 * client에서 해당 변수값을 빼먹고 전송을 했을때 오류를 최소화 하기 위해서
	 * 		@RequestParam(name="내이름",
	 * 			required=false,
	 * 			defailtValue="홍길동") String 내이름
	 * 		클라이언트에서 '내이름' 변수에 값을 보내지 않으면 
	 * 		기본값인 '홍길동' 문자열을 '내이름' 변수에 할당 한다.
	 */
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String search(
			
			@RequestParam(name="category",
					required = false,
					defaultValue = "BOOK") String category,
			
			@RequestParam(name = "search_text") String search_text,
			
			Model model) {

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
		return "naver/naver-search-list";
	
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

	// .../query?num1=30&num2=40
	@ResponseBody
	@RequestMapping(value="/query",method=RequestMethod.GET)
	public String query(int num1, 
		
			@RequestParam(name="num2",
					required = false,
					defaultValue = "500") int num_2) {
		
		return (num1 + num_2) + "";
	}

	
}
