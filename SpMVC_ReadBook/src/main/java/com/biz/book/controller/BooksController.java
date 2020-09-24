package com.biz.book.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.book.mapper.BookDao;
import com.biz.book.model.BookVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/books")
public class BooksController {

	@Autowired
	private BookDao bookDao;
	
	// locatlhost:8080/book/books
	// locatlhost:8080/book/books/
	// @ResponseBody
	@RequestMapping(value={"/",""},method=RequestMethod.GET)
	public String list(Model model) {
		
		List<BookVO> bookList = bookDao.selectAll();
		model.addAttribute("BOOKS",bookList);
		model.addAttribute("BODY","BOOK-LIST");
		return "home";
	}
	
	@RequestMapping(value="/input",method=RequestMethod.GET)
	public String input(Model model) {
		
		LocalDate localDate = LocalDate.now();
		String todayString = DateTimeFormatter
						.ofPattern("yyyy-MM-dd")
						.format(localDate);
		
		BookVO bookVO = BookVO.builder()
							.buydate(todayString)
							.build();
		
		model.addAttribute("BODY","BOOK-WRITE");
		model.addAttribute("bookVO",bookVO);
		return "home";
		
		// Controller의 Mapping method의 return type이 String일때
		// null 값을 return 하면
		// method를 호출할때 사용했던 mapping URL.jsp 형식의 return문이
		// 자동으로 생성된다.
		// return null
	}
	
	/*
	 * 
	 * spring form taglib를 사용하여 write form을 만들었을 경우에는
	 * VO 클래스, 객체를 매개변수로 사용할때
	 * @ModelAttribute("VO") 를 필수로 사용하자
	 * 
	 */
	@RequestMapping(value="/input",method=RequestMethod.POST)
	public String input(@ModelAttribute("bookVO") BookVO bookVO) {
		
		log.debug(bookVO.toString());
		
		int ret = bookDao.insert(bookVO);
		if(ret < 1) {
			// insert가 실패했으므로 그에 대한 메시지를 보여주는 페이지로 Jump
		}
		
		return "redirect:/books";
	}
	
	// localhost:8080/book/books/detail/3 이라고 Request가 오면
	// 맨 끝의 숫자 3을 Mapping 주소의 {book_seq}위치에 Mapping 한다
	// 매개변수에 설정된 PathVariable에 따라 String id 변수에
	// 3의 값이 할당되어 mathod에 전달된다.
	@RequestMapping(value="/detail/{book_seq}",
		method=RequestMethod.GET,
		produces = "application/json;charset=utf8")
	public String detail(
			@PathVariable("book_seq") String id, Model model) {
		
		log.debug("PATH: {}",id );
		long seq = Long.valueOf(id);
		BookVO bookVO = bookDao.findById(seq);

		model.addAttribute("BOOKVO",bookVO);
		model.addAttribute("BODY","BOOK-DETAIL");
		return "home";
		
	}
	
}








