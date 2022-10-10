package com.member.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.member.entity.AuthResponse;
import com.member.entity.LoginRequest;
import com.member.entity.User;
import com.member.exception.ResourceNotFoundException;
import com.member.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
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
		ResponseEntity<String> responseEntity=null;
		try {
			if(StringUtils.isNotBlank(user.getUserEmail()) && StringUtils.isNotBlank(user.getPassword())) {
				User savedUser=userService.saveUser(user);
				if(savedUser!=null)
					responseEntity= new ResponseEntity<String>("Successfully added User!",HttpStatus.OK);
				else 
					responseEntity= new ResponseEntity<String>("Failed to save User!",HttpStatus.BAD_REQUEST);
			}
			else 
				throw new ResourceNotFoundException("Please fill the mandatory fields!");
		}catch(ResourceNotFoundException e) {
			responseEntity= new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
}
