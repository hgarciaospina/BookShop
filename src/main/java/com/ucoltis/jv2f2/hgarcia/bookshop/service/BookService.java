package com.ucoltis.jv2f2.hgarcia.bookshop.service;

import java.util.List;

import com.ucoltis.jv2f2.hgarcia.bookshop.controller.dto.BookRequest;
import com.ucoltis.jv2f2.hgarcia.bookshop.controller.dto.BookResponse;


public interface BookService {
	List<BookResponse> list();

	BookResponse findById(Long id);

	BookResponse create(BookRequest bookRequest);

	BookResponse update(Long id, BookRequest bookRequest);

	void delete(Long id);
}