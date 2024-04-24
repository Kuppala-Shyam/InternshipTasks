import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../service/auth.service';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { CommonModule } from '@angular/common';
import { Token } from '@angular/compiler';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit, OnDestroy {
  loginForm: FormGroup; // Form group for login form
  errorMessage: string = ''; // Error message to display if login fails
  private subscriptions: Subscription[] = []; // Array to store subscription objects

  constructor(private authService: AuthService, private fb: FormBuilder, private router: Router) {
    // Initialize the login form with email and password fields
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(2)]],
    });
  }

  ngOnInit(): void {
    // Lifecycle hook called after component initialization
  }

  // Method to handle form submission
  onSubmit(): void {
    // Check if the form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    // Destructure email and password from the form value
    const { email, password } = this.loginForm.value;

    // Attempt to login
    const loginSubscription = this.authService
      .login({ email, password })
      .subscribe(
        (response) => {
          // If login is successful
          console.log('Login successful');
          // Set the JWT token in local storage
          this.authService.setToken(response.token);
          // Get the user role from the token and display it
          const userRole = this.authService.getUserRole();
          console.log('User role: ' + userRole);
          // Update the login status in the AuthService
          this.authService.isLoggedInSubject.next(true);
          // Set login status in local storage
          localStorage.setItem('login', 'true');
          // Navigate to the bookpage component
          this.router.navigate(['/bookpage']);
        },
        (error) => {
          // If login fails
          if (error.status === 403) {
            // Handle invalid email or password error
            this.errorMessage = 'Invalid email or password';
            console.error(this.errorMessage);
          } else {
            // Handle other errors
            this.errorMessage = 'An error occurred. Please try again later.';
            console.error(this.errorMessage);
          }
        }
      );

    // Push the login subscription to the subscriptions array
    this.subscriptions.push(loginSubscription);
  }

  onClick(){
    this.router.navigate(['/signup'])
  }
  onMove(){
    this.router.navigate([''])
  }

  ngOnDestroy(): void {
    // Unsubscribe from all subscriptions when the component is destroyed
    this.subscriptions.forEach((subscription) => subscription.unsubscribe());
  }
}
