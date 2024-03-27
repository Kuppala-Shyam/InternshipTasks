package com.example.User.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for authentication-related beans.
 */
@Configuration
public class AuthConfig {

	/**
	 * Bean for password encoder.
	 * 
	 * @return A BCryptPasswordEncoder instance.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Bean for authentication manager.
	 * 
	 * @param builder AuthenticationConfiguration instance.
	 * @return An AuthenticationManager instance.
	 * @throws Exception If an error occurs during authentication manager retrieval.
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}

	/**
	 * Bean for AuthError.
	 * 
	 * @return An AuthError instance.
	 */
	@Bean
	public AuthError authError() {
		return new AuthError();
	}

	/**
	 * Bean for ModelMapper.
	 * 
	 * @return A ModelMapper instance.
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
