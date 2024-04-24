package com.example.Library.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Custom exception class for book not found errors.
 */
@Data
@NoArgsConstructor
public class BookNotFoundException extends RuntimeException {
	private String msg = "Book with that id is not available";
	
	 /**
     * Constructs a new BookNotFoundException object with a custom message.
     * @param msg The custom error message.
     */
	public BookNotFoundException(String msg) {
		super(msg);
		this.msg =msg;
	}
}
