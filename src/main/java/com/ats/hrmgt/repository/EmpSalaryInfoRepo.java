package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmpSalaryInfo;

public interface EmpSalaryInfoRepo extends JpaRepository<EmpSalaryInfo, Integer> {

	EmpSalaryInfo findByEmpId(int empId);

	@Transactional
	@Modifying
	@Query(value = "UPDATE tbl_emp_salary_info SET  salary_type_id  =:shiftId WHERE emp_id IN(:empIdList)", nativeQuery = true)
	int assignsalStruct(@Param("empIdList") List<Integer> empIdList, @Param("shiftId") String shiftId);

}
