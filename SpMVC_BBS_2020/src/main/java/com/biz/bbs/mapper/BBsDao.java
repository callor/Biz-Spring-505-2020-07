package com.biz.bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.biz.bbs.model.BBsVO;
import com.biz.bbs.sql.BBsSQL;

public interface BBsDao {

	@Select("SELECT * FROM tbl_bbs ORDER BY b_seq DESC")
	public List<BBsVO> selectAll();
	
	@Select("SELECT * FROM tbl_bbs WHERE b_seq = #{seq} ")
	public BBsVO findBySeq(long seq);
	
	/*
	 * BBsSQL 클래스에 정의된 bbs_insert method를 호출하여
	 * SQL문을 생성하고, 여기에 코드를 추가하라
	 */
	@InsertProvider(type=BBsSQL.class,method="bbs_insert")
	public int insert(BBsVO bbsVO);
	
	@UpdateProvider(type=BBsSQL.class,method="bbs_update")
	public int update(BBsVO bbsVO);
	
	@Delete("DELETE FROM tbl_bbs WHERE b_seq = #{seq}")
	public int delete(long seq);
	
}





