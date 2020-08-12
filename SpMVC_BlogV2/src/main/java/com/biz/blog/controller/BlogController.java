package com.biz.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.blog.model.BlogVO;
import com.biz.blog.service.BlogService;

@RequestMapping(value="/blog")
@Controller
public class BlogController {
		
	// @Service Annotation이 부착된 클래스를 주입해 달라
	@Autowired
	private BlogService bService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list() {
		System.out.println("여기는 블로그 리스트 보기!!!");
		return "home";
	}
	@RequestMapping(value="/input",method=RequestMethod.GET)
	public String input() {
		return "write";
	}
	
	/*
	 * @ModelAttribute
	 * 	form에서 input에 입력한 문자열이
	 * 	전송되어 오면 input tag의 변수(name)을 분석하여
	 * 	VO class의 필드변수와 일치하면 전달된 
	 * 	데이터(값)을 VO객체에 담아달라
	 */
	@RequestMapping(value="/writer",method=RequestMethod.POST)
	public String write(@ModelAttribute BlogVO blogVO,Model model) {
		
		System.out.println("USER : " + blogVO.getUser());
		System.out.println("TITLE : " + blogVO.getTitle());
		System.out.println("CONTENT : " + blogVO.getContent());
	
		bService.insert(blogVO);
		
		model.addAttribute("TITLE",blogVO.getTitle());
		model.addAttribute("CONTENT",blogVO.getContent());
		model.addAttribute("USER",blogVO.getUser());
		return "view";
	
	}
	
}
