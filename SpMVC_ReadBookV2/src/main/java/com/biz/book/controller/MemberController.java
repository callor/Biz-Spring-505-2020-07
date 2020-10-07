package com.biz.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.book.model.UserDetailsVO;
import com.biz.book.service.MemberService;

import lombok.RequiredArgsConstructor;

@SessionAttributes("memberVO")
@RequiredArgsConstructor
@Controller
@RequestMapping(value="/member")
public class MemberController {

	private final MemberService memberService;
	
	@ModelAttribute("memberVO")
	public UserDetailsVO newMember() {
		UserDetailsVO memberVO = UserDetailsVO.builder().build();
		return memberVO;
	}
	
	
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
	
	/*
	 * 회원가입 입력폼을 2개로 분리하여 사용하기 위해
	 * join get : member-write.jsp 가 열리고
	 * join post : member-write2.jsp 가 열린다.
	 * member-write.jsp에서 입력한 id, 비밀번호를 join POST로 보내면
	 * @ModelAttribute("memberVO") 설정을 확인하고
	 * server임시 보관중인 SessionAttributes("memberVO") 를 찾아서
	 * 입력박스로부터 전달된 데이터를 보관한다.
	 * member-write2.jsp 열고 나머지 데이터를 입력 한후
	 * join_comp POST로 보내면
	 * 먼저 입력받아서 SessionAttributes에 보관중인 id, 비번과
	 * 나중에 입력한 이름, 전화번호 등등 과 함께 묶어서
	 * join_comp userVO에 담아준다.
	 * 입력폼의 항목이 매우 많을때
	 * 입력폼을 분리해서 코딩을 해도 SessionAttributes의 성질을 이용하여
	 * 마이 입력마법사와 같은 기능을 구현할 수 있다.
	 */
	@RequestMapping(value="/join",method=RequestMethod.POST)
	public String join(@ModelAttribute("memberVO") UserDetailsVO userVO,Model model,String str) {
		
		model.addAttribute("memberVO",userVO);
		model.addAttribute("BODY","MEMBER-JOIN-NEXT");
		return "home";
	}
	
	@RequestMapping(value="/join_comp",method=RequestMethod.POST)
	public String join(@ModelAttribute("memberVO") UserDetailsVO userVO,SessionStatus status) {
		memberService.insert(userVO);
		status.setComplete();
		return "redirect:/";
	}

	
	// logout.jsp 파일을 보여주기 위한 URL Mapping
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout() {
		return "member/logout";
	}
	
	
}
