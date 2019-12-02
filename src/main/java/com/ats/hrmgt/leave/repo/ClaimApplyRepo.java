package com.ats.hrmgt.leave.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.ClaimApply;

public interface ClaimApplyRepo extends JpaRepository<ClaimApply, Integer>{

	@Transactional
	@Modifying
	@Query("update ClaimType set claim_final_status=:status ,claim_remarks=:remark  WHERE emp_id=:empId and claim_id=:claimId")
	int updateClaimStatus(@Param("empId") int empId,@Param("claimId") int claimId,@Param("status") int status,@Param("remark") String remark);
 
	

	@Transactional
	@Modifying
	@Query("update ClaimApply set ex_int1=:status  WHERE claim_id=:claimId")
	int updateClaimStatus(int claimId,int status);



	ClaimApply findByClaimIdAndDelStatus(int claimId, int delStatus);


}



