package com.biz.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.data.model.BisStationData;
import com.biz.data.service.BisServiceImplV1;

@Controller
@RequestMapping(value="/bis")
public class BisController {


	@Autowired
	private BisServiceImplV1 bService;
	
	@ResponseBody
	@RequestMapping(value="/station",method=RequestMethod.GET,
	produces = "application/json;charset=UTF-8")
	public List<BisStationData> station() {
		 return bService.getStation();
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
