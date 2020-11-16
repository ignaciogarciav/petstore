package com.example.petstore.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.generated.model.User;
import com.example.petstore.entity.UserEntity;

@Component
public class UserMapper {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	public UserEntity mapToEntity(User user) {
		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		userEntity.setUsername(user.getUsername());
		userEntity.setPassword(passwordEncoder().encode(user.getPassword()));
		userEntity.setEmail(user.getEmail());
		userEntity.setPhone(user.getPhone());
		userEntity.setUserStatus(user.getUserStatus());

		return userEntity;
	}

	public void updateEntity(UserEntity userEntity, User user) {
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		userEntity.setUsername(user.getUsername());
		userEntity.setPassword(passwordEncoder().encode(user.getPassword()));
		userEntity.setEmail(user.getEmail());
		userEntity.setPhone(user.getPhone());
		userEntity.setUserStatus(user.getUserStatus());
	}

	public User mapToDTO(UserEntity userEntity) {
		User user = new User();
		user.setId(userEntity.getId());
		user.setFirstName(userEntity.getFirstName());
		user.setLastName(userEntity.getLastName());
		user.setUsername(userEntity.getUsername());
		user.setPassword(userEntity.getPassword());
		user.setEmail(userEntity.getEmail());
		user.setPhone(userEntity.getPhone());
		user.setUserStatus(userEntity.getUserStatus());

		return user;
	}
}
