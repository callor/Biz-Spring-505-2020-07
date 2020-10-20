package com.biz.bbs.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	public String fileUp(MultipartFile file);
}
