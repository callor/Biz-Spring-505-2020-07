package com.biz.shop.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.biz.shop.model.ProductVO;

public interface ProductDao extends GenericDao<ProductVO, String>{
	
	@Select(" SELECT * FROM tbl_product "
			+ " WHERE p_not_use IS NULL ")
	@Override
	public List<ProductVO> selectAll() ;
	public List<ProductVO> findByTitle(String title);

	/*
	 * Mybatis 3.4.x 이상에서 사용하는 새로운 SQL Mapper 방식
	 * mapper.xml 파일을 작성하지 않고, 직접 Dao 인터페이스에 SQL을 
	 * 구현하는 방법
	 */
	@Select(" SELECT MAX(p_code) FROM tbl_product ")
	public String maxPCode();
	
	@Select(" SELECT * FROM tbl_product "
			+ " WHERE p_not_use IS NULL AND p_code = RPAD(#{id},6 ,' ')  " )
	@Override
	public ProductVO findByID(String id) ;
	
}
