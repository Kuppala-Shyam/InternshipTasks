package com.example.User.Exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InsufficientAmountException extends RuntimeException{
	private String message = "Account has insufficient balance to transfer money.";

	public InsufficientAmountException(String message) {
		super(message);
		this.message = message;
	}
	

}
