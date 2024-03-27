package com.example.User.Exception;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Custom exception for when a user is not found.
 */
@Data
@NoArgsConstructor

public class UserNotFoundException extends RuntimeException {

	/**
	 * Default message for the exception.
	 */
	private String message = "User with details is not found please register.";

	/**
	 * Constructs a UserNotFoundException with a custom message.
	 * 
	 * @param message The custom message
	 */
	public UserNotFoundException(String message) {
		super(message);
		this.message = message;
	}

}
