package com.biz.book.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biz.book.domain.BookVO;
import com.biz.book.repository.BookRepository;

@Service
public class BookServiceImplV1 implements BookService {

	private final BookRepository bookDao;
	public BookServiceImplV1(BookRepository bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public List<BookVO> selectAll() {
		
		List<BookVO> bookList = bookDao.findAll();
		return bookList;
	}

	@Override
	public Page<BookVO> pageSelect(Pageable pageable) {
		
		int pageNumber = pageable.getPageNumber();
		pageNumber = pageNumber == 0 ? 0 : pageNumber - 1 ;
		
		pageable = PageRequest.of(pageNumber, 10);
		Page<BookVO> bookPage = bookDao.findAll(pageable);
		return bookPage;
	
	}

	@Override
	public BookVO findById(Long id) {

		Optional<BookVO> bookVO = bookDao.findById(id);
		return bookVO.get();
	
	}

	@Override
	public int insert(BookVO bookVO) {
		bookDao.save(bookVO);
		return 0;
	}

	@Override
	public int update(BookVO bookVO) {
		bookDao.save(bookVO);
		return 0;
	}

	@Override
	public int delete(Long id) {
		bookDao.deleteById(id);
		return 0;
	}

}
