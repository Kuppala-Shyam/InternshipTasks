import { Component, OnInit } from '@angular/core';
import { BookReservation } from '../../book-reservation';
import { BookReservationService } from '../../service/book-reservation.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-book-reservation',
  standalone: true, // Assuming this is a custom attribute, not a standard Angular property
  imports: [CommonModule], // Importing CommonModule for ngFor directive
  templateUrl: './book-reservation.component.html',
  styleUrl: './book-reservation.component.css',
})
export class BookReservationsComponent implements OnInit {
  reservations: BookReservation[] = [];
  displayedColumns: string[] = [
    'reserveId',
    'userId',
    'bookId',
    'reservationDate',
    'status',
    'borrowDate',
    'returnDate',
    'costOfBorrowing',
    'fine',
  ];

  constructor(private bookReservationService: BookReservationService) {}

  ngOnInit() {
    // Fetch all reservations when component initializes
    this.bookReservationService
      .getAllReservations()
      .subscribe((reservations) => {
        this.reservations = reservations; // Assuming 'reservations' is an array
      });
  }
}
