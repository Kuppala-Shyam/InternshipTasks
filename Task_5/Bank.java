package InternPack;

import java.util.HashMap;
import java.util.Scanner;

public class Bank {
	static Scanner scan = new Scanner(System.in);
	static HashMap<String, Account> accounts = new HashMap<String, Account>();
	
	public static void main(String[] args) {
		  putAccount("Ac10523", new Account("Shyam", "Ac10523", 1000.0, "Vij", "Vijayawada", "01/09/2010"));
		  putAccount("Ac56527", new Account("Tej","Ac56527",5000.0,"Hyd","Hyderabad","02/02/2005"));
		

		System.out.println("hello");
		boolean exit = false;
		while(!exit) {
			System.out.println("1.Create account");
			System.out.println("2.Deposit");
			System.out.println("3.Withdraw");
			System.out.println("4.check your balance");
			System.out.println("5.Exit from this");
			System.out.println("Enter your choice :");
			int choose = scan.nextInt();
			switch(choose) {
				case 1:{
					createAccount();
				}
				case 2:{
					deposit();
					
					break;
				}
				case 3:{
					withdraw();
					
					
				}
				case 4:{
					inquiryBalance();
					
					
				}
				case 5 :{
					scan.close();
					exit =true;
					
					break;
				}
				default :{
					System.out.println("Invalid choice.Please select the numbers from 1 to 5");
					scan.close();
					break;
				}
			}
			
		}
		
	}
	public static void putAccount(String accountNumber, Account account) {
	        accounts.put(accountNumber, account);
	}
	public static void createAccount() {
		System.out.println("Enter the account holder name");
		String accountHolderName = scan.next();
		System.out.println("Enter the account number");
		String accountNumber = scan.next();
		 while (accounts.containsKey(accountNumber)) {
	            System.out.println("Account number already exists. Please enter a unique number:");
	            accountNumber = scan.next();
	        }
		System.out.println("Enter the address of account holder");
		String address = scan.next();
		System.out.println("Enter the date of birth of account holder");
		String dateOfBirth = scan.next();
		System.out.println("Enter the branch of bank i.e where th bank is located");
		String branch = scan.next();
		Account account = new Account(accountHolderName,accountNumber,0.0, address,dateOfBirth,branch);
		putAccount(accountNumber,account);
		System.out.println("Your account is created successfully..");
		
		
	}
	public static void deposit() {
		System.out.println("Enter your account number:");
		String accountNumber = scan.next();
		Account account = accounts.get(accountNumber);
		if(account != null) {
			System.out.println("Enter the amount to deposit :");
			double amount = scan.nextDouble();
			account.deposit(amount);
			System.out.println(account.getBalance());
		}
		else {
			System.out.println("Account is not found");
		}
		System.exit(0);
		
	}
	public static void withdraw() {
		System.out.println("Enter your account number");
		String accountNumber = scan.next();
		Account account = accounts.get(accountNumber);
		if(account != null) {
			System.out.println("Enter the amount to withdraw");
			double amount = scan.nextDouble();
			account.withdraw(amount);
		}
		else {
			System.out.println("Account is not found");
		}
		System.exit(0);
	}
	public static void inquiryBalance() {
		System.out.println("Enter your account number");
		String accountNumber = scan.next();
		Account account = accounts.get(accountNumber);
		if(account != null) {
			account.displayBalance();
		}
		else {
			System.out.println("Account is not found");
		}
		System.exit(0);
	}

}
