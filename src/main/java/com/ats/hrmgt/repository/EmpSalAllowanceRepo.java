package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmpSalAllowance;

public interface EmpSalAllowanceRepo extends JpaRepository<EmpSalAllowance, Integer> {

	List<EmpSalAllowance> findByEmpId(int empId);

	@Transactional
	@Modifying
	@Query(value="DELETE FROM emp_sal_allowance WHERE emp_sal_allowance.emp_id = :empId",nativeQuery=true)
	int deleteEmpAllowances(@Param("empId") int empId);

}