package com.biz.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.data.config.model.pet.GoPetVO;
import com.biz.data.service.PetService;

@Controller
@RequestMapping(value="/pet")
public class PetController {

	@Autowired
	private PetService petService;
	
	@RequestMapping(value="/getHosp")
	public String getHospital(
			@RequestParam(name="cat",
				required = false, 
				defaultValue = "hosp") String cat,
			
			@RequestParam(name="search",
				required = false,
				defaultValue = "") String search,Model model) {

		List<GoPetVO> petList = petService.getHosp(cat,search);
		model.addAttribute("H_LIST",petList);
		model.addAttribute("BODY","PET");
		return "home";
	}
}
