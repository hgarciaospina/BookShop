package com.ucoltis.jv2f2.hgarcia.bookshop.service;

import java.util.List;

import com.ucoltis.jv2f2.hgarcia.bookshop.controller.dto.CategoryRequest;
import com.ucoltis.jv2f2.hgarcia.bookshop.controller.dto.CategoryResponse;

public interface CategoryService {

	List<CategoryResponse> list();

	CategoryResponse findById(Long id);

	CategoryResponse create(CategoryRequest category);

	CategoryResponse update(Long id, CategoryRequest category);

	void delete(Long id);

}
