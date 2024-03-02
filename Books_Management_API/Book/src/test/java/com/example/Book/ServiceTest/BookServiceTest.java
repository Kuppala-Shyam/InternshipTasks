package com.example.Book.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.Book.Entity.Book;
import com.example.Book.Exception.BookNotFoundException;
import com.example.Book.Repository.BookRepository;
import com.example.Book.Service.BookService;



@SpringBootTest
public class BookServiceTest {

	@Autowired
	private BookService bookService;

	@MockBean
	private BookRepository bookRepository;

	private Book book;
	private Book book1;

	@BeforeEach
	void setUp() {
		book = Book.builder().bookId(1).bookTitle("Strength Of Materials").authorName("R.K.Bansal").price(150.50)
				.publisherOfBook("Timoshenko").ratings(4.0).build();
		book1 = Book.builder().bookId(3).bookTitle("Refrigeration And Air Conditioning").authorName("R.S.Khurmi")
				.price(100.50).publisherOfBook("S.Chand").ratings(4.4).build();

	}

	@Test
	public void testSaveBook_Success() {
		Mockito.when(bookRepository.save(book)).thenReturn(book);
		Book savedBook = bookService.saveBook(book);
		assertEquals(book, savedBook);
		Mockito.verify(bookRepository).save(book);

	}

	@Test
	public void testFetchAllBooks_EmptyList() {
		Mockito.when(bookRepository.findAll()).thenReturn(new ArrayList<>());
		RuntimeException exception = assertThrows(BookNotFoundException.class, () -> bookService.fetchAllBooks());
		assertEquals("No books available", exception.getMessage());
	}

	@Test
	public void testFetchAllBooks_NotEmptyList() {
		List<Book> bookList = new ArrayList<>();
		bookList.add(book);
		bookList.add(book1);
		Mockito.when(bookRepository.findAll()).thenReturn(bookList);
		List<Book> fetchedBooks = bookService.fetchAllBooks();
		assertEquals(bookList, fetchedBooks);
	}

	@Test
	public void testFetchBookById_Success() {
		int id = 1;
		Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(book));
		Optional<Book> fetchedBook = bookService.fetchBookById(id);
		assertTrue(fetchedBook.isPresent());
		assertEquals(book, fetchedBook.get());
	}

	@Test
	public void testFetchBookById_NotFound() {
		int id = 3;
		Mockito.when(bookRepository.findById(id)).thenReturn(Optional.empty());
		RuntimeException exception = assertThrows(BookNotFoundException.class, () -> bookService.fetchBookById(id));
		assertEquals("Book with that ID is not available.", exception.getMessage());
	}

	@Test
	public void testFetchBookByTitle_Success() {
		String bookTitle = "Strength Of Materials";
		Mockito.when(bookRepository.findByBookTitle(bookTitle)).thenReturn(Optional.of(book));
		Optional<Book> fetchedBook = bookService.fetchByBookTitle(bookTitle);
		assertTrue(fetchedBook.isPresent());
		assertEquals(book, fetchedBook.get());
	}

	@Test
	public void testUpdateBookPriceAndRatings_ExistingBook() {
		String bookTitle = "Strength Of Materials";
		Book existingBook = new Book();
		existingBook.setPrice(100.0);
		existingBook.setRatings(4.0);


		Book updateBook = new Book();
		updateBook.setBookId(existingBook.getBookId()); 
		updateBook.setBookTitle(existingBook.getBookTitle()); 
		updateBook.setAuthorName(existingBook.getAuthorName()); 
		updateBook.setPrice(200.0);
		updateBook.setRatings(4.5);

		Mockito.when(bookRepository.findByBookTitle(bookTitle)).thenReturn(Optional.of(existingBook));
		Mockito.when(bookRepository.save(existingBook)).thenReturn(existingBook); 
																					
		Book updatedBook = bookService.updateBookDetails(bookTitle, updateBook);
		assertEquals(updateBook.getPrice(), updatedBook.getPrice());
		assertEquals(updateBook.getRatings(), updatedBook.getRatings());
		Mockito.verify(bookRepository).save(existingBook);
	}

	@Test
	public void testUpdateBookPriceAndRatings_NonExistingBook() {
		String bookTitle = "Non Existing Book";
		Book updateBook = new Book();
		updateBook.setPrice(200.0);
		updateBook.setRatings(4.5);
		Mockito.when(bookRepository.findByBookTitle(bookTitle)).thenReturn(Optional.empty());

		RuntimeException exception = assertThrows(BookNotFoundException.class,
				() -> bookService.updateBookDetails(bookTitle, updateBook));
		assertEquals("Book with that title is not available.", exception.getMessage());
	}

	@Test
	@Transactional
	public void testDeleteBookByBookTitle_ExistingBook() {
		String bookTitle = "Strength Of Materials";
		Mockito.when(bookRepository.findByBookTitle(bookTitle)).thenReturn(Optional.of(new Book()));

		bookService.deleteBookByBookTitle(bookTitle);

		Mockito.verify(bookRepository).deleteByBookTitle(bookTitle);
	}

	@Test
	@Transactional
	public void testDeleteBookByBookTitle_NonExistingBook() {
		String bookTitle = "Non Existing Book";
		Mockito.when(bookRepository.findByBookTitle(bookTitle)).thenReturn(Optional.empty());

		assertThrows(BookNotFoundException.class, () -> bookService.deleteBookByBookTitle(bookTitle));
	}

	@Test
	@Transactional
	public void testDeleteBookByBookId_ExistingBook() {
		int bookId = 1;
		Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(new Book()));

		bookService.deleteBookByBookId(bookId);

		Mockito.verify(bookRepository).deleteById(bookId);
	}

	@Test
	@Transactional
	public void testDeleteBookByBookId_NonExistingBook() {
		int bookId = 1;
		Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

		assertThrows(BookNotFoundException.class, () -> bookService.deleteBookByBookId(bookId));
	}

}
