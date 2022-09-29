package com.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.member.entity.AuthResponse;
import com.member.entity.LoginRequest;
import com.member.entity.User;
import com.member.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> checkUserLogin(@RequestBody LoginRequest loginRequest) {
		AuthResponse response=userService.checkUserExists(loginRequest.getEmail(),loginRequest.getPassword());
		return new ResponseEntity<AuthResponse>(response,HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		User savedUser=userService.saveUser(user);
		if(savedUser!=null)
			return new ResponseEntity<String>("Successfully added User!",HttpStatus.OK);
		else 
			return new ResponseEntity<String>("Failed to save User!",HttpStatus.BAD_REQUEST);
	}
}
