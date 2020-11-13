package com.example.petstore.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.generated.api.UserApi;
import com.example.generated.model.ResponseApi;
import com.example.generated.model.User;
import com.example.petstore.service.UserService;

@RestController
@Valid
public class UserController implements UserApi {

	@Autowired
	private UserService userService;

	@Override
	public ResponseEntity<ResponseApi> createUser(@Valid User body) {
		return new ResponseEntity<ResponseApi>(userService.createNewUser(body), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseApi> createUsersWithArrayInput(@Valid List<User> body) {
		return new ResponseEntity<ResponseApi>(userService.createNewUsersAsList(body), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseApi> createUsersWithListInput(@Valid List<User> body) {
		return new ResponseEntity<ResponseApi>(userService.createNewUsersAsList(body), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseApi> deleteUser(String username) {
		return ResponseEntity.ok(userService.deleteUserByUsername(username));
	}

	@Override
	public ResponseEntity<User> getUserByName(String username) {
		return ResponseEntity.ok(userService.findUserByUsername(username));
	}

	@Override
	public ResponseEntity<ResponseApi> loginUser(@NotNull String username, @NotNull String password) {
		return ResponseEntity.ok(userService.login(username, password));
	}

	@Override
	public ResponseEntity<ResponseApi> logoutUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseApi> updateUser(String username, @Valid User body) {
		return ResponseEntity.ok(userService.updateUserByUsername(username, body));
	}
	

}
