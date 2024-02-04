package com.ucoltis.jv2f2.hgarcia.bookshop.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="book")

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length=150, nullable = false)
	private String title;
	
	@Column(length = 4000)
	private String description;
	
	@Column(nullable = false, precision = 10)
	private  Double price;
	
	@Column(nullable = false, length = 20)
	private  String isbn;
	
	@Column(nullable = false)
	private Integer pages;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private LocalDate releaseDate;
	
	@Column(length = 255)
	private String image;
	
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private Author author;
}