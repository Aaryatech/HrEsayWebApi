package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.GetPayrollGeneratedList;

public interface GetPayrollGeneratedListRepo extends JpaRepository<GetPayrollGeneratedList, Integer> {

	@Query(value = "select\n" + 
			"        concat(e.first_name,\n" + 
			"        ' ',\n" + 
			"        e.surname) as name,\n" + 
			"        et.name as emp_type_name,\n" + 
			"        d.name as depart_name,\n" + 
			"        dg.name as design_name,\n" + 
			"        sc.*,\n" + 
			"        dd.payable_days,\n" + 
			"        dd.present_days,\n" + 
			"        dd.weekly_off,\n" + 
			"        dd.paid_holiday,\n" + 
			"        dd.paid_leave,\n" + 
			"        dd.unpaid_leave\n" + 
			"    from\n" + 
			"        tbl_salary_calc sc,\n" + 
			"        m_employees e,\n" + 
			"        tbl_mst_emp_types et,\n" + 
			"        m_department d,\n" + 
			"        m_designation dg,\n" + 
			"        tbl_attt_summary_daily dd\n" + 
			"    where\n" + 
			"        sc.calc_month=:month \n" + 
			"        and calc_year=:year \n" + 
			"        and e.emp_id=sc.emp_id \n" + 
			"        and et.emp_type_id=sc.emp_type \n" + 
			"        and d.depart_id=sc.depart_id \n" + 
			"        and dg.desig_id=e.designation_id\n" + 
			"        and dd.id=sc.att_sum_id order by e.emp_id asc", nativeQuery = true)
	List<GetPayrollGeneratedList> getPayrollGenratedList(@Param("month") int month, @Param("year") int year);
	
	 
	@Query(value = "select\n" + 
			"        concat(e.first_name,\n" + 
			"        ' ',\n" + 
			"        e.surname) as name,\n" + 
			"        et.name as emp_type_name,\n" + 
			"        d.name as depart_name,\n" + 
			"        dg.name as design_name,\n" + 
			"        sc.*,\n" + 
			"        dd.payable_days,\n" + 
			"        dd.present_days,\n" + 
			"        dd.weekly_off,\n" + 
			"        dd.paid_holiday,\n" + 
			"        dd.paid_leave,\n" + 
			"        dd.unpaid_leave\n" + 
			"    from\n" + 
			"        tbl_salary_calc sc,\n" + 
			"        m_employees e,\n" + 
			"        tbl_mst_emp_types et,\n" + 
			"        m_department d,\n" + 
			"        m_designation dg,\n" + 
			"        tbl_attt_summary_daily dd\n" + 
			"    where\n" + 
			"        sc.calc_month=:month \n" + 
			"        and calc_year=:year \n" + 
			"        and e.emp_id=sc.emp_id \n" + 
			"        and et.emp_type_id=sc.emp_type \n" + 
			"        and d.depart_id=sc.depart_id \n" + 
			"        and dg.desig_id=e.designation_id\n" + 
			"        and dd.id=sc.att_sum_id and e.emp_id in(:empIds) order by e.emp_id asc", nativeQuery = true)
	List<GetPayrollGeneratedList> getPayrollGenratedList(@Param("month") int month, @Param("year") int year,@Param("empIds") List<Integer> empIds);

}
