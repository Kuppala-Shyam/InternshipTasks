package com.example.Book.ControllerTest;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;
import com.example.Book.Controller.BookController;
import com.example.Book.Entity.Book;
import com.example.Book.Exception.BookNotFoundException;
import com.example.Book.Service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(BookController.class)
public class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private BookService bookService;

	private Book book;
	private List<Book> booksList;

	@BeforeEach
	void setUp() {
		book = Book.builder().bookId(1).bookTitle("Strength Of Materials").authorName("R.K.Bansal").price(150.50)
				.publisherOfBook("Timoshenko").ratings(4.0).build();
		booksList = new ArrayList<>();
		booksList.add(book);
	}

	@Test
    public void testSaveBook() throws Exception {
        when(bookService.saveBook(book)).thenReturn(book);
        mockMvc.perform(MockMvcRequestBuilders.post("/savebook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(book)));
    }

	@Test
    public void testFetchAllBooks() throws Exception {
        when(bookService.fetchAllBooks()).thenReturn(booksList);
        mockMvc.perform(MockMvcRequestBuilders.get("/fetchAllBooks"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(booksList)));
    }

	// Test case for fetchBookById with existing book
	@Test
    public void testFetchBookById_ExistingBook() throws Exception {
        when(bookService.fetchBookById(book.getBookId())).thenReturn(Optional.of(book));
        mockMvc.perform(MockMvcRequestBuilders.get("/retrieveBookById/{id}", book.getBookId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(book)));
    }

	@Test
    public void testFetchByBookTitle_ExistingBook() throws Exception {
        when(bookService.fetchByBookTitle(book.getBookTitle())).thenReturn(Optional.of(book));
        mockMvc.perform(MockMvcRequestBuilders.get("/retrieveBookByName/{bookTitle}", book.getBookTitle()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(book)));
    }

	@Test
	public void testUpdateBookDetails_NonexistentBook() throws Exception {
	    Book updatedBook = Book.builder()
	            .bookId(1) // Nonexistent book ID
	            .bookTitle("Updated Book Title")
	            .authorName(book.getAuthorName())
	            .price(book.getPrice())
	            .publisherOfBook(book.getPublisherOfBook())
	            .ratings(book.getRatings())
	            .build();

	    when(bookService.updateBookDetails(updatedBook.getBookTitle(), updatedBook))
	            .thenThrow(new BookNotFoundException("Book with that title is not available."));

	    mockMvc.perform(MockMvcRequestBuilders.put("/updateBookDetailsByBookTitle/{bookTitle}", updatedBook.getBookTitle())
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(updatedBook)))
	            .andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	@Test
	public void testUpdateBookDetails_ExistingBook() throws Exception {
		Book updatedBook = Book.builder().bookId(book.getBookId()).bookTitle("Updated Book Title")
				.authorName(book.getAuthorName()).price(book.getPrice()).publisherOfBook(book.getPublisherOfBook())
				.ratings(book.getRatings()).build();

		when(bookService.updateBookDetails(book.getBookTitle(), updatedBook)).thenReturn(updatedBook);
		mockMvc.perform(MockMvcRequestBuilders.put("/updateBookDetailsByBookTitle/{bookTitle}", book.getBookTitle())
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(updatedBook)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(updatedBook)));
	}

	@Test
	public void testDeleteBookByBookTitle_ExistingBook() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteBookByBookTitle/{bookTitle}", book.getBookTitle()))
				.andExpect(MockMvcResultMatchers.status().isOk())
				// Verify response message
				.andExpect(MockMvcResultMatchers.content().string("Book delete successfully by using book tilte")); 																										// content
	}

	@Test
    public void testDeleteBookByBookId_ExistingBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/deleteBookByBookId/{bookId}", book.getBookId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
             // Verify response message content
                .andExpect(MockMvcResultMatchers.content().string("Book delete successfully by using book id")); 

  


	}
   
}
