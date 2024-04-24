import { Component, OnInit, OnDestroy } from '@angular/core';
import { User } from '../../user';
import { AuthService } from '../../service/auth.service';
import { Subscription } from 'rxjs';
import { BookService } from '../../service/book.service';
import { CommonModule } from '@angular/common';
import { Book } from '../../book';
import {
  HttpClient,
  HttpHeaderResponse,
  HttpHeaders,
} from '@angular/common/http';
import { UserService } from '../../service/user.service';
import { BookReservation } from '../../book-reservation';
import { BookReservationService } from '../../service/book-reservation.service';
import { AddBookComponent } from '../add-book/add-book.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [CommonModule, AddBookComponent],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css',
})
export class UserComponent implements OnInit, OnDestroy {
  user: User | null = null; // Initialize user object
  allUsers: User[] = []; // Initialize array to hold all users
  bookId: number | undefined; // Initialize bookId variable
  userId: number | undefined; // Initialize userId variable
  showUserDetails = false; // Initialize flag to show user details
  showAddBookForm = false; // Initialize flag to show add book form
  private subscriptions: Subscription[] = []; // Array to hold subscriptions
  private headers = new HttpHeaders({
    Authorization: `Bearer ${this.authService.getToken()}`,
  }); // Initialize HttpHeaders for authorization

  constructor(
    private authService: AuthService,
    private bookService: BookService,
    private http: HttpClient,
    private userService: UserService,
    private bookReservationService: BookReservationService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Subscribe to get user details
    this.subscriptions.push(
      this.authService.getUserDetails().subscribe((userData) => {
        this.user = userData; // Set user data
        this.userId = userData.userId; // Set user ID
      })
    );

    // Subscribe to check if user is logged in
    this.subscriptions.push(
      this.authService.isLoggedIn$.subscribe((isLoggedIn) => {
        if (isLoggedIn) {
          // If user is logged in, get user details
          this.subscriptions.push(
            this.authService.getUserDetails().subscribe((userData) => {
              this.user = userData; // Set user data
            })
          );
        } else {
          this.user = null; // Set user data to null if not logged in
        }
      })
    );
  }

  ngOnDestroy(): void {
    // Unsubscribe from all subscriptions to prevent memory leaks
    this.subscriptions.forEach((subscription) => subscription.unsubscribe());
  }

  // Method to get logged in username
  getLoggedInUsername(): string {
    return `${this.user?.firstName || ''} ${this.user?.lastName || ''}`;
  }

  // Method to return a book
  returnBook(userId: any, bookId: any): void {
    if (!userId) {
      // If user is not logged in, show alert
      console.error('User not logged in. Please login to borrow books.');
      return;
    }
    if (!bookId) {
      // If book ID is not provided, show alert
      console.error('No book ID provided. Please specify a book to return.');
      return;
    }

    // Attempt to return the book
    this.bookService.returnBook(userId, bookId).subscribe(
      (response: string) => {
        console.log(response); // Show success message
      },
      (error) => {
        console.error('Error returning book: ' + error.message); // Show error message if any
      }
    );
  }

  // Method to fetch all users' data
  getAllUsersData(): void {
    this.router.navigate(['/user-details']);
  }

  // Method to handle click event for fetching book reservations
  onClick() {
    // Navigate to the book reservation component
    this.router.navigate(['/book-reservation']);
  }

  // Method to navigate to add book page
  addBook(): void {
    this.router.navigate(['/addBook']); // Navigate to add book page
  }
}
