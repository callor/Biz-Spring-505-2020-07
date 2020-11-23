package com.biz.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biz.book.domain.BookVO;

public interface BookRepository extends JpaRepository<BookVO, Long>{

	
	
}
