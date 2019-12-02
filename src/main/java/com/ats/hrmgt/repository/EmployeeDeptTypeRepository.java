package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmpDocType;

public interface EmployeeDeptTypeRepository extends JpaRepository<EmpDocType, Integer>{

	List<EmpDocType> findByDelStatus(int i);

	EmpDocType findByDoctypeIdAndDelStatus(int doctypeId, int i);

	@Transactional
	@Modifying
	@Query("update EmpDocType set del_status=0  WHERE doctype_id=:doctypeId")
	int deleteEmpDocType(@Param("doctypeId") int doctypeId);

}
