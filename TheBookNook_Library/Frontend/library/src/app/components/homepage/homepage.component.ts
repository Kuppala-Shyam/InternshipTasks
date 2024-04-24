import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../service/auth.service';
import {MatIconModule} from '@angular/material/icon';

@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [CommonModule,MatIconModule],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css'
})
export class HomepageComponent implements OnInit {
  login = false; // Flag to track the login status

  constructor(private router: Router, private authService: AuthService) {}

  ngOnInit(): void {
    // Check if the user is already logged in using local storage
    if (localStorage.getItem('login') == 'true') {
      this.login = true;
    } else {
      // Subscribe to the isLoggedInSubject observable from AuthService
      this.authService.isLoggedInSubject.subscribe((res) => {
        // Update the login status when the observable emits a value
        this.login = res;
      });
    }
  }

  // Navigate to the signin page
  onLoginClick(): void {
    this.router.navigate(['/signin']);
  }

  // Navigate to the signup page
  onSignupClick(): void {
    this.router.navigate(['/signup']);
  }
}