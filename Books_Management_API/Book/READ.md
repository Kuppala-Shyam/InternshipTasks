<<<<<<< HEAD
#Book Management API
The Book Management API is a platform that connects different books in online resources with clients who are interested in obtaining specific books.
Building and Running the Application
To build and run the application, follow these steps:



=======
<h1>Book Management API</h1>
<p>The Book Management API is a platform that connects different books in online resources with clients who are interested in obtaining specific books.</p>
<h2>Building and Running the Application</h2>
<p>To build and run the application, follow these steps:</p>
<p>1.Ensure you have Maven, Java, and MySQL installed on your system.</p>
<p>2.Clone the repository to your local machine:</p>
<code>git clone <a href="https://github.com/Kuppala-Shyam/InternshipTasks.git">https://github.com/Kuppala-Shyam/InternshipTasks.git</a></code>
<p>3.Navigate to the project directory:</p>
<code>cd BookManagementAPI</code>
<p>4.Configure your MySQL database connection in the application.properties file:</p>
<code>spring.datasource.url=jdbc:mysql://localhost:3306/book?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root
</code>
<p>5.Build the application using Maven:</p>
<code>mvn clean package</code>
<p>6.Run the application:</p>
<code>java -jar target/BookManagementAPI-0.0.1-SNAPSHOT.jar</code>
<h2>API Documentation</h2>
<p>This API provides endpoints to manage books. Below are the available endpoints:</p>
<ul>
  <li>POST /savebook: Add a new book to the system.<a href="http://localhost:8080/savebook">http://localhost:8080/savebook</a> </li>
   <li>GET /fetchAllBooks: Retrieve a list of all books.<a href ="http://localhost:8080/fetchAllBooks">http://localhost:8080/fetchAllBooks</a> </li>
   <li>GET /retrieveBookById/{id}: Retrieve details of a book by ID.<a href="http://localhost:8080/retrieveBookById/1">http://localhost:8080/retrieveBookById/1</a> </li>
   <li>GET /retrieveBookByName/{bookTitle}: Retrieve details of a book by title.
     <a href="http://localhost:8080/retrieveBookByName/Thermodynamics">http://localhost:8080/retrieveBookByName/Thermodynamics</a></li>
   <li>PUT /updateBookDetailsByBookTitle/{bookTitle}: Update details of a book by title.<a href ="http://localhost:8080/updateBookDetailsByBookTitle/Thermodynamics">http://localhost:8080/updateBookDetailsByBookTitle/Thermodynamics</a></li>
   <li>DELETE /deleteBookByBookTitle/{bookTitle}: Delete a book by title.<a href ="http://localhost:8080/deleteBookByBookTitle/Thermodynamics">http://localhost:8080/deleteBookByBookTitle/Thermodynamics</a></li>
   <li>DELETE /deleteBookByBookId/{bookId}: Delete a book by ID.<a href ="http://localhost:8080/deleteBookByBookId/1">http://localhost:8080/deleteBookByBookId/1</a></li>
</ul>
<h2>Testing the Application</h2>
<p>You can test the application using the provided controller and service tests. These tests cover various scenarios to ensure the proper functioning of the API</p>
<h2>Swagger Documentation</h2>
<p>The API documentation is available using Swagger. After running the application, you can access the Swagger UI at <a href ="http://localhost:8080/swagger-ui.html">http://localhost:8080/swagger-ui.html</a> to interactively explore the endpoints and test them.</p>
<h2>Dependencies</h2>
<ul>
  <li>Spring Boot:<p>Utilizes Spring Boot framework for rapid application development and dependency management.</p> </li>
   <li>Spring Data JPA:<p> Implements data access layer using Spring Data JPA for easy interaction with the database.</p></li>
   <li>Spring Web:<p> Provides features for building web applications, including handling HTTP requests, managing web views, and supporting RESTful APIs.</p></li>
   <li>Springfox Swagger 2:<p>Integrates Swagger for API documentation and exploration.</p></li>
   <li>MySQL Connector:<p>Uses MySQL database for storing and managing book data.</p></li>
   <li>Lombok <p>Enhances code readability and conciseness using Lombok annotations.</p></li>
   <li>ModelMapper: <p>Utilizes ModelMapper for DTO to entity mapping.</p></li>
</ul>
<h2>Contributing</h2>
<p>Contributions are welcome! If you have any suggestions, bug reports, or feature requests, please open an issue or submit a pull request.</p>
<h2>License</h2>
<p>This project is licensed under the MIT License. See the <a href="LICENSE ">LICENSE </a> file for details.</p>
<h2>Authors</h2>
<h4>K.Venkata Shyam</h4>
<h2>Acknowledgments</h2>
<p>Special thanks to the Spring Boot and Swagger communities for their excellent documentation and support.</p>
>>>>>>> e38ec89b5b5cf067afc54e1ba1765e129bdc9c32
