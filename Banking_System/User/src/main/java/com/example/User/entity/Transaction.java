package com.example.User.entity;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Double amount;
	private Date timestamp;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="sender_account_number")
	private Account senderAccountNumber;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="receiver_account_number")
	private Account receiverAccountNumber;

}
