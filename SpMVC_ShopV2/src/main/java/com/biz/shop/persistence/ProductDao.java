package com.biz.shop.persistence;

import java.util.List;

import com.biz.shop.model.ProductVO;

public interface ProductDao extends GenericDao<ProductVO, String>{
	
	public List<ProductVO> findByTitle(String title);

}
