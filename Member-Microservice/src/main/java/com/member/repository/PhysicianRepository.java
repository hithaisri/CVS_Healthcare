package com.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.member.entity.Physician;

public interface PhysicianRepository extends JpaRepository<Physician, Integer>{
	
	Physician findByPhysicianId(Integer physicianId);
	

}
