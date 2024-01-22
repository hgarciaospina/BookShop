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
	
	private static final String INVALID_PARAMETER_CODE = "101";
    private static final String INVALID_PARAMETER_MESSAGE = "The parameter is not valid";

    private static final String CATEGORY_INFO_REQUIRED_CODE = "102";
    private static final String CATEGORY_INFO_REQUIRED_MESSAGE = "Category information is required";

    private static final String CATEGORY_NOT_FOUND_CODE = "103";
    private static final String CATEGORY_NOT_FOUND_MESSAGE = "Category not found with the provided id: ";
    
    private static final String CATEGORIES_NOT_FOUND_CODE = "104";
    private static final String CATEGORIES_NOT_FOUND_MESSAGE = "Categories not found.";

    private static final String CATEGORY__NAME_REQUIRED_CODE = "105";
    private static final String CATEGORY_NAME_REQUIRED_MESSAGE = "Category name is required";

	@Override
	public List<CategoryResponse> list() {
					
	    var response = categoryRepository.findAll()
	            .stream()
	            .map(category -> new CategoryResponse(category.getId(), category.getName()))
	            .collect(Collectors.toList());
	    
	    if(response.isEmpty()) {
	    	throw new NotFoundException(CATEGORIES_NOT_FOUND_CODE, CATEGORIES_NOT_FOUND_MESSAGE);
	    } 
	    
	    return response;
	}

	@Override
	public CategoryResponse findById(Long id) {
	    if(id == null || id == 0) {
	    	throw new BadArgumentException(INVALID_PARAMETER_CODE, INVALID_PARAMETER_MESSAGE);		
	    }
		
		return categoryRepository.findById(id)
	            .map(category -> new CategoryResponse(category.getId(), category.getName()))
	            .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND_CODE , CATEGORY_NOT_FOUND_MESSAGE  + id) );
	}

	@Override
	public CategoryResponse create(CategoryRequest category) {
		if(category == null || category.getName().isBlank()) {
			throw new BadArgumentException(CATEGORY_INFO_REQUIRED_CODE, CATEGORY_INFO_REQUIRED_MESSAGE);		
		} 
		
		var newCategory = new Category(null, category.getName()); 
	     	newCategory =	categoryRepository.save(newCategory);
		return new CategoryResponse(newCategory.getId(), newCategory.getName());
	}

	@Override
	public CategoryResponse update(Long id, CategoryRequest category) {
		if(id == null || id == 0) {
	    	throw new BadArgumentException(INVALID_PARAMETER_CODE, INVALID_PARAMETER_MESSAGE);		
	    }
		
		if(category == null || category.getName().isBlank()) {
			throw new BadArgumentException(CATEGORY__NAME_REQUIRED_CODE, CATEGORY_NAME_REQUIRED_MESSAGE);		
		}
		
	    return categoryRepository.findById(id)
	            .map(existingCategory -> {
	                existingCategory.setName(category.getName());
	                return new CategoryResponse(existingCategory.getId(), existingCategory.getName());
	            })
	            .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND_CODE, CATEGORY_NOT_FOUND_MESSAGE  + id));
	}

	@Override
	public void delete(Long id) {
		if(id == null || id == 0) {
	    	throw new BadArgumentException(INVALID_PARAMETER_CODE, INVALID_PARAMETER_MESSAGE);		
	    }
		
	    categoryRepository.findById(id)
	    	.orElseThrow(() -> new NotFoundException(CATEGORIES_NOT_FOUND_CODE, CATEGORIES_NOT_FOUND_CODE + id));
	    
	    categoryRepository.deleteById(id);
	}

}