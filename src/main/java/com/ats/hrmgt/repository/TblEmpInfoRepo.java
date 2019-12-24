package com.ats.hrmgt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.hrmgt.model.TblEmpInfo;

@Repository
public interface TblEmpInfoRepo extends JpaRepository<TblEmpInfo, Integer> {

	public TblEmpInfo findByEmpIdAndDelStatus(int empId, int del);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE `tbl_emp_info` SET del_status=0 WHERE emp_id=:empId",nativeQuery=true)
	public int deleteEmployeeInfo(@Param("empId") int empId);
}
