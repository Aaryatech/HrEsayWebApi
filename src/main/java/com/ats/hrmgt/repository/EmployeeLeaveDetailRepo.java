package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.EmployeeLeaveDetail; 

public interface EmployeeLeaveDetailRepo extends JpaRepository<EmployeeLeaveDetail, Integer>{

	@Query(value = " SELECT\n" + 
			"l.*,\n" +  
			"e.emp_fname,\n" + 
			"e.emp_mname,"
			+"e.emp_code,"+
			"e.emp_sname,\n" + 
			"e.emp_photo,\n" + 
			"d.emp_dept_name,\n" + 
			"lt.lv_title ,COALESCE((\n" + 
			"        select\n" + 
			"            concat(e.emp_fname,\n" + 
			"            \" \",\n" + 
			"            e.emp_mname,\n" + 
			"            \" \",\n" + 
			"            e.emp_sname) as user_name \n" + 
			"        from\n" + 
			"            emp_info as e,\n" + 
			"            m_user u \n" + 
			"        where\n" + 
			"            u.user_id=l.maker_user_id \n" + 
			"            and e.emp_id=u.emp_id),null) as user_name\n" + 
			"FROM\n" + 
			"leave_apply AS l,\n" + 
			"emp_info AS e,\n" + 
			"m_emp_department d,\n" + 
			"leave_type AS lt  \n" + 
			"WHERE \n" + 
			"l.emp_id =:empId AND  l.emp_id =e.emp_id AND l.del_status=1 AND d.emp_dept_id = e.emp_dept_id AND lt.lv_type_id = l.lv_type_id ORDER BY l.leave_id DESC ", nativeQuery = true)
	List<EmployeeLeaveDetail> getLeaveListByEmp(@Param("empId") int empId);

	@Query(value = " SELECT\n" + 
			"l.*,\n" +  
			"e.emp_fname,\n" + 
			"e.emp_mname,"
			+"e.emp_code,"+
			"e.emp_sname,\n" + 
			"e.emp_photo,\n" + 
			"d.emp_dept_name,\n" + 
			"lt.lv_title ,COALESCE((\n" + 
			"        select\n" + 
			"            concat(e.emp_fname,\n" + 
			"            \" \",\n" + 
			"            e.emp_mname,\n" + 
			"            \" \",\n" + 
			"            e.emp_sname) as user_name \n" + 
			"        from\n" + 
			"            emp_info as e,\n" + 
			"            m_user u \n" + 
			"        where\n" + 
			"            u.user_id=l.maker_user_id \n" + 
			"            and e.emp_id=u.emp_id),null) as user_name\n" + 
			"FROM\n" + 
			"leave_apply AS l,\n" + 
			"emp_info AS e,\n" + 
			"m_emp_department d,\n" + 
			"leave_type AS lt  \n" + 
			"WHERE \n" + 
			"l.emp_id =:empId AND  l.emp_id = e.emp_id AND l.del_status=:i and l.cal_yr_id=:calYrId  AND d.emp_dept_id = e.emp_dept_id AND lt.lv_type_id = l.lv_type_id ", nativeQuery = true)
	List<EmployeeLeaveDetail> getLeaveListByLocIdAndEmp(@Param("empId") int empId,@Param("calYrId") int calYrId,@Param("i") int i);
}
