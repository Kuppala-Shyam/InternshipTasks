package com.example.Library.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Library.Entity.UserEntity;
import com.example.Library.Exception.UserNotFoundException;
import com.example.Library.service.UserEntityService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
@CrossOrigin(origins = "*",methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RequestMapping("/user/**")
@RestController
public class UserEntityController {
	@Autowired
	private UserEntityService userEntityService;
	

	@GetMapping("/fetchUserById/{userId}")
	public ResponseEntity<UserEntity> fetchUserById(@PathVariable("userId") Integer userId){
		try {
			UserEntity userEntity=userEntityService.fetchUserById(userId);
			return ResponseEntity.ok(userEntity);
		}catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
		
	}
	
	@PutMapping("/updateUserdetailsByUserEmail/{email}")
	public ResponseEntity<UserEntity> UpdateUserDetailsByEmail(@PathVariable("email") String email, @RequestBody UserEntity updatedUser){
		try {
			UserEntity user = userEntityService.UpdateUserDetailsByEmail(email,updatedUser );
			return ResponseEntity.ok(user);
		}catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
	
	@GetMapping("/fetchAllUsers")
	public ResponseEntity<?> fetchAllUsers(){
		List<UserEntity> user = userEntityService.fetchAllUsers();
		try {
			return ResponseEntity.ok(user);
		}catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
 	}
}
