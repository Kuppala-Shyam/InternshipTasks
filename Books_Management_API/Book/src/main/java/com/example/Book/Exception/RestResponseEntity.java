package com.example.Book.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseEntity {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleBookNotFoundException(BookNotFoundException exception, WebRequest request) {
        ErrorMessage errorResponse = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
