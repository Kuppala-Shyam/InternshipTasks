package com.example.User.entity;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

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

	public Account() {
		this.accountNumber = generatedAccountNumber();
	}

	private String generatedAccountNumber() {
		StringBuilder stringBuilder = new StringBuilder();
		Random random = new Random();
		stringBuilder.append(1 + random.nextInt(9));
		for (int i = 0; i < 11; i++) {
			stringBuilder.append(random.nextInt(10));

		}
		return stringBuilder.toString();
	}

	public Account(Account savedAccount) {
		// TODO Auto-generated constructor stub
	}

	public Account(String name2, String existingAccountNumber, String email2, AccountType accounType2,
			double newBalance) {
		// TODO Auto-generated constructor stub
	}
}
