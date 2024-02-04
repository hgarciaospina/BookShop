package com.ucoltis.jv2f2.hgarcia.bookshop.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="author")

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "first_name", length=100, nullable = false)
	private String firstName;
	
	@Column(name = "last_name", length=100, nullable = false)
	private String lastName;
	
	@Column(length = 4000)
	private String biography;
}