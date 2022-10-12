package com.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.member.entity.Physician;

public interface PhysicianRepository extends JpaRepository<Physician, Integer>{
	
	Physician findByPhysicianId(Integer physicianId);
	
	@Query(value="select physician_name from physician where physician_id=:id",nativeQuery = true)
	String getPhysicianNameById(@Param("id") Integer id);
	

}
