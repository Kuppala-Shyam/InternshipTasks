package com.example.Library.Model;

import com.example.Library.Entity.Roles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpResponse {
	private String firstName;
	private String lastName;
	private String email;
	private Long phoneNumber;
	private Double balance;
	private String address;
	private Roles role;
}
