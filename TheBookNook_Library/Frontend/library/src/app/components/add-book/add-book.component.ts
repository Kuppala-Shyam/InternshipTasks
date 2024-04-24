import { Component, EventEmitter, Output } from '@angular/core';
import { Book } from '../../book';
import { BookService } from '../../service/book.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-book',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './add-book.component.html',
  styleUrl: './add-book.component.css',
})
export class AddBookComponent {
  newBook: Book = {
    bookTitle: '',
    authorName: '',
    category: '',
    available: true,
    reserved: false,
    borrowed: false,
  };
  @Output() onBookAdded: EventEmitter<Book> = new EventEmitter<Book>();

  constructor(private bookService: BookService, private router: Router) {}

  /**
   * Adds a new book.
   */
  addBook(): void {
    // Call the addBook method from the BookService
    this.bookService.addBook(this.newBook).subscribe(
      // If successful, emit the added book and reset the form
      (book: Book) => {
        this.onBookAdded.emit(book);
        this.newBook = {
          bookTitle: '',
          authorName: '',
          category: '',
          available: true,
          reserved: false,
          borrowed: false,
        };
        // Navigate back to the bookpage component after adding the book
        this.router.navigate(['/bookpage']);
      },
      // If there's an error, display an alert and log the error
      (error) => {
        console.error(
          'An unexpected error occurred while adding the book. Please try again later.',
          error
        );
      }
    );
  }
}
