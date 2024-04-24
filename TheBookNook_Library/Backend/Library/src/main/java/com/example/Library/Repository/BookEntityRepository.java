package com.example.Library.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Library.Entity.BookEntity;

@Repository
public interface BookEntityRepository extends JpaRepository<BookEntity, Long>{


	BookEntity findByBookTitle(String bookTitle);

	BookEntity findBookByAuthorName(String authorName);

	BookEntity findBookByCategory(String category);

	Optional<BookEntity> findByBookId(Long bookId);

	Optional<BookEntity> findByBookId(BookEntity bookId);

		

}
