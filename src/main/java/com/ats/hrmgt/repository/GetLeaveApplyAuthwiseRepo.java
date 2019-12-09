package com.ats.hrmgt.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.GetLeaveApplyAuthwise;
 


public interface GetLeaveApplyAuthwiseRepo extends JpaRepository<GetLeaveApplyAuthwise, Integer>  {
	
	

	
	//to get initial data 
	
	@Query(value = "SELECT\n" + 
			"        la.leave_id,\n" + 
			"        la.cal_yr_id,\n" + 
			"        la.emp_id,\n" + 
			"        la.lv_type_id,\n" + 
			"        la.leave_duration,\n" + 
			"        la.leave_fromdt,\n" + 
			"        la.leave_todt,\n" + 
			"        la.leave_num_days,\n" + 
			"        la.circulated_to,\n" + 
			"        la.leave_emp_reason,\n" + 
			"        e.emp_code,\n" + 
			"        \"\" as emp_photo,\n" + 
			"        lt.lv_title AS leave_title,\n" + 
			"        CONCAT(e.surname,\n" + 
			"        \" \",\n" + 
			"        e.first_name) AS emp_name,\n" + 
			"        le.ini_auth_emp_id,\n" + 
			"        le.fin_auth_emp_id,\n" + 
			"        la.ex_int1 ,\n" + 
			"        0 AS leave_type_name \n" + 
			"    FROM\n" + 
			"        leave_type lt,\n" + 
			"        leave_apply la,\n" + 
			"        leave_authority le,\n" + 
			"        m_employees e \n" + 
			"    WHERE\n" + 
			"        la.lv_type_id = lt.lv_type_id \n" + 
			"        AND le.emp_id = la.emp_id \n" + 
			"        AND e.emp_id = le.emp_id \n" + 
			"        AND(\n" + 
			"            (\n" + 
			"                le.ini_auth_emp_id=:empId \n" + 
			"                AND la.ex_int1 = 1         \n" + 
			"            ) \n" + 
			"            OR(\n" + 
			"                le.fin_auth_emp_id=:empId \n" + 
			"                AND la.ex_int1 = 2         \n" + 
			"            )          \n" + 
			"        ) \n" + 
			"        AND la.cal_yr_id = :currYrId", nativeQuery = true)

	List<GetLeaveApplyAuthwise> getLeaveApplyList(@Param("empId") int empId,@Param("currYrId") int currYrId);
	
	//final
	
	
	@Query(value = "SELECT\n" + 
			"        la.leave_id,\n" + 
			"        la.cal_yr_id,\n" + 
			"        la.emp_id,\n" + 
			"        la.lv_type_id,\n" + 
			"        la.leave_duration,\n" + 
			"        la.leave_fromdt,\n" + 
			"        la.leave_todt,\n" + 
			"        la.leave_num_days,\n" + 
			"        la.circulated_to,\n" + 
			"        la.leave_emp_reason,\n" + 
			"        e.emp_code,\n" + 
			"        \"\" as emp_photo,\n" + 
			"        lt.lv_title AS leave_title,\n" + 
			"        CONCAT(e.surname,\n" + 
			"        \" \",\n" + 
			"        e.first_name) AS emp_name,\n" + 
			"        le.ini_auth_emp_id,\n" + 
			"        le.fin_auth_emp_id,\n" + 
			"        la.ex_int1,\n" + 
			"        0 AS leave_type_name \n" + 
			"    FROM\n" + 
			"        leave_type lt,\n" + 
			"        leave_apply la,\n" + 
			"        leave_authority le,\n" + 
			"        m_employees e \n" + 
			"    WHERE\n" + 
			"        la.lv_type_id = lt.lv_type_id \n" + 
			"        AND le.emp_id = la.emp_id \n" + 
			"        AND e.emp_id = le.emp_id \n" + 
			"        AND(\n" + 
			"            (\n" + 
			"                le.ini_auth_emp_id=:empId  \n" + 
			"                AND la.ex_int1=2 \n" + 
			"                AND le.fin_auth_emp_id != le.ini_auth_emp_id           \n" + 
			"            ) \n" + 
			"            OR(\n" + 
			"                le.fin_auth_emp_id=:empId \n" + 
			"                AND la.ex_int1=1 \n" + 
			"                AND le.fin_auth_emp_id != le.ini_auth_emp_id           \n" + 
			"            )  \n" + 
			"            OR(\n" + 
			"                le.emp_id=:empId \n" + 
			"                AND  la.ex_int1 in (\n" + 
			"                    2,1\n" + 
			"                )                      \n" + 
			"            )       \n" + 
			"        ) \n" + 
			"        AND la.cal_yr_id =:currYrId \n" + 
			"    ORDER BY\n" + 
			"        la.ex_int1 DESC", nativeQuery = true)

	List<GetLeaveApplyAuthwise> getLeaveApplyList2(@Param("empId") int empId,@Param("currYrId") int currYrId);

	
	@Query(value = "SELECT\n" + 
			"        la.leave_id,\n" + 
			"        la.cal_yr_id,\n" + 
			"        la.emp_id,\n" + 
			"        la.lv_type_id,\n" + 
			"        la.leave_duration,\n" + 
			"        la.leave_fromdt,\n" + 
			"        la.leave_todt,\n" + 
			"        la.leave_num_days,\n" + 
			"        la.circulated_to,\n" + 
			"        la.leave_emp_reason,\n" + 
			"        e.emp_code,\n" + 
			"        \"\" as emp_photo,\n" + 
			"        lt.lv_title AS leave_title,\n" + 
			"        CONCAT(e.surname,\n" + 
			"        \" \",\n" + 
			"        e.first_name) AS emp_name,\n" + 
			"        0 AS ini_auth_emp_id,\n" + 
			"        0 AS fin_auth_emp_id,\n" + 
			"        la.ex_int1,\n" + 
			"        0 AS leave_type_name \n" + 
			"    FROM\n" + 
			"        leave_type lt,\n" + 
			"        leave_apply la,\n" + 
			"        m_employees e \n" + 
			"    WHERE\n" + 
			"        la.emp_id = e.emp_id \n" + 
			"        AND la.lv_type_id = lt.lv_type_id \n" + 
			"        AND la.leave_id = :leaveId" 
			 , nativeQuery = true)

	GetLeaveApplyAuthwise getLeaveApplyDetails(@Param("leaveId") int leaveId);

	
	
	
}