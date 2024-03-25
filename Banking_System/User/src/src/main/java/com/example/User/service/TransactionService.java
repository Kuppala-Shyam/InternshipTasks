package com.example.User.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.User.Exception.AccountNotFoundException;
import com.example.User.Exception.InsufficientAmountException;
import com.example.User.Repository.AccountRepository;
import com.example.User.Repository.TransactionRepository;
import com.example.User.entity.Account;
import com.example.User.entity.Transaction;
import com.example.User.model.TransactionRequest;


@Service
public class TransactionService  {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private AccountService accountService;
	@Autowired
	private TransactionRepository transactionRepository;
	
	public TransactionRequest  transferMoney(Double amount, String senderAccountNumber, String receiverAccountNumber) {
	    Account senderAccount = accountRepository.findByAccountNumber(senderAccountNumber);
	    Account receiverAccount = accountRepository.findByAccountNumber(receiverAccountNumber);
	    TransactionRequest transcationRequest = new TransactionRequest();
        try {
            if (senderAccount == null || receiverAccount == null) {
                throw new AccountNotFoundException("Please check the account numbers you entered. They are not correct. Re-enter the correct credentials.");
            }
            if (senderAccount.getBalance() < amount) {
                throw new InsufficientAmountException("Account has insufficient balance.!!!!");
            }
            senderAccount.setBalance(senderAccount.getBalance() - amount);
            accountService.updateAccountBalance(senderAccount);
            receiverAccount.setBalance(receiverAccount.getBalance() + amount);
            accountService.updateAccountBalance(receiverAccount);
            Transaction transcation = new Transaction();
            transcation.setAmount(amount);
            transcation.setSenderAccountNumber(senderAccount);
            transcation.setReceiverAccountNumber(receiverAccount);
            transcation.setTimestamp(new Date());
            Transaction savedTranscation = transactionRepository.save(transcation);
            transcationRequest.setReceiverAccountNumber(savedTranscation.getReceiverAccountNumber().getAccountNumber());
            transcationRequest.setSenderAccountNumber(savedTranscation.getSenderAccountNumber().getAccountNumber());
            transcationRequest.setAmount(savedTranscation.getAmount());
        } catch (AccountNotFoundException | InsufficientAmountException e) {

            throw e; 
        } catch (Exception e) {
            // Catching any other unexpected exceptions and wrapping them in a RuntimeException
            throw new RuntimeException("An unexpected error occurred during money transfer.", e);
        }
	    return transcationRequest;
	}

	
}
