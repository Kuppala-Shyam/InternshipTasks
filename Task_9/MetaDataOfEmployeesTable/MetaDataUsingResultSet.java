package jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MetaDataUsingResultSet {
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
			System.out.println("Register the driver properly by giving it's name properly.");
			//e.printStackTrace();
		}
		try {
			// Establish connection from java to database
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:" + portNumber + "/"
					+ schemaName + "?user=" + user_name + "&password=" + password);
			//table name exists in schema
			String tableName;
			// checking for  table name until it is correct
			while (true) {
				System.out.println("Enter the table name");
				tableName = scanner.next();
				if(isValidTableName(connection, tableName, schemaName)) {
					break;
				}
				else {
					System.out.println("Invalid table name ! Please Enter the valid table name");
				}
			}
			//SQL query for display all the records present in the table
			String sqlQuery = "SELECT * FROM " + tableName;
			// creating statement to transfer the data from java application to database
			Statement statement = connection.createStatement();
			// executing the query and storing the data in ResultSet object
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			//to get the meta data from ResultSet using getMetaData() and storing that in ResultMetaData object
			ResultSetMetaData metaDataInf = resultSet.getMetaData();
			//counting number of columns present in the table 
			int columnCount = metaDataInf.getColumnCount();
			System.out.println("Total columns in the " + tableName + " is :" + columnCount);
			System.out.println("Details of columns are loading......");
			//displays meta data present in the table
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaDataInf.getColumnName(i);
				String columnTypeName = metaDataInf.getColumnTypeName(i);
				int columnIsNullOrNot = metaDataInf.isNullable(i);
				System.out.println("Column number :" + i);
				System.out.println("Column name : " + columnName);
				System.out.println("Column type name :" + columnTypeName);
				System.out.println("Null value check :"+ columnIsNullOrNot);
				//checking for null values and provides information of it
				if(columnIsNullOrNot == 1) {
					System.out.println("It will allow null values which means you leave the column as empty..");
				}
				if(columnIsNullOrNot == 0) {
					System.out.println("It won't allow the null values that means the column must not be empty..");
				}
				System.out.println();
			}
			//closing the ResultSet
			resultSet.next();
			// closing the statement
			statement.close();
			// closing the connection
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("Please provide correct credentials like schema name, user_name, password. ");
			//e.printStackTrace();
		}

	}

	private static boolean isValidTableName(Connection connection, String tableName, String schemaName) {
		try{
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SHOW TABLES LIKE '" +tableName+ "'");
			return resultSet.next(); 
		}catch (SQLException e) {
			if (e.getErrorCode() == 1146) { // Table doesn't exist
				System.out.println("Error: Table 'employees' does not exist in the schema '" + schemaName + "'.");
			}
		}
		return false;
	}

}
