package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmpSalAllowance;

public interface EmpSalAllowanceRepo extends JpaRepository<EmpSalAllowance, Integer> {

	List<EmpSalAllowance> findByEmpIdAndDelStatus(int empId, int del);

	@Transactional
	@Modifying
	@Query(value="UPDATE `emp_sal_allowance` SET del_status=0 WHERE emp_id=:empId",nativeQuery=true)
	int deleteEmpAllowances(@Param("empId") int empId);

	List<EmpSalAllowance> findByDelStatus(int i);

 
	EmpSalAllowance findByAllowanceIdAndEmpId(int allowanceId, int empId);

	

}
