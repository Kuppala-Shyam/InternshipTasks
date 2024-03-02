package com.example.Book.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Book.Entity.Book;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	
	Optional<Book> findByBookTitle(String bookTitle);

	void deleteByBookTitle(String bookTitle);
	
}
