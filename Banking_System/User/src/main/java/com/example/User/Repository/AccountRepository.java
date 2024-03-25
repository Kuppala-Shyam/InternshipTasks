package com.example.User.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.User.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	Account findAccountByName(String name);

	Account findByAccountNumber(String accountNumber);

	Account findByAccountNumber(Account senderAccountNumber);

}
