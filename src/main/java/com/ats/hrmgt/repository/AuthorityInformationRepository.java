package com.ats.hrmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.AuthorityInformation;

public interface AuthorityInformationRepository extends JpaRepository<AuthorityInformation, Integer>{

	
	@Query(value = "select  e.emp_id,coalesce((select concat(emp_info.emp_sname,' ',emp_info.emp_fname)  from leave_authority,emp_info where leave_authority.emp_id=:empId and emp_info.emp_id=leave_authority.ini_auth_emp_id),0) as leave_initial_auth,\n" + 
			"	coalesce((select concat(emp_info.emp_sname,' ',emp_info.emp_fname)  from leave_authority,emp_info where leave_authority.emp_id=:empId and emp_info.emp_id=leave_authority.fin_auth_emp_id),0) as leave_final_auth,\n" + 
			"	coalesce((select concat(emp_info.emp_sname,' ',emp_info.emp_fname)  from claim_authority,emp_info where claim_authority.emp_id=:empId and emp_info.emp_id=claim_authority.ca_ini_auth_emp_id),0) as claim_initial_auth,\n" + 
			"	coalesce((select concat(emp_info.emp_sname,' ',emp_info.emp_fname)  from claim_authority,emp_info where claim_authority.emp_id=:empId and emp_info.emp_id=claim_authority.ca_fin_auth_emp_id),0) as claim_final_auth from emp_info e  where e.emp_id=:empId  ", nativeQuery = true)
	AuthorityInformation getAuthorityInfoByEmpId(@Param("empId") int empId);

	
	
}
