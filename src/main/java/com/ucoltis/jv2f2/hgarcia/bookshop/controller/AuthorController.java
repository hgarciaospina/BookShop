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

import com.ucoltis.jv2f2.hgarcia.bookshop.controller.dto.AuthorRequest;
import com.ucoltis.jv2f2.hgarcia.bookshop.controller.dto.AuthorResponse;
import com.ucoltis.jv2f2.hgarcia.bookshop.service.AuthorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/author")
@RequiredArgsConstructor
@CrossOrigin(origins = ("http://localhost:4200"))
public class AuthorController {
	
	private final AuthorService authorService;
	
	@GetMapping
	public List<AuthorResponse> list() {
		return authorService.list();
	}
	
	@GetMapping("/{id}")
	public AuthorResponse getById(@PathVariable("id") Long id) {
		return authorService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public AuthorResponse create(@RequestBody AuthorRequest  authorRequest) {
		return authorService.create(authorRequest);
		
	}
	
	@PutMapping("/{id}")
	public AuthorResponse update(@PathVariable("id") Long id, @RequestBody AuthorRequest authorRequest) {
		return authorService.update(id, authorRequest);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		authorService.delete(id);
	}
	
}
