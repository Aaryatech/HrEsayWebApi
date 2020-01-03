package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmpSalaryInfoForPayroll;

public interface EmpSalaryInfoForPayrollRepository extends JpaRepository<EmpSalaryInfoForPayroll, Integer> {

	@Query(value = "select e.emp_code,et.name as emp_type_name,l.loc_name,st.sal_type_name,de.name as designation,dp.name as dept_name,concat(e.first_name,' ', e.surname) "
			+ "as emp_name,si.* from tbl_emp_salary_info si,m_employees e,tbl_mst_emp_types et,m_location l,mst_salary_types st,m_designation de,m_department dp "
			+ "where e.emp_id=si.emp_id and e.del_status=1 and et.emp_type_id=e.emp_type and l.loc_id = e.location_id and st.sal_type_id=si.salary_type_id "
			+ "and de.desig_id=e.designation_id and dp.depart_id=e.depart_id and e.emp_id not in (select emp_id from tbl_salary_calc where calc_month=:month and calc_year=:year) "
			+ "order by e.emp_id", nativeQuery = true)
	List<EmpSalaryInfoForPayroll> getEmployeeListWithEmpSalEnfoForPayRoll(@Param("month") int month,
			@Param("year") int year);

}
