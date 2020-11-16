package com.example.petstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.example.petstore.auth.filter.JWTAuthenticationFilter;
import com.example.petstore.auth.filter.JWTAuthorizationFilter;
import com.example.petstore.auth.service.JWTService;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JWTService jwtService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "/user").permitAll()
//				.antMatchers("/**").permitAll()
				.anyRequest().authenticated().and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtService))
				.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService))
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().headers().frameOptions().sameOrigin();
	}
}
