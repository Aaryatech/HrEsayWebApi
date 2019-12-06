package com.ats.hrmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.AuthorityInformation;

public interface AuthorityInformationRepository extends JpaRepository<AuthorityInformation, Integer>{

	
	@Query(value = "select\n" + 
			"        e.emp_id,\n" + 
			"        coalesce((select\n" + 
			"            concat(m_employees.surname,\n" + 
			"            ' ',\n" + 
			"            m_employees.first_name)  \n" + 
			"        from\n" + 
			"            leave_authority,\n" + 
			"            m_employees \n" + 
			"        where\n" + 
			"            leave_authority.emp_id=e.emp_id \n" + 
			"            and m_employees.emp_id=leave_authority.ini_auth_emp_id),\n" + 
			"        0) as leave_initial_auth,\n" + 
			"        coalesce((select\n" + 
			"            concat(m_employees.surname,\n" + 
			"            ' ',\n" + 
			"            m_employees.first_name)  \n" + 
			"        from\n" + 
			"            leave_authority,\n" + 
			"            m_employees \n" + 
			"        where\n" + 
			"            leave_authority.emp_id=e.emp_id \n" + 
			"            and m_employees.emp_id=leave_authority.fin_auth_emp_id),\n" + 
			"        0) as leave_final_auth,\n" + 
			"       0 as claim_initial_auth,\n" + 
			"       0 as claim_final_auth \n" + 
			"    from\n" + 
			"        m_employees e  \n" + 
			"    where\n" + 
			"        e.emp_id=:empId", nativeQuery = true)
	AuthorityInformation getAuthorityInfoByEmpId(@Param("empId") int empId);

	
	
}
