package com.example.Library.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Library.Entity.BookReservation;
import com.example.Library.service.BookResevationService;
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RestController
public class BookResevationController {
	@Autowired
	private BookResevationService bookResevationService;
	
	@GetMapping("/fetchAllBookReservations")
	public ResponseEntity<?> getReservationStatus() {
		ResponseEntity<List<BookReservation>> bookReservation = bookResevationService.getReservationStatus();
		try {
			return ResponseEntity.ok(bookReservation);
		}catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
}
