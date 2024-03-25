package com.example.User.Exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class UserNotFoundException extends RuntimeException{
	 private String message = "User with details is not found please register.";

	public UserNotFoundException(String message) {
		super(message);
		this.message = message;
	}
	 
	 
}
