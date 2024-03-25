package com.example.User.controller;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.User.Exception.UserNotFoundException;
import com.example.User.entity.UserEntity;
import com.example.User.service.UserService;
import com.example.User.util.JwtUtil;

@RestController
@RequestMapping("/user/")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/hello")
	public String hello() {
		System.out.println("This is called..");
		return "hello from Jwt Authorization";
	}
	
	

	@GetMapping("/fetchUserByEmail/{email}")
	public ResponseEntity<?> fetchUserByEmail(@PathVariable("email") String email) {
		try {
			return userService.fetchUserByEmail(email);
		}catch(UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	@PutMapping("/updateUserdetails/{email}")
	public ResponseEntity<?> updateUserdetails(@PathVariable("email") String email, @RequestBody UserEntity updateUser) {
		try {
			return userService.updateUserdetails(email,updateUser );
		}catch(UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}
	
	@DeleteMapping("/deleteUserdetails/{email}")
	public ResponseEntity<?> deleteUserDetails(@PathVariable("email") String email) {
		try {
			userService.deleteUserDetails(email);
			return ResponseEntity.ok("User details deleted sucess fully.");
		}catch(UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
}
