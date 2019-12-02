package com.ats.hrmgt.leave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.GetClaimHead;
 
public interface GetClaimHeadRepo extends JpaRepository<GetClaimHead, Integer>{
	
	
	@Query(value = "SELECT\n" + 
			"    claim_apply_header.ca_head_id, claim_apply_header.ex_var1,\n" + 
			"    claim_apply_header.ca_from_dt as claim_from_date,\n" + 
			"    claim_apply_header.ca_to_dt as claim_to_date,\n" + 
			"    claim_apply_header.claim_title,\n" + 
			"    claim_apply_header.proj_id as project_id,\n" + 
			"    claim_apply_header.emp_id,\n" + 
			"    claim_apply_header.claim_status as claim_final_status,\n" + 
			"    claim_apply_header.claim_amount,\n" + 
			"    emp_info.emp_fname,\n" + 
			"    emp_info.emp_sname,\n" + 
			"    project_header.project_title,emp_info.emp_code,m_emp_department.emp_dept_name\n" + 
			"FROM\n" + 
			"    claim_apply_header,\n" + 
			"    emp_info,\n" + 
			"    project_header,m_emp_department\n" + 
			"WHERE\n" + 
			"    claim_apply_header.proj_id = project_header.project_id AND claim_apply_header.emp_id=emp_info.emp_id AND claim_apply_header.del_status=1 AND claim_apply_header.is_active=1 AND claim_apply_header.emp_id=:empId AND m_emp_department.emp_dept_id=emp_info.emp_dept_id"  , nativeQuery = true)
	List<GetClaimHead> getClaimHeadByEmpId(@Param("empId") int empId);
	

}
