package com.example.User.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for representing a user.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

	/**
	 * The unique identifier of the user.
	 */
	private Integer id;

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

}
