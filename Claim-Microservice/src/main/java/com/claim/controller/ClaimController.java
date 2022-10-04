package com.claim.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.entity.Claim;
import com.claim.entity.ClaimType;
import com.claim.service.ClaimService;


@RestController
@RequestMapping("/claim")
public class ClaimController {
	
	@Autowired
	ClaimService claimService;
	
	@PostMapping("/submit")
	public ResponseEntity<String> submitClaim(@RequestBody Claim claim){
		ResponseEntity<String> responseEntity=null;
		try {
			Integer claimId=claimService.submitClaim(claim);
			if(claimId!=null && claimId>0)
				responseEntity=new ResponseEntity<>("Successfully Submitted Claim with Id-"+claimId,HttpStatus.OK);
			else
				responseEntity=new ResponseEntity<>("Failed to Submit Claim. Please try again!",HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			responseEntity=new ResponseEntity<>("Failed to Submit Claim!",HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
	@GetMapping("/getClaimsForMember/{id}")
	public List<Claim> getClaimsForMember(@PathVariable Integer id){
		List<Claim> claims=null;
		try {
			claims=claimService.getClaimsForMember(id);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return claims;
		
	}
	
	@GetMapping("/getClaimTypes")
	public List<ClaimType> getClaimTypes(){
		List<ClaimType> claimsTypes=null;
		try {
			claimsTypes=claimService.getClaimTypes();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return claimsTypes;
		
	}
	
	@GetMapping("/getClaimById/{id}")
	public Optional<Claim> getClaimById(@PathVariable Integer id){
		Optional<Claim> claim=null;
		try {
			claim=claimService.getClaimById(id);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return claim;
	}
	
	@GetMapping("/getMemberByClaimId/{id}")
	public Integer getMemberByClaimId(@PathVariable Integer id){
		Integer memberId=null;
		try {
			memberId=claimService.getMemberByClaimId(id);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return memberId;
	}
	
}
