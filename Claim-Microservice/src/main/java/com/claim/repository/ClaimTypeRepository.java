package com.claim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claim.entity.ClaimType;

public interface ClaimTypeRepository extends JpaRepository<ClaimType, Integer>{

}
