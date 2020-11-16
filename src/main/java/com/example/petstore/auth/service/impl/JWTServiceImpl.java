package com.example.petstore.auth.service.impl;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.petstore.auth.service.JWTService;

@Component
public class JWTServiceImpl implements JWTService {
	
	public static final String SECRET = Base64Utils.encodeToString("Alguna clave secreta".getBytes());
	public static final Long EXPIRATION_DATE =  3600000L;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";

	@Override
	public String create(Authentication auth) {

		String username = ((User) auth.getPrincipal()).getUsername();
		String token = JWT.create()
				.withSubject(username)
				.withIssuedAt(new Date())
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_DATE))
				.sign(Algorithm.HMAC512(SECRET));
		return token;
	}


}
