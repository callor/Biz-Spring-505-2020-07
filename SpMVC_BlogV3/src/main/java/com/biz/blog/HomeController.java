package com.biz.blog;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.blog.service.BlogService;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	
	// a href="constext/input"으로 request를 했을때
	// a href="http://localhost/blog/input"으로 request를 했을때
	// 처리할 method를 지정
	@RequestMapping(value="/input",method=RequestMethod.GET)
	public String input() {

		// /WEB-INF/views/write.jsp 파일을 response 하여라
		return "write";
	}

	/*
	 * form에서 input box에 입력한 내용은
	 * http protocol에 의해 네트워크를 건너서 Server로 전송이 되고
	 * 그 데이터는 Controller의 함수에서 받을수 있다.
	 * 
	 * 함수에는 input box의 name으로 설정된 것과 같은 이름의 문자열 변수를
	 * 매개변수로 설정만 해두면
	 * 나머지는 SpringFramework가 자동으로 값을 수신해서 변수에 담아준다.
	 */
	@RequestMapping(value="/writer",method=RequestMethod.POST)
	public String writer(String title, String content, Model model) {
		
		System.out.println("나는 POST Type writer method !!");
		System.out.println("제목 : " + title);
		System.out.println("내용 : " + content);
		
		model.addAttribute("TITLE",title);
		model.addAttribute("CONTENT" , content);
		
		// Controller의 함수에서 return null을 수행하면
		// RequestMapping에서 지정한 value 값을 문자열로 자동인식한다.
		// return null;
		return "view";
	
	}

	
	
	@RequestMapping(value="/writer",method=RequestMethod.GET)
	public String writer() {
		System.out.println("나는 GET Type writer method !!");
		return null;
	}
	
	
}





