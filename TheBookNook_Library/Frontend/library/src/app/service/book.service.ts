import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';
import { Book } from '../book';

@Injectable({
  providedIn: 'root',
})
export class BookService {
  private baseUrl = 'http://localhost:8080/book'; // Base URL for book-related API endpoints
  private headers = new HttpHeaders({
    Authorization: `Bearer ${this.authService.getToken()}`,
  }); // HTTP headers including authorization token

  constructor(private http: HttpClient, private authService: AuthService) {}

  // Method to add a new book to the database
  addBook(newBook: Book): Observable<Book> {
    return this.http.post<Book>(this.baseUrl + '/saveBook', newBook, {
      headers: this.headers,
    });
  }

  // Method to fetch all books from the database
  getAllBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(this.baseUrl + '/fetchAllBooks', {
      headers: this.headers,
    });
  }

  // Method to borrow a book by a user
  borrowBook(userId: number, bookId: number): Observable<string> {
    return this.http.post<string>(
      `${this.baseUrl}/${userId}/borrow/${bookId}`,
      null,
      { headers: this.headers }
    );
  }

  // Method to return a borrowed book by a user
  returnBook(userId: number, bookId: number): Observable<string> {
    return this.http.post<string>(
      `${this.baseUrl}/${userId}/return/${bookId}`,
      null,
      { headers: this.headers }
    );
  }

  // Method to reserve a book by a user
  reserveBook(userId: number, bookId: number): Observable<string> {
    return this.http.post<string>(
      `${this.baseUrl}/${userId}/reserve/${bookId}`,
      null,
      { headers: this.headers }
    );
  }

  // Method to fetch a book by its title
  fetchBookByTitle(bookTitle: string): Observable<Book> {
    return this.http.get<Book>(
      `${this.baseUrl}/fetchBookByTitle/${bookTitle}`,
      { headers: this.headers }
    );
  }

  // Method to fetch books by author name
  fetchBookByAuthorName(authorName: string): Observable<Book> {
    return this.http.get<Book>(
      `${this.baseUrl}/fetchBookByAuthorName/${authorName}`,
      { headers: this.headers }
    );
  }

  // Method to fetch books by category
  fetchBookByCategory(category: string): Observable<Book> {
    return this.http.get<Book>(
      `${this.baseUrl}/fetchBookByCategory/${category}`,
      { headers: this.headers }
    );
  }

  // Method to delete a book by its ID
  deleteBook(bookId: number): Observable<string> {
    return this.http.delete<string>(`${this.baseUrl}/deleteBook/${bookId}`, {
      headers: this.headers,
    });
  }

  // Method to update a book's details
  updateBook(bookId: number, updatedBook: Book): Observable<Book> {
    return this.http.put<Book>(
      `${this.baseUrl}/updateBook/${bookId}`,
      updatedBook,
      { headers: this.headers }
    );
  }
}
