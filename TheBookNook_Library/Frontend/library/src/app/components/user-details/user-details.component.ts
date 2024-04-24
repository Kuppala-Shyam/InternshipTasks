import { Component, Input, OnInit } from '@angular/core';
import { User } from '../../user';
import { AuthService } from '../../service/auth.service';
import { UserService } from '../../service/user.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './user-details.component.html',
  styleUrl: './user-details.component.css',
})
export class UserDetailsComponent implements OnInit {
  @Input() users: User[] = [];
  allUsers: User[] = []; // Initialize array to hold all users
  constructor(
    private router: Router,
    private authService: AuthService,
    private userService: UserService
  ) {}

  ngOnInit() {
    // Call the method to fetch all users data
    this.getAllUsersData();
  }

  getAllUsersData(): void {
    if (this.authService.getUserRole() === 'ADMIN') {
      this.userService.getAllUsers().subscribe(
        (usersData) => {
          console.log('All users data:', usersData);
          this.allUsers = usersData;
        },
        (error) => {
          console.error('Error fetching users data:', error);
        }
      );
    }
  }
}
