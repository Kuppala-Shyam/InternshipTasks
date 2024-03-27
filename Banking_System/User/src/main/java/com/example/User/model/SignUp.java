package com.example.User.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for representing a sign-up request.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUp {
	
	 /**
     * The first name of the user.
     */
	private String firstName;
	
	/**
     * The last name of the user.
     */
	private String lastName;

    /**
     * The email of the user.
     */
	private String email;
	
    /**
     * The password for the user.
     */
	private String password;
	
}
