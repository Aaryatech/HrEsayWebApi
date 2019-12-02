package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{

	List<Company> findByDelStatusOrderByCompanyIdDesc(int i);
	 
	@Transactional
	@Modifying
	@Query("update Company set del_status=0  WHERE company_id=:compId")
	int deleteCompany(@Param("compId") int compId);

	Company findByCompanyIdAndDelStatus(int i, int j);

}
