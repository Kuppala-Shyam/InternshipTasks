package com.example.User.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	
	
}
