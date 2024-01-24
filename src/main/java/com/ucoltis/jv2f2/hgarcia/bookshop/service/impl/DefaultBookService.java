package com.ucoltis.jv2f2.hgarcia.bookshop.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ucoltis.jv2f2.hgarcia.bookshop.controller.dto.BookRequest;
import com.ucoltis.jv2f2.hgarcia.bookshop.controller.dto.BookResponse;
import com.ucoltis.jv2f2.hgarcia.bookshop.controller.exception.BadArgumentException;
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
	
	private static final String INVALID_PARAMETER_CODE = "301";
    private static final String INVALID_PARAMETER_MESSAGE = "The parameter is not valid";
    
    private static final String BOOK_INVALID_PRICE_CODE = "302";
    private static final String BOOK_INVALID_PRICE_MESSAGE = "The price must be a value greater than zero";

    private static final String BOOK_PRICE_REQUIRED_CODE = "303";
    private static final String BOOK_PRICE_REQUIRED_MESSAGE = "Book price is required";
    
    private static final String BOOK_ISBN_REQUIRED_CODE = "304";
    private static final String BOOK_ISBN_REQUIRED_MESSAGE = "Book isbn is required";
    
    private static final String BOOK_PAGES_REQUIRED_CODE = "305";
    private static final String BOOK_PAGES_REQUIRED_MESSAGE = "The number of pages is required";
    
    private static final String BOOK_RELEASE_DATE_REQUIRED_CODE = "306";
    private static final String BOOK_RELEASE_DATE_REQUIRED_MESSAGE = "The release date is required";
    
    private static final String BOOK_INVALID_PAGES_CODE = "307";
    private static final String BOOK_INVALID_PAGES_MESSAGE = "The number of pages must be greater than zero";
    
    private static final String BOOK_INFO_REQUIRED_CODE = "308";
    private static final String BOOK_INFO_REQUIRED_MESSAGE = "Book information is required";

    private static final String BOOK_NOT_FOUND_CODE = "309";
    private static final String BOOK_NOT_FOUND_MESSAGE = "Book not found with the provided id: ";
    
    private static final String BOOKS_NOT_FOUND_CODE = "310";
    private static final String BOOKS_NOT_FOUND_MESSAGE = "Books not found.";
    
    private static final String BOOK_TITLE_REQUIRED_CODE = "311";
    private static final String BOOK_TITLE_REQUIRED_MESSAGE = "Book title is required";

    private static final String CATEGORY_INFO_REQUIRED_CODE = "312";
    private static final String CATEGORY_INFO_REQUIRED_MESSAGE = "Category information is required";
    
    private static final String CATEGORY_NOT_FOUND_CODE = "313";
    private static final String CATEGORY_NOT_FOUND_MESSAGE = "Category not found with the provided id: ";
    
    private static final String CATEGORY_INVALID_ID_CODE ="314";
    private static final String CATEGORY_INVALID_ID_MESSAGE = "The category id must be greater than zero and exist in the categories.";
    
    private static final String AUTHOR_INVALID_ID_CODE = "315"; 
    private static final String AUTHOR_INVALID_ID_MESSAGE = "The author id must be greater than zero and exist in the authors.";
    
    private static final String AUTHOR_INFO_REQUIRED_CODE = "316";
    private static final String AUTHOR_INFO_REQUIRED_MESSAGE = "Author information is required";
    
    private static final String AUTHOR_NOT_FOUND_CODE = "317";
    private static final String AUTHOR_NOT_FOUND_MESSAGE = "Author not found with the provided id: ";

	@Override
	public List<BookResponse> list() {
		var response = bookRepository.findAll()
	            .stream()
	            .map(book -> modelMapper.map(book, BookResponse.class))
	            .collect(Collectors.toList());
	    
	    if(response.isEmpty()) {
	    	throw new NotFoundException(BOOKS_NOT_FOUND_CODE, BOOKS_NOT_FOUND_MESSAGE);
	    } 
	    
	    return response;
	}

	@Override
	public BookResponse findById(Long id) {
		if(id == null || id == 0) {
	    	throw new BadArgumentException(INVALID_PARAMETER_CODE, INVALID_PARAMETER_MESSAGE);		
	    }
		
		return bookRepository.findById(id)
				 .map(book -> modelMapper.map(book, BookResponse.class))
	            .orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND_CODE, BOOK_NOT_FOUND_MESSAGE + id) );
	}

	@Override
	public BookResponse create(BookRequest bookRequest) {
		if(bookRequest == null ) {
			throw new BadArgumentException(BOOK_INFO_REQUIRED_CODE, BOOK_INFO_REQUIRED_MESSAGE);		
		} 
				
		var category = categoryRepository.findById(bookRequest.getCategoryId())
	            .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND_CODE, CATEGORY_NOT_FOUND_MESSAGE + bookRequest.getCategoryId()));
		 
		 var author = authorRepository.findById(bookRequest.getAuthorId())
		            .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND_CODE, AUTHOR_NOT_FOUND_MESSAGE + bookRequest.getAuthorId()));
		 
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
	    	throw new BadArgumentException(INVALID_PARAMETER_CODE, INVALID_PARAMETER_MESSAGE);		
	    }
		
		if(bookRequest == null ) {
			throw new BadArgumentException(BOOK_INFO_REQUIRED_CODE,  BOOK_INFO_REQUIRED_MESSAGE);		
		} 
		
		Book existingBook = null;
	    existingBook = bookRepository.findById(id)	                
	        .orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND_CODE, BOOK_NOT_FOUND_MESSAGE + id));
		 
		 if(bookRequest.getTitle() == null) {
			 throw new BadArgumentException(BOOK_TITLE_REQUIRED_CODE, BOOK_TITLE_REQUIRED_MESSAGE);
		 }
		 
		 if(bookRequest.getTitle().isBlank() || bookRequest.getTitle().isEmpty()) {
			 throw new BadArgumentException(BOOK_ISBN_REQUIRED_CODE, BOOK_ISBN_REQUIRED_MESSAGE);
		 }
		 
		 if(bookRequest.getPrice() == null) {
			 throw new BadArgumentException(BOOK_PRICE_REQUIRED_CODE, BOOK_PRICE_REQUIRED_MESSAGE);
		 }
	 
		 if(bookRequest.getPrice() <= 0)  {
			 throw new BadArgumentException(BOOK_INVALID_PRICE_CODE, BOOK_INVALID_PRICE_MESSAGE);
		 }
		 
		 if(bookRequest.getIsbn() == null) {
			 throw new BadArgumentException(BOOK_ISBN_REQUIRED_CODE, BOOK_ISBN_REQUIRED_MESSAGE);
		 }
		 
		 if(bookRequest.getIsbn().isBlank() || bookRequest.getIsbn().isEmpty()) {
			 throw new BadArgumentException(BOOK_ISBN_REQUIRED_CODE, BOOK_ISBN_REQUIRED_MESSAGE);
		 }
		 
		 if(bookRequest.getPages() == null) {
			 throw new BadArgumentException(BOOK_PAGES_REQUIRED_CODE, BOOK_PAGES_REQUIRED_MESSAGE);
		 }
		 
		 if(bookRequest.getPages() <= 0) {
			 throw new BadArgumentException(BOOK_INVALID_PAGES_CODE, BOOK_INVALID_PAGES_MESSAGE);
		 }
		 
		 if(bookRequest.getCategoryId() == null) {
			 throw new BadArgumentException(CATEGORY_INFO_REQUIRED_CODE, CATEGORY_INFO_REQUIRED_MESSAGE);
		 }
		 
		 if(bookRequest.getReleaseDate() == null) {
			 throw new BadArgumentException(BOOK_RELEASE_DATE_REQUIRED_CODE, BOOK_RELEASE_DATE_REQUIRED_MESSAGE);
		 }
		 
		 if(bookRequest.getCategoryId() <= 0) {
		    	throw new BadArgumentException(CATEGORY_INVALID_ID_CODE, CATEGORY_INVALID_ID_MESSAGE);		
		 }
		 
		 if(bookRequest.getAuthorId() == null) {
			 throw new BadArgumentException(AUTHOR_INFO_REQUIRED_CODE, AUTHOR_INFO_REQUIRED_MESSAGE);
		 }
		 
		 if(bookRequest.getAuthorId() <= 0) {
		    	throw new BadArgumentException(AUTHOR_INVALID_ID_CODE, AUTHOR_INVALID_ID_MESSAGE);		
		 }
		 
		 var category = categoryRepository.findById(bookRequest.getCategoryId())
	            .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND_CODE, CATEGORY_NOT_FOUND_MESSAGE + id));
		 
		 var author = authorRepository.findById(bookRequest.getAuthorId())
		            .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND_CODE, AUTHOR_NOT_FOUND_MESSAGE + id));
		 
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
        	.orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND_CODE,  BOOK_NOT_FOUND_MESSAGE + id));
		
		bookRepository.deleteById(id);
		
	}

}