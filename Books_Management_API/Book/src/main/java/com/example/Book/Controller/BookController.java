package com.example.Book.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Book.Entity.Book;
import com.example.Book.Service.BookService;

@RestController
public class BookController {
	@Autowired
	public BookService bookService;
	
	@PostMapping("/savebook")
	public Book saveBook(@RequestBody Book book) {
		
		return bookService.saveBook(book);
	}
	
	@GetMapping("/fetchAllBooks")
	public List<Book> fetchAllBooks(Book book){
		return bookService.fetchAllBooks();
	}
	
	@GetMapping("/retrieveBookById/{id}")
	public Optional<Book> fetchBookById(@PathVariable("id") Integer id){
		return bookService.fetchBookById(id);
	}
	
	@GetMapping("/retrieveBookByName/{bookTitle}")
	public Optional<Book> fetchByBookTitle(@PathVariable("bookTitle") String bookTitle){
		return bookService.fetchByBookTitle(bookTitle);
	}
	@PutMapping("/updateBookDetailsByBookTitle/{bookTitle}")
	public Book updateBookDetails(@PathVariable("bookTitle")String bookTitle ,@RequestBody Book book){
		return bookService.updateBookDetails(bookTitle, book);
	}
	
	@DeleteMapping("/deleteBookByBookTitle/{bookTitle}")
	public String deleteBookByBookTitle(@PathVariable("bookTitle") String bookTitle, Book book) {
		bookService.deleteBookByBookTitle(bookTitle);
		return "Book delete successfully by using book tilte";
	}
	
	@DeleteMapping("/deleteBookByBookId/{bookId}")
	public String deleteBookByBookIdeBook(@PathVariable("bookId") Integer bookId) {
		bookService.deleteBookByBookId(bookId);
		return "Book delete successfully by using book id";
	}
}
	

