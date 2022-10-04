package com.claim.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Claim {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer claimId;
	
	private String claimAmount;
	
	private Date claimDate;
	
	private Integer claimType;
	
	private String remarks;
	
	private Integer memberId;

	public Integer getClaimId() {
		return claimId;
	}

	public void setClaimId(Integer claimId) {
		this.claimId = claimId;
	}

	public String getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(String claimAmount) {
		this.claimAmount = claimAmount;
	}

	public Date getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(Date claimDate) {
		this.claimDate = claimDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getClaimType() {
		return claimType;
	}

	public void setClaimType(Integer claimType) {
		this.claimType = claimType;
	}


	public Claim(Integer claimId, String claimAmount, Date claimDate, Integer claimType, String remarks,
			Integer memberId) {
		super();
		this.claimId = claimId;
		this.claimAmount = claimAmount;
		this.claimDate = claimDate;
		this.claimType = claimType;
		this.remarks = remarks;
		this.memberId = memberId;
	}

	public Claim() {
		super();
	}	
}
