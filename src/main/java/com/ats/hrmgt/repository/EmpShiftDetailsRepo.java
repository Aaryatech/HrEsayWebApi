package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmpShiftDetails;

public interface EmpShiftDetailsRepo extends JpaRepository<EmpShiftDetails, Integer>{
	
	@Transactional
	@Modifying
	@Query("delete from EmpShiftDetails")
	int deleteEmpShiftDetailsAll();
	

}
