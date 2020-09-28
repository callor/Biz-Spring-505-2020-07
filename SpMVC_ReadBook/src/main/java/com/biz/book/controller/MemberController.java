package com.biz.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.book.model.UserDetailsVO;
import com.biz.book.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping(value="/member")
public class MemberController {

	private final MemberService memberService;
	
	/*
	 * (VO)클래스를 Controller의 매개변수로 설정하고
	 * @ModelAttribute("이름")을 설정했을 경우
	 * 1. form에서 POST로 데이터를 보냈을 경우 form에서 보낸 데이터가 담긴
	 * 		vo 객체를 생성하여 method내의 코드에서 사용할수 있도록 준비해준다
	 * 2. 아무도(아무곳에서도) 매개변수 클래스와 일치하는 변수를 전달하지 않을경우
	 * 		자체적으로 클래스의 생성자를 호출하여 비어있는 객체를 만들어서
	 * 		method내에 코드에서 사용할수 있도록 준비해 준다. 
	 */
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public String join(@ModelAttribute("memberVO") UserDetailsVO userVO,Model model) {
		
		// UserDetailsVO userVO = new UserDetailsVO();
		// 이 코드를 @ModelAttribute("memberVO")가 대신한다.
		
		model.addAttribute("memberVO",userVO);
		model.addAttribute("BODY","MEMBER-JOIN");
		return "home";
	}
	
	@RequestMapping(value="/join",method=RequestMethod.POST)
	public String join(@ModelAttribute("memberVO") UserDetailsVO userVO) {
		
		memberService.insert(userVO);
		return "redirect:/";
	
	}
	
	// logout.jsp 파일을 보여주기 위한 URL Mapping
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout() {
		return "member/logout";
	}
	
	
}
