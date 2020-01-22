package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.model.SalaryCalc;

public interface SalaryCalcRepo extends JpaRepository<SalaryCalc, Integer> {

	SalaryCalc findByEmpId(int empId);
	
	
	List<SalaryCalc> findAllByEmpId(int empId);

 	
	
	

}
