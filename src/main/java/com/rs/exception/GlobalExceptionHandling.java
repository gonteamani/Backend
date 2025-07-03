package com.rs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalExceptionHandling {
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<String>responseNotFound(Exception e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		
			
	}
	@ExceptionHandler(DuplicateFound.class)
	public ResponseEntity<String>duplicateFound(Exception e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}

}
