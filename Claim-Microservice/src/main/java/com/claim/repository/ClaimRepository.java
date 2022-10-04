package com.claim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claim.entity.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Integer>{

	List<Claim> findByMemberId(Integer memberId);

	Claim findByClaimId(Integer id);

}
