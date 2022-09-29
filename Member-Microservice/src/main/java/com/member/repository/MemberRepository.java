package com.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{

	Member findByEmail(String email);

}
