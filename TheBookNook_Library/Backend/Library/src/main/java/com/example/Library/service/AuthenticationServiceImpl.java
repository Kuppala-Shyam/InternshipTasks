package com.example.Library.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Library.Config.AuthConfig;
import com.example.Library.Entity.UserEntity;
import com.example.Library.Model.JwtAuthenticationResponse;
import com.example.Library.Model.SignUpRequest;
import com.example.Library.Model.SignUpResponse;
import com.example.Library.Model.SigninRequest;
import com.example.Library.Repository.UserEntityRepository;

/**
 * Implementation of the AuthenticationService interface for handling user authentication.
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private AuthConfig authConfig;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Handles user signup.
     * @param request The signup request containing user details.
     * @return The response containing signup status.
     */
    @Override
    public SignUpResponse signup(SignUpRequest request) {
        // Create a new UserEntity object
        UserEntity user = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(authConfig.passwordEncoder().encode(request.getPassword()))
                .balance(request.getBalance()) 
                .address(request.getAddress()) 
                .phoneNumber(request.getPhoneNumber()) 
                .role(request.getRole())
                .build();
        
        // Save the user to the database
        UserEntity savedUser = userEntityRepository.save(user);
        
        // Create a SignupResponse object with the saved user details
        SignUpResponse response = SignUpResponse.builder()
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .address(savedUser.getAddress())
                .balance(savedUser.getBalance())
                .phoneNumber(savedUser.getPhoneNumber())
                .role(savedUser.getRole())
                .build();
        
        return response;
    }

    /**
     * Handles user signin.
     * @param request The signin request containing user credentials.
     * @return The response containing authentication token.
     */
    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            var userDetails = loadUserByUsername(request.getEmail());
            var jwt = jwtService.generateToken(userDetails);
            return JwtAuthenticationResponse.builder().token(jwt).build();
        } catch (UsernameNotFoundException e) {
            // User not found
            throw new UsernameNotFoundException("User not found with email: " + request.getEmail());
        } catch (BadCredentialsException e) {
            // Invalid credentials
            throw new BadCredentialsException("Invalid email or password");
        } catch (Exception e) {
            // Other exceptions
            throw new RuntimeException("Authentication failed: " + e.getMessage(), e);
        }
    }

    /**
     * Loads user details by email.
     * @param email The email address of the user.
     * @return UserDetails object containing user details.
     * @throws UsernameNotFoundException If the user is not found.
     */
    private UserDetails loadUserByUsername(String email) {
        // Find the user by email
        UserEntity userEntity = userEntityRepository.findByEmail(email);

        // Check if the user exists
        if (userEntity != null) {
            // Create UserDetails object from UserEntity attributes
            return org.springframework.security.core.userdetails.User.builder().username(userEntity.getEmail())
                    .password(userEntity.getPassword())
                    .authorities(Collections.singleton(new SimpleGrantedAuthority(userEntity.getRole().name())))
                    .accountExpired(!userEntity.isAccountNonExpired())
                    .accountLocked(!userEntity.isAccountNonLocked())
                    .credentialsExpired(!userEntity.isCredentialsNonExpired()).disabled(!userEntity.isEnabled())
                    .build();
        } else {
            // If user does not exist, throw UsernameNotFoundException
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
}
