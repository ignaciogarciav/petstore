package com.example.petstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.generated.model.ResponseApi;
import com.example.generated.model.User;

@Service
public interface UserService {

	ResponseApi createNewUser(User user);

	ResponseApi createNewUsersAsArray(User[] userArray);

	ResponseApi createNewUsersAsList(List<User> userList);

	ResponseApi login(String username, String password);

	ResponseApi logout();

	User findUserByUsername(String username);

	ResponseApi updateUserByUsername(String username, User user);

	ResponseApi deleteUserByUsername(String username);
}
