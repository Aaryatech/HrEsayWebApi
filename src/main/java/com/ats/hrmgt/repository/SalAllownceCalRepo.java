package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.SalAllownceCal;

public interface SalAllownceCalRepo extends JpaRepository<SalAllownceCal, Integer> {

	@Query(value = "select sa.* from tbl_salary_calc_allowance_cal sa,tbl_salary_calc sc where sa.salary_calc_id=sc.id and sc.calc_month=:month and calc_year=:year", nativeQuery = true)
	List<SalAllownceCal> getPayrollAllownceList(@Param("month") int month, @Param("year") int year);

	@Query(value = "select sa.* from tbl_salary_calc_allowance_cal sa,tbl_salary_calc sc where sa.salary_calc_id=sc.id and "
			+ "sc.calc_month=:month and calc_year=:year and sc.emp_id in(:empIds)", nativeQuery = true)
	List<SalAllownceCal> getPayrollAllownceList(@Param("month") int month, @Param("year") int year,@Param("empIds") List<Integer> empIds);

}
