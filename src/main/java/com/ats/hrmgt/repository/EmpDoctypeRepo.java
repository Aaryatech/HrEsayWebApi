package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.model.EmpDoctype;

public interface EmpDoctypeRepo extends JpaRepository<EmpDoctype, Integer> {

	List<EmpDoctype> findByDelStatusAndCompanyId(int del, int companyId);
	
}
