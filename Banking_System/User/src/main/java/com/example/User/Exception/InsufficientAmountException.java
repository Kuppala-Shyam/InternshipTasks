package com.example.User.Exception;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Exception indicating that there is insufficient balance in the account for a
 * money transfer.
 */
@Data
@NoArgsConstructor
public class InsufficientAmountException extends RuntimeException {
	private String message = "Account has insufficient balance to transfer money.";

	/**
	 * Constructs an InsufficientAmountException with the specified message.
	 * 
	 * @param message the detail message
	 */
	public InsufficientAmountException(String message) {
		super(message);
		this.message = message;
	}

}
