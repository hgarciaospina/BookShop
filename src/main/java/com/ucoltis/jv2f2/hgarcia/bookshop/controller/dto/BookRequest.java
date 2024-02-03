package com.ucoltis.jv2f2.hgarcia.bookshop.controller.dto;

import java.time.LocalDate;

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
	
	private LocalDate releaseDate;
	
	private String image;
	
	private Long categoryId;
	
	private Long authorId;
}