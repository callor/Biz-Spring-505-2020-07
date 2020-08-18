package com.biz.blog.service;

import java.util.List;

import com.biz.blog.model.BlogVO;

public interface BlogService {

	public List<BlogVO> selectAll();
	
	// PK칼럼을 대상으로 조회를 하면
	// 데이터는 절대 2개이상 나오지 않는다
	// 반드시 1개만 나온다.
	// return type이 절대 List 형이 아니다.
	public BlogVO findBySeq(long seq);
			
	
	// 제목으로 검색하기
	// PK칼럼이 아닌 나머지 칼럼을 대상으로 조회를 하면
	// 설령 데이터가 1개밖에 나오지 않더라도
	// 이 결과값은 반드시 List형 이다.
	public List<BlogVO> findByTitle(String title);
	
	// VO에 담긴 데이터를 보내서 DB Table에 추가를 하고
	// 성공을 하면 1이상의 리턴결과를 정수형으로 리턴한다 
	public int insert(BlogVO blogVO);
	public int update(BlogVO blogVO);
	public int delete(long seq);
		
}



