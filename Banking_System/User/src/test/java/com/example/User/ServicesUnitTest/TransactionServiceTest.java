package com.example.User.ServicesUnitTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.User.Exception.AccountNotFoundException;
import com.example.User.Exception.InsufficientAmountException;
import com.example.User.Repository.AccountRepository;
import com.example.User.entity.Account;
import com.example.User.service.AccountService;
import com.example.User.service.TransactionService;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @MockBean
    private AccountRepository accountRepositoryMock;

    private Account senderAccount;
    private Account receiverAccount;

    @BeforeEach
    public void setUp() {
        senderAccount = Account.builder()
                .accountId(10)
                .accountNumber("1234567890")
                .accounType(null)
                .balance(2000.00)
                .email("john@gmail.com")
                .name("John")
                .phoneNumber(null)
                .build();
        receiverAccount = Account.builder()
                .accountId(11) 
                .accountNumber("1234567891") 
                .accounType(null)
                .balance(1500.00)
                .email("jane@gmail.com")
                .name("Jane") 
                .phoneNumber(null) 
                .build();
    }

    @Test
    public void testTransferMoney_AccountNotFound() {
        double transferAmount = 200.00;
        String senderAccountNumber = "InvalidAccountNumber";
        String receiverAccountNumber = receiverAccount.getAccountNumber();

        when(accountRepositoryMock.findByAccountNumber(senderAccountNumber)).thenReturn(null);

        assertThrows(AccountNotFoundException.class, () -> transactionService.transferMoney(transferAmount, senderAccountNumber, receiverAccountNumber));

        verify(accountRepositoryMock, times(1)).findByAccountNumber(senderAccountNumber);
       
    }


}
