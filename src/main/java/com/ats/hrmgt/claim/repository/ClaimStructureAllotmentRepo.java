package com.ats.hrmgt.claim.repository;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.claim.ClaimStructureAllotment;

 
public interface ClaimStructureAllotmentRepo extends JpaRepository<ClaimStructureAllotment, Integer> {

	ClaimStructureAllotment findByEmpIdAndDelStatus(int empId, int i);

	 
	@Transactional
	@Modifying
	@Query("update ClaimStructureAllotment set clmsId=:lvsId,makerUserId =:userId,makerEnterDatetime=:dateTime  WHERE emp_id=:claimId")
 	int updateClaimStructure(@Param("claimId") int claimId, @Param("userId") int userId,
			@Param("dateTime") String dateTime, @Param("lvsId") int lvsId);

}
