package com.example.User.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
	
	private String senderAccountNumber;
	private String receiverAccountNumber;
	private Double amount;
}
