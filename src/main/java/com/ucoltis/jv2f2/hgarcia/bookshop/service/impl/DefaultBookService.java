package com.ucoltis.jv2f2.hgarcia.bookshop.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ucoltis.jv2f2.hgarcia.bookshop.controller.dto.BookRequest;
import com.ucoltis.jv2f2.hgarcia.bookshop.controller.dto.BookResponse;
import com.ucoltis.jv2f2.hgarcia.bookshop.controller.exception.BadArgumentException;
import com.ucoltis.jv2f2.hgarcia.bookshop.controller.exception.BookExceptionMessage;
import com.ucoltis.jv2f2.hgarcia.bookshop.controller.exception.NotFoundException;
import com.ucoltis.jv2f2.hgarcia.bookshop.model.entity.Author;
import com.ucoltis.jv2f2.hgarcia.bookshop.model.entity.Book;
import com.ucoltis.jv2f2.hgarcia.bookshop.model.entity.Category;
import com.ucoltis.jv2f2.hgarcia.bookshop.model.repository.AuthorRepository;
import com.ucoltis.jv2f2.hgarcia.bookshop.model.repository.BookRepository;
import com.ucoltis.jv2f2.hgarcia.bookshop.model.repository.CategoryRepository;
import com.ucoltis.jv2f2.hgarcia.bookshop.service.BookService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultBookService implements BookService {
	
	private final BookRepository bookRepository;
	private final CategoryRepository categoryRepository;
	private final AuthorRepository authorRepository;
	private final ModelMapper modelMapper;
	
	@Override
	public List<BookResponse> list() {
		var response = bookRepository.findAll()
	            .stream()
	            .map(book -> modelMapper.map(book, BookResponse.class))
	            .collect(Collectors.toList());
	    
	    if(response.isEmpty()) {
	    	throw new NotFoundException(BookExceptionMessage.BOOKS_NOT_FOUND_CODE, BookExceptionMessage.BOOKS_NOT_FOUND_MESSAGE);
	    } 
	    
	    return response;
	}

	@Override
	public BookResponse findById(Long id) {
		if(id == null || id == 0) {
	    	throw new BadArgumentException(BookExceptionMessage.INVALID_PARAMETER_CODE, BookExceptionMessage.INVALID_PARAMETER_MESSAGE);		
	    }
		
		return bookRepository.findById(id)
				 .map(book -> modelMapper.map(book, BookResponse.class))
	            .orElseThrow(() -> new NotFoundException(BookExceptionMessage.BOOK_NOT_FOUND_CODE, BookExceptionMessage.BOOK_NOT_FOUND_MESSAGE + id) );
	}

	@Override
	public BookResponse create(BookRequest bookRequest) {
		if(bookRequest == null ) {
			throw new BadArgumentException(BookExceptionMessage.BOOK_INFO_REQUIRED_CODE, BookExceptionMessage.BOOK_INFO_REQUIRED_MESSAGE);		
		} 
				
		var category = categoryRepository.findById(bookRequest.getCategoryId())
	            .orElseThrow(() -> new NotFoundException(BookExceptionMessage.CATEGORY_NOT_FOUND_CODE, BookExceptionMessage.CATEGORY_NOT_FOUND_MESSAGE + bookRequest.getCategoryId()));
		 
		 var author = authorRepository.findById(bookRequest.getAuthorId())
		            .orElseThrow(() -> new NotFoundException(BookExceptionMessage.AUTHOR_NOT_FOUND_CODE, BookExceptionMessage.AUTHOR_NOT_FOUND_MESSAGE + bookRequest.getAuthorId()));
		 
		var newBook = new Book();
		
		newBook.setTitle(bookRequest.getTitle());
		newBook.setDescription(bookRequest.getDescription());
		newBook.setPrice(bookRequest.getPrice());
		newBook.setIsbn(bookRequest.getIsbn());
		newBook.setPages(bookRequest.getPages());
		newBook.setReleaseDate(bookRequest.getReleaseDate());
		newBook.setImage(bookRequest.getImage());
	    newBook.setCategory(category);
	    newBook.setAuthor(author);
	    
		newBook = bookRepository.save(newBook);
		    
		return modelMapper.map(newBook, BookResponse.class);
	}

	@Override
	public BookResponse update(Long id, BookRequest bookRequest) {
		if(id == null || id == 0) {
	    	throw new BadArgumentException(BookExceptionMessage.INVALID_PARAMETER_CODE, BookExceptionMessage.INVALID_PARAMETER_MESSAGE);		
	    }
		
		if(bookRequest == null ) {
			throw new BadArgumentException(BookExceptionMessage.BOOK_INFO_REQUIRED_CODE, BookExceptionMessage.BOOK_INFO_REQUIRED_MESSAGE);		
		} 
		
		Book existingBook = null;
	    existingBook = bookRepository.findById(id)	                
	        .orElseThrow(() -> new NotFoundException(BookExceptionMessage.BOOK_NOT_FOUND_CODE, BookExceptionMessage.BOOK_NOT_FOUND_MESSAGE + id));
		 
		 if(bookRequest.getTitle() == null) {
			 throw new BadArgumentException(BookExceptionMessage.BOOK_TITLE_REQUIRED_CODE, BookExceptionMessage.BOOK_TITLE_REQUIRED_MESSAGE);
		 }
		 
		 if(bookRequest.getTitle().isBlank() || bookRequest.getTitle().isEmpty()) {
			 throw new BadArgumentException(BookExceptionMessage.BOOK_TITLE_REQUIRED_CODE, BookExceptionMessage.BOOK_TITLE_REQUIRED_MESSAGE);
		 }
		 
		 if(bookRequest.getPrice() == null) {
			 throw new BadArgumentException(BookExceptionMessage.BOOK_PRICE_REQUIRED_CODE, BookExceptionMessage.BOOK_PRICE_REQUIRED_MESSAGE);
		 }
	 
		 if(bookRequest.getPrice() <= 0)  {
			 throw new BadArgumentException(BookExceptionMessage.BOOK_INVALID_PRICE_CODE, BookExceptionMessage.BOOK_INVALID_PRICE_MESSAGE);
		 }
		 
		 if(bookRequest.getIsbn() == null) {
			 throw new BadArgumentException(BookExceptionMessage.BOOK_ISBN_REQUIRED_CODE, BookExceptionMessage.BOOK_ISBN_REQUIRED_MESSAGE);
		 }
		 
		 if(bookRequest.getIsbn().isBlank() || bookRequest.getIsbn().isEmpty()) {
			 throw new BadArgumentException(BookExceptionMessage.BOOK_ISBN_REQUIRED_CODE, BookExceptionMessage.BOOK_ISBN_REQUIRED_MESSAGE);
		 }
		 
		 if(bookRequest.getPages() == null) {
			 throw new BadArgumentException(BookExceptionMessage.BOOK_PAGES_REQUIRED_CODE, BookExceptionMessage.BOOK_PAGES_REQUIRED_MESSAGE);
		 }
		 
		 if(bookRequest.getPages() <= 0) {
			 throw new BadArgumentException(BookExceptionMessage.BOOK_INVALID_PAGES_CODE, BookExceptionMessage.BOOK_INVALID_PAGES_MESSAGE);
		 }
		 
		 if(bookRequest.getCategoryId() == null) {
			 throw new BadArgumentException(BookExceptionMessage.CATEGORY_INFO_REQUIRED_CODE, BookExceptionMessage.CATEGORY_INFO_REQUIRED_MESSAGE);
		 }
		 
		 if(bookRequest.getReleaseDate() == null) {
			 throw new BadArgumentException(BookExceptionMessage.BOOK_RELEASE_DATE_REQUIRED_CODE, BookExceptionMessage.BOOK_RELEASE_DATE_REQUIRED_MESSAGE);
		 }
		 
		 if(bookRequest.getCategoryId() <= 0) {
		    	throw new BadArgumentException(BookExceptionMessage.CATEGORY_INVALID_ID_CODE, BookExceptionMessage.CATEGORY_INVALID_ID_MESSAGE);		
		 }
		 
		 if(bookRequest.getAuthorId() == null) {
			 throw new BadArgumentException(BookExceptionMessage.AUTHOR_INFO_REQUIRED_CODE, BookExceptionMessage.AUTHOR_INFO_REQUIRED_MESSAGE);
		 }
		 
		 if(bookRequest.getAuthorId() <= 0) {
		    	throw new BadArgumentException(BookExceptionMessage.AUTHOR_INVALID_ID_CODE, BookExceptionMessage.AUTHOR_INVALID_ID_MESSAGE);		
		 }
		 
		 var category = categoryRepository.findById(bookRequest.getCategoryId())
	            .orElseThrow(() -> new NotFoundException(BookExceptionMessage.CATEGORY_NOT_FOUND_CODE, BookExceptionMessage.CATEGORY_NOT_FOUND_MESSAGE + bookRequest.getCategoryId()));
		 
		 var author = authorRepository.findById(bookRequest.getAuthorId())
		            .orElseThrow(() -> new NotFoundException(BookExceptionMessage.AUTHOR_NOT_FOUND_CODE, BookExceptionMessage.AUTHOR_NOT_FOUND_MESSAGE + bookRequest.getAuthorId()));
		 
		 existingBook.setTitle(bookRequest.getTitle());
		 existingBook.setDescription(bookRequest.getDescription());
		 existingBook.setPrice(bookRequest.getPrice());
		 existingBook.setIsbn(bookRequest.getIsbn());
		 existingBook.setPages(bookRequest.getPages());
		 existingBook.setReleaseDate(bookRequest.getReleaseDate());
		 existingBook.setImage(bookRequest.getImage());
		 existingBook.setCategory(modelMapper.map(category, Category.class));
		 existingBook.setAuthor(modelMapper.map(author, Author.class));
		 
		 bookRepository.save(existingBook);
		 
		 return modelMapper.map(existingBook, BookResponse.class);
	}

	@Override
	public void delete(Long id) {
		bookRepository.findById(id)	                
        	.orElseThrow(() -> new NotFoundException(BookExceptionMessage.BOOK_NOT_FOUND_CODE, BookExceptionMessage.BOOK_NOT_FOUND_MESSAGE + id));
		
		bookRepository.deleteById(id);
		
	}

}