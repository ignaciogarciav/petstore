package com.example.petstore.service.impl;

import java.util.List;

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
		UserEntity userEntity = userRepository.findByUsername(username);
		if (userEntity.getPassword().equals(password)) {
			return responseFactory.createNew200Response();
		}
		throw new ResourceNotFoundException();
	}

	@Override
	public ResponseApi logout() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByUsername(String username) {
		try {
			return userMapper.mapToDTO(userRepository.findByUsername(username));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ResourceNotFoundException();
		}

	}

	@Override
	public ResponseApi updateUserByUsername(String username, User user) {
		try {
			UserEntity userEntity = userRepository.findByUsername(username);
			userMapper.updateEntity(userEntity, user);
			userRepository.save(userEntity);
			return responseFactory.createNew200Response();
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public ResponseApi deleteUserByUsername(String username) {
		try {
			UserEntity userEntity = userRepository.findByUsername(username);
			userRepository.delete(userEntity);
			return responseFactory.createNew200Response();
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ResourceNotFoundException();
		}

	}
}