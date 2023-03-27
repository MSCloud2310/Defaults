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
    public ResponseEntity<ErrorMessage> handleNonExistingProvider(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ResponseEntity<ErrorMessage> handleAlreadyExistingProvider(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorMessage(HttpStatus.CONFLICT.value(), e.getMessage()));
    }

    @ExceptionHandler(EmptyCartException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleEmptyCart(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }
}
