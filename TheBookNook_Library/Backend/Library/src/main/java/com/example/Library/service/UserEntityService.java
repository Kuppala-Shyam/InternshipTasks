package com.example.Library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Library.Entity.UserEntity;
import com.example.Library.Exception.UserNotFoundException;
import com.example.Library.Repository.UserEntityRepository;

@Service
public class UserEntityService {
	@Autowired
	private UserEntityRepository userEntityRepository;

	public UserEntity fetchUserById(Integer userId) {

		UserEntity user = userEntityRepository.findById(userId).get();
		if (user != null) {
			return user;
		} else {
			throw new UserNotFoundException(
					"User with this id id not found. May this id is not existed or not registered yet!");
		}
	}

	public UserEntity UpdateUserDetailsByEmail(String email, UserEntity updatedUser) {
		UserEntity existingUser = userEntityRepository.findByEmail(email);
		if (existingUser != null) {
			existingUser.setFirstName(updatedUser.getFirstName());
			existingUser.setLastName(updatedUser.getLastName());
			existingUser.setEmail(updatedUser.getEmail());
			existingUser.setAddress(updatedUser.getAddress());
			existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
			existingUser.setBalance(updatedUser.getBalance());
			return userEntityRepository.save(existingUser);
		} else {
			throw new UserNotFoundException(
					"User with this id id not found. May this id is not existed or not registered yet!");
		}
	}

	public List<UserEntity> fetchAllUsers() {
		return userEntityRepository.findAll();
	};
	
}
