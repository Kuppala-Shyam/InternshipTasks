# 📚 THE BOOK NOOK 📖

This project is a The Book Nook implemented using Angular for the frontend and Spring Boot for the backend. It provides functionalities for users to browse, search, borrow, and return books. The system also includes user authentication and authorization mechanisms.

## Features

### Frontend (Angular)

- View a list of available books.
- Search for books by title, author, or category.
- Display detailed information about each book.
- Register and log in to the system.
- Borrow and return books (authentication required for borrowing).

### Backend (Spring Boot)

- Developed a RESTful API to handle CRUD operations for books.
- Implemented user authentication and authorization.
- Provided endpoints for book search based on title, author, or category.
- Managed book borrowing and returning, ensuring authentication for these actions.

### Database Integration

- Utilized a relational database like MySQL to store book and user data.
- Designed appropriate database schemas for books, users, and book reservation.

### Additional Features 

- Implemented user roles (e.g., admin, regular user) with different permissions.
- Allow users to reserve books that are currently unavailable.
- Implemented a fine system for overdue books.

## Setup Instructions

1. Clone the repository to your local machine:
      git clone https://github.com/Kuppala-Shyam/InternshipTasks.git
2. Set up the backend by navigating to the `backend` directory and running `mvn spring-boot:run`.
3. Set up the frontend by navigating to the `frontend` directory and running `npm install` followed by `ng serve`.
4. Access the application at `http://localhost:4200` in your browser.

## Technologies Used

- Angular 17
- Spring Boot
- MySQL
- Maven (for backend dependency management)
- npm (for frontend dependency management)

## Testing the Application
You can test the application using the provided controller and service tests. These tests cover various scenarios to ensure the proper functioning of the API
## Contributing
Contributions to the project are welcome! If you have any ideas for improvements or new features, feel free to open an issue or submit a pull request.

## License
This project is licensed under the MIT License.
## Authors
-K.Venkata Shyam
## Acknowledgments
- Special thanks to the Spring Boot and Angular communities for their excellent documentation and support.
- Special thanks to Jeev Lifeworks for giving this project for me.

