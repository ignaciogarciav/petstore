package com.example.petstore.auth.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
public interface JWTService {

	String create(Authentication auth);
	
}
