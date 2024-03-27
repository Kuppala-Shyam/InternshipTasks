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

	/**
	 * Creates a new account.
	 * 
	 * @param account The account object containing details of the account to be
	 *                created.
	 * @return The created account object.
	 */
	public Account createAccount(Account account) {

		return accountRepository.save(account);
	}

	/**
	 * Retrieves account details by name.
	 * 
	 * @param name The name of the account holder.
	 * @return The account object corresponding to the provided name.
	 * @throws AccountNotFoundException if no account is found with the given name.
	 */
	public Account viewAccountDetails(String name) {
		Account account = accountRepository.findAccountByName(name);
		if (account == null) {
			throw new AccountNotFoundException("Account with name '" + name + "' not found.");
		}
		return account;
	}

	/**
	 * Retrieves account balance by account number.
	 * 
	 * @param accountNumber The account number.
	 * @return The account object containing balance information.
	 * @throws AccountNotFoundException if no account is found with the given
	 *                                  account number.
	 */
	public Account viewBalance(String accountNumber) {

		Account findAccountNumber = accountRepository.findByAccountNumber(accountNumber);
		if (findAccountNumber != null) {
			return findAccountNumber;
		} else {
			throw new AccountNotFoundException(
					"Account with given account number.Please check the input details and Re-enter");
		}
	}

	/**
	 * Updates account details.
	 * 
	 * @param accountNumber The account number to identify the account.
	 * @param updateAccount The updated account object.
	 * @return The updated account object.
	 * @throws AccountNotFoundException if no account is found with the given
	 *                                  account number.
	 */
	public Account updateAccountDetails(String accountNumber, Account updateAccount) {
		Account findAccount = accountRepository.findByAccountNumber(accountNumber);
		if (findAccount != null) {
			findAccount.setAccounType(updateAccount.getAccounType());
			findAccount.setBalance(updateAccount.getBalance());
			findAccount.setEmail(updateAccount.getEmail());
			findAccount.setName(updateAccount.getName());
			findAccount.setPhoneNumber(updateAccount.getPhoneNumber());
			return accountRepository.save(findAccount);
		} else {
			throw new AccountNotFoundException(
					"Account with given credentials is not existed to update the details.Please check the input details and Re-enter");
		}
	}

	/**
	 * Deletes an account.
	 * 
	 * @param accountNumber The account number to identify the account.
	 * @return The deleted account object.
	 * @throws AccountNotFoundException if no account is found with the given
	 *                                  account number.
	 */
	public Account deleteAccount(String accountNumber) {

		Account findAccount = accountRepository.findByAccountNumber(accountNumber);
		if (findAccount != null) {
			accountRepository.delete(findAccount);
			return findAccount;
		} else {
			throw new AccountNotFoundException(
					"Account with given credentials is not existed to delete.Please check the input details and Re-enter");
		}

	}

	/**
	 * Updates account balance.
	 * 
	 * @param account The account object containing updated balance information.
	 * @return The updated account object.
	 * @throws AccountNotFoundException if no account is found with the given
	 *                                  account number.
	 */
	public Account updateAccountBalance(Account account) {
		Account findAccount = accountRepository.findByAccountNumber(account.getAccountNumber());
		if (findAccount != null) {
			findAccount.setBalance(account.getBalance());

			return accountRepository.save(findAccount);
		} else {
			throw new AccountNotFoundException(
					"Account with given credentials is not existed and balance is not updated.Please check the input details and Re-enter");
		}

	}

}
