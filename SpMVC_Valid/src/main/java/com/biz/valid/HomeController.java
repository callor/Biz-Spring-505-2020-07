package com.biz.valid;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.valid.model.UserVO;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@ModelAttribute("userVO") UserVO userVO, Model model) {
		
		model.addAttribute("userVO", userVO);
		return "home";
	}
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public String input(
			@ModelAttribute("userVO") @Valid UserVO userVO,
			BindingResult result) {
		
		if(result.hasErrors()) {
			return "home";
		}
		return "redirect:/";
	
	}
	
}
