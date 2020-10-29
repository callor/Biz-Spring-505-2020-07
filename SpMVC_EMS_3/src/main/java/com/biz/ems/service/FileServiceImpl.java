package com.biz.ems.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service("fileServiceV1")
public class FileServiceImpl implements FileService{
	
	@Autowired
	private String winPath;
	
	@Autowired
	private String linuxPath;
	
	private String fileUploadPath;
	
	@Autowired
	public void fileUploadPath() {
		this.fileUploadPath = winPath;
		File dir = new File(linuxPath);
		if(dir.exists()) {
			this.fileUploadPath = linuxPath;
		}
	}
	
	
	@Override
	public List<String> filesUp(MultipartHttpServletRequest files) {

		List<MultipartFile> fileList = files.getFiles("file");
		List<String> fileNames = new ArrayList<String>();
		
		for(MultipartFile file : fileList) {
			String fileName = this.fileUp(file);
			fileNames.add(fileName);
		}
		return fileNames;
		
	}

	@Override
	public String fileUp(MultipartFile file) {

		String originalName = file.getOriginalFilename();
		String uuid = UUID.randomUUID().toString();
		
		if(file != null) {
			
			File dir = new File(this.fileUploadPath);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			String uploadFileName = uuid + originalName;
			
			File uploadFile = new File(this.fileUploadPath,uploadFileName);
			
			try {
				file.transferTo(uploadFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return uploadFileName;	
		}
		return null;
	}

	@Override
	public int fileDelete(String fileName) {

		File delFile = new File(this.fileUploadPath,fileName);
		if(delFile.exists()) {
			delFile.delete();
		}
		return 0;
	
	}
}



