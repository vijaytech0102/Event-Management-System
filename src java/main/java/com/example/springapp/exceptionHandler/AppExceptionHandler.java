package com.example.springapp.exceptionHandler;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(value=EventBookedException.class)
	public ResponseEntity<?> foo(EventBookedException e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.ALREADY_REPORTED);
	}
    @ExceptionHandler(value=FacilityNotAddedException.class)
	public ResponseEntity<?> foo(FacilityNotAddedException e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.PARTIAL_CONTENT);
	}
    @ExceptionHandler(value=EventAlreadyExistException.class)
	public ResponseEntity<?> foo(EventAlreadyExistException e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.ALREADY_REPORTED);
	}
    @ExceptionHandler(value=NoBodyFoundException.class)
	public ResponseEntity<?> foo(NoBodyFoundException e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
	}
    @ExceptionHandler(value=ConstraintViolationException.class)
	public ResponseEntity<?> foo(ConstraintViolationException e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
