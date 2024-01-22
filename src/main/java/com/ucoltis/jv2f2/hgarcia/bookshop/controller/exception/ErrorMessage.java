package com.ucoltis.jv2f2.hgarcia.bookshop.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorMessage {
		private String code;
		private String message;
}
