package com.biz.hello.service;

import java.util.List;

import com.biz.hello.model.StudentVO;

public interface StudentService {
	
	public List<StudentVO> selectAll();
	public StudentVO findByStNum(String st_num);
	
	public int insert(StudentVO studentVO);
	public int update(StudentVO studentVO);
	public int delete(String st_num);
	
}
