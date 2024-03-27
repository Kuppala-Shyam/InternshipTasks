package com.example.User.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.User.Repository.UserRepository;
import com.example.User.entity.UserEntity;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	/**
	 * Implementation of UserDetailsService interface to load user details by email.
	 * 
	 * @param email The email of the user.
	 * @return UserDetails object containing user details.
	 * @throws UsernameNotFoundException if the user with the provided email is not
	 *                                   found.
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserEntity> user = userRepository.findUserByEmail(email);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException("User not found" + email);
		}
		// Returning UserDetails object with email as username, password, and an empty
		// list of authorities

		return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(),
				new ArrayList<>());
	}

}