package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.GetPayrollGeneratedList;

public interface GetPayrollGeneratedListRepo extends JpaRepository<GetPayrollGeneratedList, Integer> {

	@Query(value = "select  concat(e.first_name,' ',e.surname) as name,et.name as emp_type_name,d.name as depart_name,dg.name as design_name,sc.* "
			+ "from tbl_salary_calc sc,m_employees e,tbl_mst_emp_types et,m_department d,m_designation dg  where sc.calc_month=:month and calc_year=:year "
			+ "and e.emp_id=sc.emp_id and et.emp_type_id=sc.emp_type and d.depart_id=sc.depart_id and dg.desig_id=e.designation_id ", nativeQuery = true)
	List<GetPayrollGeneratedList> getPayrollGenratedList(@Param("month") int month, @Param("year") int year);

}
