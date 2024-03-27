package com.example.User.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.User.entity.Account;
/**
 * Repository interface for Account entity.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	/**
     * Find an account by name.
     * 
     * @param name The name of the account holder.
     * @return The account found with the given name.
     */
	Account findAccountByName(String name);
	/**
     * Find an account by account number.
     * 
     * @param accountNumber The account number.
     * @return The account found with the given account number.
     */
	Account findByAccountNumber(String accountNumber);
	/**
     * Find an account by account number.
     * 
     * @param senderAccountNumber The account number.
     * @return The account found with the given account number.
     */
	Account findByAccountNumber(Account senderAccountNumber);

}
