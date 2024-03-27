package com.example.User.Exception;

import org.springframework.http.HttpStatus;

/**
 * Class representing an error message with status and message.
 */
public class ErrorMessage {
	private HttpStatus status;
	private String message;

	/**
	 * Constructs an ErrorMessage object with the specified status and message.
	 * 
	 * @param status  the HTTP status code
	 * @param message the error message
	 */
	public ErrorMessage(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

}
