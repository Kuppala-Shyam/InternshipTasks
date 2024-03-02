package com.example.Book.Entity;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookId;
	private String bookTitle;
	private String authorName;
	private double price;
	private Double ratings;
	private String publisherOfBook;

	public Book(String bookTitle) {
		this.bookTitle = bookTitle;
	
	}
}
