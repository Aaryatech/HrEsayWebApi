package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmployeeDepartment;

public interface EmployeeDepartmentRepository extends JpaRepository<EmployeeDepartment, Integer>{

	List<EmployeeDepartment> findByDelStatus(int i);

	@Transactional
	@Modifying
	@Query("update EmployeeDepartment set del_status=0  WHERE emp_dept_id=:empDeptId")
	int deleteEmpDept(@Param("empDeptId") int empDeptId);

	EmployeeDepartment findByEmpDeptIdAndDelStatus(int empDeptId, int i);

	List<EmployeeDepartment> findByDelStatusAndCompanyId(int i, int compId);

}
