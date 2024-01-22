package com.ucoltis.jv2f2.hgarcia.bookshop.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ucoltis.jv2f2.hgarcia.bookshop.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
}
