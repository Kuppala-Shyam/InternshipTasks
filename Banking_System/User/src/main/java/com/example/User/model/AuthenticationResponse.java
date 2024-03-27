package com.example.User.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for representing an authentication response.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {

	/**
	 * The access token generated upon successful authentication.
	 */
	private String accessToken;
}
