package com.example.User.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for representing account details.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetails {

	/**
	 * The name of the account holder.
	 */
	private String name;

	/**
	 * The balance of the account.
	 */
	private Double balance;

}
