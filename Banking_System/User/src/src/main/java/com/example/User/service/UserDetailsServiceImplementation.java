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
	@Override
	public UserDetails loadUserByUsername(String email
			) throws UsernameNotFoundException {
		Optional<UserEntity> user = userRepository.findUserByEmail(email);
		if(!user.isPresent()) {
			throw new UsernameNotFoundException("User not found"+email);
		}
		return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), new ArrayList<>());
	}

}