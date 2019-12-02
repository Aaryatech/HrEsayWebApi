package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmpType;
import com.ats.hrmgt.model.Location;

public interface EmpTypeRepository extends JpaRepository<EmpType, Integer>{

	
	List<EmpType> findByDelStatus(int i);

	@Transactional
	@Modifying
	@Query("update EmpType set del_status=0  WHERE emp_type_id=:empTypeId")
	int deleteEmpType(@Param("empTypeId") int empTypeId);

	EmpType findByEmpTypeIdAndDelStatus(int empTypeId, int i);

	List<EmpType> findByDelStatusAndCompanyId(int i, int compId);

}
