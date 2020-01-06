package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.GetEmployeeDetails;

public interface GetEmployeeDetailsRepo extends JpaRepository<GetEmployeeDetails, Integer>{
	

	@Query(value = "SELECT\n" + 
			"    emp.*,\n" + 
			"    dep.name AS dept_name,\n" + 
			"    dg.name AS emp_desgn,\n" + 
			"    loc.loc_name,\n" + 
			"    con.org_name,\n" + 
			"    sht.shiftname,\n" + 
			"    emptyp.name AS emp_type_name,\n" + 
			"    saltype.sal_type_name,\n" + 
			"    bonus.fy_title\n" + 
			"FROM\n" + 
			"    m_employees emp\n" + 
			"INNER JOIN tbl_emp_salary_info salinfo ON\n" + 
			"    emp.emp_id = salinfo.emp_id\n" + 
			"INNER JOIN t_bonus_calc bonusinfo ON\n" + 
			"    emp.emp_id = bonusinfo.emp_id\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    emp.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"LEFT JOIN m_contractor con ON\n" + 
			"    emp.contractor_id = con.contractor_id\n" + 
			"LEFT JOIN m_location loc ON\n" + 
			"    emp.location_id = loc.loc_id\n" + 
			"LEFT JOIN tbl_mst_emp_types emptyp ON\n" + 
			"    emp.emp_type = emptyp.emp_type_id\n" + 
			"LEFT JOIN tbl_shift_timming sht ON\n" + 
			"    emp.current_shiftid = sht.id\n" + 
			"LEFT JOIN mst_salary_types saltype ON\n" + 
			"    salinfo.salary_type_id = saltype.sal_type_id\n" + 
			"LEFT JOIN m_bonus_fy bonus ON\n" + 
			"    bonus.bonus_id = bonus.bonus_id\n" + 
			"WHERE\n" + 
			"    emp.del_status = 1 AND emp.is_emp = 1", nativeQuery = true)

	List<GetEmployeeDetails> getEmpDetailList();
	
	@Query(value = "SELECT\n" + 
			"    emp.*,\n" + 
			"    dep.name AS dept_name,\n" + 
			"    dg.name AS emp_desgn,\n" + 
			"    loc.loc_name,\n" + 
			"    con.org_name,\n" + 
			"    sht.shiftname,\n" + 
			"    emptyp.name AS emp_type_name,\n" + 
			"    saltype.sal_type_name,\n" + 
			"    bonus.fy_title\n" + 
			"FROM\n" + 
			"    m_employees emp\n" + 
			"INNER JOIN tbl_emp_salary_info salinfo ON\n" + 
			"    emp.emp_id = salinfo.emp_id\n" + 
			"INNER JOIN t_bonus_calc bonusinfo ON\n" + 
			"    emp.emp_id = bonusinfo.emp_id\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    emp.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"LEFT JOIN m_contractor con ON\n" + 
			"    emp.contractor_id = con.contractor_id\n" + 
			"LEFT JOIN m_location loc ON\n" + 
			"    emp.location_id = loc.loc_id\n" + 
			"LEFT JOIN tbl_mst_emp_types emptyp ON\n" + 
			"    emp.emp_type = emptyp.emp_type_id\n" + 
			"LEFT JOIN tbl_shift_timming sht ON\n" + 
			"    emp.current_shiftid = sht.id\n" + 
			"LEFT JOIN mst_salary_types saltype ON\n" + 
			"    salinfo.salary_type_id = saltype.sal_type_id\n" + 
			"LEFT JOIN m_bonus_fy bonus ON\n" + 
			"    bonus.bonus_id = bonus.bonus_id\n" + 
			"WHERE\n" + 
			"    emp.del_status = 1 AND emp.is_emp = 1 AND emp.emp_id=:empId", nativeQuery = true)

 GetEmployeeDetails  getEmpDetailList(@Param("empId") int empId);
	
	
	
	@Query(value = "SELECT\n" + 
			"    emp.*,\n" + 
			"    dep.name AS dept_name,\n" + 
			"    dg.name AS emp_desgn,\n" + 
			"    loc.loc_name,\n" + 
			"    con.org_name,\n" + 
			"    sht.shiftname,\n" + 
			"    emptyp.name AS emp_type_name,\n" + 
			"    saltype.sal_type_name,\n" + 
			"    bonus.fy_title\n" + 
			"FROM\n" + 
			"    m_employees emp\n" + 
			"INNER JOIN tbl_emp_salary_info salinfo ON\n" + 
			"    emp.emp_id = salinfo.emp_id\n" + 
			"INNER JOIN t_bonus_calc bonusinfo ON\n" + 
			"    emp.emp_id = bonusinfo.emp_id\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    emp.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    emp.depart_id = dep.depart_id\n" + 
			"LEFT JOIN m_contractor con ON\n" + 
			"    emp.contractor_id = con.contractor_id\n" + 
			"LEFT JOIN m_location loc ON\n" + 
			"    emp.location_id = loc.loc_id\n" + 
			"LEFT JOIN tbl_mst_emp_types emptyp ON\n" + 
			"    emp.emp_type = emptyp.emp_type_id\n" + 
			"LEFT JOIN tbl_shift_timming sht ON\n" + 
			"    emp.current_shiftid = sht.id\n" + 
			"LEFT JOIN mst_salary_types saltype ON\n" + 
			"    salinfo.salary_type_id = saltype.sal_type_id\n" + 
			"LEFT JOIN m_bonus_fy bonus ON\n" + 
			"    bonus.bonus_id = bonus.bonus_id\n" + 
			"WHERE\n" + 
			"    emp.del_status = 1 AND emp.is_emp = 1 AND emp.location_id IN(:locId)", nativeQuery = true) 
	List<GetEmployeeDetails> getEmpDetailListByLocId(@Param("locId") List<Integer> locId);

	

	

}
