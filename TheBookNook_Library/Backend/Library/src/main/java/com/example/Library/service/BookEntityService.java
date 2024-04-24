package com.example.Library.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Library.Entity.BookEntity;
import com.example.Library.Entity.BookReservation;
import com.example.Library.Entity.ReservationStatus;
import com.example.Library.Entity.UserEntity;
import com.example.Library.Exception.BookNotFoundException;
import com.example.Library.Exception.UserNotFoundException;
import com.example.Library.Repository.BookEntityRepository;
import com.example.Library.Repository.BookReservationRepository;
import com.example.Library.Repository.UserEntityRepository;

@Service
public class BookEntityService {
	@Autowired
	private BookEntityRepository bookEntityRepository;
	@Autowired
	private UserEntityRepository userEntityRepository;
	@Autowired
	private BookReservationRepository bookReservationRepository;

	public ResponseEntity<BookEntity> saveBook(BookEntity bookEntity) {
		BookEntity savedBook = bookEntityRepository.save(bookEntity);
		if (savedBook != null) {
			return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public void borrowBook(Integer userId, Long bookId) {
		BookEntity book = bookEntityRepository.findById(bookId)
				.orElseThrow(() -> new BookNotFoundException("Book not found"));

		if (!book.isAvailable()) {
			throw new BookNotFoundException("Book is not available for borrowing");
		}

		book.setAvailable(false);
		book.setBorrowed(true);
		bookEntityRepository.save(book);

		UserEntity user = userEntityRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found"));

		BookReservation reservation = BookReservation.builder().userId(user).bookId(book)
				.reservationDate(LocalDateTime.now()).status(ReservationStatus.borrowed).borrowDate(LocalDateTime.now())
				.costOfBorrowing(5).build();

		bookReservationRepository.save(reservation);
	}

	@Transactional
	public ResponseEntity<String> returnBook(Integer userId, Long bookId) {
	    try {
	        // Retrieve all reservations for the given user and book
	        List<BookReservation> reservations = bookReservationRepository
	                .findByUserId_UserIdAndBookId_BookId(userId, bookId);

	        if (reservations.isEmpty()) {
	            throw new BookNotFoundException("Book reservation not found for user " + userId + " and book " + bookId);
	        }

	        boolean bookReturned = false;

	        for (BookReservation reservation : reservations) {
	            BookEntity book = reservation.getBookId();
	            if (book == null) {
	                throw new BookNotFoundException("Book not found with id " + bookId);
	            }

	            if (reservation.getStatus() == ReservationStatus.borrowed) {
	                double fine = reservation.calculateFine();

	                reservation.setReturnDate(LocalDateTime.now());
	                reservation.setStatus(ReservationStatus.returned);
	                bookReservationRepository.save(reservation);

	                if (fine > 0) {
	                    UserEntity user = reservation.getUserId();
	                    double userBalance = user.getBalance();
	                    user.setBalance(userBalance - fine);
	                    userEntityRepository.save(user);
	                }

	                book.setAvailable(true);
	                book.setBorrowed(false);
	                bookEntityRepository.save(book);

	                bookReturned = true;
	            }
	        }

	        if (bookReturned) {
	            System.out.println("Book returned successfully");
	            return ResponseEntity.ok("Book returned successfully");
	        } else {
	            throw new BookNotFoundException("Book with id " + bookId + " is not borrowed by user " + userId);
	        }
	    } catch (BookNotFoundException | IllegalStateException e) {
	        // Log the exception and handle it accordingly
	        System.err.println("Error returning book: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    } catch (Exception e) {
	        // Log the exception and handle it accordingly
	        System.err.println("Unexpected error returning book: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}

	public ResponseEntity<?> reserveBook(Integer userId, Long bookId) {
		BookEntity book = bookEntityRepository.findByBookId(bookId)
				.orElseThrow(() -> new BookNotFoundException("Book not found"));

		if (book.isAvailable()) {
			throw new BookNotFoundException("Book is currently available for borrowing");
		}

		if (book.isReserved()) {
			throw new BookNotFoundException("Book is already reserved");
		}

		book.setReserved(true);
		book.setBorrowed(false);
		bookEntityRepository.save(book);

		UserEntity user = userEntityRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found"));

		BookReservation reservation = BookReservation.builder().userId(user).bookId(book)
				.reservationDate(LocalDateTime.now()).status(ReservationStatus.reserved).build();

		bookReservationRepository.save(reservation);

		return ResponseEntity.ok("Book reserved successfully");
	}

	public ResponseEntity<List<BookEntity>> fetchAllBooks() {
		List<BookEntity> books = bookEntityRepository.findAll();
		if (books.isEmpty()) {
			// If no books are found, return a ResponseEntity with NOT FOUND status
			return ResponseEntity.notFound().build();
		} else {
			// If books are found, return them with OK status
			return ResponseEntity.ok(books);
		}
	}

	public BookEntity fetchBookByTitle(String bookTitle) {
		BookEntity book = bookEntityRepository.findByBookTitle(bookTitle);
		if (book == null) {
			throw new BookNotFoundException("Book not found with this title");
		}
		return book;
	}

	public BookEntity fetchBookByAuthorName(String authorName) {
		BookEntity book = bookEntityRepository.findBookByAuthorName(authorName);
		if (book == null) {
			throw new BookNotFoundException("Book not found with this authorName");
		}
		return book;
	}

	public BookEntity fetchBookByCategory(String category) {
		BookEntity book = bookEntityRepository.findBookByCategory(category);
		if (book == null) {
			throw new BookNotFoundException("Book not found with this category");
		}
		return book;
	}

	public void deleteBookById(Long bookId) {
		BookEntity book = bookEntityRepository.findById(bookId).get();
		if (book != null) {
			bookEntityRepository.deleteById(bookId);
		} else {
			throw new BookNotFoundException("Book with that id doesn't exist. Please check and Re-enter!");
		}

	}

	public BookEntity updateBookDetailsById(Long bookId, BookEntity updatedBook) {
		BookEntity existingBook = bookEntityRepository.findByBookId(bookId).get();
		if (existingBook != null) {
			existingBook.setAuthorName(updatedBook.getAuthorName());
			existingBook.setAvailable(updatedBook.isAvailable());
			existingBook.setBookTitle(updatedBook.getBookTitle());
			existingBook.setCategory(updatedBook.getCategory());
			existingBook.setReserved(updatedBook.isReserved());
			return bookEntityRepository.save(existingBook);
		} else {
			throw new BookNotFoundException("Book with that title is not available.");
		}
	}
			
}
