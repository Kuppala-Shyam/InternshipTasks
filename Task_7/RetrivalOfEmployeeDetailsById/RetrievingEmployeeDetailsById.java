package jdbc1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class RetrievingEmployeeDetailsById {
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
			try {
				//validating the correct table
				String tableNameInSchema = "";
				boolean validTableName = false;
				while (!validTableName) {
					// Prompt for table name and offer exit option
					System.out.println("Enter the table name (or 'exit' to quit):");
					String user_choice = scanner.next();

					tableNameInSchema = user_choice;
					//if user want to exit from the program then he may use exit
					if (user_choice.equalsIgnoreCase("exit")) {
						System.out.println("Exit the program");
						break;
					}
					//SQl query to fetch the details of employees by using Id
					String sqlQuery = "SELECT * FROM " + tableNameInSchema + " where id=?";
					System.out.println("Enter the Id of employee in oreder to fetch his details.");
					int EmployeeId;
					//validating the employee id
					while (true) {
						if (!scanner.hasNextInt()) {
							System.out.println("Invalid input: Please enter a valid integer for the employee ID.");
							scanner.next();
							continue;
						} else {
							EmployeeId = scanner.nextInt();
							break;
						}
					}
					//creating PreparedStatement to pre-compile the SQL query and to accept the values dynamically 
					PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
					//Using setter method setInt() we have provided the respective column number and column name
					preparedStatement.setInt(1, EmployeeId);
					try {
						//ResultSet Object stores the data of employee after executing the executeQuery() 
						ResultSet employeeData = preparedStatement.executeQuery();
						// moving the cursor to the next row it is present it will return true
						if (employeeData.next()) {
							System.out.println("Employee Details fetching by id ....");
							//printing employee id
							System.out.println("Employee Id :" + employeeData.getInt("id"));
							//printing employee name
							System.out.println("Employee name :" + employeeData.getString("name"));
							//printing employee email
							System.out.println("Employee email :" + employeeData.getString("email"));
							//printing employee department
							System.out.println("Employee department :" + employeeData.getString("department"));
							// printing employee salary
							System.out.println("Employee salary :" + employeeData.getString("salary"));
							//printing employee hire_date
							System.out.println("Employee hire-date :" + employeeData.getString("hire_date"));
						} else {
							System.out.println("Employee details not found. Because the entered id does not exits ");
						}
						validTableName = true;
						break;
					} catch (SQLException e) {
						if (e.getErrorCode() == 1146) { // Table doesn't exist
							System.out.println(
									"Error: Table 'employees' does not exist in the schema '" + schemaName + "'.");
						} else {
							System.out.println("Database error: " + e.getMessage());
						}
					}
				}
				//closing the connection
				connection.close();
			} catch (SQLException e) {
				System.out.println(
						"Please enter the correct port number or user_name or password or schema name. Because the entered credentials does not match.");
			}
		} catch (SQLException e) {
			System.out.println("There is an issue with connection. So  connection to databse is failed.");
//			e.printStackTrace();
		}

	}

}
