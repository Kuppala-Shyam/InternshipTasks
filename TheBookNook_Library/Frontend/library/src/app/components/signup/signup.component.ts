import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../service/auth.service'; // Import your AuthService
import { User } from '../../user';
import { Subscription } from 'rxjs';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent implements OnInit, OnDestroy {
  signupForm!: FormGroup; // Initialize signupForm as a FormGroup
  errorMessage: string = ''; // Initialize error message variable to store signup errors
  private subscriptions: Subscription[] = []; // Array to hold subscription references

  constructor(private fb: FormBuilder, private authService: AuthService, private router:Router) { }

  ngOnInit(): void {
    // Initialize signupForm with form controls and validators
    this.signupForm = this.fb.group({
      firstName: ['', Validators.required], // First name input with required validator
      lastName: ['', Validators.required], // Last name input with required validator
      email: ['', [Validators.required, Validators.email]], // Email input with required and email validators
      phoneNumber: ['', [Validators.required, Validators.pattern('^[0-9]+$')]], // Phone number input with required validator and numeric pattern validator
      password: ['', [Validators.required, Validators.minLength(2)]], // Password input with required and minimum length validators
      confirmPassword: ['', Validators.required], // Confirm password input with required validator
      balance: ['', Validators.required], // Balance input with required validator
      address: ['', Validators.required], // Address input with required validator
      role: ['', Validators.required], // Role input with required validator
    }, { validators: this.passwordMatchValidator }); // Add passwordMatchValidator to validate password and confirm password

    // Initialize the form with the defined controls and validators
  }

  onSubmit(): void {
    if (this.signupForm.invalid) {
      return; // Form is invalid, do not submit
    }

    // Extract user data from the form
    const user: User = {
      firstName: this.signupForm.value.firstName,
      lastName: this.signupForm.value.lastName,
      email: this.signupForm.value.email,
      phoneNumber: this.signupForm.value.phoneNumber,
      password: this.signupForm.value.password,
      balance: this.signupForm.value.balance,
      address: this.signupForm.value.address,
      role: this.signupForm.value.role
    };

    // Subscribe to the AuthService signup method to perform user registration
    const signupSubscription = this.authService.signup(user)
      .subscribe(response => {
        console.log('Signup successful:', response); // Log successful signup response
        // Handle successful signup (e.g., redirect to login page)
      }, error => {
        console.error('Signup error:', error); // Log signup error
        this.errorMessage = 'Signup failed. Please try again later.'; // Set generic error message
        // Handle specific errors (e.g., email already exists, network issues) based on backend responses
      });

    this.subscriptions.push(signupSubscription); // Add the subscription to the array
  }

  // Custom validator function to check if password and confirm password match
  passwordMatchValidator(group: FormGroup): { [key: string]: any } | null {
    const password = group.get('password')?.value; // Get password value
    const confirmPassword = group.get('confirmPassword')?.value; // Get confirm password value

    return password === confirmPassword ? null : { passwordMismatch: true }; // Return null if passwords match, otherwise return passwordMismatch error
  }

  onClick(){
    this.router.navigate(['/signin'])
  }
  onMove(){
    this.router.navigate([''])
  }

 

  ngOnDestroy(): void {
    // Unsubscribe from all subscriptions to avoid memory leaks
    this.subscriptions.forEach((subscription) => subscription.unsubscribe());
  }
}