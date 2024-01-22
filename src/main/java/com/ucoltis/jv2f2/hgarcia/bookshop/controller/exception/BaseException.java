package com.ucoltis.jv2f2.hgarcia.bookshop.controller.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String code;
	private final String message;
	
	
    public BaseException(String code, String message) {
    	super(message);
    	this.code = code;
    	this.message = message;
    }
}
