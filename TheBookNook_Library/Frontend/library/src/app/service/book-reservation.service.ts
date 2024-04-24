import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BookReservation } from '../book-reservation';
import { Observable, take, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class BookReservationService {
  constructor(private http: HttpClient) {}

  private baseUrl = 'http://localhost:8080/'; // Base URL of the backend API

  // Method to fetch all book reservations from the backend
  getAllReservations(): Observable<BookReservation[]> {
    // Set authorization header with JWT token from localStorage
    const headers = new HttpHeaders().set(
      'Authorization',
      `Bearer ${localStorage.getItem('token')}`
    );

    // Send GET request to fetch all book reservations
    return this.http
      .get<BookReservation[]>(this.baseUrl + 'fetchAllBookReservations', {
        headers,
      })
      .pipe(
        tap((reservations: any) => console.log(reservations)) // Log the data here
      );
  }
}
