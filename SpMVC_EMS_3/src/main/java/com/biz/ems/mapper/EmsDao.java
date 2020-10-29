package com.biz.ems.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.biz.ems.model.EmsVO;

public interface EmsDao {

	@Select("SELECT * FROM tbl_ems ORDER BY s_date DESC, s_time DESC")
	public List<EmsVO> selectAll();
	
	@Select("SELECT * FROM tbl_ems WHERE id = #{id}")
	public EmsVO findById(Long id);

	@Delete("DELETE FROM tbl_ems WHERE id =#{id}")
	public int delete(Long id);
	
	@InsertProvider(type=EmsSQL.class,method="insert")
	public int insert(EmsVO emsVO);
	
	@UpdateProvider(type=EmsSQL.class,method="update")
	public int update(EmsVO emsVO);

}
