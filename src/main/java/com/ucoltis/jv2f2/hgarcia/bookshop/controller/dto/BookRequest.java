package com.ucoltis.jv2f2.hgarcia.bookshop.controller.dto;

import java.time.LocalDate;

import com.ucoltis.jv2f2.hgarcia.bookshop.model.entity.Author;
import com.ucoltis.jv2f2.hgarcia.bookshop.model.entity.Category;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequest {
	
	private String title;
	
	private String description;
	
	private  Double price;
	
	private  String isbn;
	
	private Integer pages;
	
	@Temporal(TemporalType.DATE)
	private LocalDate releaseDate;
	
	private String image;
	
	private Category category;
	
	private Author author;
}
