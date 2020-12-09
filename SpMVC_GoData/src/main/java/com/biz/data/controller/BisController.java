package com.biz.data.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.data.config.model.BisStationData;
import com.biz.data.service.BisService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/bis")
public class BisController {


	@Autowired
	private BisService bService;
	
	@RequestMapping(value="/station",method=RequestMethod.GET,
	produces = "application/json;charset=UTF-8")
	public String station(
			@RequestParam(name="station",
					required = false,
					defaultValue = "") String station, Model model) {
		
		List<BisStationData> stList = bService.getStation();
		
		if( !station.isEmpty() ) {
			stList = stList.stream()
					.filter(bis->bis.BUSSTOP_NAME.contains(station))
					.collect(Collectors.toList());
		}
		model.addAttribute("ST_LIST",stList);
		model.addAttribute("BODY","STATION");
		return "home";
	}

	@ResponseBody
	@RequestMapping(value="/string",method=RequestMethod.GET,
	produces = "application/json;charset=UTF-8")
	public String string() {
		return bService.getString();
	}

	// BUSSTOP_ID값으로 도착정보 찾아오기
	@ResponseBody
	@RequestMapping(value="/busstop",method=RequestMethod.POST)
	public String busstop(
			@RequestBody Map<String,String> data) {
		
		log.debug("BUSSTOP " + data.get("station"));
		
		bService.busstop(data.get("station"));
		
		return "OK";
	
	}
	@ResponseBody
	@RequestMapping(value="/stopString",method=RequestMethod.POST)
	public String busstopString(
			@RequestBody Map<String,String> data) {
		
		log.debug("BUSSTOP " + data.get("station"));
		return bService.busstopString(data.get("station"));
		
	}


}
