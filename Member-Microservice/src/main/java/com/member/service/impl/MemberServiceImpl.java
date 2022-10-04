package com.member.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.member.entity.Member;
import com.member.entity.Physician;
import com.member.entity.User;
import com.member.exception.ResourceNotFoundException;
import com.member.repository.MemberRepository;
import com.member.repository.PhysicianRepository;
import com.member.repository.UserRepository;
import com.member.service.MemberService;


@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PhysicianRepository physicianRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public Integer saveMember(Member member) {
		Integer id=null;
		if(member.getEmail()!=null) {
			User user=userRepository.findByUserEmail(member.getEmail());
			if(user!=null) {
				Member existing=memberRepository.findByEmail(member.getEmail());
				if(existing==null) {
					setPhysicianId(member);
					Member newMember=memberRepository.save(member);
					id=newMember.getId();
					user.setMemberId(id);
					
					userRepository.save(user);
				}
			}
		}
		return id;
	}

	private void setPhysicianId(Member member) {
		List<Integer> list=new ArrayList<Integer>();
		List<Physician> phys=physicianRepository.findAll();
		if(phys!=null && phys.size()>0) {
			for(Physician phy:phys) 
				list.add(phy.getPhysicianId());
			Random rand = new Random();
			member.setPhyiscianId(list.get(rand.nextInt(list.size())));
		}
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
		existingMember.setDob(member.getDob());
		existingMember.setAddress(member.getAddress());
		existingMember.setState(member.getState());
		existingMember.setCity(member.getCity());
		memberRepository.save(existingMember);
		return existingMember;
	}

	@Override
	public List<Physician> getPhysicians() {
		return physicianRepository.findAll();
	}

	@Override
	public List<Member> searchMember(String firstName, String lastName, Integer claimId, String physicianName,
			Integer memberId) {
		List<Member> members=null;
		Optional<Member> mem=null;

		if(StringUtils.isNotBlank(firstName) && StringUtils.isNotBlank(lastName))
			members=memberRepository.findByFirstNameAndLastName(firstName,lastName);
		else if(claimId!=null && claimId>0) {
			Integer mid = this.restTemplate.getForObject("http://claim-service/claim/getMemberByClaimId/"+claimId, Integer.class);
			if(mid!=null && mid>0) 
				mem=memberRepository.findById(mid); 
			if(mem!=null && mem.get()!=null) {
				members=new ArrayList<Member>();
				members.add(mem.get());
			}
		}
		else if(memberId!=null && memberId>0) {
			mem=memberRepository.findById(memberId);
			members=new ArrayList<Member>();
			members.add(mem.get());
		}
		else if(StringUtils.isNotBlank(physicianName))
			members=memberRepository.getMemberByPhysician(physicianName);

		return members;
	}

}
