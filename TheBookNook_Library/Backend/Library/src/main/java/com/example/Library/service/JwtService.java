package com.example.Library.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.Library.Entity.UserEntity;

public interface JwtService {
    String extractUserName(String token);
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
}
