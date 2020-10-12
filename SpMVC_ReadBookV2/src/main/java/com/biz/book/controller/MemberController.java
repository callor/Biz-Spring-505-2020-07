package com.biz.book.controller;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.book.model.UserDetailsVO;
import com.biz.book.service.MemberService;

import lombok.RequiredArgsConstructor;

/*
 * @SesssionAttributes("memberVO")항목을 설정하면
 * 서버의 메모리에 memberVO 이름으로 객체변수를 마련해 놓는다.
 * 이 변수는 서버가 재 시작하거나 하더라도 유지되는 성질이 있고
 * 클라이언트에서 URL Request를 수행했을때 메모리에 계속 유지된다.
 * 
 * RequestMapping method에 @ModelAttribute("객체이름") 클래스 객체 형식으로
 * 매개변수가 있으면 메모리에 저장된 객체변수에서 값을 추출하여
 * 객체 포함해 준다
 */
@SessionAttributes("memberVO")
@RequiredArgsConstructor
@Controller
@RequestMapping(value="/member")
public class MemberController {

	private final MemberService memberService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(@ModelAttribute("memberVO") UserDetailsVO userVO, Model model) {
		
		
		model.addAttribute("BODY","MEMBER-LOGIN");
		return "home";
	}
	
	
	/*
	 * @SessionAttributes("memberVO") 를 사용하려면
	 * 반드시 memberVO를 생성하는 method가 클래스에 있어야 된다.
	 * UserDetailsVO 클래스로 생성된 memberVO가 "memberVO" 이름으로 보관된다.
	 * @SessionAttributes() 있는데 @ModelAttribute() 가 붙은 method가 없으면
	 * 컴파일 오류가 난다.
	 */
	@ModelAttribute("memberVO")
	public UserDetailsVO newMember() {
		UserDetailsVO memberVO = new UserDetailsVO(); //UserDetailsVO.builder().build();
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
	
	/*
	 * @SessionAttributes()를 사용할때는
	 * DB에 데이터를 insert, update를 최종수행하고 나면
	 * SessionStatus 클래스의 setComplete() method 를 호출하여
	 * 서버에 남아 있는 메모리를 clear해 주어야 한다.
	 */
	@RequestMapping(value="/join_comp",method=RequestMethod.POST)
	public String join(@ModelAttribute("memberVO") UserDetailsVO userVO,SessionStatus status) {
		memberService.insert(userVO);
		status.setComplete();
		return "redirect:/";
	}

	@RequestMapping(value="/mypage",method=RequestMethod.GET)
	public String mypage(@ModelAttribute("memberVO") UserDetailsVO userVO,
			Authentication authProvider,
			Model model) {
		
		/*
		 * 현재 로그인한 사용자의 정보를 추출하는 method
		 * spring security를 통과하여 login이 인가된 사용자의 정보는
		 * 현재 method가 호출될때 spring security의 Filter Chain에 의해서
		 * method의 매개변수로 설정된 Authentication 클래스로 선언된 객체에 담겨서
		 * 전달이 된다.
		 * 객체서 getPrincipal() method를 호출하여 데이터를 UserDetailsVO 의 userVO 객체에
		 * 담아서 일반 userVO(memberVO) 처럼 취급하여 사용할 수 있다.
		 */
		userVO = (UserDetailsVO) authProvider.getPrincipal();
		userVO.setPassword("");
		model.addAttribute("memberVO",userVO);
		model.addAttribute("BODY","MEMBER-UPDATE");
		return "home";
	}
	
	@RequestMapping(value="/mypage",method=RequestMethod.POST)
	public String mypage(@ModelAttribute("memberVO") UserDetailsVO userVO,Model model,String str) {
		model.addAttribute("memberVO",userVO);
		model.addAttribute("BODY","MEMBER-UPDATE-NEXT");
		return "home";
	}
	
	@RequestMapping(value="/update_comp",method=RequestMethod.POST)
	public String update(@ModelAttribute("memberVO") UserDetailsVO userVO,SessionStatus status) {
		memberService.update(userVO);
		status.setComplete();
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping(value="/password_check",method=RequestMethod.POST)
	public String password_check(String username, String password) {
		return memberService.userNameAndPassword(username, password);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/id_check",method=RequestMethod.POST)
	public String id_check(String username) {
		
		// TDD(Test Driven Developer)
		// memberService에 아직 구현되지 않은 method를 사용처에서 먼저 만들고
		// 문법 오류가 발생하면 구체적으로 memberService method를 구현하는 방법
		UserDetailsVO userVO = memberService.findById(username);
		
		// userVO가 null이면 username 이 DB에 없다
		if(userVO == null) {
			return "OK";
		} else {
			return "FAIL";
		}
	}
	
	
	// logout.jsp 파일을 보여주기 위한 URL Mapping
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout() {
		return "member/logout";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/user_info",method=RequestMethod.GET)
	public UserDetailsVO userInfo(Principal principal, 
				Authentication authentication, 
				@ModelAttribute("memberVO") @AuthenticationPrincipal UserDetailsVO userVO,Model model) {
		
		// Spring security 프로젝트에서 로그인한 사용자정보를 추출하는 여러가지 방법
		// 1. UserDetailsServiceImplV1에서 공급받는 방법
		//  서버의 Session memory에 직접 접근하여 사용자 정보를 추출하는 방법으로
		//  보안에 상당히 취약해서 사용을 지양하는 방법
		//UsernamePasswordAuthenticationToken upa 
		//		= (UsernamePasswordAuthenticationToken) principal;;
		//userVO = (UserDetailsVO) upa.getPrincipal();
		
		// 2. SecurityContextHolder로 부터 추출하는 방법
		// userVO = (UserDetailsVO) SecurityContextHolder
		//				.getContext()
		//				.getAuthentication()
		//				.getPrincipal();
		
		// 3. Authentication 클래스를 매개변수로 설정하는 방법
		// @@AuthenticationPrincipal 이 작동이 안되는 관계로
		// 매개변수에 Authentication 클래스를 객체로 선언하고
		// authentication.getPrincipal() method를 호출하여 userVO를 추출하는 방법
		userVO = (UserDetailsVO) authentication.getPrincipal();
		
		
		return userVO;
		
	}
	
	
	
	
}
