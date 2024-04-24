package com.example.Library.Entity;
/**
 * Enum representing the status of a book reservation.
 * Possible values are 'borrowed', 'reserved', and 'returned'.
 */
public enum ReservationStatus {
    borrowed, // The book has been borrowed by a user
    reserved, // The book has been reserved by a user
    returned; // The book has been returned by a user
}