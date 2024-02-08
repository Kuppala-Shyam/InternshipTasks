package InternPack;
public class Account {
	private String accountHolderName;
	private String accountNumber;
	private double balance;
	private String branch;
	private String addressOfaccountHolder;
	private String dateOfBirth;
	public Account(String accountHolderName, String accountNumber,double balance, String branch, String addressOfaccountHolder,
			String dateOfBirth) {
		
		this.accountHolderName = accountHolderName;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.branch = branch;
		this.addressOfaccountHolder = addressOfaccountHolder;
		this.dateOfBirth = dateOfBirth;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getAddressOfaccountHolder() {
		return addressOfaccountHolder;
	}
	public void setAddressOfaccountHolder(String addressOfaccountHolder) {
		this.addressOfaccountHolder = addressOfaccountHolder;
	}
	
	
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public void deposit(double amount) {
		this.balance += amount;
	}
	public void withdraw(double amount) {
		if(balance>=amount) {
			balance -= amount;
			System.out.println("withdrawal of rupees"+amount+"is sucessful....");
			System.out.println("Current balance is:"+balance);
		}
		else {
			System.out.println("Please check you have insufficient balance. Withdrawal is failed...");
			
		}
	}
	public void displayBalance() {
		System.out.println("Account Balance:"+balance);
	}
	@Override
	public String toString() {
		return "Account [accountHolderName=" + accountHolderName + ", accountNumber=" + accountNumber + ", balance="
				+ balance + ", branch=" + branch + ", addressOfaccountHolder=" + addressOfaccountHolder
				+ ", dateOfBirth=" + dateOfBirth + "]";
	}
	
	
}
