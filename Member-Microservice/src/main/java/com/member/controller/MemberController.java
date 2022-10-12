package com.member.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.member.entity.Member;
import com.member.entity.MemberVO;
import com.member.entity.Physician;
import com.member.service.MemberService;

@RestController
@RequestMapping("/member")
@CrossOrigin(origins = "http://localhost:4200")
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveMember(@RequestBody Member member) {
		ResponseEntity<String> responseEntity=null;
		try {
			Integer id=memberService.saveMember(member);
			if(id!=null && id>0)
				responseEntity=new ResponseEntity<>("Successfully Added Member with Id-"+id,HttpStatus.OK);
			else
				responseEntity=new ResponseEntity<>("Failed to Add Member. Please Register the Member!",HttpStatus.OK);

		}catch(Exception e) {
			e.printStackTrace();
			responseEntity=new ResponseEntity<>("Failed to Save Member!",HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
	@GetMapping("/getMembers")
	public List<Member> getMembers() {
		return memberService.getAllMembers();
	}
	
	@GetMapping("/getMember/{id}")
	public Optional<Member> getMember(@PathVariable Integer id) {
		return memberService.getMember(id);
	}
	
	@DeleteMapping("/deleteMember/{id}")
	public ResponseEntity<String> deleteMember(@PathVariable Integer id) {
		
		ResponseEntity<String> responseEntity=null;
		try {
			memberService.deleteMember(id);
			responseEntity=new ResponseEntity<>("Successfully Deleted Member!",HttpStatus.OK);

		}
		catch(Exception e)
		{
			e.printStackTrace();
			responseEntity=new ResponseEntity<>("Error In Deleting Member!",HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
		
	}
	
	@DeleteMapping("/deleteAllMembers")
	public void deleteAllMembers() {
		memberService.deleteAllMembers();
	}
	
	@PutMapping("/updateMember/{id}")
	public ResponseEntity<Member> updateEmployee(@RequestBody Member member,@PathVariable Integer id) {
		return new	ResponseEntity<Member>(memberService.updateMember(member, id),HttpStatus.OK);
	}
	
	@GetMapping("/getPhysicians")
	public List<Physician> getPhysicians(){
		List<Physician> physicians=null;
		try {
			physicians=memberService.getPhysicians();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return physicians;
		
	}
	
	@GetMapping("/search")
	public List<MemberVO> searchBooks(@RequestParam(required=false) String firstName,@RequestParam(required=false) String lastName,@RequestParam(required=false) Integer claimId,@RequestParam(required=false) String physicianName,@RequestParam(required=false) Integer memberId) {
		return memberService.searchMember(firstName,lastName,claimId,physicianName,memberId);
	}
}
