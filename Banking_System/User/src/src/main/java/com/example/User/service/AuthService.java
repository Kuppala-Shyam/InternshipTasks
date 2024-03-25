package com.example.User.service;

import com.example.User.model.SignUp;
import com.example.User.model.UserModel;

public interface AuthService {

	UserModel createUser(SignUp signUp);

}
