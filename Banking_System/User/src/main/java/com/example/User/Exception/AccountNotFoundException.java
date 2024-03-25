package com.example.User.Exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountNotFoundException extends RuntimeException {
	private String message = "Account is not found with your entered details please check and Re-enter the details or create a bank account first";

	public AccountNotFoundException(String message) {
		super(message);
		this.message = message;
	}
	
}
