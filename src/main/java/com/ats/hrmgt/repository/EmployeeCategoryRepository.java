package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmployeeCategory;

public interface EmployeeCategoryRepository extends JpaRepository<EmployeeCategory, Integer>{

	List<EmployeeCategory> findByDelStatus(int i);

	@Transactional
	@Modifying
	@Query("update EmployeeCategory set del_status=0  WHERE emp_cat_id=:empCatId")
	int deleteEmpCategory(@Param("empCatId") int empCatId);

	EmployeeCategory findByEmpCatIdAndDelStatus(int empCatId, int i);

	List<EmployeeCategory> findByDelStatusAndCompanyId(int i, int compId);

}
