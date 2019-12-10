package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.Designation;

public interface DesignationRepo extends JpaRepository<Designation, Integer> {
	
	public List<Designation> findByCompanyIdAndDelStatus(int companyId, int del);
	
	public Designation findByDesigIdAndCompanyIdAndDelStatus(int desigId, int companyId, int del);
	@Transactional
	@Modifying
	@Query(value="UPDATE m_designation SET del_status=0 WHERE desig_id=:desigId And company_id=:companyId",nativeQuery=true)
	public int deleteDesignation(@Param("desigId") int desigId, @Param("companyId") int companyId);
}
