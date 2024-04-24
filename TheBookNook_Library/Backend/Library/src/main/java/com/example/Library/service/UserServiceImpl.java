package com.example.Library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Library.Entity.UserEntity;
import com.example.Library.Repository.UserEntityRepository;

/**
 * Implementation of the UserService interface.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    /**
     * Retrieves the UserDetailsService implementation.
     * 
     * @return The UserDetailsService implementation.
     */
    @Override
    public UserDetailsService userDetailsService() {
        return username -> {
            // Find the user by email
            UserEntity userEntity = userEntityRepository.findByEmail(username);
            // Check if the user exists
            if (userEntity == null) {
                // If not found, throw UsernameNotFoundException
                throw new UsernameNotFoundException("User not found");
            }
            // If found, return the UserEntity
            return userEntity;
        };
    }
}
