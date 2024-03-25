package com.example.User.ServicesUnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.User.Exception.AccountNotFoundException;
import com.example.User.Repository.AccountRepository;
import com.example.User.entity.Account;
import com.example.User.service.AccountService;

@SpringBootTest
public class AccountServiceTest {
	@Autowired
	private AccountService accountService;
	@MockBean
	private AccountRepository accountRepository;
	private Account testAccount;

	@BeforeEach
	public void setUp() {
	  testAccount = Account.builder()
			  .accountId(10)
			  .accountNumber("23648464431312")
			  .accounType(null)
			  .balance(1000.00)
			  .email("Smith@gamil.com")
			  .name("smith")
			  .phoneNumber(9766431561l)
			  .build();
	    
	}
	@Test
	public void testCreateAccount() {

	    Mockito.when(accountRepository.save(testAccount)).thenReturn(testAccount);

	    Account createdAccount = accountService.createAccount(testAccount);

	    assertNotNull(createdAccount);
	    assertEquals(testAccount, createdAccount);

	    Mockito.verify(accountRepository).save(testAccount);
	}
	@Test
	public void testViewAccountDetails_AccountFound() {
	  
	    String existingName = "Jane Smith";
	    Mockito.when(accountRepository.findAccountByName(existingName)).thenReturn(testAccount);

	    Account retrievedAccount = accountService.viewAccountDetails(existingName);

	    assertNotNull(retrievedAccount);
	    assertEquals(testAccount, retrievedAccount);

	    Mockito.verify(accountRepository).findAccountByName(existingName);
	}

	@Test
	public void testViewAccountDetails_AccountNotFound() {
	

	    String nonExistingName = "Non-Existing Name";
	    Mockito.when(accountRepository.findAccountByName(nonExistingName)).thenReturn(null);

	    assertThrows(AccountNotFoundException.class, () -> accountService.viewAccountDetails(nonExistingName));

	    Mockito.verify(accountRepository).findAccountByName(nonExistingName);
	}
	@Test
	public void testViewBalance_AccountFound() {
	   
	    String existingAccountNumber = "12345";
	    Mockito.when(accountRepository.findByAccountNumber(existingAccountNumber)).thenReturn(testAccount);

	    Account retrievedAccount = accountService.viewBalance(existingAccountNumber);

	    assertNotNull(retrievedAccount);
	    assertEquals(testAccount, retrievedAccount);

	    Mockito.verify(accountRepository).findByAccountNumber(existingAccountNumber);
	}

	@Test
	public void testViewBalance_AccountNotFound() {

	    String nonExistingAccountNumber = "98765";
	    Mockito.when(accountRepository.findByAccountNumber(nonExistingAccountNumber)).thenReturn(null);

	    assertThrows(AccountNotFoundException.class, () -> accountService.viewBalance(nonExistingAccountNumber));

	    Mockito.verify(accountRepository).findByAccountNumber(nonExistingAccountNumber);
	}

	@Test
	public void testUpdateAccountDetails_AccountNotFound() {

	    String nonExistingAccountNumber = "98765";
	    Mockito.when(accountRepository.findByAccountNumber(nonExistingAccountNumber)).thenReturn(null);
	    Account updatedAccount = new Account();
        updatedAccount.setAccountId(1);
        updatedAccount.setAccountNumber(nonExistingAccountNumber);
        updatedAccount.setAccounType(null);
        updatedAccount.setBalance(1050.00);
        updatedAccount.setEmail("joe@gmail.com");
        updatedAccount.setName("Joe");
        updatedAccount.setPhoneNumber(8741356512l);
	 
	    assertThrows(AccountNotFoundException.class, () -> accountService.updateAccountDetails(nonExistingAccountNumber, updatedAccount));

	    Mockito.verify(accountRepository).findByAccountNumber(nonExistingAccountNumber);
	}
	@Test
	public void testDeleteAccount_AccountFound() {

	    String existingAccountNumber = "12345";
	    Mockito.when(accountRepository.findByAccountNumber(existingAccountNumber)).thenReturn(testAccount);

	    Account deletedAccount = accountService.deleteAccount(existingAccountNumber);

	    
	    assertNotNull(deletedAccount);
	    assertEquals(testAccount, deletedAccount);


	    Mockito.verify(accountRepository).delete(testAccount);
	}

	@Test
	public void testDeleteAccount_AccountNotFound() {

	    String nonExistingAccountNumber = "98765";
	    Mockito.when(accountRepository.findByAccountNumber(nonExistingAccountNumber)).thenReturn(null);

	    
	    assertThrows(AccountNotFoundException.class, () -> accountService.deleteAccount(nonExistingAccountNumber));

	    Mockito.verify(accountRepository).findByAccountNumber(nonExistingAccountNumber);
	}
	@Test
	public void testUpdateAccountBalance_AccountFound() {

	    String existingAccountNumber = "12345";
	    double newBalance = 3000.00;
	    Account accountWithNewBalance = new Account(testAccount.getName(), existingAccountNumber, testAccount.getEmail(), testAccount.getAccounType(), newBalance);
	    Mockito.when(accountRepository.findByAccountNumber(existingAccountNumber)).thenReturn(testAccount);


	}
}
