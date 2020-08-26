package com.biz.shop.persistence;

import org.apache.ibatis.annotations.Select;

import com.biz.shop.model.DeptVO;

public interface DeptDao extends GenericDao<DeptVO, String>{

	@Select(" SELECT MAX(d_code) FROM tbl_dept ")
	public String maxDCode();

}
