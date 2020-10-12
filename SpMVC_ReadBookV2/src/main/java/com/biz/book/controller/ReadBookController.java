package com.biz.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.book.mapper.ReadBookDao;
import com.biz.book.model.ReadBookVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping(value="/read")
public class ReadBookController {
	
	/*
	 * Service, Dao 와 등 Dependency Inject가 일어나는 클래스들
	 * @Autowried를 주로 많이 하는데
	 * @Autowried로 묶을때 최근에 권장사항이 모든 변수의 Scope를 private으로 선언하라고 한다
	 * 
	 * @Autowried가 내부에서 메모리 누수 현상이 발견되어서
	 * 가급적이면 private final로 선언을 하고
	 * 생성자를 만들어서 각 객체 변수를 초기화하도록 한다
	 * final로 선언된 모든 변수는 반드시 생성자에서 외부로 부터 주입받거나
	 * 자체적으로 변수를 초기화 하는 코드를 만들어야 한다.
	 * 코딩을 하다보면 변수는 final로 선언했는데
	 * 생성자를 만들지 않아서 문제가 발생한다.
	 * 변수만 private final로 선언하고
	 * lombok에 있는 @RequiredArgsConstructor를 사용하여 생성자 코드를
	 * 자동으로 만들도록 해준다
	 */
	private final ReadBookDao rbookDao;
	//public ReadBookController(ReadBookDao rbookDao) {
	//	this.rbookDao = rbookDao;
	//}
	
	@RequestMapping(value="/write",method=RequestMethod.POST)
	public String write( @ModelAttribute ReadBookVO rbookVO,Model model ) {
		
		log.debug("독서록정보 : " + rbookVO.toString());
		
		int ret = rbookDao.insert(rbookVO);
		
		// model.addAttribute("BODY","BOOK-DETAIL");
		return "redirect:/books/detail/" + rbookVO.getR_book_seq();
	
	}
	

}
