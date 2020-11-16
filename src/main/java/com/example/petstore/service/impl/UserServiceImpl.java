package com.example.petstore.service.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.generated.model.ResponseApi;
import com.example.generated.model.User;
import com.example.petstore.entity.UserEntity;
import com.example.petstore.exceptions.ResourceNotFoundException;
import com.example.petstore.mapper.UserMapper;
import com.example.petstore.repository.UserRepository;
import com.example.petstore.service.UserService;
import com.example.petstore.utils.ResponseApiFactory;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ResponseApiFactory responseFactory;

	@Override
	public ResponseApi createNewUser(@Valid User user) {
		userRepository.save(userMapper.mapToEntity(user));
		return responseFactory.createNew201Response();
	}

	@Override
	public ResponseApi createNewUsersAsArray(User[] userArray) {
		for (User user : userArray) {
			userRepository.save(userMapper.mapToEntity(user));
		}
		return responseFactory.createNew201Response();
	}

	@Override
	public ResponseApi createNewUsersAsList(List<User> userList) {

		userList.forEach(user -> userRepository.save(userMapper.mapToEntity(user)));
		return responseFactory.createNew201Response();
	}

	@Override
	public ResponseApi login(String username, String password) {
		Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);
		if (userEntityOptional.isPresent()) {
			UserEntity userEntity = userEntityOptional.get();
			if (userEntity.getPassword().equals(password)) {
				return responseFactory.createNew200Response();
			} else {
				throw new NullPointerException("Invalid username or password");
			}
		} else {
			throw new ResourceNotFoundException("Invalid username or password");
		}
	}

	@Override
	public ResponseApi logout() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByUsername(String username) {
		Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);
		if (userEntityOptional.isPresent()) {
			return userMapper.mapToDTO(userEntityOptional.get());
		} else {
			throw new ResourceNotFoundException();
		}

	}

	@Override
	public ResponseApi updateUserByUsername(String username, User user) {
		Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);
		if (userEntityOptional.isPresent()) {
			userMapper.updateEntity(userEntityOptional.get(), user);
			userRepository.save(userEntityOptional.get());
			return responseFactory.createNew200Response();
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public ResponseApi deleteUserByUsername(String username) {
		Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);
		if (userEntityOptional.isPresent()) {
			userRepository.delete(userEntityOptional.get());
			return responseFactory.createNew200Response();
		} else {
			throw new ResourceNotFoundException();
		}

	}
}