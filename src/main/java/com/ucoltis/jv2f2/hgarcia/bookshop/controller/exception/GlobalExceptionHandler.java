package com.ucoltis.jv2f2.hgarcia.bookshop.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BadArgumentException.class)
	public ResponseEntity<ErrorMessage> handleBadArgument(BadArgumentException exception) {
		return ResponseEntity.badRequest()
				.body(ErrorMessage.builder().code(exception.getCode()).message(exception.getMessage()).build());
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorMessage> handleNotFound(NotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
				.body(ErrorMessage.builder().code(exception.getCode()).message(exception.getMessage()).build());
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorMessage> handleDefault(RuntimeException exception) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.body(ErrorMessage.builder().code("999").message(exception.getMessage()).build());
	}
}