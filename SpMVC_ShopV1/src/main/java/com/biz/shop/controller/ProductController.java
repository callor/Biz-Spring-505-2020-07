package com.biz.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.shop.model.ProductVO;
import com.biz.shop.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value="/product")
@Controller
public class ProductController {

	@Autowired
	@Qualifier("proServiceV1")
	private ProductService proService;

	// 상품리스트보이기
	// http://localhost:8080/shop/product/
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String  proHome(Model model) {
	
		// 전체상품리스트를 SELECT
		List<ProductVO> proList = proService.selectAll();
		
		// PRO_LIST 에 담아서 home으로 보내기
		model.addAttribute("PRO_LIST",proList);
		model.addAttribute("BODY","PRO_HOME");
		return "home";
	
	}
	
	// insert GET method : 상품정보 추가 anchor를 클릭했을때
	// write 화면을 보여주는 method
	// <a href="http://localhost:8080/shop/product/list">상품등록</a> request 반응
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String insert(Model model) {
		// return "product/product_write";
		model.addAttribute("BODY","PRO_WRITE");
		return "home";
	}
	
	// form에서 값을 입력한 후 저장버튼을 눌렀을때 호출되는 method
	// <form method="POST">로 되어 있을때 반응하는 method
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(@ModelAttribute ProductVO proVO) {

		log.debug("상품정보 입력 : {}" , proVO.toString());
		
		int ret = proService.insert(proVO);
		
		return "redirect:/";
	
	}
	
}




