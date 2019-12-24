package com.ats.hrmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.hrmgt.model.TblEmpNominees;

public interface TblEmpNomineesRepo extends JpaRepository<TblEmpNominees, Integer> {

	TblEmpNominees findByEmpIdAndDelStatus(int empId, int del);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE `tbl_emp_nominees` SET del_status=0 WHERE emp_id=:empId",nativeQuery=true)
	int deleteEmpNominee(@Param("empId") int empId);
}
