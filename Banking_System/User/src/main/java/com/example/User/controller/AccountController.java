package com.example.User.controller;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.User.Exception.AccountNotFoundException;
import com.example.User.entity.Account;
import com.example.User.entity.AccountType;
import com.example.User.model.AccountDetails;
import com.example.User.service.AccountService;

/**
 * Controller class for handling account-related operations.
 */
@RestController
@RequestMapping("/account/")
public class AccountController {
	@Autowired
	private AccountService accountService;

	/**
	 * Endpoint for creating a new account.
	 *
	 * @param account The account object containing details of the account to be
	 *                created.
	 * @return ResponseEntity with the created account object.
	 */
	@PostMapping("/createAccount")
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
		if (account.getAccounType() == null
				|| !EnumUtils.isValidEnum(AccountType.class, account.getAccounType().toString())) {
			return ResponseEntity.badRequest().build();
		}
		Account createdAccount = accountService.createAccount(account);
		return ResponseEntity.ok(createdAccount);
	}

	/**
	 * Endpoint for viewing account details by account holder name.
	 *
	 * @param name The name of the account holder.
	 * @return ResponseEntity with the account details.
	 */
	@GetMapping("/viewAccountDetails/{name}")
	public ResponseEntity<?> viewAccountDetails(@PathVariable("name") String name) {
		try {
			Account account = accountService.viewAccountDetails(name);
			return ResponseEntity.ok(account);

		} catch (AccountNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	/**
	 * Endpoint for viewing account balance by account number.
	 *
	 * @param accountNumber The account number.
	 * @return ResponseEntity with the account balance details.
	 */
	@GetMapping("/viewBalance/{accountNumber}")
	public ResponseEntity<?> viewBalance(@PathVariable("accountNumber") String accountNumber) {
		try {
			Account account = accountService.viewBalance(accountNumber);
			AccountDetails accountDetails = new AccountDetails(account.getName(), account.getBalance());
			return ResponseEntity.ok(accountDetails);

		} catch (AccountNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	/**
	 * Endpoint for updating account details.
	 *
	 * @param accountNumber The account number to identify the account.
	 * @param updateAccount The updated account object.
	 * @return ResponseEntity with the updated account object.
	 */
	@PutMapping("/updateAccountDetails/{accountNumber}")
	public ResponseEntity<?> updateAccountDetails(@PathVariable("accountNumber") String accountNumber,
			@RequestBody Account updateAccount) {
		try {
			Account accountUpdate = accountService.updateAccountDetails(accountNumber, updateAccount);
			return ResponseEntity.ok(accountUpdate);
		} catch (AccountNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	/**
	 * Endpoint for deleting an account.
	 *
	 * @param accountNumber The account number to identify the account.
	 * @return ResponseEntity indicating the status of the operation.
	 */
	@DeleteMapping("/deleteAccount/{accountNumber}")
	public ResponseEntity<?> deleteAccount(@PathVariable("accountNumber") String accountNumber) {
		try {
			accountService.deleteAccount(accountNumber);

			return ResponseEntity.ok("account with " + accountNumber + " deleted successfully ");
		} catch (AccountNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}
}
