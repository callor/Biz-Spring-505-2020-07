package com.biz.bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.biz.bbs.model.ImageVO;
import com.biz.bbs.sql.ImageSQL;

public interface ImageDao {
	
	@Select("SELECT * FROM tbl_images WHERE i_b_seq = #{i_b_seq}")
	public List<ImageVO> findByBSeq(long i_b_seq);
	
	@InsertProvider(type = ImageSQL.class,method = "insert")
	public int insert(@Param("vo") ImageVO imageVO, 
					@Param("b_seq") long b_seq);
	
	@Insert(" INSERT INTO tbl_images (i_seq, i_b_seq, i_org_name, i_file_name) "
			+ " SELECT SEQ_IMAGES.NEXTVAL,1, SUB.* FROM ( "
			+ " <foreach collection='list' item='vo' separator='UNION ALL'> "
			+ " SELECT #{vo.i_org_name},#{vo.i_file_name} FROM DUAL "
			+ " </foreach> "
			+ " ) SUB "
			
			)
	public int insert_multi(List<ImageVO> images); // , @Paramlong b_seq);
	
}
