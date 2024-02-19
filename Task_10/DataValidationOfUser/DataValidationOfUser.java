package jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DataValidationOfUser {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		String schemaName;
		String user_name;
		String password;
		int portNumber;
		System.out.println("Please provide the below details in order to get you required ascepts.");
		// Validate port number input
		while (true) {
			try {
				System.out.println("Please provide database port number (numerical value):");
				if (!scanner.hasNextInt()) {
					System.out.println("Invalid input: Please enter a valid port number.");
					// Clear invalid input from the buffer
					scanner.next();
					continue; // Retry port number input
				} else {
					portNumber = scanner.nextInt();
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
			if (scanner.hasNext()) {
				user_name = scanner.next();
				// Add your specific validation rules here (e.g., length, allowed characters)
				if (user_name.length() >= 4) {

					break;
				} else {
					// Provide informative error message
					System.out.println("Invalid username. Please try again.");
				}
			} else {
				scanner.next(); // Clear invalid input
			}
		}
		// Password validation
		while (true) {
			System.out.println("Enter password (string with at least 4 characters):");
			if (scanner.hasNext()) {
				password = scanner.next();
				// Add your specific validation rules here (e.g., minimum length, character
				// types)
				if (password.length() >= 4) {
					break;
				} else {
					// Provide informative error message and consider masking the input
					System.out.println("Invalid password. Please try again.");
				}
			} else {
				scanner.next(); // Clear invalid input
			}
		}

		// user input for schema name
		System.out.println("Enter the schema name (case-insensetive) :");
		schemaName = scanner.next();

		try {
			// Register the driver or load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("There is issue in loading or registering the mysql driver");
			// e.printStackTrace();
		}
		try {
			// Establish connection from java to database
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:" + portNumber + "/"
					+ schemaName + "?user=" + user_name + "&password=" + password);
			// table name exists in schema
			String tableName;
			// checking for table name until it is correct
			while (true) {
				System.out.println("Enter the table name");
				tableName = scanner.next();
				if (isValidTableName(connection, tableName, schemaName)) {
					break;
				} else {
					System.out.println("Invalid table name ! Please Enter the valid table name");
				}
			}
			// taking values of employee from user
			System.out.println("Enter the employee id :");
			int id = scanner.nextInt();
			System.out.println("Enter the employee name :");
			String name = scanner.next();
			System.out.println("Enter the employee email :");
			String email = scanner.next();
			System.out.println("Enter the employee department :");
			String department = scanner.next();
			System.out.println("Enter the employee salary :");
			double salary = scanner.nextDouble();
			System.out.println("Enter the employee hire_date :");
			String hire_date = scanner.next();
			System.out.println("Enter the age of employee :");
			int age = scanner.nextInt();
			// checking if there are any empty fields present
			if (id <= 0 || name.isEmpty() || department.isEmpty() || salary <= 0.0 || hire_date.isEmpty()
					|| age <= 18) {
				System.out.println("Invalid input !Please provide the details of employee");
			}

			// Proceed with getting other user inputs and executing SQL operation

			String sqlQuery = "INSERT INTO "+tableName+" (id,name,email, department,salary,hire_date,age)VALUES(?,?,?,?,?,?,?) "
					+ "ON DUPLICATE KEY UPDATE id=VALUES(id), name=VALUES(name), email=VALUES(email), department=VALUES(department), salary=VALUES(salary), hire_date=VALUES(hire_date),age=VALUES(age)  ";
	        // Check if the provided ID exists in the database
            boolean idExists = isIdExists(connection, id);
            // if employee already existed the we have to update his details or not
			if (idExists) {
				System.out.println("Employee with ID " + id + " already exists in the database.");
				System.out.println("Do you want to update the existing data? (Y/N)");
				String choice = scanner.next().toUpperCase(); // Convert user input to uppercase
				// user choice y then details will update
				if (choice.equals("Y")) {
					System.out.println("Updating existing data...");
					dataInsertion(connection, id, name, email, department, salary, hire_date, age, sqlQuery);
				} else {
					System.out.println("No changes will be made.");
					// Optionally, you can exit the program or take other actions
				}
			} else {
				// The code for inserting new data remains the same
				System.out.println(
						"Employee with ID " + id + " does not exist in the database. Proceeding to insert new data...");
				dataInsertion(connection, id, name, email, department, salary, hire_date, age, sqlQuery);
			}

		} catch (SQLException e) {
			System.out.println("There is an issue with connection.Please enter the valid user_name or password or schema name.");
			//e.printStackTrace();
		}

	}
	//Method to check whether the entered table name present or not
	private static boolean isValidTableName(Connection conn, String tableName, String schemaName) {
		// Check if table exists using SHOW TABLES
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE '" + tableName + "'");
			// it return boolean value
			return rs.next();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1146) { // Table doesn't exist
				System.out.println("Error: Table 'employees' does not exist in the schema '" + schemaName + "'.");
			}
		}
		return false;
	}
	//Method to check if the entered id already existed or not
	private static boolean isIdExists(Connection conn, int id) {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT id FROM employees WHERE id = ?")) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			return rs.next(); // If next() returns true, it means the ID exists
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // Return false in case of any error
		}
	}
	
	// Method to insert or update the data of employee
	private static void dataInsertion(Connection connection, int id, String name, String email, String department, double salary, String hire_date, int age, String sqlQuery ) {

		try (PreparedStatement updateStatement = connection.prepareStatement(sqlQuery)) {
			updateStatement.setInt(1, id);
			updateStatement.setString(2, name);
			updateStatement.setString(3, email);
			updateStatement.setString(4, department);
			updateStatement.setDouble(5, salary);
			updateStatement.setString(6, hire_date);
			updateStatement.setInt(7, age);

			// Execute the update query
			int updatedRows = updateStatement.executeUpdate();

			if (updatedRows > 0) {
				System.out.println("Employee data updated or inserted successfully.");
			} else {
				System.out.println("Failed to update/insert employee data.");
			}
		} catch (SQLException e) {
			System.out.println("Enter the employee fields that must be matched with fieldset of your database.");
			e.printStackTrace();
		}
	}

}
