package com.biz.blog.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.blog.dao.BlogDao;
import com.biz.blog.model.BlogVO;

/*
 * No qualifying bean of type 'com.biz.blog.service.BlogService'
 * spring 프로젝트에서 서버를 시작했을때 매우 자주 만나는 exception
 * Controller, Service 등의 클래스에 Annotation을 부착하지 않았을때 발생
 */
@Service
public class BlogServiceImplV1 implements BlogService {
	
	// 객체 주입
	// @Autowired를 빼먹으면? : NullPointerException
	@Autowired
	// mybatis-context에서 설정한 SqlSessionTemplate를 가져와서
	// 사용할수 있도록 선언
	private SqlSession sqlSession;
		
	@Override
	public List<BlogVO> selectAll() {

		// BlogDao와 SqlSession을 연동하여 Mybatis 연결 구성
		// sqlSession에게 BlogDao 인터페이스와 blog-mapper.xml 파일을 참조하여
		// BlogDao 인터페이스를 구현한 클래스를 만들고, 객체로 생성하여
		// 사용할 수 있도록 해달라
		BlogDao blogDao = sqlSession.getMapper(BlogDao.class);
		List<BlogVO> blogList = blogDao.selectAll();
		
		return blogList;
	}

	@Override
	public BlogVO findBySeq(long seq) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long seq) {
		// TODO Auto-generated method stub
		return 0;
	}

}
