package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmployeeMaster;

public interface EmployeeMasterRepository extends JpaRepository<EmployeeMaster, Integer> {

	@Query(value = " SELECT * FROM m_employees ", nativeQuery = true)
	List<EmployeeMaster> getEmplistForAssignAuthority();

	@Query(value = "SELECT\n" + 
			"        e.*      \n" + 
			"    from\n" + 
			"        m_employees e \n" + 
			"    where\n" + 
			"        e.emp_id NOT IN(\n" + 
			"            SELECT\n" + 
			"                auth.emp_id \n" + 
			"            FROM\n" + 
			"                leave_authority auth  \n" + 
			"            WHERE\n" + 
			"                auth.del_status=1 \n" + 
			"        ) \n" + 
			"     ", nativeQuery = true)
	List<EmployeeMaster> getEmpListByCompanyIdForAuth();

	@Query(value = "SELECT e.* from m_employees e  where e.emp_id IN(:empIdList)", nativeQuery = true)
	List<EmployeeMaster> getEmpListByCompanyIdAndEmpIdList(@Param("empIdList") List<Integer> empIdList);

	@Query(value = "SELECT e.* from m_employees e, leave_authority au where au.emp_id=e.emp_id and (au.ini_auth_emp_id=:empId or au.fin_auth_emp_id=:empId or e.emp_id=:empId)", nativeQuery = true)
	List<EmployeeMaster> getAuthorityWiseEmpListByEmpId(@Param("empId")int empId);

	@Query(value = "select\n" + 
			"        * \n" + 
			"    from\n" + 
			"        m_employees \n" + 
			"    where\n" + 
			"        emp_id not in (\n" + 
			"            select\n" + 
			"                emp_id \n" + 
			"            from\n" + 
			"                leave_balance_cal \n" + 
			"            where\n" + 
			"                cal_yr_id=(\n" + 
			"                    select\n" + 
			"                        cal_yr_id \n" + 
			"                    from\n" + 
			"                        dm_cal_year \n" + 
			"                    where\n" + 
			"                        is_current=1\n" + 
			"                )\n" + 
			"            ) \n" + 
			"            ", nativeQuery = true)
	List<EmployeeMaster> getemplistwhichisnotyearend();

}
