
package com.example.User.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.User.model.AuthenticationRequest;
import com.example.User.model.AuthenticationResponse;
import com.example.User.service.UserDetailsServiceImplementation;
import com.example.User.util.JwtUtil;

/**
 * Controller class for handling authentication-related operations.
 */
@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsServiceImplementation userDetailsServiceImplementation;
	@Autowired
	private JwtUtil jwtUtil;
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	/**
	 * Endpoint for user authentication.
	 * 
	 * @param authenticationRequest The authentication request containing email and
	 *                              password.
	 * @return ResponseEntity with the authentication response.
	 */
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
		this.doAuthenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		UserDetails userDetails = userDetailsServiceImplementation.loadUserByUsername(authenticationRequest.getEmail());
		String token = this.jwtUtil.generateToken(userDetails);
		AuthenticationResponse authenticationResponse = AuthenticationResponse.builder().accessToken(token).build();
		return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
	}

	/**
	 * Method to authenticate user credentials.
	 * 
	 * @param email    The user email.
	 * @param password The user password.
	 */

	private void doAuthenticate(String email, String password) {

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			authenticationManager.authenticate(authentication);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (BadCredentialsException e) {
			System.out.println("Authentication not-successful for user: " + email);
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}
	}

	/**
	 * Exception handler for BadCredentialsException.
	 * 
	 * @param ex The exception object.
	 * @return Error message for bad credentials.
	 */
	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler(BadCredentialsException ex) {
		return "Credentials Invalid !!";
	}
}
