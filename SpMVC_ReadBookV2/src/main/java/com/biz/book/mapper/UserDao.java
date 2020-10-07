package com.biz.book.mapper;

import org.apache.ibatis.annotations.Select;

import com.biz.book.model.UserDetailsVO;

public interface UserDao extends GenericDao<UserDetailsVO, String>{
	
	// member table의 전체 회원수를 알기
	@Select("SELECT COUNT(*) FROM tbl_member")
	public int userCount() ;
	
}
