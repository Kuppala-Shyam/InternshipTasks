package com.example.User.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a transaction in the system.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

	// Unique identifier for the transaction
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// The amount involved in the transaction
	private Double amount;

	// The timestamp indicating when the transaction occurred
	private Date timestamp;

	// The sender account number associated with the transaction
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sender_account_number")
	private Account senderAccountNumber;

	// The receiver account number associated with the transaction
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "receiver_account_number")
	private Account receiverAccountNumber;

}
