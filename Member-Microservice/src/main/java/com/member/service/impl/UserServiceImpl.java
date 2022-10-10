package com.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.member.entity.AuthResponse;
import com.member.entity.LoggedInUser;
import com.member.entity.User;
import com.member.exception.ResourceNotFoundException;
import com.member.repository.UserRepository;
import com.member.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public AuthResponse checkUserExists(String email, String password) {
		AuthResponse response=null;
		User existingUser=null;
		existingUser=userRepository.findByUserEmailAndPassword(email, password);
		if(existingUser!=null) {
			response=new AuthResponse();
			response.setMessage("Successfully Logged In");
			response.setRole(existingUser.getRole());
			response.setStatusCode(200);
			LoggedInUser loggedInUser=new LoggedInUser();
			loggedInUser.setMemberId(existingUser.getMemberId());
			loggedInUser.setEmail(existingUser.getUserEmail());
			loggedInUser.setUserId(existingUser.getUserId());
			response.setUser(loggedInUser);
		} else {
			response=new AuthResponse();
			response.setMessage("Failed To Login! Check your credentials");
			response.setRole(0);
			response.setStatusCode(400);
			LoggedInUser loggedInUser=new LoggedInUser();
			response.setUser(loggedInUser);	
		}
		return response;
	}

	@Override
	public User saveUser(User user) {
		User newUser=null;
		User exisitngUser=userRepository.findByUserEmail(user.getUserEmail());
		if(exisitngUser==null)
			 newUser=userRepository.save(user);
		else 
			throw new ResourceNotFoundException("User Duplicate Entry!");
		return newUser;
	}
	
}
