package com.example.User.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseEntity {
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleUserNotFoundException(Exception exception,WebRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErrorMessage errorMessage = new ErrorMessage(status, exception.getMessage());
		return  ResponseEntity.status(status).body(errorMessage);
	}
	@ExceptionHandler(InsufficientAmountException.class)
	public ResponseEntity<ErrorMessage> handleAccessDeniedException(Exception exception, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorMessage errorMessage = new ErrorMessage(status, exception.getMessage());
		return  ResponseEntity.status(status).body(errorMessage);
	}
	
	
}
