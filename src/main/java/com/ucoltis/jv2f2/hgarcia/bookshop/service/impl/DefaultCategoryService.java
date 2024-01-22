package com.ucoltis.jv2f2.hgarcia.bookshop.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ucoltis.jv2f2.hgarcia.bookshop.controller.dto.CategoryRequest;
import com.ucoltis.jv2f2.hgarcia.bookshop.controller.dto.CategoryResponse;
import com.ucoltis.jv2f2.hgarcia.bookshop.controller.exception.BadArgumentException;
import com.ucoltis.jv2f2.hgarcia.bookshop.controller.exception.NotFoundException;
import com.ucoltis.jv2f2.hgarcia.bookshop.model.entity.Category;
import com.ucoltis.jv2f2.hgarcia.bookshop.model.repository.CategoryRepository;
import com.ucoltis.jv2f2.hgarcia.bookshop.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultCategoryService implements CategoryService{
	
	private final CategoryRepository categoryRepository;

	@Override
	public List<CategoryResponse> list() {
					
	    var response = categoryRepository.findAll()
	            .stream()
	            .map(category -> new CategoryResponse(category.getId(), category.getName()))
	            .collect(Collectors.toList());
	    
	    if(response.isEmpty()) {
	    	throw new NotFoundException("001", "No categories found");
	    } 
	    
	    return response;
	}

	@Override
	public CategoryResponse findById(Long id) {
	    if(id == null || id == 0) {
	    	throw new BadArgumentException("002", "The parameter is not valid");		
	    }
		
		return categoryRepository.findById(id)
	            .map(category -> new CategoryResponse(category.getId(), category.getName()))
	            .orElseThrow(() -> new NotFoundException("001", "No categories found with the id: " + id) );
	}

	@Override
	public CategoryResponse create(CategoryRequest category) {
		if(category == null || category.getName().isBlank()) {
			throw new BadArgumentException("002", "Category name is required");		
		} 
		
		var newCategory = new Category(null, category.getName()); 
	     	newCategory =	categoryRepository.save(newCategory);
		return new CategoryResponse(newCategory.getId(), newCategory.getName());
	}

	@Override
	public CategoryResponse update(Long id, CategoryRequest category) {
		if(id == null || id == 0) {
	    	throw new BadArgumentException("002", "The parameter is not valid");		
	    }
		
		if(category == null || category.getName().isBlank()) {
			throw new BadArgumentException("002", "Category name is required");		
		}
		
	    return categoryRepository.findById(id)
	            .map(existingCategory -> {
	                existingCategory.setName(category.getName());
	                return new CategoryResponse(existingCategory.getId(), existingCategory.getName());
	            })
	            .orElseThrow(() -> new NotFoundException("001", "No categories found with the id: " + id));
	}

	@Override
	public void delete(Long id) {
		if(id == null || id == 0) {
	    	throw new BadArgumentException("002", "The parameter is not valid");		
	    }
		
	    categoryRepository.findById(id)
	    	.orElseThrow(() -> new NotFoundException("001", "No categories found with the id: " + id));
	    
	    categoryRepository.deleteById(id);
	}

}