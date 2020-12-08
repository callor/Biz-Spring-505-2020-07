package com.biz.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.data.model.pet.GoPetVO;
import com.biz.data.service.PetServiceImplV1;

@Controller
@RequestMapping(value="/pet")
public class PetController {

	@Autowired
	private PetServiceImplV1 petService;
	
	@RequestMapping(value="/getHosp")
	public String getHospital(
			@RequestParam(name="hosp",required = false,defaultValue = "") String hosp,Model model) {

		List<GoPetVO> petList = petService.getHosp(hosp);
		model.addAttribute("H_LIST",petList);
		return "home";
	}

	
}
