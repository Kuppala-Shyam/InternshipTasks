package com.example.User.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.User.Exception.AccountNotFoundException;
import com.example.User.Exception.InsufficientAmountException;
import com.example.User.model.TransactionRequest;
import com.example.User.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/transferMoney")
	public ResponseEntity<?> transferMoney(@RequestBody TransactionRequest request){
		 try {
	            TransactionRequest transaction = transactionService.transferMoney(request.getAmount(), request.getSenderAccountNumber(), request.getReceiverAccountNumber());
	            return ResponseEntity.ok(transaction);
	        } catch (AccountNotFoundException | InsufficientAmountException e) {
	            // Handling known exceptions
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	        } catch (Exception e) {
	            // Handling unexpected exceptions
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
	        }
	}
}
