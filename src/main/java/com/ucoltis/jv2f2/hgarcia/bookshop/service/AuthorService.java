package com.ucoltis.jv2f2.hgarcia.bookshop.service;

import java.util.List;

import com.ucoltis.jv2f2.hgarcia.bookshop.controller.dto.AuthorRequest;
import com.ucoltis.jv2f2.hgarcia.bookshop.controller.dto.AuthorResponse;

public interface AuthorService {

	List<AuthorResponse> list();

	AuthorResponse findById(Long id);

	AuthorResponse create(AuthorRequest authorRequest);

	AuthorResponse update(Long id, AuthorRequest authorRequest);

	void delete(Long id);
}
