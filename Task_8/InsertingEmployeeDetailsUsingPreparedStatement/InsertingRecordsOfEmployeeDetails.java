package jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertingRecordsOfEmployeeDetails {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Login to mysql database by giving suitable fields ");
		String schemaName;
		String user_name;
		String password;
		int portNumber;

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
		System.out.println("Enter the schema name");
		schemaName = scanner.next();
		scanner.nextLine();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
			System.out.println("There is an issue with registering database driver.Please check the port number");
		}
		try {
			// Establish connection from java to database
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:" + portNumber + "/"
					+ schemaName + "?user=" + user_name + "&password=" + password);
			String sqlQuery = "INSERT INTO employees (id,name,email,salary,department,hire_date,age) VALUES(?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			try {
				// Validate table name
				String tableName;
				while (true) {
					System.out.print("Enter table name: ");
					tableName = scanner.nextLine();

					if (isValidTableName(connection, tableName, schemaName)) {
						break; // Valid table found, exit loop
					} else {
						System.out.println("Invalid table name. Please enter a valid table name.");
					}
				}
				System.out.println("Enter the employee details......");
				boolean continuosAddtionOfDetails = true;
				while (continuosAddtionOfDetails) {
					System.out.print("Enter employee ID (greater than 0): ");
					int id;
					while (true) {
						id = scanner.nextInt();
						if (id <= 0) {
							System.out.println("Invalid ID. Please enter a positive integer value for ID.");
							System.out.print("Enter employee ID (greater than 0): ");
						} else {
							if (isIdExists(connection, id)) {
								System.out.println("ID already exists. Please enter a unique ID.");
								System.out.print("Enter employee ID (greater than 0): ");
							} else {
								break;
							}
						}
					}

					// Now you can proceed with entering other details of employees
					scanner.nextLine();
					System.out.println("Enter employee name: ");
					String name = scanner.nextLine();
					System.out.println("Enter employee email: ");
					String email = scanner.next();
					System.out.println("Enter employee salary: ");
					int salary = scanner.nextInt();
					System.out.println("Enter employee department: ");
					String department = scanner.next();
					System.out.println("Enter employee hire_date: ");
					String hire_date = scanner.next();
					System.out.println("Enter employee age: ");
					int age = scanner.nextInt();
					preparedStatement.setInt(1, id);
					preparedStatement.setString(2, name);
					preparedStatement.setString(3, email);
					preparedStatement.setInt(4, salary);
					preparedStatement.setString(5, department);
					preparedStatement.setString(6, hire_date);
					preparedStatement.setInt(7, age);

					int effectedRows = preparedStatement.executeUpdate();
					if (effectedRows > 0) {
						System.out.println("Employee inserted successfully into \"" + tableName + "\" table!");
					} else {
						System.out.println("Error in inserting the employee details:");
					}
					// Ask if the user wants to add another employee
					System.out.print("Do you want to add another employee? (y/n): ");
					continuosAddtionOfDetails = scanner.next().equalsIgnoreCase("y");
				}
			} finally {
				preparedStatement.close();
				connection.close();

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(
					"There is an issue with database connection. Please enter the correct schema name or user_name or password.");
		}
	}

	private static boolean isValidTableName(Connection conn, String tableName, String schemaName) {
		// Check if table exists using SHOW TABLES
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE '" + tableName + "'");
			return rs.next();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1146) { // Table doesn't exist
				System.out.println("Error: Table 'employees' does not exist in the schema '" + schemaName + "'.");
			}
		}
		return false;
	}

	private static boolean isIdExists(Connection connection, int id) {
		try (Statement stmt = connection.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM employees WHERE id = " + id);
			return rs.next();
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("The employee id you have entered is already exits ! Please try with another id.");
		}
		return false;
	}

}
