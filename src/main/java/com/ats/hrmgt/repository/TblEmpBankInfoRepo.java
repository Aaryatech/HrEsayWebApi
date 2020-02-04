package com.ats.hrmgt.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.hrmgt.model.TblEmpBankInfo;

public interface TblEmpBankInfoRepo extends JpaRepository<TblEmpBankInfo, Integer> {
	
	TblEmpBankInfo findByEmpIdAndDelStatus(int empId, int del);

	@Transactional
	@Modifying
	@Query(value="UPDATE `tbl_emp_bank_info` SET del_status=0 WHERE emp_id=:empId",nativeQuery=true)
	public int deleteEmpBankInfo(@Param("empId") int empId);
	
	List<TblEmpBankInfo> findByBankIdAndDelStatus(int bankId, int i);
	
}
