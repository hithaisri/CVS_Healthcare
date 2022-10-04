package com.claim.service;

import java.util.List;
import java.util.Optional;

import com.claim.entity.Claim;
import com.claim.entity.ClaimType;

public interface ClaimService {

	Integer submitClaim(Claim claim);

	List<Claim> getClaimsForMember(Integer memberId);

	List<ClaimType> getClaimTypes();

	Optional<Claim> getClaimById(Integer id);

	Integer getMemberByClaimId(Integer id);

}
