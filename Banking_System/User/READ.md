# Banking System Application Using Spring Boot

## Overview
This project is a Spring Boot-based Banking System that provides functionalities for user registration, authentication, and various banking operations such as account management and transactions. The system is secured using JWT (JSON Web Tokens) authentication.

## Features
- User Registration
- User Authentication
- Account Management
- Transactions
- Swagger Documentation

## Components

### User Module
- User Entity
- User Service
- User Controller
- User Repository
### Account Module
- Account Entity
- Account Service
- Account Controller
- Account Repository
### Transaction Module
- Transaction Entity
- Transaction Service
- Transaction Controller
- Transaction Repository
### Security Configuration
- AuthConfig
- Auth Error
- Security Config
- Jwt Authentication Entry Point
- Swagger Config
-Simple Cors Filter
###Filter
- Jwt Authentication Filter
### Exception Handling
- Account Not Found Exception
- Error Message
- Insufficient Amount Exception
- Rest Response Entity
- User Not Found Exception
 ### Util
- Jwt Util
### Models
- Account Details
- Authentication Request
- Authentication Response
- Singup
- Transaction Request
- User Model
## Building and Running the Application
- To build and run the application, follow these steps:
- 1.Ensure you have Maven, Java, and MySQL installed on your system.
- 2.Clone the repository to your local machine:
      git clone https://github.com/Kuppala-Shyam/InternshipTasks.git
- 3.Navigate to the project directory:
    cd Banking_System
- 4.Configure your MySQL database connection in the application.properties file:
   spring.datasource.url=jdbc:mysql://localhost:3306/register?createDatabaseIfNotExist=true
   spring.datasource.username=root
   spring.datasource.password=root
-5.Build the application using Maven:
    mvn clean package
- 6.Run the application:
    java -jar target/Account-service-0.0.1-SNAPSHOT.jar
## API Documentation
### The API endpoints for User service
- Post/sign-up: Allows user to register with hi details.
- Post/login: Allow user to get authenticated with his correct credentials
- Get/fetchUserByEmail : Allows user to get his details based on his eamil
- Put/updateUserDetailsByEmail : Allow user to update his details using his email
- Delete/deleteUserdetails : Allow user to delete his details from the page.
### The API endpoints for Account service
- Post/account/createAccount : Allows to create the account
- Get/account/viewAccountDetails/{name} : Allow user to retriev his bank account details by using name after creating account
- Get/account/viewBalance/{accountNumber} : Alloe user to retriev his balance or amount in his account with account number
- Put/account/updateAccountDetails/{accountNumber} : Allow user to update his details that are provided during account creation time.
- Delete/account/deleteAccount/{accountNumber}: Allow User to delete hi account
### The API endpoints for Transcation service
-Post/transcation/transferMoney : Allow user to transfer moaney from their own account to another account number.
## OpenAPI Documentation
 Access the OpenAPI documentation at: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
## Testing the Application
You can test the application using the provided controller and service tests. These tests cover various scenarios to ensure the proper functioning of the API
## Contributing
Contributions to the project are welcome! If you have any ideas for improvements or new features, feel free to open an issue or submit a pull request.

## License
This project is licensed under the MIT License.
## Authors
-K.Venkata Shyam
## Acknowledgments
- Special thanks to the Spring Boot and Swagger communities for their excellent documentation and support.
- Special thanks to Jeev Lifeworks for giving this project for me.
