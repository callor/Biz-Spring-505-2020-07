package com.biz.book.mapper;

import com.biz.book.model.BookVO;

/*
 * GenericDao 를 extends 하면 
 * 기본 CRUD메서드를
 * 별도로 정의하지 않아도 된다.
 */
public interface BookDao extends GenericDao<BookVO, Long> {
}
