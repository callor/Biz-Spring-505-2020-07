package com.biz.ems.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.biz.ems.model.EmsVO;
import com.biz.ems.service.EmsService;
import com.biz.ems.service.FileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	@Autowired
	@Qualifier("emsServiceV1")
	private EmsService emsService;
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		List<EmsVO> emsList = emsService.selectAll();
		model.addAttribute("EMS_LIST",emsList);
		return "home";
	}
	
	@RequestMapping(value="/write",method=RequestMethod.GET)
	public String write(Model model) {
		
		LocalDateTime ldt = LocalDateTime.now();
		String cd = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(ldt);
		String ct = DateTimeFormatter.ofPattern("HH:mm:ss").format(ldt);
		
		EmsVO emsVO = EmsVO.builder()
						.s_date(cd)
						.s_time(ct)
						.build();
		model.addAttribute("EMS",emsVO);
		return "ems-write";
	}
	
	@RequestMapping(value="/write",method=RequestMethod.POST)
	// public String write(String from_email,String to_email,String s_subject,Model model) {
	public String write(@ModelAttribute EmsVO emsVO,
			@RequestParam(value="file1",required = false) MultipartFile file1,
			@RequestParam(value="file2",required = false) MultipartFile file2, 
							Model model) {
		
		log.debug("EMSVO {}",emsVO.toString());
		log.debug("file1 {}",file1.getOriginalFilename());
		log.debug("file2 {}",file2.getOriginalFilename());
		
		String file1Name = null;
		String file2Name = null;
		if(!file1.getOriginalFilename().isEmpty()) {
			file1Name = fileService.fileUp(file1);
			emsVO.setS_file1(file1Name);
		}
		if(!file2.getOriginalFilename().isEmpty()) {
			file2Name = fileService.fileUp(file2);
			emsVO.setS_file2(file2Name);
		}
		
		int ret = emsService.insert(emsVO);
		if(ret > 0) {
			log.debug("INERT 수행한 후 결과 {}개 성공",ret);
		}
		
//		model.addAttribute("from_email",emsVO.getFrom_email());
//		model.addAttribute("to_email",emsVO.getTo_email());
//		model.addAttribute("s_subject",emsVO.getS_subject());
//	
//		model.addAttribute("EMS",emsVO);
		return "redirect:/";
	}

	/*
	 * 목록보기(home)에서 제목을 클릭하면
	 * id값을 변수로 넘기면서 update, GET 로 호출이된다.
	 * id값으로 findById()조회를 해서 EmsVO를 DB로 부터 Select하고
	 * 그 결과를 model에 담아서 다시 ems-write.jsp로 보내기
	 */
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String update(String id,Model model) {
		
		Long long_id = Long.valueOf(id);
		EmsVO emsVO = emsService.findById(long_id);
		model.addAttribute("EMS",emsVO);
		return "ems-write";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(
			EmsVO emsVO,
		@RequestParam(value="file1",required = false) MultipartFile file1,
		@RequestParam(value="file2",required = false) MultipartFile file2
			) {

		log.debug("UPDATE 요청데이터 {}",emsVO.toString());
		
		// 1. update 할 데이터의 id값을 추출
		Long id = emsVO.getId();
		// 2. DB에 저장된 데이터를 id값으로 select하여 추출 
		EmsVO emsOldVO = emsService.findById(id); 
		
		boolean file1_ex = file1.getOriginalFilename().isEmpty();
		// 3. upload된 file1이 있는지 검사해서 emsVO에 setting
		// 3-1. upload된 file1 있으면
		if( !file1_ex ) {
			
			// 3-2 파일을 서버 저장소에 저장하고 파일이름 추출
			String file_1_Name = fileService.fileUp(file1);
			
			// 3-3 DB에 저장된 데이터에 파일이름이 있는지 검사
			if( emsOldVO.getS_file1() != null && !emsOldVO.getS_file1().isEmpty() ) {
				
				// 3-4 있으면 서버에서 파일을 삭제
				fileService.fileDelete(emsOldVO.getS_file1());
			}
			//3-5 새로변경된 파일이름을 VO에 저장하여 update 준비
			emsVO.setS_file1(file_1_Name);
			
		//3-6 upload된 파일이 없으면	
		} else if( file1_ex ) {
			// 3-7 db에서 추출한 vo에서 파일이름을 update할 vo에 복사
			emsVO.setS_file1(emsOldVO.getS_file1());
		}

		boolean file2_ex = file2.getOriginalFilename().isEmpty();
		// 4. upload된 file2가 있는지 검사해서 emsVO에 setting
		if( !file2_ex ) {
			
			String file_2_Name = fileService.fileUp(file2);
			if(emsOldVO.getS_file2() != null 
					&& !emsOldVO.getS_file2().isEmpty()) {
				fileService.fileDelete(emsOldVO.getS_file2());
			}
			emsVO.setS_file2(file_2_Name);
			
		} else if( file2_ex ) {
			emsVO.setS_file2(emsOldVO.getS_file2());
		}

		int ret  = emsService.update(emsVO);
		if(ret > 0) {
			log.debug("업데이트된 데이터 개수 {}",ret);
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(String id) {
		
		Long long_id = Long.valueOf(id);
		EmsVO emsVO = emsService.findById(long_id);
		
		if(emsVO.getS_file1()!=null) {
			fileService.fileDelete(emsVO.getS_file1());	
		}
		if(emsVO.getS_file2()!=null) {
			fileService.fileDelete(emsVO.getS_file2());	
		}
		
		int ret = emsService.delete(long_id);
		if(ret > 0) {
			log.debug("삭제된 데이터 개수 {}",ret);
		}
		return "redirect:/";
	
	}
	

	
	
}


