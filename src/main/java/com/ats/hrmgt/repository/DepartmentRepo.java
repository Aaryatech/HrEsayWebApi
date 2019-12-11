package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {

	public List<Department> findBydelStatusOrderByDepartIdDesc(int del);
	
	public Department findByDepartIdAndDelStatus(int deptId, int del);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_department SET del_status=0 WHERE depart_id=:deptId",nativeQuery=true)
	public int deleteDepartment(@Param("deptId") int deptId);
}
