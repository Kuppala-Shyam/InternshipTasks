package com.example.User.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.User.Exception.UserNotFoundException;
import com.example.User.Repository.UserRepository;
import com.example.User.entity.UserEntity;
import com.example.User.model.UserModel;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<?> fetchUserByEmail(String email) {

		Optional<UserEntity> user = userRepository.findUserByEmail(email);
		if (user.isPresent()) {
			return ResponseEntity.ok(user);
		} else {
			throw new UserNotFoundException("email is not matching. Please check and Re-enter it.");
		}

	}

	public ResponseEntity<?> updateUserdetails(String email, UserEntity updateUser) {
		try {
			Optional<UserEntity> findUser = userRepository.findUserByEmail(email);
			if (findUser.isPresent()) {
				UserEntity existingUser = findUser.get();
				if (updateUser.getEmail() != null) {
					existingUser.setEmail(updateUser.getEmail());
				}
				existingUser.setFirstName(updateUser.getFirstName());
				existingUser.setLastName(updateUser.getLastName());
				UserEntity updatedUserEntity = userRepository.save(existingUser);
				UserModel updatedUserModel = userEntityToUserModel(updatedUserEntity);
				return ResponseEntity.ok(updatedUserModel);
			} else {
				throw new UserNotFoundException("User with the entered email does not exist. Please check it.");
			}

		} catch (Exception e) {
			throw new RuntimeException("An unexpected error occurred during money transfer.", e);
		}

	}

	public UserModel userEntityToUserModel(UserEntity user) {
		UserModel userModel = this.modelMapper.map(user, UserModel.class);
		return userModel;
	}

	public void deleteUserDetails(String email) {
		try {
			Optional<UserEntity> findUser = userRepository.findUserByEmail(email);
			if (findUser.isPresent()) {
				userRepository.deleteByEmail(email);
			} else {
				throw new UserNotFoundException("User with the entered mail not existed.Please check it");
			}
		} catch (Exception e) {
			throw new RuntimeException("An unexpected error occurred during money transfer.", e);
		}

	}

}
