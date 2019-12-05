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

}
