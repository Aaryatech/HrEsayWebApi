package com.ats.hrmgt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.PayDeductionDetails;

public interface PayDeductionDetailsRepo extends JpaRepository<PayDeductionDetails, Integer> {

	@Transactional
	@Modifying
	@Query(value = "UPDATE tblm_pay_deduction_details SET del_status=0 WHERE ded_id=:dedId",nativeQuery=true)
	int deletePayDeductnDetailById(@Param("dedId") int dedId);
	
	

}
