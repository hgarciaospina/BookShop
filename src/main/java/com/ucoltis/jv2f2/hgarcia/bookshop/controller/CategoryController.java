package com.ucoltis.jv2f2.hgarcia.bookshop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ucoltis.jv2f2.hgarcia.bookshop.controller.dto.CategoryRequest;
import com.ucoltis.jv2f2.hgarcia.bookshop.controller.dto.CategoryResponse;
import com.ucoltis.jv2f2.hgarcia.bookshop.service.CategoryService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
	
	private final CategoryService categoryService;
	
	@GetMapping
	public List<CategoryResponse> list() {
		
		return categoryService.list();
		
	}
	
	@GetMapping("/{id}")
	
	public CategoryResponse getById (@PathVariable("id") Long id) {
		
		return categoryService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public CategoryResponse create(@RequestBody CategoryRequest category) {
		
		return categoryService.create(category);
	}
	
	@PutMapping("/{id}")
	public CategoryResponse update(@PathVariable("id") Long id, @RequestBody CategoryRequest category) {
		
		return categoryService.update(id, category);
	}
	

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {

		categoryService.delete(id);
	}
	
}
