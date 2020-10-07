package com.biz.book.service;

import com.biz.book.model.UserDetailsVO;

public interface MemberService {

	public int insert(UserDetailsVO userVO);

	public UserDetailsVO findById(String username);
	public String userNameAndPassword(String username, String password) ;
	public int update(UserDetailsVO userVO);
	
}
