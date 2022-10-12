package com.member.service;

import java.util.List;
import java.util.Optional;

import com.member.entity.Member;
import com.member.entity.MemberVO;
import com.member.entity.Physician;

public interface MemberService {

	Integer saveMember(Member member);

	List<Member> getAllMembers();

	Optional<Member> getMember(Integer id);

	void deleteMember(Integer id);

	void deleteAllMembers();

	Member updateMember(Member member, Integer id);

	List<Physician> getPhysicians();

	List<MemberVO> searchMember(String firstName, String lastName, Integer claimId, String physicianName,
			Integer memberId);

}
