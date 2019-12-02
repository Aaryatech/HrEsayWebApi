package com.ats.hrmgt.krakpi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.krakpi.model.FinancialYear;
 
public interface FinancialYearRepo extends JpaRepository<FinancialYear, Integer> {

	List<FinancialYear> findByDelStatusAndIsActive(int i, int j);

	FinancialYear findByFinYrIdAndDelStatusAndIsActive(int finYrId, int i, int j);
	
	

}
