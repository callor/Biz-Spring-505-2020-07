package com.biz.book.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.biz.book.model.ReadBookVO;

public interface ReadBookDao extends GenericDao<ReadBookVO, Long> {
	
	@Select("SELECT * FROM tbl_readbook WHERE r_book_seq = #{r_book_seq}")
	public List<ReadBookVO> findByBSeq(Long r_book_seq);
	
}
