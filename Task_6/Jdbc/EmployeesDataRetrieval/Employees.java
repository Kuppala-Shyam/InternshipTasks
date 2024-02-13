package jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Employees {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		
		System.out.println("Login to mysql database by giving suitable fields ");
		String schemaName ;
		String user_name ;
		String password ;
		int portNumber;
		// Validate port number input
        while (true) {
            try {
                System.out.println("Please provide database port number (numerical value):");
                if (!scan.hasNextInt()) {
                    System.out.println("Invalid input: Please enter a valid port number.");
                    // Clear invalid input from the buffer
                    scan.next();
                    continue; // Retry port number input
                } else {
                    portNumber = scan.nextInt();
                    break; // Valid input received, proceed
                }
            } catch (Exception e) {
                System.out.println("An unexpected error occurred. Please try again.");
                // Additional error handling could be added here
            }
        }
        // User_name validation
        while (true) {
            System.out.println("Enter username (string):");
            if (scan.hasNext()) {
                user_name = scan.next();
                // Add your specific validation rules here (e.g., length, allowed characters)
                if (user_name.length()>=4) {
                    break;
                } else {
                    // Provide informative error message
                    System.out.println("Invalid username. Please try again.");
                }
            } else {
                scan.next(); // Clear invalid input
            }
        }

        // Password validation
        while (true) {
            System.out.println("Enter password (string with at least 4 characters):");
            if (scan.hasNext()) {
                password = scan.next();
                // Add your specific validation rules here (e.g., minimum length, character types)
                if (password.length()>=4) {
                    break;
                } else {
                    // Provide informative error message and consider masking the input
                    System.out.println("Invalid password. Please try again.");
                }
            } else {
                scan.next(); // Clear invalid input
            }
        }

       // user input for schema name 
        System.out.println("Enter the schema name (case-insensetive) :");
        schemaName = scan.next();
       
		try {
			// Register the driver or load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				// Establish connection
				Connection connection = DriverManager.getConnection(
						"jdbc:mysql://localhost:"+portNumber+"/"+schemaName+"?user=" + user_name + "&password=" + password);
				// Creating statement
				Statement statement = connection.createStatement();
				// Here we are store the employee data in resultSet object
				ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
				System.out.println("Data present inside the table is as follows :");
				// iterating object to get the data in it
				while (resultSet.next()) {
					// to access id
					int id = resultSet.getInt(1);
					// to access name
					String name = resultSet.getString("name");
					// to access email
					String email = resultSet.getString("email");
					// to access department
					String departmet = resultSet.getString("department");
					// to access hire_date
					String hire_date = resultSet.getString("hire_date");
					// printing the data in the console
					System.out.println("id :" + id + "," + "name :" + name + "," + "email :" + email + ", departmet :"
							+ departmet + "," + "hire_date :" + hire_date);

				}
				// closing statement flow with database
				statement.close();
				// closing the connection database
				connection.close();
			} catch (SQLException e) {
				System.out.println(
						"Please check the localhost number  or user_name or password you provide may be wrong");
//				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {

			System.out.println("Your not registering the databse driver proper. Please check it and make it correct");
//			e.printStackTrace();
		}
	}

}
