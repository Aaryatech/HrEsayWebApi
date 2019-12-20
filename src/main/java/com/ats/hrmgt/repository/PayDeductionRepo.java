package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ats.hrmgt.model.PayDeduction;

@Repository
public interface PayDeductionRepo extends JpaRepository<PayDeduction, Integer> {

	public List<PayDeduction> findByDelStatus(int del);
	
	public PayDeduction findBydedTypeIdAndDelStatus(int typeId,int del);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE tbl_pay_deduction SET del_status=0 WHERE ded_type_id=:typeId",nativeQuery=true)
	public int deleteDeductnTypeById(int typeId);
	
}
