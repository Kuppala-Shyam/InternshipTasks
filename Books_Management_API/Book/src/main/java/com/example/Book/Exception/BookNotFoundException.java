package com.example.Book.Exception;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class BookNotFoundException extends RuntimeException{
	private String msg = "Book with that id is not available";

	public BookNotFoundException(String msg) {
		super(msg);
		this.msg =msg;
	}
}
