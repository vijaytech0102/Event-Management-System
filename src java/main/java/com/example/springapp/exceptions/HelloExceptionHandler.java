package com.example.springapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HelloExceptionHandler {
	@ExceptionHandler(value=UserExistsException.class)
	public ResponseEntity<?> foo(UserExistsException e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.FOUND);
	}
}
