package com.ucoltis.jv2f2.hgarcia.bookshop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ucoltis.jv2f2.hgarcia.bookshop.controller.dto.BookRequest;
import com.ucoltis.jv2f2.hgarcia.bookshop.controller.dto.BookResponse;
import com.ucoltis.jv2f2.hgarcia.bookshop.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/book")
@RequiredArgsConstructor
@CrossOrigin(origins = ("http://localhost:4200"))
public class BookController {
	
private final BookService bookService;
	
	@GetMapping
	public List<BookResponse> list() {
		return bookService.list();
	}
	
	@GetMapping("/{id}")
	public BookResponse getById(@PathVariable("id") Long id) {
		return bookService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public BookResponse create(@RequestBody BookRequest  bookRequest) {
		return bookService.create(bookRequest);
		
	}
	
	@PutMapping("/{id}")
	public BookResponse update(@PathVariable("id") Long id, @RequestBody BookRequest bookRequest) {
		return bookService.update(id, bookRequest);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		bookService.delete(id);
	}

}