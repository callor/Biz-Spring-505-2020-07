package com.callor.data.controller;

import java.util.List;
<<<<<<< HEAD:SpMVC_GoData/src/main/java/com/callor/data/controller/BisController.java
=======
import java.util.Map;
>>>>>>> 097dbe2c03d4f463c1cda3baf7880b1f008e2a33:SpMVC_GoData/src/main/java/com/biz/data/controller/BisController.java
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
<<<<<<< HEAD:SpMVC_GoData/src/main/java/com/callor/data/controller/BisController.java
=======
import org.springframework.web.bind.annotation.RequestBody;
>>>>>>> 097dbe2c03d4f463c1cda3baf7880b1f008e2a33:SpMVC_GoData/src/main/java/com/biz/data/controller/BisController.java
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

<<<<<<< HEAD:SpMVC_GoData/src/main/java/com/callor/data/controller/BisController.java
import com.callor.data.model.BisStationData;
import com.callor.data.service.BisService;
=======
import com.biz.data.model.BisStationData;
import com.biz.data.service.BisService;
>>>>>>> 097dbe2c03d4f463c1cda3baf7880b1f008e2a33:SpMVC_GoData/src/main/java/com/biz/data/controller/BisController.java

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/bis")
public class BisController {


	@Autowired
	private BisService bService;
<<<<<<< HEAD:SpMVC_GoData/src/main/java/com/callor/data/controller/BisController.java
	
	
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
=======
	
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
>>>>>>> 097dbe2c03d4f463c1cda3baf7880b1f008e2a33:SpMVC_GoData/src/main/java/com/biz/data/controller/BisController.java
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
