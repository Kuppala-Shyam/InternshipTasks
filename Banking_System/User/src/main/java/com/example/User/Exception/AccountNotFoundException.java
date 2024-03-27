package com.example.User.Exception;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Exception thrown when an account is not found.
 */
@Data
@NoArgsConstructor
public class AccountNotFoundException extends RuntimeException {

	// Default message for the exception
	private String message = "Account is not found with your entered details please check and Re-enter the details or create a bank account first";

	/**
	 * Constructs an AccountNotFoundException with the specified detail message.
	 * 
	 * @param message the detail message
	 */
	public AccountNotFoundException(String message) {
		super(message);
		this.message = message;
	}

}
