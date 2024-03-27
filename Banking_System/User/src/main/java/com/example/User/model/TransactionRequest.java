package com.example.User.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for representing a transaction request.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

	/**
	 * The account number of the sender.
	 */
	private String senderAccountNumber;

	/**
	 * The account number of the receiver.
	 */
	private String receiverAccountNumber;

	/**
	 * The amount to be transferred.
	 */
	private Double amount;
}
