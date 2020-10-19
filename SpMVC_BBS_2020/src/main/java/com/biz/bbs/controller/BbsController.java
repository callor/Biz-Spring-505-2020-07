package com.biz.bbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.bbs.model.BBsVO;
import com.biz.bbs.service.BBsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping(value="/bbs")
public class BbsController {
	
	@Qualifier("bbsServiceV1")
	private final BBsService bbsService;

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
		return "bbs/list";
	
	}
	@RequestMapping(value="/write",method=RequestMethod.GET)
	public String write() {
		return "bbs/write";
	}
	
	@RequestMapping(value="/write",method=RequestMethod.POST)
	public String write(BBsVO bbsVO) {
		
		bbsService.insert(bbsVO);
		return "redirect:/bbs/list";
	
	}
	
	
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String detail() {
		return "bbs/detail";
	}
	
	
	
}
