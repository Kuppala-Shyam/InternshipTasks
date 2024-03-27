package com.example.User.service;

import com.example.User.model.SignUp;
import com.example.User.model.UserModel;

public interface AuthService {
	
	/**
	 * Creates a new user.
	 * 
	 * @param signUp The SignUp object containing details of the user to be created.
	 * @return The created UserModel object.
	 */
	UserModel createUser(SignUp signUp);

}
