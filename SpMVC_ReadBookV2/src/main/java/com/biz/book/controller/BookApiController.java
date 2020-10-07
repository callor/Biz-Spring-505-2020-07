package com.biz.book.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.biz.book.mapper.BookDao;
import com.biz.book.model.BookVO;
import com.biz.book.service.NaverService;

import lombok.extern.slf4j.Slf4j;

/*
 * view를 return response하지 않고 
 * 객체,문자열 등을 직접 JSON형태로
 * response하는 Controller
 * 
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class BookApiController {

	@Autowired
	@Qualifier(value = "naverServiceV2")
	private NaverService<BookVO> nService;
	
	@Autowired
	private BookDao bookDao;
	
	/*
	 * produces
	 * 클라이언트에게 데이터를 보내는 형식을 지정하는 속성
	 * 기본값이 application/json형태인데 만약
	 * 클라이언트에서 json데이터를 제대로 수신하지 못하면
	 * 강제로 값을 지정해 주자
	 */
	@RequestMapping(value="/isbn",
		method=RequestMethod.POST,
		produces = "application/json;charset=utf8")
	public BookVO naverSearch(String search_text) {
		String queryURL = nService.queryURL("BOOK", search_text);
		
		/*
		 * getNaverList(query) Method는 도서명, ISBN으로 조회를 하여
		 * 도서 리스트를 return 해주는 method 이다
		 * 도서명으로 검색을 하면 당연히 1개 이상의 데이터가 return되어
		 * List형이 될것이다.
		 * 만약 ISBN으로 검색을 하면 ISBN은 중복값이 없어
		 * 당연히 1개만 return이 될것이다.
		 * 하지만 이 method는 태생이 List를 return하도록 만들어져 있기 때문에
		 * 1개의 데이터라도 List<BookVO>에 담겨서 return된다
		 * ISBN으로 조회를 했을때는 다른 데이터는 있더라도 무시하고
		 * 첫번째(0번)데이터만 필요하므로 
		 * ...get(0) 으로 첫번째 데이터만 추출한ㄷ
		 */
		BookVO bookVO = nService.getNaverList(queryURL).get(0);
		log.debug("도서정보 :"+ bookVO.toString());
		return bookVO;
	}
		

	@RequestMapping(value="/detail/{book_seq}",
		method=RequestMethod.GET,
		produces = "application/json;charset=utf8")
	public BookVO detail(
			@PathVariable("book_seq") String id, Model model) {
		
		log.debug("PATH: {}",id );
		long seq = Long.valueOf(id);
		BookVO bookVO = bookDao.findById(seq);
		// log.debug(bookVO.toString());
		return bookVO;
		
	}
	
	
}




