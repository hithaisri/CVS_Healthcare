package com.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.member.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUserEmailAndPassword(String email, String password);
	
	User findByUserEmail(String email);

}
