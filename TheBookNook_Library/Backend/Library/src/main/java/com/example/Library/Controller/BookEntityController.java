package com.example.Library.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Library.Entity.BookEntity;
import com.example.Library.Exception.BookNotFoundException;
import com.example.Library.service.BookEntityService;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/book/")
@RestController
public class BookEntityController {
	@Autowired
	private BookEntityService bookEntityService;

	@GetMapping("/hello")
	public String hello() {
		return "Welcome";
	}

	@PostMapping("/saveBook")
	public ResponseEntity<BookEntity> saveBook(@RequestBody BookEntity bookEntity) {
		return bookEntityService.saveBook(bookEntity);

	}

	@PostMapping("/{userId}/borrow/{bookId}")
	public ResponseEntity<String> borrowBook(@PathVariable Integer userId, @PathVariable Long bookId) {
		try {
			bookEntityService.borrowBook(userId, bookId);
			return ResponseEntity.ok("Book borrowed successfully");
		} catch (BookNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// Endpoint to return a book
	@PostMapping("/{userId}/return/{bookId}")
	public ResponseEntity<String> returnBook(@PathVariable Integer userId, @PathVariable Long bookId) {
		try {
			bookEntityService.returnBook(userId, bookId);
			return ResponseEntity.ok("Book returned successfully");
		} catch (BookNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/{userId}/reserve/{bookId}")
	public ResponseEntity<String> reserveBook(@PathVariable Integer userId, @PathVariable Long bookId) {
		try {
			bookEntityService.reserveBook(userId, bookId);
			return ResponseEntity.ok("Book reserved successfully");
		} catch (BookNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/fetchAllBooks")
	public ResponseEntity<?> fetchAllBooks() {
		ResponseEntity<List<BookEntity>> books = bookEntityService.fetchAllBooks();
		try {
			return books;
		} catch (BookNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/fetchBookByTitle/{bookTitle}")
	public ResponseEntity<?> fetchBookByTitle(@PathVariable("bookTitle") String bookTitle) {
		BookEntity book = bookEntityService.fetchBookByTitle(bookTitle);
		try {
			return ResponseEntity.ok(book);
		} catch (BookNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GetMapping("/fetchBookByAuthorName/{authorName}")
	public ResponseEntity<?> fetchBookByAuthorName(@PathVariable("authorName") String authorName) {
		BookEntity book = bookEntityService.fetchBookByAuthorName(authorName);
		try {
			return ResponseEntity.ok(book);
		} catch (BookNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GetMapping("/fetchBookByCategory/{category}")
	public ResponseEntity<?> fetchBookByCategory(@PathVariable("category") String category) {
		BookEntity book = bookEntityService.fetchBookByCategory(category);
		try {
			return ResponseEntity.ok(book);
		} catch (BookNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@DeleteMapping("/deleteBook/{bookId}")
	public String deleteBookById(@PathVariable("bookId") Long bookId) {
		bookEntityService.deleteBookById(bookId);
		return "Book deleted successful !";
	}

	@PutMapping("/updateBook/{bookId}")
	public ResponseEntity<?> updateBookDetailsById(@PathVariable("bookId") Long bookId,
			@RequestBody BookEntity updatedBook) {
		BookEntity book = bookEntityService.updateBookDetailsById(bookId, updatedBook);
		try {
			return ResponseEntity.ok(book);
		} catch (BookNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}
