package com.example.User.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.User.Repository.UserRepository;
import com.example.User.entity.UserEntity;
import com.example.User.model.SignUp;
import com.example.User.model.UserModel;

@Service
public class AuthServiceImplementation implements AuthService {
	@Autowired
    private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	private static final Logger logger =  LoggerFactory.getLogger(AuthServiceImplementation.class);
	/**
	 * Implementation of AuthService interface to create a new user.
	 * 
	 * @param signUp The SignUp object containing details of the user to be created.
	 * @return The created UserModel object.
	 */
    @Override
    public UserModel createUser(SignUp signUp) {
        UserEntity user = new UserEntity();
        user.setFirstName(signUp.getFirstName());
        user.setLastName(signUp.getLastName());
        user.setEmail(signUp.getEmail());
        user.setPassword(passwordEncoder.encode(signUp.getPassword()));
        String encodedPassword = user.getPassword();
        logger.info("Encoded password: {}", encodedPassword);
        if (user.getPassword() == null) {
            throw new RuntimeException("Password cannot be null!");
        }
        UserEntity createdUser = userRepository.save(user);
        logger.info("Saved user object: {}", createdUser); 
        UserModel userModel = new UserModel();
        userModel.setId(createdUser.getId());
        userModel.setEmail(createdUser.getEmail());
        userModel.setFirstName(createdUser.getFirstName());
        userModel.setLastName(createdUser.getLastName());
        return userModel;
    }

}
