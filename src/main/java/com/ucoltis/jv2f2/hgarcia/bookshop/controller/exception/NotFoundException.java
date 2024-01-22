package com.ucoltis.jv2f2.hgarcia.bookshop.controller.exception;

public class NotFoundException extends BaseException {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public NotFoundException(String code, String message) {
			super(code, message);
	}
}