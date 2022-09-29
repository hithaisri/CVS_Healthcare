package com.member.service;

import java.util.List;
import java.util.Optional;

import com.member.entity.Member;

public interface MemberService {

	Integer saveMember(Member member);

	List<Member> getAllMembers();

	Optional<Member> getMember(Integer id);

	void deleteMember(Integer id);

	void deleteAllMembers();

	Member updateMember(Member member, Integer id);

}
