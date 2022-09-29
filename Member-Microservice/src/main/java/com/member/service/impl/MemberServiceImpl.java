package com.member.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.member.exception.ResourceNotFoundException;
import com.member.entity.Member;
import com.member.entity.User;
import com.member.repository.MemberRepository;
import com.member.repository.UserRepository;
import com.member.service.MemberService;


@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public Integer saveMember(Member member) {
		Integer id=null;
		if(member.getEmail()!=null) {
			User user=userRepository.findByUserEmail(member.getEmail());
			if(user!=null) {
				Member existing=memberRepository.findByEmail(member.getEmail());
				if(existing==null) {
					Member newMember=memberRepository.save(member);
					id=newMember.getId();
					user.setMemberId(id);
					userRepository.save(user);
				}
			}
		}
		return id;
	}

	@Override
	public List<Member> getAllMembers() {
		return memberRepository.findAll();
	}

	@Override
	public Optional<Member> getMember(Integer id) {
		return memberRepository.findById(id);
	}

	@Override
	public void deleteMember(Integer id) {
		memberRepository.deleteById(id);
	}

	@Override
	public void deleteAllMembers() {
		memberRepository.deleteAll();
		
	}

	@Override
	public Member updateMember(Member member, Integer id) {
		Member existingMember=memberRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Member", "id", id));
		existingMember.setFirstName(member.getFirstName());
		existingMember.setLastName(member.getLastName());
		existingMember.setEmail(member.getEmail());
		//existingMember.setPassword(member.getPassword());
		existingMember.setDob(member.getDob());
		existingMember.setAddress(member.getAddress());
		existingMember.setState(member.getState());
		existingMember.setCity(member.getCity());
		memberRepository.save(existingMember);
		return existingMember;
	}

}
