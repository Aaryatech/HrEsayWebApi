package com.ats.hrmgt.repository;

import java.util.List;

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

	@Transactional
	@Modifying
	@Query(value = "update tblm_pay_deduction_details set is_deducted=1 where month=:month and year=:year and del_status=1  and is_deducted=0 and emp_id in (:empIds) ",nativeQuery=true)
	int updatePayde(@Param("month")int month, @Param("year") int year, @Param("empIds") List<Integer> empIds);
	
	

}
