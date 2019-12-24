package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmployeDoc;


public interface EmployeeDocsRepository extends JpaRepository<EmployeDoc, Integer>
{

	List<EmployeDoc> findByDelStatus(int i);

	EmployeDoc findByDocIdAndDelStatus(int docId, int i);

	@Transactional
	@Modifying
	@Query(value="UPDATE emp_docs SET del_status=1 WHERE emp_id=:empId",nativeQuery=true)
	int deleteEmpDoc(@Param("empId") int empId);

	List<EmployeDoc> findByEmpIdAndDelStatus(int empId, int del);

}
