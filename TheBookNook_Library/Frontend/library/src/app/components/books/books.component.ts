import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../service/auth.service';
import { Book } from '../../book';
import { BookService } from '../../service/book.service';

@Component({
  selector: 'app-books',
  standalone: true,
  imports: [],
  templateUrl: './books.component.html',
  styleUrl: './books.component.css',
})
export class BooksComponent implements OnInit {
  books: Book[] = [];

  constructor(
    private bookService: BookService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    // Check if the user is logged in
    if (!this.authService.isLoggedIn$) {
      // Handle not logged in scenario, for example redirect to login page
      // Alternatively, display a message to prompt the user to log in
      return;
    }

    // Fetch all books from the book service
    this.bookService.getAllBooks().subscribe((data) => {
      // Assign the fetched books to the local books array
      this.books = data;
    });
  }
}
