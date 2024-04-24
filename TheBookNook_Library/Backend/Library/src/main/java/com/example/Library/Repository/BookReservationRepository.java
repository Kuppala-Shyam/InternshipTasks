package com.example.Library.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Library.Entity.BookReservation;
@Repository
public interface BookReservationRepository extends JpaRepository<BookReservation, Long> {

	

	List<BookReservation> findByUserId_UserIdAndBookId_BookId(Integer userId, Long bookId);

	

}
