package InternPack;

import java.util.Scanner;

public class FibonacciSeries {
static Scanner scan= new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("Enter the first number ");
		int firstNumber=scan.nextInt();
		System.out.println("Enter how many numbers you required starting from first number");
		int specifiedDigits=scan.nextInt();
		for(int i=firstNumber;i<specifiedDigits;i++) {
			System.out.println(fibonacci(i)+" ");
		}
	}
	public static int fibonacci (int n) {
		if(n<=1) {
			return n;
		}
		return fibonacci(n-1)+fibonacci(n-2);
	}

}
