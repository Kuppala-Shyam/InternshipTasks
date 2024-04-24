import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, numberAttribute } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../user';
import { Token } from '@angular/compiler';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseUrl = 'http://localhost:8080'; // Base URL of the backend API

  // BehaviorSubject to track the login status
  public isLoggedInSubject = new BehaviorSubject<boolean>(false);
  isLoggedIn$ = this.isLoggedInSubject.asObservable();

  constructor(private http: HttpClient) {}

  // Method to register a new user
  signup(user: User): Observable<any> {
    return this.http.post(`${this.baseUrl}/api/v1/auth/signup`, user);
  }

  // Method to log in a user
  login(credentials: { email: string; password: string }): Observable<any> {
    return this.http.post(`${this.baseUrl}/api/v1/auth/signin`, credentials);
  }

  // Method to set the JWT token in localStorage and update the login status
  setToken(token: string) {
    localStorage.setItem('token', token); // Store token in localStorage
    const decodedToken = this.decodeToken(token); // Decode token to extract user role
    localStorage.setItem('userRole', decodedToken.role); // Store user role in localStorage
    this.isLoggedInSubject.next(true); // Update isLoggedInSubject to indicate user is logged in
  }

  // Method to get the JWT token from localStorage
  getToken(): string {
    return localStorage.getItem('token') || '';
  }

  // Method to get the user role from localStorage
  getUserRole(): string {
    return localStorage.getItem('userRole') || '';
  }

  // Method to extract the current user's ID from the JWT token
  getCurrentUserId(): string {
    const token = this.getToken();
    const decodedToken = this.decodeToken(token);
    return decodedToken.userId;
  }

  // Method to fetch the details of the current user from the backend
  getUserDetails(): Observable<User> {
    const userId = this.getCurrentUserId();
    const token = this.getToken();
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`, // Add authorization header with JWT token
    });
    return this.http.get<User>(`${this.baseUrl}/user/fetchUserById/${userId}`, {
      headers,
    });
  }

  // Method to decode the JWT token and extract the payload
  public decodeToken(token: string): any {
    const tokenPayload = token.split('.')[1]; // Extract payload part of JWT token
    const decodedPayload = JSON.parse(atob(tokenPayload)); // Decode payload from base64 format
    return decodedPayload; // Return decoded payload
  }

  // Method to log out the user by clearing localStorage and updating login status
  logout(){
    localStorage.clear(); // Clear localStorage
    this.isLoggedInSubject.next(false); // Update isLoggedInSubject to indicate user is logged out
  }
}