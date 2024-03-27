package com.example.User.config;

import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Custom authentication entry point to handle authentication failures.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	private Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

	/**
	 * Commences the authentication failure handling.
	 *
	 * @param request       The HTTP request.
	 * @param response      The HTTP response.
	 * @param authException The authentication exception.
	 * @throws IOException      If an I/O error occurs.
	 * @throws ServletException If a servlet-related error occurs.
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);

		// Log the authentication exception
		logger.error("Authentication failed: " + authException.getMessage(), authException);

		// Send an error response
		PrintWriter writer = response.getWriter();
		writer.println("Authentication failed: " + authException.getMessage());
	}
}
