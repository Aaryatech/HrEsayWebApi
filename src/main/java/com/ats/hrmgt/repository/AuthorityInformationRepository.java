package com.ats.hrmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.AuthorityInformation;

public interface AuthorityInformationRepository extends JpaRepository<AuthorityInformation, Integer>{

	
	@Query(value = "SELECT\n" + 
			"    e.emp_id,\n" + 
			"    COALESCE(\n" + 
			"        (\n" + 
			"        SELECT\n" + 
			"            CONCAT(\n" + 
			"                m_employees.surname,\n" + 
			"                ' ',\n" + 
			"                m_employees.first_name\n" + 
			"            )\n" + 
			"        FROM\n" + 
			"            leave_authority,\n" + 
			"            m_employees\n" + 
			"        WHERE\n" + 
			"            leave_authority.emp_id = e.emp_id AND m_employees.emp_id = leave_authority.ini_auth_emp_id\n" + 
			"    ),\n" + 
			"    0\n" + 
			"    ) AS leave_initial_auth,\n" + 
			"    COALESCE(\n" + 
			"        (\n" + 
			"        SELECT\n" + 
			"            CONCAT(\n" + 
			"                m_employees.surname,\n" + 
			"                ' ',\n" + 
			"                m_employees.first_name\n" + 
			"            )\n" + 
			"        FROM\n" + 
			"            leave_authority,\n" + 
			"            m_employees\n" + 
			"        WHERE\n" + 
			"            leave_authority.emp_id = e.emp_id AND m_employees.emp_id = leave_authority.fin_auth_emp_id\n" + 
			"    ),\n" + 
			"    0\n" + 
			"    ) AS leave_final_auth,\n" + 
			"    COALESCE(\n" + 
			"        (\n" + 
			"        SELECT\n" + 
			"            CONCAT(\n" + 
			"                m_employees.surname,\n" + 
			"                ' ',\n" + 
			"                m_employees.first_name\n" + 
			"            )\n" + 
			"        FROM\n" + 
			"            claim_authority,\n" + 
			"            m_employees\n" + 
			"        WHERE\n" + 
			"            claim_authority.emp_id = e.emp_id AND m_employees.emp_id = claim_authority.ca_ini_auth_emp_id\n" + 
			"    ),\n" + 
			"    0\n" + 
			"    ) AS claim_initial_auth,\n" + 
			"    COALESCE(\n" + 
			"        (\n" + 
			"        SELECT\n" + 
			"            CONCAT(\n" + 
			"                m_employees.surname,\n" + 
			"                ' ',\n" + 
			"                m_employees.first_name\n" + 
			"            )\n" + 
			"        FROM\n" + 
			"            claim_authority,\n" + 
			"            m_employees\n" + 
			"        WHERE\n" + 
			"            claim_authority.emp_id = e.emp_id AND m_employees.emp_id = claim_authority.ca_fin_auth_emp_id\n" + 
			"    ),\n" + 
			"    0\n" + 
			"    ) AS claim_final_auth\n" + 
			"FROM\n" + 
			"    m_employees e\n" + 
			"WHERE\n" + 
			"    e.emp_id = :empId", nativeQuery = true)
	AuthorityInformation getAuthorityInfoByEmpId(@Param("empId") int empId);
	
	
 

	
	
}
