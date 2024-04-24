package com.example.Library.service;

import com.example.Library.Model.JwtAuthenticationResponse;
import com.example.Library.Model.SignUpRequest;
import com.example.Library.Model.SignUpResponse;
import com.example.Library.Model.SigninRequest;

public interface AuthenticationService {
	 SignUpResponse signup(SignUpRequest request);

	    JwtAuthenticationResponse signin(SigninRequest request);
}
