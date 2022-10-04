package com.member.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{

	Member findByEmail(String email);

	List<Member> findByFirstNameAndLastName(String firstName, String lastName);
		
	@Query(value="select m.* from member m,physician p where m.phyiscian_id=p.physician_id and p.physician_name=:physicianName ",nativeQuery = true)
	List<Member> getMemberByPhysician(String physicianName);

}
