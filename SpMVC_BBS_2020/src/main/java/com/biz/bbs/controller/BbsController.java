package com.biz.bbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.biz.bbs.model.BBsVO;
import com.biz.bbs.service.BBsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@Controller
@RequestMapping(value="/bbs")
public class BbsController {
	
	@Autowired
	@Qualifier("bbsServiceV1")
	private BBsService bbsService;
	

	/*
	 * return문에 bbs/list 문자열이 있으면
	 * 1. tiles-layout.xml 에서 bbs/list로 설정된 항목을 검사
	 * 2. 만약 해당하는 항목이 있으면 layout을 rendering할 것이다
	 * 3. 작성된 tiles-layout.xml에는 bbs/* 로 설정된 항목이 있으므로
	 * 4. * 대신 list문자열을 치환하여 마치 bbs/list 항목이 있는 것처럼 변환이된다
	 * 5. * 대신 치환된 list 문자열은 {1}.jsp 항목에서 {1} 대신 list문자열로 치환된다
	 * 6. 결국 bbs/list라고 return된 문자열은 list.jsp 파일을 읽어서
	 * 7. rendering 하는 용도로 사용된다. 
	 */
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model) {
		
		List<BBsVO> bbsList = bbsService.selectAll();
		
		model.addAttribute("BBS_LIST",bbsList);
		return "/bbs/list";
	
	}

	@RequestMapping(value="/notice",method=RequestMethod.GET)
	public String notice(Model model) {
		return "/bbs/write";
	}
	
	@RequestMapping(value="/write",method=RequestMethod.GET)
	public String write(Model model) {
		return "/bbs/write";
	}
	
	/*
	 * form에서 보낸 파일받기
	 * MultipartFile 클래스를 매개변수로 설정하여 파일을 받기
	 * 이 클래스에 @RequestParam(이름) : 이름 = form에서 input type=file로 설정된
	 * 		tag의 name값
	 * 
	 * MultipartHttpServletRequest
	 * 멀티파일(여러개의파일)을 한꺼번에 업로드했을때
	 * 파일들을 받을 클래스(인터페이스)
	 * 
	 * 1. 단독파일들을 추출하고
	 * 2. 각각의 파일들을 모두 업로드 수행하고
	 * 3. 업로드된 파일 이름을 DB에 저장하는 처리를 해야한다.
	 * 
	 */
	@RequestMapping(value="/write",method=RequestMethod.POST)
	public String write(BBsVO bbsVO, 
			@RequestParam(name = "file",required = false) MultipartFile file,
			MultipartHttpServletRequest files) {
		
//		log.debug("업로드한 파일 이름" + file.getOriginalFilename());
		// bbsService.insert(bbsVO) : text 데이터만 insert
		// bbsService.insert(bbsVO, file) : text 데이터와 한개의 파일을 insert 
		// text 데이터와 멀티파일을 insert
		List<String> fileName = bbsService.insert(bbsVO,files);
		return "redirect:/bbs/list";
	
	}
	
	@RequestMapping(value="/detail/{seq}",method=RequestMethod.GET)
	public String detail(@PathVariable("seq") String seq,Model model) {
		
		long long_seq = Long.valueOf(seq);
		BBsVO bbsVO = bbsService.findBySeq(long_seq);
		
		model.addAttribute("BBSVO",bbsVO);
		return "/bbs/detail";
	}
	
	@RequestMapping(value="/{seq}/{url}",method=RequestMethod.GET)
	public String update(@PathVariable("seq") String seq,
			@PathVariable("url") String url,Model model) {
		
		long long_seq = Long.valueOf(seq);
		String ret_url = "redirect:/bbs/list";
		
		if(url.equalsIgnoreCase("DELETE")) {
			bbsService.delete(long_seq);			
		} else if(url.equalsIgnoreCase("UPDATE")) {
			model.addAttribute("BBSVO",bbsService.findBySeq(long_seq));
			ret_url = "/bbs/write";
		}
		return ret_url;
	}
	
	
}
