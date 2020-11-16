package com.example.petstore.service.impl;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.petstore.entity.UserEntity;
import com.example.petstore.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);
		UserEntity userEntity = userEntityOptional.get();
		if (userEntity != null) {
			return new User(userEntity.getUsername(), userEntity.getPassword(),
					Collections.emptyList());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}
