package com.example.User.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model class for representing an authentication request.
 */
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRequest {

	/**
	 * The email of the user.
	 */
	private String email;

	/**
	 * The password for authentication.
	 */
	private String password;
}
