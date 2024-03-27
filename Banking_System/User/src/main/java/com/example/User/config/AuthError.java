package com.example.User.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Custom exception class for handling authentication errors.
 */
public class AuthError extends RuntimeException {

	/**
	 * Constructs a new AuthError with the default message "Access Denied".
	 * 
	 * @return A ResponseEntity with "Access Denied" message and HTTP status code
	 *         403 (Forbidden).
	 */
	public ResponseEntity<String> AuthError() {
		return new ResponseEntity<>("Access Denied", HttpStatus.FORBIDDEN);
	}
}
