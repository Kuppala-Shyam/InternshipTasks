package com.example.User.Repository;
 

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.User.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer>{
	
	 /**
     * Find a user by email.
     * 
     * @param email The email of the user.
     * @return An Optional containing the user found with the provided email, or empty if not found.
     */
	Optional<UserEntity> findUserByEmail(String email);

	/**
     * Delete a user by email.
     * 
     * @param email The email of the user to be deleted.
     */

	void deleteByEmail(String email);

	
	
	

	

}
