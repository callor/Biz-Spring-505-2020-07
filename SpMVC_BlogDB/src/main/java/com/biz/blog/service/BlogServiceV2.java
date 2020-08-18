package com.biz.blog.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.blog.dao.BlogDao;
import com.biz.blog.model.BlogVO;

@Service(value="bServiceV2")
public class BlogServiceV2 implements BlogService{

	@Autowired
	private BlogDao blogDao;
	
	@Override
	public List<BlogVO> selectAll() {
		return blogDao.selectAll();
	}

	@Override
	public BlogVO findBySeq(long seq) {
		return blogDao.findBySeq(seq);
	}

	@Override
	public List<BlogVO> findByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(BlogVO blogVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(BlogVO blogVO) {

		/*
		 * LocalDate : Java 8(1.8) 이상에서 사용하는 날짜관련 클래스
		 */
		LocalDate localDate = LocalDate.now();
		
		
		return 0;
	}

	@Override
	public int delete(long seq) {
		// TODO Auto-generated method stub
		return 0;
	}

}
