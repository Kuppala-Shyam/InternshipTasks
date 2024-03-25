package com.example.User.controller;

import java.util.List;

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

@RestController
@RequestMapping("/account/")
public class AccountController {
	@Autowired
	private AccountService accountService;

	@PostMapping("/createAccount")
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
		if (account.getAccounType() == null
				|| !EnumUtils.isValidEnum(AccountType.class, account.getAccounType().toString())) {
			return ResponseEntity.badRequest().build();
		}
		Account createdAccount = accountService.createAccount(account);
		return ResponseEntity.ok(createdAccount);
	}

	@GetMapping("/viewAccountDetails/{name}")
	public ResponseEntity<?> viewAccountDetails(@PathVariable("name") String name) {
		try {
			Account account = accountService.viewAccountDetails(name);
			return ResponseEntity.ok(account);

			}catch (AccountNotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}
		}

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
