package com.example.Library.Entity;

import java.time.Duration;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookReservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reserveId;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userId;
	@ManyToOne
	@JoinColumn(name="book_id")
	private BookEntity bookId;
	private LocalDateTime reservationDate;
	@Enumerated(EnumType.STRING)
    private ReservationStatus status;
	private LocalDateTime borrowDate;
	private LocalDateTime returnDate;
    private double costOfBorrowing;
    private double fine;

	 // Add methods to calculate cost and fines
	 // Method to calculate cost dynamically
    public double calculateCost() {
        // Check if the book has been borrowed and returned
        if (borrowDate != null && returnDate != null) {
            // Calculate the duration between borrowDate and returnDate
            long daysBorrowed = Duration.between(borrowDate, returnDate).toDays();
            // Calculate the cost based on the duration
            // Example: $2 per day
            return 2 * daysBorrowed;
        }
        return 0; // Default cost if not borrowed or returned
    }

    // Method to calculate fine dynamically
    public double calculateFine() {
        // Check if the book has been borrowed and returned
        if (returnDate != null) {
            // Calculate the duration between reservationDate and returnDate
            long daysLate = Duration.between(reservationDate.plusDays(7), returnDate).toDays();
            // Calculate the fine for late submission
            // Example: $1 per day late
            return Math.max(0, 1 * daysLate); // Fine should not be negative
        }
        return 0; // Default fine if not returned
    }
    // Method to update the cost dynamically
    public void updateCost() {
        this.costOfBorrowing = calculateCost();
    }

    // Method to update the fine dynamically
    public void updateFine() {
        this.fine = calculateFine();
    }
  }

