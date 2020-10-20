package com.biz.bbs.service;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service(value = "fileServiceV2")
public class FileServiceImplV2 extends FileServiceImplV1 {

	/*
	 * servlet-context.xml에 static으로 설정한 폴더에 접근하기 위한
	 * 보조도구
	 */
	private final ResourceLoader resourceLoader;
	
	
	@Override
	public String fileUp(MultipartFile file) {
		
		// 이 프로젝트에서 
		// static 으로 선언된 folder에 files라는 폴더 정보를 가져와라
		Resource resource = resourceLoader.getResource("/static/files");
		
		try {

			String fileSaveFolder = resource.getURI().getPath();
			
			File dir = new File(fileSaveFolder);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			String fileName = file.getOriginalFilename();
			
			File serverSaveFile = new File(fileSaveFolder,fileName);
			file.transferTo(serverSaveFile);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	
	
	
}
