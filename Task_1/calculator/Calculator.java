package InternPack;

import java.util.Scanner;

public class Calculator {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
			String firstNumberStr = getValidatedNumber("Enter first number: ");
			char operator = getValidatedOperator();
			String secondNumberStr = getValidatedNumber("Enter second number: ");

			Number result = null; // Initialize result to null

			try {
				result = performOperation(firstNumberStr, secondNumberStr, operator);
			} catch (NumberFormatException e) {
				System.out.println("Enter the valid Number.");
				continue;
			}

			if (result != null) {
				System.out.println("Result: " + result);
			}

			if (!askForContinuation()) {
				break;
			}
		}
	}	
	
	// to check whether the entered number is valid or not
	private static String getValidatedNumber(String prompt) {
		while (true) {
			System.out.println(prompt);
			String numberStr = scanner.next();

			if (numberStr.matches("^\\d+(?:\\.\\d+)?$")) {
				return numberStr;
			} else {
				System.out.println("Invalid input. Please enter a valid number.");
			}
		}
	}
	// method to check valid operator or not
	private static char getValidatedOperator() {
		while (true) {
			System.out.println("Enter operator (+, -, *, /): ");
			char operator = scanner.next().charAt(0);
			scanner.nextLine(); // Consume newline

			if ("+-*/".indexOf(operator) != -1) {
				return operator;
			} else {
				System.out.println("Invalid operator. Please use +, -, *, or /.");
			}
		}
	}
	// to perform operations
	private static Number performOperation(String firstNumberStr, String secondNumberStr, char operator) {
		Number num1 = parseNumber(firstNumberStr);
		Number num2 = parseNumber(secondNumberStr);

		if (num1 == null || num2 == null) {
			return null; // Indicate invalid input
		}

		switch (operator) {
		case '+':
			return add(num1, num2);
		case '-':
			return subtract(num1, num2);
		case '*':
			return multiply(num1, num2);
		case '/':
			if (num2.doubleValue() == 0) {
				System.out.println("Division by zero is not possible!");
				return null;
			}
			return divide(num1, num2);
		default:
			System.out.println("Invalid operator!");
			return null;
		}
	}
	// method to check whether number is double or integer type
	private static Number parseNumber(String numberStr) {
		try {
			if (numberStr.contains(".")) {
				return Double.parseDouble(numberStr);
			} else {
				return Integer.parseInt(numberStr);
			}
		} catch (Exception e) {
			System.out.println("Invalid input format detected. Please check your input.");
			return null;
		}
	}
	// to continue the using of calculator
	private static boolean askForContinuation() {
		System.out.println("Continue? (y/n)");
		String answer = scanner.next().toLowerCase();
		return answer.equalsIgnoreCase("y");
	}

	// Helper methods for each operation with integer checks
	private static Number add(Number num1, Number num2) {
		if (num1 instanceof Integer && num2 instanceof Integer) {
			return num1.intValue() + num2.intValue();
		} else {
			return num1.doubleValue() + num2.doubleValue();
		}
	}

	private static Number subtract(Number num1, Number num2) {
		if (num1 instanceof Integer && num2 instanceof Integer) {
			return num1.intValue() - num2.intValue();
		} else {
			return num1.doubleValue() - num2.doubleValue();
		}
	}

	private static Number multiply(Number num1, Number num2) {
		if (num1 instanceof Integer && num2 instanceof Integer) {
			return num1.intValue() * num2.intValue();
		} else {
			return num1.doubleValue() * num2.doubleValue();
		}
	}

	private static Number divide(Number num1, Number num2) {
		if (num1 instanceof Integer && num2 instanceof Integer) {
			// Check for integer division overflow
			if (Math.abs(num1.longValue()) > Integer.MAX_VALUE / Math.abs(num2.longValue())) {
				return num1.doubleValue() / num2.doubleValue(); // Use double for overflow
			}
			return num1.intValue() / num2.intValue();
		} else {
			return num1.doubleValue() / num2.doubleValue();
		}
	}
}
