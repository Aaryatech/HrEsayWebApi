package com.ats.hrmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.model.SalaryCalc;

public interface SalaryCalcRepo extends JpaRepository<SalaryCalc, Integer> {

	SalaryCalc findByEmpId(int empId);

 	
	
	

}
