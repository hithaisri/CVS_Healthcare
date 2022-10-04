package com.claim.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ClaimType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer claimTypeId;
	
	private String claimType;


	public Integer getClaimTypeId() {
		return claimTypeId;
	}

	public void setClaimTypeId(Integer claimTypeId) {
		this.claimTypeId = claimTypeId;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public ClaimType() {
		super();
	}

	public ClaimType(Integer claimTypeId, String claimType) {
		super();
		this.claimTypeId = claimTypeId;
		this.claimType = claimType;
	}

}
