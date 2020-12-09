package com.callor.data.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.callor.data.model.BisStationData;
import com.callor.data.service.BisService;

@Controller
@RequestMapping(value="/bis")
public class BisController {


	@Autowired
	private BisService bService;
	
	
	@RequestMapping(value="/station",method=RequestMethod.GET,
	produces = "application/json;charset=UTF-8")
	public String station(String station,Model model) {
		
		List<BisStationData> bisList = bService.getStation();
		
				
		 model.addAttribute("ST_LIST",bisList
				 .stream()
				 .filter(bis->bis.BUSSTOP_NAME.contains(station))
				 .collect(Collectors.toList()));
		 model.addAttribute("BODY","BIS");
		 return "home";
	}

	@ResponseBody
	@RequestMapping(value="/string",method=RequestMethod.GET,
	produces = "application/json;charset=UTF-8")
	public String string() {
		
		// bService.getString();
		return bService.getString();
//		return null;
	}

	
	
	
	
}
