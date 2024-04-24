package com.example.Library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Library.Entity.BookReservation;
import com.example.Library.Repository.BookReservationRepository;

@Service
public class BookResevationService {
	@Autowired
	private BookReservationRepository bookReservationRepository;
	
	public ResponseEntity<List<BookReservation>> getReservationStatus() {
		
		List<BookReservation> bookReservation = bookReservationRepository.findAll();
		if(bookReservation.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			// If books are found, return them with OK status
			return ResponseEntity.ok(bookReservation);
		}
	}

}
