package com.claim.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.entity.Claim;
import com.claim.entity.ClaimType;
import com.claim.repository.ClaimRepository;
import com.claim.repository.ClaimTypeRepository;
import com.claim.service.ClaimService;

@Service
public class ClaimServiceImpl implements ClaimService {

	@Autowired
	ClaimRepository claimRepository;
	
	@Autowired
	ClaimTypeRepository claimTypeRepository;
	
	@Override
	public Integer submitClaim(Claim claim) {
		Claim newClaim=claimRepository.save(claim);
		return newClaim.getClaimId();
	}

	@Override
	public List<Claim> getClaimsForMember(Integer memberId) {
		return claimRepository.findByMemberId(memberId);
	}

	@Override
	public List<ClaimType> getClaimTypes() {
		return claimTypeRepository.findAll();
	}

	@Override
	public Optional<Claim> getClaimById(Integer id) {
		return claimRepository.findById(id);
	}

	@Override
	public Integer getMemberByClaimId(Integer id) {
		Integer memberId=null;
		Claim claim=claimRepository.findByClaimId(id);
		memberId=claim.getMemberId();
		return memberId;
	}

}
