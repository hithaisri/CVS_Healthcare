package com.member.service;

import com.member.entity.AuthResponse;
import com.member.entity.User;

public interface UserService {

	AuthResponse checkUserExists(String email, String password);

	User saveUser(User user);

}
