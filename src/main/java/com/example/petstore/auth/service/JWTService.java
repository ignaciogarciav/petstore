package com.example.petstore.auth.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
public interface JWTService {

	String create(Authentication auth);
	
	UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request);
	
}
