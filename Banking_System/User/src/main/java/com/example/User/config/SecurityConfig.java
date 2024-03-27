package com.example.User.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.User.Filters.JwtAuthenticationFilter;

/**
 * Security configuration class for configuring Spring Security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final UserDetailsService userDetailService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
			JwtAuthenticationFilter jwtAuthenticationFilter, UserDetailsService userDetailService,
			PasswordEncoder passwordEncoder) {
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.userDetailService = userDetailService;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * Configures security filter chain.
	 * 
	 * @param http HttpSecurity instance.
	 * @return SecurityFilterChain instance.
	 * @throws Exception If an error occurs during configuration.
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// configuration
		http.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable())
				.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
						.requestMatchers("/user/*", "/account/*", "/transaction/*").authenticated()
						.requestMatchers("/swagger-ui.html", "swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**")
						.permitAll().requestMatchers("/login").permitAll().requestMatchers("/sign-up").permitAll()
						.anyRequest().authenticated())
				.exceptionHandling(
						exceptionHandling -> exceptionHandling.authenticationEntryPoint(jwtAuthenticationEntryPoint)) // if
																														// any
																														// exception
																														// came
				.sessionManagement(
						sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // nothing
																														// to
																														// save
																														// on
																														// server
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	/**
	 * Bean for DaoAuthenticationProvider.
	 * 
	 * @return A DaoAuthenticationProvider instance.
	 */
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		return daoAuthenticationProvider;
	}
}
