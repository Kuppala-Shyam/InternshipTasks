package com.example.User.entity;

import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Entity class representing an account.
 */
@Entity
@Data
@AllArgsConstructor
@Builder
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accountId;
	private String name;
	private String email;
	private String accountNumber;
	private Long phoneNumber;
	private Double balance;
	@Enumerated(EnumType.STRING)
	private AccountType accounType;

	/**
	 * Default constructor for Account class. Generates a random account number.
	 */
	public Account() {
		this.accountNumber = generatedAccountNumber();
	}

	/**
	 * Method to generate a random account number.
	 * 
	 * @return The generated account number.
	 */
	private String generatedAccountNumber() {
		StringBuilder stringBuilder = new StringBuilder();
		Random random = new Random();
		stringBuilder.append(1 + random.nextInt(9));
		for (int i = 0; i < 11; i++) {
			stringBuilder.append(random.nextInt(10));

		}
		return stringBuilder.toString();
	}

	/**
	 * Copy constructor for Account class.
	 * 
	 * @param savedAccount The account to be copied.
	 */
	public Account(Account savedAccount) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Parameterized constructor for Account class.
	 * 
	 * @param name                  The name of the account holder.
	 * @param existingAccountNumber The existing account number.
	 * @param email                 The email of the account holder.
	 * @param accountType           The type of account.
	 * @param newBalance            The new balance of the account.
	 */
	public Account(String name2, String existingAccountNumber, String email2, AccountType accounType2,
			double newBalance) {
		// TODO Auto-generated constructor stub
	}
}
