package com.ats.hrmgt.leave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.ClaimDetail;
import com.ats.hrmgt.leave.model.LeaveDetail;

public interface ClaimDetailRepo extends JpaRepository<ClaimDetail, Integer>{

	/*
	 * @Query(value = " SELECT\n" + "ca.*,\n" + "e.emp_fname,\n" + "e.emp_mname,"
	 * +"e.emp_code,"+ "e.emp_sname,\n" + "e.emp_photo,\n" + "d.emp_dept_name,\n" +
	 * "ct.claim_type_title,"+ "p.project_title as project_type_title \n" + "FROM\n"
	 * + "claim_apply AS ca,\n" + "emp_info AS e,\n" + "m_emp_department d,\n" +
	 * "claim_type AS ct," + "project_header as p\n" + "WHERE \n" +
	 * "ca.emp_id =:empId AND ca.ex_int1 IN(:status) AND ca.emp_id = e.emp_id AND ca.del_status = 1 AND d.emp_dept_id = e.emp_dept_id AND ca.claim_type_id = ct.claim_type_id AND ca.project_id=p.project_id "
	 * , nativeQuery = true) List<ClaimDetail> getClaimStatus(@Param("empId") int
	 * empId,@Param("status") List<Integer> status);
	 * 
	 * 
	 * @Query(value = " SELECT\n" + "ca.*,\n" + "e.emp_fname,\n" + "e.emp_mname,"
	 * +"e.emp_code,"+ "e.emp_sname,\n" + "e.emp_photo,\n" + "d.emp_dept_name,\n" +
	 * "ct.claim_type_title,"+ "p.project_type_title \n" + "FROM\n" +
	 * "claim_apply AS ca,\n" + "emp_info AS e,\n" + "m_emp_department d,\n" +
	 * "claim_type AS ct," + "project_type as p\n" + "WHERE \n" +
	 * "ca.emp_id =:empId  AND ca.emp_id = e.emp_id AND ca.del_status = 1 AND d.emp_dept_id = e.emp_dept_id AND ca.claim_type_id = ct.claim_type_id AND ca.project_id=p.project_type_id "
	 * , nativeQuery = true) List<ClaimDetail> getClaimList(@Param("empId") int
	 * empId);
	 */
	
	@Query(value = " SELECT\n" + 
			"   claim_apply.*,claim_type.claim_type_title,claim_type.claim_type_title_short as claim_type_title_sshort\n" + 
			"FROM\n" + 
			"    claim_apply,\n" + 
			"    claim_type\n" + 
			" WHERE\n" + 
			"   claim_type.claim_type_id= claim_apply.claim_type_id AND  claim_apply.ex_int2=:caHeadId AND claim_apply.del_status=1 AND claim_apply.is_active=1", nativeQuery = true)
	List<ClaimDetail> getClaimDetailList(@Param("caHeadId") int caHeadId);
	
	
	
	
	

	
}
