package com.ucoltis.jv2f2.hgarcia.bookshop.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ucoltis.jv2f2.hgarcia.bookshop.controller.dto.AuthorRequest;
import com.ucoltis.jv2f2.hgarcia.bookshop.controller.dto.AuthorResponse;
import com.ucoltis.jv2f2.hgarcia.bookshop.controller.exception.BadArgumentException;
import com.ucoltis.jv2f2.hgarcia.bookshop.controller.exception.NotFoundException;
import com.ucoltis.jv2f2.hgarcia.bookshop.model.entity.Author;
import com.ucoltis.jv2f2.hgarcia.bookshop.model.repository.AuthorRepository;
import com.ucoltis.jv2f2.hgarcia.bookshop.service.AuthorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultAuthorService implements AuthorService {
	
	private final AuthorRepository authorRepository;
	private final ModelMapper modelMapper;
	
	private static final String INVALID_PARAMETER_CODE = "201";
    private static final String INVALID_PARAMETER_MESSAGE = "The parameter is not valid";

    private static final String AUTHOR_INFO_REQUIRED_CODE = "202";
    private static final String AUTHOR_INFO_REQUIRED_MESSAGE = "Author information is required";

    private static final String AUTHOR_NOT_FOUND_CODE = "203";
    private static final String AUTHOR_NOT_FOUND_MESSAGE = "Author not found with the provided id: ";
    
    private static final String AUTHORS_NOT_FOUND_CODE = "204";
    private static final String AUTHORS_NOT_FOUND_MESSAGE = "Authors not found.";

    private static final String AUTHOR_FIRST_NAME_REQUIRED_CODE = "205";
    private static final String AUTHOR_FIRST_NAME_REQUIRED_MESSAGE = "Author firstname is required";

    private static final String AUTHOR_LAST_NAME_REQUIRED_CODE = "206";
    private static final String AUTHOR_LAST_NAME_REQUIRED_MESSAGE = "Author lastname is required";	
	
	
	@Override
	public List<AuthorResponse> list() {
		var response = authorRepository.findAll()
	            .stream()
	            .map(author -> modelMapper.map(author, AuthorResponse.class))
	            .collect(Collectors.toList());
	    
	    if(response.isEmpty()) {
	    	throw new NotFoundException(AUTHORS_NOT_FOUND_CODE, AUTHORS_NOT_FOUND_MESSAGE);
	    } 
	    
	    return response;
	}

	@Override
	public AuthorResponse findById(Long id) {
		if(id == null || id == 0) {
	    	throw new BadArgumentException(INVALID_PARAMETER_CODE, INVALID_PARAMETER_MESSAGE);		
	    }
		
		return authorRepository.findById(id)
				 .map(author -> modelMapper.map(author, AuthorResponse.class))
	            .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND_CODE, AUTHOR_NOT_FOUND_MESSAGE + id) );
	}

	@Override
	public AuthorResponse create(AuthorRequest authorRequest) {
		if(authorRequest == null ) {
			throw new BadArgumentException(AUTHOR_INFO_REQUIRED_CODE, AUTHOR_INFO_REQUIRED_MESSAGE);		
		} 
		
		var newAuthor = modelMapper.map(authorRequest, Author.class); 
		    newAuthor =	authorRepository.save(newAuthor);
		    
		return modelMapper.map(newAuthor, AuthorResponse.class);
	}

	@Override
	public AuthorResponse update(Long id, AuthorRequest authorRequest) {
		if(id == null || id == 0) {
	    	throw new BadArgumentException(INVALID_PARAMETER_CODE, INVALID_PARAMETER_MESSAGE);		
	    }
		
		if(authorRequest == null ) {
			throw new BadArgumentException( AUTHOR_INFO_REQUIRED_CODE,  AUTHOR_INFO_REQUIRED_MESSAGE);		
		} 
		
		 var existingAuthor = authorRepository.findById(id)	                
	            .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND_CODE, AUTHOR_NOT_FOUND_MESSAGE + id));
		 
		 if(authorRequest.getFirstName() == null) {
			 throw new BadArgumentException(AUTHOR_FIRST_NAME_REQUIRED_CODE , AUTHOR_FIRST_NAME_REQUIRED_MESSAGE);
		 }
	 
		 if(authorRequest.getFirstName().isBlank() || authorRequest.getFirstName().isEmpty()) {
			 throw new BadArgumentException(AUTHOR_FIRST_NAME_REQUIRED_CODE, AUTHOR_FIRST_NAME_REQUIRED_MESSAGE);
		 }
		 
		 if(authorRequest.getLastName() == null) {
			 throw new BadArgumentException(AUTHOR_LAST_NAME_REQUIRED_CODE, AUTHOR_LAST_NAME_REQUIRED_MESSAGE);
		 }
		 
		 if(authorRequest.getLastName().isBlank() || authorRequest.getLastName().isEmpty()) {
			 throw new BadArgumentException(AUTHOR_LAST_NAME_REQUIRED_CODE, AUTHOR_LAST_NAME_REQUIRED_MESSAGE);
		 }
		 
		 existingAuthor.setFirstName(authorRequest.getFirstName());
		 existingAuthor.setLastName(authorRequest.getLastName());
		 existingAuthor.setBiography(authorRequest.getBiography());
		 
		 authorRepository.save(existingAuthor);
		 
		 return modelMapper.map(existingAuthor, AuthorResponse.class);
	}

	@Override
	public void delete(Long id) {
		authorRepository.findById(id)	                
	            .orElseThrow(() -> new NotFoundException( AUTHOR_NOT_FOUND_CODE,  AUTHOR_NOT_FOUND_MESSAGE + id));
		
		authorRepository.deleteById(id);	
	}

}