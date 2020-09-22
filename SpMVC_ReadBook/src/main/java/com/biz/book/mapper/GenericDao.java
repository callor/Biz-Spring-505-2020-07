package com.biz.book.mapper;

import java.util.List;

/*
 * 다수의 Dao interface를 생성할때 사용할
 * 부모 interface
 * 여기에는 Generic으로 VO 클래스의 타입과 PK 변수의 타입을 설정
 */
public interface GenericDao<VO, PK> {

	public List<VO> selectAll();
	public VO findById(PK id);
	public int insert(VO vo);
	public int update(VO vo);
	public int delete(PK id);
	
}
