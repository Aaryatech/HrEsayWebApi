package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.GetEmployeeAuthorityWise;
import com.ats.hrmgt.model.GetEmployeeInfo;

public interface GetEmpInfoRepo extends JpaRepository<GetEmployeeInfo, Integer> {

	// List<GetEmployeeInfo> getEmpListByCompanyId(int companyId, List<Integer>
	// locIdList);

	@Query(value = " SELECT	  emp_info.emp_code ,emp_info.emp_id,emp_info.emp_fname,emp_info.emp_mname  \n"
			+ "		,emp_info.emp_sname ,emp_info.emp_mobile1 " + ",emp_info.emp_email  \n"
			+ "		 ,m_emp_department.emp_dept_short_name, emp_info.emp_rate_perhr  ,emp_info.emp_prev_exp_yrs , \n"
			+ "		m_emp_department.emp_dept_name as emp_dept ,m_emp_type.emp_type_name emp_type,m_emp_category.emp_cat_name as emp_category,\n"
			+ "		emp_info.company_id,emp_info.ex_var1,m_company.company_name   ,m_emp_category.emp_cat_short_name,m_emp_type.emp_type_short_name, \n"
			+ "		emp_info.emp_cat_id,emp_info.emp_type_id,emp_info.emp_dept_id  from emp_info,m_emp_department,m_emp_type, \n"
			+ "		m_emp_category,m_company where emp_info.emp_dept_id=m_emp_department.emp_dept_id and emp_info.emp_cat_id= \n"
			+ "	    m_emp_category.emp_cat_id  and emp_info.emp_type_id=m_emp_type.emp_type_id and emp_info.del_status=1 \n"
			+ "		and emp_info.is_active=1 and emp_info.company_id=:companyId  and emp_info.loc_id IN(:locIdList) and emp_info.company_id=m_company.company_id "
			+ "    and emp_info.company_id=m_company.company_id and emp_info.ex_int1=1 order by emp_info.emp_id desc ", nativeQuery = true)

	List<GetEmployeeInfo> getEmpListByCompanyId(@Param("companyId") int companyId,
			@Param("locIdList") List<Integer> locIdList);

	@Query(value = " SELECT	  emp_info.emp_code ,emp_info.emp_id,emp_info.emp_fname,emp_info.emp_mname  \n"
			+ "		,emp_info.emp_sname ,emp_info.emp_mobile1 " + ",emp_info.emp_email  \n"
			+ "		 ,m_emp_department.emp_dept_short_name,emp_info.emp_rate_perhr  ,emp_info.emp_prev_exp_yrs , \n"
			+ "		m_emp_department.emp_dept_name as emp_dept ,m_emp_type.emp_type_name emp_type,"
			+ "		m_emp_category.emp_cat_name as emp_category,\n"
			+ "		emp_info.company_id,emp_info.ex_var1,m_company.company_name   , m_emp_category.emp_cat_short_name,m_emp_type.emp_type_short_name,\n"
			+ "		emp_info.emp_cat_id,emp_info.emp_type_id,emp_info.emp_dept_id  "
			+ "		from emp_info,m_emp_department,m_emp_type,m_emp_category,m_company "
			+ "		where emp_info.emp_dept_id=m_emp_department.emp_dept_id and emp_info.emp_cat_id= \n"
			+ "	    m_emp_category.emp_cat_id  and emp_info.emp_type_id=m_emp_type.emp_type_id and emp_info.del_status=1 \n"
			+ "		and emp_info.is_active=1 and emp_info.emp_id=:empId  and emp_info.company_id=m_company.company_id "
			+ "    and emp_info.company_id=m_company.company_id ", nativeQuery = true)

	GetEmployeeInfo getEmpByEmpId(@Param("empId") int empId);

	@Query(value = " SELECT	  emp_info.emp_code ,emp_info.emp_id,emp_info.emp_fname,emp_info.emp_mname,m_emp_department.emp_dept_short_name  \n"
			+ "		,emp_info.emp_sname ,emp_info.emp_mobile1 " + ",emp_info.emp_email  \n"
			+ "		 ,emp_info.emp_rate_perhr  ,emp_info.emp_prev_exp_yrs , \n"
			+ "		m_emp_department.emp_dept_name as emp_dept ,m_emp_type.emp_type_name emp_type,m_emp_category.emp_cat_name as emp_category,\n"
			+ "		emp_info.company_id,emp_info.ex_var1,m_company.company_name   ,m_emp_category.emp_cat_short_name,m_emp_type.emp_type_short_name, \n"
			+ "		emp_info.emp_cat_id,emp_info.emp_type_id,emp_info.emp_dept_id  from emp_info,m_emp_department,m_emp_type, \n"
			+ "		m_emp_category,m_company where emp_info.emp_dept_id=m_emp_department.emp_dept_id and emp_info.emp_cat_id= \n"
			+ "	    m_emp_category.emp_cat_id  and emp_info.emp_type_id=m_emp_type.emp_type_id and emp_info.del_status=1 \n"
			+ "		and emp_info.is_active=1 and emp_info.company_id=:companyId and emp_info.company_id=m_company.company_id "
			+ "    and emp_info.company_id=m_company.company_id  and emp_info.emp_id IN(:empIdList) ", nativeQuery = true)

	List<GetEmployeeInfo> getEmpIdListByCompanyId(@Param("companyId") int companyId,
			  @Param("empIdList") List<GetEmployeeAuthorityWise> empIdList);

	@Query(value = "  SELECT\n" + "        emp_info.emp_code ,\n" + "        emp_info.emp_id,\n"
			+ "        emp_info.emp_fname,\n" + "        emp_info.emp_mname     ,\n" + "        emp_info.emp_sname ,\n"
			+ "        emp_info.emp_mobile1 ,\n" + "        emp_info.emp_email      ,\n"
			+ "        emp_info.emp_rate_perhr  ,\n" + "        emp_info.emp_prev_exp_yrs ,\n"
			+ "        m_emp_department.emp_dept_name as emp_dept ,m_emp_department.emp_dept_short_name, m_emp_category.emp_cat_short_name,m_emp_type.emp_type_short_name,\n" + "        m_emp_type.emp_type_name emp_type,\n"
			+ "        m_emp_category.emp_cat_name as emp_category,\n" + "        emp_info.company_id,\n"
			+ "        emp_info.ex_var1,\n" + "        m_company.company_name   ,\n" + "        emp_info.emp_cat_id,\n"
			+ "        emp_info.emp_type_id,\n" + "        emp_info.emp_dept_id  \n" + "    from\n"
			+ "        emp_info,\n" + "        m_emp_department,\n" + "        m_emp_type,\n"
			+ "        m_emp_category,\n" + "        m_company  \n" + "    where\n"
			+ "        emp_info.emp_dept_id=m_emp_department.emp_dept_id \n"
			+ "        and emp_info.emp_cat_id= m_emp_category.emp_cat_id  \n"
			+ "        and emp_info.emp_type_id=m_emp_type.emp_type_id \n" + "        and emp_info.del_status=1    \n"
			+ "        and emp_info.is_active=1 and emp_info.loc_id IN(:locIdList) \n" + "        and emp_info.company_id=:companyId\n" + "       \n"
			+ "        and emp_info.company_id=m_company.company_id\n"
			+ "         AND emp_info.emp_id NOT IN(SELECT auth.emp_id FROM leave_authority auth  WHERE auth.del_status=1 ) order by emp_info.emp_id desc", nativeQuery = true)

	List<GetEmployeeInfo> getEmpListByCompanyIdForAuth(@Param("companyId") int companyId,@Param("locIdList") List<Integer> locIdList);

	@Query(value = "  SELECT\n" + "        emp_info.emp_code ,\n" + "        emp_info.emp_id,\n"
			+ "        emp_info.emp_fname,\n" + "        emp_info.emp_mname     ,m_emp_department.emp_dept_short_name, m_emp_category.emp_cat_short_name,m_emp_type.emp_type_short_name,\n" + "        emp_info.emp_sname ,\n"
			+ "        emp_info.emp_mobile1 ,\n" + "        emp_info.emp_email      ,\n"
			+ "        emp_info.emp_rate_perhr  ,\n" + "        emp_info.emp_prev_exp_yrs ,\n"
			+ "        m_emp_department.emp_dept_name as emp_dept ,\n" + "        m_emp_type.emp_type_name emp_type,\n"
			+ "        m_emp_category.emp_cat_name as emp_category,\n" + "        emp_info.company_id,\n"
			+ "        emp_info.ex_var1,\n" + "        m_company.company_name   ,\n" + "        emp_info.emp_cat_id,\n"
			+ "        emp_info.emp_type_id,\n" + "        emp_info.emp_dept_id       \n" + "    from\n"
			+ "        emp_info,\n" + "        m_emp_department,\n" + "        m_emp_type,\n"
			+ "        m_emp_category,\n" + "        m_company       \n" + "    where\n"
			+ "        emp_info.emp_dept_id=m_emp_department.emp_dept_id          \n"
			+ "        and emp_info.emp_cat_id= m_emp_category.emp_cat_id           \n"
			+ "        and emp_info.emp_type_id=m_emp_type.emp_type_id          \n"
			+ "        and emp_info.del_status=1             \n" + "        and emp_info.is_active=1          \n"
			+ "        and emp_info.company_id=:companyId               \n"
			+ "        and emp_info.company_id=m_company.company_id          \n"
			+ "        AND emp_info.emp_id AND emp_info.emp_id IN(:empIdList) ", nativeQuery = true)

	List<GetEmployeeInfo> getEmpListByCompanyIdAndEmpIdList(@Param("companyId") int companyId,
			@Param("empIdList") List<Integer> empIdList);

	@Query(value = "  SELECT\n" + "        emp_info.emp_code ,\n" + "        emp_info.emp_id,\n"
			+ "        emp_info.emp_fname,\n" + "        emp_info.emp_mname     ,\n" + "        emp_info.emp_sname ,m_emp_category.emp_cat_short_name,m_emp_type.emp_type_short_name,\n"
			+ "        emp_info.emp_mobile1 ,\n" + "        emp_info.emp_email      ,m_emp_department.emp_dept_short_name, \n"
			+ "        emp_info.emp_rate_perhr  ,\n" + "        emp_info.emp_prev_exp_yrs ,\n"
			+ "        m_emp_department.emp_dept_name as emp_dept ,\n" + "        m_emp_type.emp_type_name emp_type,\n"
			+ "        m_emp_category.emp_cat_name as emp_category,\n" + "        emp_info.company_id,\n"
			+ "        emp_info.ex_var1,\n" + "        m_company.company_name   ,\n" + "        emp_info.emp_cat_id,\n"
			+ "        emp_info.emp_type_id,\n" + "        emp_info.emp_dept_id  \n" + "    from\n"
			+ "        emp_info,\n" + "        m_emp_department,\n" + "        m_emp_type,\n"
			+ "        m_emp_category,\n" + "        m_company  \n" + "    where\n"
			+ "        emp_info.emp_dept_id=m_emp_department.emp_dept_id \n"
			+ "        and emp_info.emp_cat_id= m_emp_category.emp_cat_id  \n"
			+ "        and emp_info.emp_type_id=m_emp_type.emp_type_id \n" + "        and emp_info.del_status=1    \n"
			+ "        and emp_info.is_active=1 and emp_info.loc_id IN(:locIdList) \n" + "        and emp_info.company_id=:companyId\n" + "       \n"
			+ "        and emp_info.company_id=m_company.company_id\n"
			+ "         AND emp_info.emp_id NOT IN(SELECT claim.emp_id FROM claim_authority claim  WHERE claim.del_status=1 )   ", nativeQuery = true)

	List<GetEmployeeInfo> getEmpListByCompanyIdForAuthClaim(@Param("companyId") int companyId,@Param("locIdList") List<Integer> locIdList);
	/*
	 * @Query(value =
	 * "SELECT emp.* FROM emp_info emp ,leave_authority auth WHERE emp.del_status=1 AND emp.is_active=1 AND auth.emp_id NOT IN(emp.emp_id)  AND emp.company_id=:companyId "
	 * , nativeQuery = true)
	 * 
	 * List<EmployeeInfo> findByCompanyIdAndDelStatus(@Param("companyId") int
	 * companyId);
	 */
	
	//Claim
	
	
	@Query(value = " SELECT	  emp_info.emp_code ,emp_info.emp_id,emp_info.emp_fname,emp_info.emp_mname  \n"
			+ "		,emp_info.emp_sname ,emp_info.emp_mobile1 " + ",emp_info.emp_email  \n"
			+ "		 ,m_emp_department.emp_dept_short_name,emp_info.emp_rate_perhr  ,emp_info.emp_prev_exp_yrs ,m_emp_category.emp_cat_short_name,m_emp_type.emp_type_short_name, \n"
			+ "		m_emp_department.emp_dept_name as emp_dept ,m_emp_type.emp_type_name emp_type,m_emp_category.emp_cat_name as emp_category,\n"
			+ "		emp_info.company_id,emp_info.ex_var1,m_company.company_name   , \n"
			+ "		emp_info.emp_cat_id,emp_info.emp_type_id,emp_info.emp_dept_id  from emp_info,m_emp_department,m_emp_type, \n"
			+ "		m_emp_category,m_company where emp_info.emp_dept_id=m_emp_department.emp_dept_id and emp_info.emp_cat_id= \n"
			+ "	    m_emp_category.emp_cat_id  and emp_info.emp_type_id=m_emp_type.emp_type_id and emp_info.del_status=1 \n"
			+ "		and emp_info.is_active=1 and emp_info.company_id=:companyId  and emp_info.company_id=m_company.company_id "
			+ "    and emp_info.company_id=m_company.company_id  and emp_info.emp_id IN(:empIdList) ", nativeQuery = true)

	List<GetEmployeeInfo> getEmpIdListByCompanyIdForClaim(@Param("companyId") int companyId,@Param("empIdList") List<GetEmployeeAuthorityWise> empIdList);

}
