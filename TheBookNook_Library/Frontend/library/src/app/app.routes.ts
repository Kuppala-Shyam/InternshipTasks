import { Routes } from '@angular/router';
import { HomepageComponent } from './components/homepage/homepage.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { BooksPageComponent } from './components/books-page/books-page.component';
import { AddBookComponent } from './components/add-book/add-book.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { BookReservationsComponent } from './components/book-reservation/book-reservation.component';

export const routes: Routes = [
  { path: '', component: HomepageComponent }, // Homepage as default route
  { path: 'signin', component: LoginComponent }, // Login page
  { path: 'signup', component: SignupComponent }, // signup page
  { path: 'bookpage', component: BooksPageComponent }, // book page
  { path: 'addBook', component: AddBookComponent }, // to add book
  { path: 'user-details', component: UserDetailsComponent },
  {path: 'book-reservation', component:BookReservationsComponent}
];
