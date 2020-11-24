package com.biz.book.constroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.book.domain.BookVO;
import com.biz.book.service.BookService;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;

import sun.security.action.GetBooleanAction;

@Controller
@RequestMapping(value="/book")
public class BookController {

	@Autowired
	private BookService bService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(
			@RequestParam(name="size",
					required = false, 
					defaultValue = "10") int size, 
			@RequestParam(name="page",
					required = false,
					defaultValue = "0" ) int page,
			Model model) {
		
		page = page == 0 ? 0 : page - 1;
		Pageable pageable = PageRequest.of(page, size);
		Page<BookVO> pageList = bService.pageSelect(pageable);
		
		model.addAttribute("BOOKS",pageList);
		return "book/list";
	
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String insert(@ModelAttribute BookVO bookVO, Model model) {
		
		model.addAttribute("BOOK",bookVO);
		return "book/input";
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(@ModelAttribute BookVO bookVO) {
		bService.insert(bookVO);
		return "redirect:/book/list";
	}

	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String view(String id, Model model) {
		
		BookVO bookVO = bService.findById(Long.valueOf(id));
		model.addAttribute("BOOK",bookVO);
		return "book/view";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String update(String id, Model model) {
		
		BookVO bookVO = bService.findById(Long.valueOf(id));
		model.addAttribute("BOOK",bookVO);
		return "book/input";
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(BookVO bookVO, String id, Model model) {
		
		bService.update(bookVO);
		model.addAttribute("id",bookVO.getId());
		return "redirect:/book/view";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(String id) {
		
		bService.delete(Long.valueOf(id));
		return "redirect:/book/list";
	}
	
}
