import { Book } from "./book";
import { ReservationStatus } from "./reservation-status";
import { User } from "./user";
// Interface for representing a book reservation
export interface BookReservation {
  reserveId?: number; // Optional reservation ID
  userId: User; // User who made the reservation
  bookId: Book; // Book being reserved
  reservationDate: Date; // Date when the reservation was made
  status: ReservationStatus; // Status of the reservation (e.g., pending, borrowed, returned)
  borrowDate?: Date; // Date when the book was borrowed
  returnDate?: Date; // Date when the book is returned
  costOfBorrowing?: number; // Cost associated with borrowing the book
  fine?: number; // Fine imposed for late return of the book
  }
  