package com.example.User.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.User.Exception.AccountNotFoundException;
import com.example.User.Repository.AccountRepository;
import com.example.User.entity.Account;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;

	public Account createAccount(Account account) {
		
		return accountRepository.save(account);
	}

	public Account viewAccountDetails(String name) {
		Account account= accountRepository.findAccountByName(name);
		if(account == null) {
			 throw new AccountNotFoundException("Account with name '" + name + "' not found.");
		}
		return account;
	}

	public Account viewBalance(String accountNumber) {

		Account findAccountNumber= accountRepository.findByAccountNumber(accountNumber);
		if(findAccountNumber != null) {
			return findAccountNumber;
		}
		else {
			throw new AccountNotFoundException("Account with given account number.Please check the input details and Re-enter");
		}
	}

	public Account updateAccountDetails(String accountNumber, Account updateAccount) {
		Account findAccount = accountRepository.findByAccountNumber(accountNumber);
		if(findAccount != null) {
			findAccount.setAccounType(updateAccount.getAccounType());
			findAccount.setBalance(updateAccount.getBalance());
			findAccount.setEmail(updateAccount.getEmail());
			findAccount.setName(updateAccount.getName());
			findAccount.setPhoneNumber(updateAccount.getPhoneNumber());
			return accountRepository.save(findAccount);
		}
		else{
			throw new AccountNotFoundException("Account with given credentials is not existed to update the details.Please check the input details and Re-enter");
		}
	}

	public Account deleteAccount(String accountNumber) {
		
		Account findAccount = accountRepository.findByAccountNumber(accountNumber);
		if(findAccount != null) {
			 accountRepository.delete(findAccount);
			 return findAccount;
		}
		else {
			throw new AccountNotFoundException("Account with given credentials is not existed to delete.Please check the input details and Re-enter");		
		}
	
	}

	public Account updateAccountBalance(Account account) {
		Account findAccount = accountRepository.findByAccountNumber(account.getAccountNumber());
		if(findAccount != null) {
			findAccount.setBalance(account.getBalance());

			return accountRepository.save(findAccount);
		}
		else{
			throw new AccountNotFoundException("Account with given credentials is not existed and balance is not updated.Please check the input details and Re-enter");
		}
		
	}

	

	

}
