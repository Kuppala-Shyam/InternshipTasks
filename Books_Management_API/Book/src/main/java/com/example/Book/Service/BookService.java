package com.example.Book.Service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Book.Entity.Book;
import com.example.Book.Exception.BookNotFoundException;
import com.example.Book.Repository.BookRepository;


@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	public List<Book> fetchAllBooks() {
		List<Book> booksList = bookRepository.findAll();
		if (booksList.isEmpty()) {
			throw new BookNotFoundException("No books available");
		}
		return booksList;
	}

	public Optional<Book> fetchBookById(Integer id) {
		Optional<Book> book = bookRepository.findById(id);
		if (!book.isPresent()) {
			throw new BookNotFoundException("Book with that ID is not available.");
		}
		return book;
	}

	public Optional<Book> fetchByBookTitle(String bookTitle) {
		// Modify to return `Optional<Book>`
		return bookRepository.findByBookTitle(bookTitle);
	}

	public Book updateBookDetails(String bookTitle, Book updateBook) {
		if (updateBook == null) {
			throw new IllegalArgumentException("Update book cannot be null");
		}

		Optional<Book> existingBook = bookRepository.findByBookTitle(bookTitle);
		if (existingBook.isPresent()) {
			existingBook.get().setBookTitle(updateBook.getBookTitle());
			existingBook.get().setAuthorName(updateBook.getAuthorName());
			existingBook.get().setPrice(updateBook.getPrice());
			existingBook.get().setRatings(updateBook.getRatings());
			existingBook.get().setPublisherOfBook(updateBook.getPublisherOfBook());
			return bookRepository.save(existingBook.get());
		} else {
			throw new BookNotFoundException("Book with that title is not available.");
		}
	}

	@Transactional
	public void deleteBookByBookTitle(String bookTitle) {
		Optional<Book> book = bookRepository.findByBookTitle(bookTitle);
		if (book.isPresent()) {
			bookRepository.deleteByBookTitle(bookTitle);
		} else {
			throw new BookNotFoundException("Book with that title is not available.");
		}
	}

	@Transactional
	public void deleteBookByBookId(Integer bookId) {
		Optional<Book> book = bookRepository.findById(bookId);
		if (book.isPresent()) {
			bookRepository.deleteById(bookId);
		} else {
			throw new BookNotFoundException("Book with that ID is not available.");
		}
	}

}
