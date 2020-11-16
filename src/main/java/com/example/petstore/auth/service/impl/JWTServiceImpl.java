package com.example.petstore.auth.service.impl;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
	public static final Long EXPIRATION_DATE = 3600000L;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";

	@Override
	public String create(Authentication auth) {

		String username = ((User) auth.getPrincipal()).getUsername();
		String token = JWT.create().withSubject(username)
				.withIssuedAt(new Date())
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_DATE))
				.sign(Algorithm.HMAC512(SECRET.getBytes()));
		return token;
	}

	@Override
	public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(JWTServiceImpl.HEADER_STRING);
		if (token != null) {
			String user = JWT.require(Algorithm.HMAC512(JWTServiceImpl.SECRET))
					.build()
					.verify(token.replace(JWTServiceImpl.TOKEN_PREFIX, ""))
					.getSubject();

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
			return null;
		}
		return null;
	}

}
