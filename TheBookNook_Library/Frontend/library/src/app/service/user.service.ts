import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';
import { User } from '../user';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private baseUrl = 'http://localhost:8080/user'; // Base URL for user-related API endpoints
  private headers = new HttpHeaders({
    Authorization: `Bearer ${this.authService.getToken()}`,
  }); // HTTP headers including authorization token
  constructor(private authService: AuthService, private http: HttpClient) {}

  // Method to fetch all users from the database
  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.baseUrl + '/fetchAllUsers', {
      headers: this.headers,
    });
  }
}
