package InternPack;

import java.util.Scanner;

public class Calculator {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan =new Scanner(System.in);
		System.out.println("Enter the first number");
		int firtNumber=scan.nextInt();
		System.out.println("Enter the second number");
		int secondNumber=scan.nextInt();
		System.out.println("Enter the select preffered operation you want i.e +, -, *, /");
		String operation=scan.next();
		scan.close();
		double result=operation(firtNumber, secondNumber,operation);
		System.out.println(result);
		
		

	}
	public static double operation(int n1, int n2, String operation) {
		int result=0;
		switch(operation) {
		case "+":{
			result=n1+n2;
			break;
		}
		case "-":{
			result=n1-n2;
			break;
		}
		case "*":{
			result=n1*n2;
			break;
		}
		case "/":{
			result=n1/n2;
			break;
		}
		default:{
			System.out.println("please enter correct value");
		}
	}
		
		return result;
		
	}

}
