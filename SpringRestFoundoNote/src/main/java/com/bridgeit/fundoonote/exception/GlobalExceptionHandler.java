package com.bridgeit.fundoonote.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value=RuntimeException.class)
	public ResponseEntity<?> getMet(RuntimeException runtimeException)
	{
		return new ResponseEntity<>(runtimeException.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
