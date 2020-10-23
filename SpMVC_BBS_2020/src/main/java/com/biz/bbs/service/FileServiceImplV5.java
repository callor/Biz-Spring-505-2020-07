package com.biz.bbs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.biz.bbs.model.ImageVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(value = "fileServiceV5")
public class FileServiceImplV5 extends FileServiceImplV4 {
	
	@Override

	/*
	 * 멀티파일을 매개변수로 받아서
	 * List로 각 파일을 추출한 후
	 * fileUp() 메서드를 호출하여 파일들을 업로드 하고
	 * 파일 이름을 다시 List로 만들고 return
	 */
	public List<ImageVO> filesUp(MultipartHttpServletRequest files) {
	
		List<MultipartFile> fileList = files.getFiles("files");
		List<ImageVO> fileNames = new ArrayList<ImageVO>();
		
		for(MultipartFile f : fileList) {
			if(!f.getOriginalFilename().isEmpty()) {
				String fileName = this.fileUp(f);
				ImageVO imageVO = ImageVO.builder()
									.i_org_name(f.getOriginalFilename())
									.i_file_name(fileName)
									.build();
				
				fileNames.add( imageVO );
			}
		}
		return fileNames;
	
	}
}
