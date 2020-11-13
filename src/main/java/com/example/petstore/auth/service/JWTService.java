package com.example.petstore.auth.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;

@Service
public interface JWTService {

	String create(Authentication auth);
	
	boolean validateToken(String token);
	
	Claims getClaims(String token);
	
	String getUsername(String token);
	
	String resolve(String token);
}
