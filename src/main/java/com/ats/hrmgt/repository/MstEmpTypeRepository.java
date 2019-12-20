package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.LeaveType;
import com.ats.hrmgt.model.MstEmpType;

public interface MstEmpTypeRepository extends JpaRepository<MstEmpType, Integer>{

	List<MstEmpType> findByDelStatusAndCompanyId(int i, int companyId);

	MstEmpType findByDelStatusAndEmpTypeId(int i, int empTypeId);

 
	@Transactional
	@Modifying
	@Query("update MstEmpType set del_status=0  WHERE emp_type_id=:lvTypeId")
	int deleteMstType(int lvTypeId);

 	

}
