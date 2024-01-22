package com.ucoltis.jv2f2.hgarcia.bookshop.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorRequest {

	private String firstName;
	
	private String lastName;
	
	private String biography;

}
