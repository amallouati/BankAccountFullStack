package com.kata.bankAccount.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(OperationFailException.class)
	public ResponseEntity<Object> handleOperationFailException(final OperationFailException ex) {
		String bodyOfResponse = "No account matched provided Id";
		return ResponseEntity.badRequest().body(bodyOfResponse + ex.getMessage());
	}
}