package com.ats.hrmgt.leave.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.ClaimAuthority;

public interface ClaimAuthorityRepo extends JpaRepository<ClaimAuthority, Integer>{

	ClaimAuthority findByDelStatusAndEmpId(int i, int empId);
/*
	@Transactional
	@Modifying
	@Query("update ClaimType set claim_final_status=:status ,claim_remarks=:remark  WHERE emp_id=:empId and claim_id=:claimId")
	int updateClaimStatus(@Param("empId") int empId,@Param("claimId") int claimId,@Param("status") int status,@Param("remark") String remark);
 */
}
/*SELECT l.*,e.emp_fname,e.emp_mname,e.emp_sname, e.emp_photo,d.emp_dept_name,lt.lv_title FROM leave_apply as l, emp_info as e ,m_emp_department d,leave_type lt 
 * WHERE  l.del_status=1 and l.ex_int1=1 and l.emp_id=e.emp_id and d.emp_dept_id=e.emp_dept_id and lt.lv_type_id=l.lv_type_id 
 * and l.emp_id IN(SELECT emp_id FROM `leave_authority` WHERE ini_auth_emp_id=1 OR fin_auth_emp_id=1) */
 


