package com.biz.blog.service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
@Service(value="bServiceV1")
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
		
		BlogDao blogDao = sqlSession.getMapper(BlogDao.class);
		BlogVO blogVO = blogDao.findBySeq(seq);
		return blogVO;
	
	}
	
	
	
	@Override
	public List<BlogVO> findByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 
	 * INSERT 문을 실행할때 많이 발생하는Exception
	 * java.sql.SQLException: 부적합한 열 유형: 1111
	 * 
	 * MyBatis를 사용하여 insert를 수행할때
	 * NOT NULL이 아닌 칼럼에 값이 없으면 발생하는 Exception
	 * 
	 * 날짜와 시간칼럼의 값이 없어서 발생하는 Exception
	 * 
	 */
	
	@Override
	public int insert(BlogVO blogVO) {
		// TODO Auto-generated method stub
		BlogDao blogDao = sqlSession.getMapper(BlogDao.class);
		
		// 작성한 날짜와 시각을 VO에 담기위해 insert 서비스에서 
		// 날짜 시각 생성
		Date date = new Date(System.currentTimeMillis());
		
		// Date 데이터를 문자열형으로 변환하기 위해
		// 2020-08-18 형식의 문자열로 변환하기 위한 format 선언
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		
		// 09:32:00 형식의 문자열로 변환하기 위한 format 선언
		SimpleDateFormat st = new SimpleDateFormat("hh:mm:dd");
		
		// 문자열형으로 날짜와 시각을 변환하여 VO에 담기
		blogVO.setBl_date(sd.format(date));
		blogVO.setBl_time(st.format(date));
				
		int ret = blogDao.insert(blogVO);
		return ret;
	}

	@Override
	public int update(BlogVO blogVO) {

		BlogDao blogDao = sqlSession.getMapper(BlogDao.class);
		return blogDao.update(blogVO);
	}

	@Override
	public int delete(long seq) {

		BlogDao blogDao = sqlSession.getMapper(BlogDao.class);
		return blogDao.delete(seq);
	}

}
