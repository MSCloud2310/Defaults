package com.defaults.marketplace.msorders.advisors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.defaults.marketplace.msorders.exceptions.*;

@RestControllerAdvice
public class RestAdvisor {
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleNonExistingProvider(RuntimeException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}

	@ExceptionHandler(AlreadyExistsException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public ResponseEntity<String> handleAlreadyExistingProvider(RuntimeException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
}
