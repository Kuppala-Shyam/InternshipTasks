import { Component, OnInit } from '@angular/core';
import { BookService } from '../../service/book.service';
import { Book } from '../../book';
import { CommonModule } from '@angular/common';
import { User } from '../../user';
import { AuthService } from '../../service/auth.service';
import { HttpErrorResponse } from '@angular/common/http';
import { UserComponent } from '../user/user.component';
import { ProfileComponent } from '../profile/profile.component';
import { Router } from '@angular/router';
import { MatIcon } from '@angular/material/icon';

@Component({
  selector: 'app-books-page',
  standalone: true,
  imports: [CommonModule, UserComponent, ProfileComponent, MatIcon],
  templateUrl: './books-page.component.html',
  styleUrls: ['./books-page.component.css'],
})
export class BooksPageComponent implements OnInit {
  books: Book[] = [];
  userId: any | undefined;
  currentUser: User | undefined;
  showProfileDetails: boolean = false;
  booksfinal: any = [];
  user: User | undefined;

  role: any;
  constructor(
    private bookService: BookService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Check if the user is logged in and fetch their details
    const token = this.authService.getToken();
    if (token) {
      try {
        const decodedToken = this.authService.decodeToken(token);
        this.userId = decodedToken.userId;
        this.role = decodedToken.role;
        this.fetchCurrentUser();
      } catch (error) {
        alert('An error occurred while decoding token. Please try again.');
      }
    }
    // Fetch all books when the component initializes
    this.getAllBooks();
  }

  // Logout user and navigate to the home page
  onLogout(): void {
    this.authService.logout();
    this.router.navigate(['']);
  }

  // Fetch all books from the book service
  getAllBooks(): void {
    this.bookService.getAllBooks().subscribe(
      (data: Book[]) => {
        this.books = data;
        this.booksfinal = data;
        console.log('books', this.books);
      },
      (error) => {
        console.error('Error fetching books:', error);
      }
    );
  }

  // Borrow a book
  borrowBook(userId: number, bookId: any): void {
    // Check if the user is logged in
    if (!userId) {
      alert('User not logged in. Please login to borrow books.');
      return;
    }
    // Call the book service to borrow the book
    this.bookService.borrowBook(userId, bookId).subscribe(
      (response: any) => {
        if (
          typeof response === 'string' &&
          response.toLowerCase().includes('successfully')
        ) {
          alert(response); // Display success message
        } else {
          // Handle unexpected response format
          alert(
            'An unexpected response was received while borrowing the book.'
          );
          console.error('Unexpected response while borrowing book:', response);
        }
        // Update book availability if necessary
        this.updateBookAvailability(bookId, false);
      },
      (error: HttpErrorResponse) => {
        if (error.status === 400) {
          alert('Book is already borrowed by someone.');
        } else {
          alert('An unexpected error occurred while borrowing the book.');
          console.error('Error borrowing book:', error);
        }
      }
    );
  }

  // Return a borrowed book
  returnBook(userId: number, bookId: any): void {
    // Check if the user is logged in
    if (!userId) {
      alert('User not logged in. Please login to return books.');
      return;
    }
    // Call the book service to return the book
    this.bookService.returnBook(userId, bookId).subscribe(
      (response: any) => {
        if (
          typeof response === 'string' &&
          response.toLowerCase().includes('successfully')
        ) {
          alert(response); // Display success message
        } else {
          // Handle unexpected response format
          alert(
            'An unexpected response was received while returning the book.'
          );
          console.error('Unexpected response while returning book:', response);
        }
        // Update book availability if necessary
        this.books.forEach((x) => {
          if (x.bookId == bookId) {
            x.borrowed = false;
            this.updateBookAvailability(bookId, true);
          }
        });
      },
      (error) => {
        alert('Book returned successfully');
        console.error('Error returning book:', error);
        // Handle error and update UI if necessary
        this.books.forEach((x) => {
          if (x.bookId == bookId) {
            x.borrowed = false;
            this.updateBookAvailability(bookId, true);
          }
        });
      }
    );
  }

  // Reserve a book
  reserveBook(userId: number, bookId: any): void {
    // Check if the user is logged in
    if (!userId) {
      alert('User not logged in. Please login to reserve books.');
      return;
    }
    // Call the book service to reserve the book
    this.bookService.reserveBook(userId, bookId).subscribe(
      (response: any) => {
        if (
          typeof response === 'string' &&
          response.toLowerCase().includes('successfully')
        ) {
          alert(response); // Display success message
        } else {
          // Handle unexpected response format
          alert(
            'An unexpected response was received while reserving the book.'
          );
          console.error('Unexpected response while reserving book:', response);
        }
        // Update book availability if necessary
        this.updateBookAvailability(bookId, false);
      },
      (error: HttpErrorResponse) => {
        if (error.status === 400) {
          alert('Book is already reserved by someone.');
        } else {
          alert('An unexpected error occurred while reserving the book.');
          console.error('Unexpected error during reserving book:', error);
        }
      }
    );
  }
  // Fetch books by category
  fetchBookByCategory(category: string): void {
    this.bookService.fetchBookByCategory(category).subscribe(
      (response: any) => {
        if (Array.isArray(response)) {
          this.books = response as Book[];
        } else {
          alert('Unexpected response format for fetching books by category.');
          console.error(
            'Unexpected response format for fetching books by category:',
            response
          );
        }
      },
      (error: HttpErrorResponse) => {
        alert('Error fetching books by category. Please try again later.');
        console.error('Error fetching books by category:', error);
      }
    );
  }

  // Fetch books by author name
  fetchBookByAuthorName(authorName: string): void {
    this.bookService.fetchBookByAuthorName(authorName).subscribe(
      (response: any) => {
        if (Array.isArray(response)) {
          this.books = response as Book[];
        } else {
          alert(
            'Unexpected response format for fetching books by author name.'
          );
          console.error(
            'Unexpected response format for fetching books by author name:',
            response
          );
        }
      },
      (error: HttpErrorResponse) => {
        alert('Error fetching books by author name. Please try again later.');
        console.error('Error fetching books by author name:', error);
      }
    );
  }

  // Search for books by title, category, or author
  searchBooks(event: any): void {
    const searchTerm = event.target.value.trim();
    console.log('hii');
    console.log(searchTerm);
    if (searchTerm === '') {
      // this.getAllBooks();

      this.booksfinal = this.books;
      // return;
    } else {
      this.booksfinal = this.books.filter((x) => {
        return (
          x.bookTitle
            .toLocaleLowerCase()
            .includes(searchTerm.toLocaleLowerCase()) ||
          x.category
            .toLocaleLowerCase()
            .includes(searchTerm.toLocaleLowerCase()) ||
          x.authorName
            .toLocaleLowerCase()
            .includes(searchTerm.toLocaleLowerCase())
        );
      });
    }

    // if (searchTerm.startsWith('category:')) {
    //   const category = searchTerm.replace('category:', '').trim();
    //   this.fetchBookByCategory(category);
    // } else if (searchTerm.startsWith('author:')) {
    //   const authorName = searchTerm.replace('author:', '').trim();
    //   this.fetchBookByAuthorName(authorName);
    // } else {
    //   // Search books by title
    //   this.bookService.fetchBookByTitle(searchTerm).subscribe(
    //     (response: any) => {
    //       if (Array.isArray(response)) {
    //         this.books = response as Book[];
    //       } else {
    //         alert('Unexpected response format for searching books by title.');
    //         console.error(
    //           'Unexpected response format for searching books by title:',
    //           response
    //         );
    //       }
    //     },
    //     (error: HttpErrorResponse) => {
    //       alert('Error searching books. Please try again later.');
    //       console.error('Error searching books:', error);
    //     }
    //   );
    // }
  }

  // Delete a book
  deleteBook(bookId: number): void {
    if (confirm('Are you sure you want to delete this book?')) {
      this.bookService.deleteBook(bookId).subscribe(
        (response: string) => {
          alert('Book deleted successfully.');
          console.log(response);
          this.removeBookFromList(bookId); // Call new method to remove from UI
        },
        (error) => {
          alert('An unexpected error occurred while deleting the book.');
          console.error('Error deleting book:', error);
        }
      );
    }
  }

  removeBookFromList(bookId: number) {
    this.books = this.books.filter((book) => book.bookId !== bookId);
  }

  // Update a book
  updateBook(bookId: number, updatedBook: Book) {
    this.bookService.updateBook(bookId, updatedBook).subscribe(
      (book: Book) => {
        console.log(book);
      },
      (error) => {
        alert('An unexpected error occurred while updating the book.');
        console.error('Error updating book:', error);
      }
    );
  }

  // Update book availability
  updateBookAvailability(bookId: number, available: boolean) {
    this.books.forEach((book, index) => {
      if (book.bookId === bookId) {
        this.books[index].available = available;
      }
    });
  }

  // Fetch current user details
  fetchCurrentUser(): void {
    this.authService.getUserDetails().subscribe(
      (user: User) => {
        this.currentUser = user;
      },
      (error) => {
        alert('An unexpected error occurred while fetching current user.');
        console.error('Error fetching current user:', error);
      }
    );
  }

  // Toggle profile details visibility
  toggleProfileDetails(): void {
    this.showProfileDetails = !this.showProfileDetails;
    console.log(this.showProfileDetails);
  }
}
