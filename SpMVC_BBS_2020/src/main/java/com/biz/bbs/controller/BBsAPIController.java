package com.biz.bbs.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@RestController
@RequestMapping(value="/api")
public class BBsAPIController {
	
	
	@RequestMapping(value="/bbs",method=RequestMethod.GET)
	public String bbs_list() {
		return "bbs_list";
	}
	@RequestMapping(value="/bbs/{seq}",method=RequestMethod.GET)
	public String bbs_item() {
		return "bbs_item";
	}
	@RequestMapping(value="/bbs",method = RequestMethod.POST)
	public String bbs_insert(@RequestBody Map<String, String> data) {

		log.debug("POST RequestMethod Type으로 요청된 메소드");
		log.debug("시퀀스값 {}", data.get("seq").toString());

		return "bbs_insert";
	}
	@RequestMapping(value="/bbs",method=RequestMethod.PUT)
	public String bbs_update(@RequestBody Map<String, String> data) {
		
		log.debug("PUT RequestMehtod Type으로 요청된 메소드");
		log.debug("시퀀스값 {}", data.get("seq"));
		
		return "bbs_update";
	}
	@CrossOrigin("*")
	@RequestMapping(value="/bbs",method=RequestMethod.DELETE)
	public String bbs_delete(@RequestBody Map<String, String> data) {
		
		log.debug("DELETE RequestMethod Type으로 요청된 메소드");
		log.debug("시퀀스값 {}", data.get("seq").toString());
		return "bbs_delete";
	}
	

}




