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
	@Query("update EmployeDoc set del_status=0  WHERE doc_id=:docId")
	int deleteEmpDoc(@Param("docId") int docId);

	List<EmployeDoc> findByEmpIdAndDelStatus(int empId, int del);

}
