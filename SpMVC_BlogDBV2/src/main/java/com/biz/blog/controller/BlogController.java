package com.biz.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.blog.model.BlogVO;
import com.biz.blog.service.BlogService;

import lombok.extern.slf4j.Slf4j;

// lombok을 사용하여 slf4j와 logback을 연동하고 log를 사용할 수 있도록 설정하라
@Slf4j
@RequestMapping(value="/blog")
@Controller
public class BlogController {
		
	// @Service Annotation이 부착된 클래스를 주입해 달라
	@Autowired
	
	// 1개의 인터페이스를 상속받은 클래스가 2개 이상일때
	// 어떤 클래스를 가져와서 Autowired를 할지 명시해준다.
	@Qualifier("bServiceV2")
	private BlogService bService;
	
	// http://localhost:8080/blog/blog/list 주소로 req했을때
	// 응답할 함수
	
	// method=RequestMethod.GET : req 를 할때 <a href=주소> 를 클릭했을때
	// 응답하는 method
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model) {
		
		List<BlogVO> blogList = bService.selectAll();
		// System.out.println(blogList.get(0).getBl_title());
		model.addAttribute("BLOGS",blogList);
		return "list";
	
	}
	
	@RequestMapping(value="/getblog",method=RequestMethod.GET)
	public String getBlog(Model model) {
		System.out.println("여기는 블로그 리스트 보기!!!");
		
		List<BlogVO> blogList = bService.selectAll();
		if(blogList != null) {
			int size = blogList.size();
			model.addAttribute("TITLE",blogList.get(size - 1).getBl_title());
			model.addAttribute("CONTENT",blogList.get(size - 1).getBl_contents());
			model.addAttribute("USER",blogList.get(size - 1).getBl_user());
		} else {
			model.addAttribute("TITLE","데이터가 없음");
		}
		return "view";
	}
	@RequestMapping(value="/input",method=RequestMethod.GET)
	public String input(Model model) {
		
		// write.jsp에서 input POST로 데이터를 전달할때
		// 비어있는("")데이터 때문에 발생하는 400 오류를 방지하기 위해
		// 공백의 새로운 BlogVO만들어서 write.jsp로 보내준다.
		model.addAttribute("BLOG", new BlogVO());
		return "write";
	}
	
	/*
	 * @ModelAttribute
	 * 	form에서 input에 입력한 문자열이
	 * 	전송되어 오면 input tag의 변수(name)을 분석하여
	 * 	VO class의 필드변수와 일치하면 전달된 
	 * 	데이터(값)을 VO객체에 담아달라
	 */
	@RequestMapping(value="/input",method=RequestMethod.POST)
	public String write(@ModelAttribute BlogVO blogVO,Model model) {
		
		/*
		 * 
		 * Debuging Code : 어떤 값을 확인하는 용도
		 * form에서 건너온 데이터가 정확히 VO에 담겼는가를 확인하기 위해
		 * 사용한 코드
		 * 이 코드는 프로젝트 수행과는 아무런 관련이 없는 코드
		 * 
		 */
		log.debug("USER : " + blogVO.getBl_user());
		log.debug("TITLE : " + blogVO.getBl_title());
		log.debug("CONTENT : " + blogVO.getBl_contents());
		log.debug("로그인한 사용자는?" + "홍길동");
		log.debug("로그인한 비밀번호 는?" + "12345");

		//	
		bService.insert(blogVO);
//		
//		model.addAttribute("TITLE",blogVO.getTitle());
//		model.addAttribute("CONTENT",blogVO.getContent());
//		model.addAttribute("USER",blogVO.getUser());

		return "redirect:/blog/list";
	
	}
	
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String view(String seq, Model model) {
		
		log.debug("SEQ : {}", seq);
		
		/*
		 * SQL Injection공격을 사전에 차단하기 위해
		 * Controller에서  SEQ값을 문자열형에서 Long형으로 변환하는 코드를 추가
		 */
		long long_seq = 0;
		try {
			long_seq = Long.valueOf(seq);	
		} catch (Exception e) {
			model.addAttribute("ERROR",seq + " 형식의 QUERY금지!!!");
			return "view_error";
		}
		
		BlogVO blogVO = bService.findBySeq(long_seq);
		model.addAttribute("BLOG",blogVO);
		return "view";
		
	}
	
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(String seq) {
		
		long long_seq = 0;
		try {
			long_seq = Long.valueOf(seq);
		} catch (Exception e) {
			return "view_error";
		}
		bService.delete(long_seq);
		return "redirect:/blog/list";
	
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String update(String seq, Model model) {
		
		long long_seq = 0;
		try {
			long_seq = Long.valueOf(seq);
		} catch (Exception e) {
			return "view_error";
		}
		
		// update할 데이터를 SELECT 해 오기
		BlogVO blogVO = bService.findBySeq(long_seq);
		
		// update할 데이터를 model에 싣기
		model.addAttribute("BLOG",blogVO);
		
		// 입력폼 화면 열기
		return "write";
		
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(BlogVO blogVO,Model model) {
		
		log.debug("UPDATE POST Method");
		log.debug(blogVO.toString());
		
		bService.update(blogVO);

		// return "redirect:/blog/view?seq=" 
		// 		+ blogVO.getBl_seq();
		
		// 수정이 완료되면 다시 detail view로 화면을 전환하기
		model.addAttribute("seq",blogVO.getBl_seq());
		return "redirect:/blog/view";
		
	}
		
}
