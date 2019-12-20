package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.hrmgt.model.MstCompany;

@Repository
public interface MstCompanyRepo extends JpaRepository<MstCompany, Integer> {

	List<MstCompany> findByDelStatusOrderByCompanyIdDesc(int del);
	
	MstCompany findByCompanyIdAndDelStatus(int companyId, int del);
}
