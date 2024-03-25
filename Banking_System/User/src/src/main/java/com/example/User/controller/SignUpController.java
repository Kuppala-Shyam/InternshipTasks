package com.example.User.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.User.model.SignUp;
import com.example.User.model.UserModel;
import com.example.User.service.AuthService;

@RestController
public class SignUpController {
	 @Autowired
	    private AuthService authService;

	    @PostMapping("/sign-up")
	    public ResponseEntity<?> signupUser(@RequestBody SignUp signUp) {
	       UserModel createdUser = authService.createUser(signUp);
	       if (createdUser == null){
	           return new ResponseEntity<>("User not created, come again later!", HttpStatus.BAD_REQUEST);
	       }
//	       System.out.println("Received data: " + signUp);
	       return new ResponseEntity<>("User signed-up successfully", HttpStatus.CREATED);
	    }
}
