package com.ats.hrmgt.leave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.GetClaimTrailStatus;

public interface GetClaimTrailStatusRepo extends JpaRepository<GetClaimTrailStatus, Integer> {
	
	
	@Query(value = "SELECT\n" + 
			"    claim_trail.claim_id,\n" + 
			"    claim_trail.emp_remarks,\n" + 
			"    claim_trail.claim_status,\n" + 
			"    claim_trail.claim_trail_pkey,\n" + 
			"    claim_apply_header.emp_id,\n" + 
			"    claim_apply_header.claim_title,\n" + 
			"    claim_apply_header.claim_amount,\n" + 
			"    e.emp_code,\n" + 
			"    claim_apply_header.ca_from_dt,\n" + 
			"    claim_apply_header.ca_to_dt,\n" + 
			"    e.emp_fname,\n" + 
			"    e.emp_mname,\n" + 
			"    e.emp_sname,\n" + 
			"    h.project_title,\n" + 
			"    claim_trail.maker_enter_datetime,\n" + 
			"    COALESCE(\n" + 
			"        (\n" + 
			"        SELECT DISTINCT\n" + 
			"            CONCAT(\n" + 
			"                e.emp_fname,\n" + 
			"                \" \",\n" + 
			"                e.emp_mname,\n" + 
			"                \" \",\n" + 
			"                e.emp_sname\n" + 
			"            ) AS user_name\n" + 
			"        FROM\n" + 
			"            emp_info AS e,\n" + 
			"            m_user u\n" + 
			"        WHERE\n" + 
			"            u.user_id = claim_trail.maker_user_id AND e.emp_id = u.emp_id\n" + 
			"    ),\n" + 
			"    NULL\n" + 
			"    ) AS user_name\n" + 
			"FROM\n" + 
			"    claim_apply_header,\n" + 
			"    emp_info e,\n" + 
			"    project_header h,\n" + 
			"    claim_trail\n" + 
			"WHERE\n" + 
			"    claim_apply_header.proj_id = h.project_id AND claim_apply_header.ca_head_id = claim_trail.claim_id AND claim_apply_header.emp_id = e.emp_id AND claim_trail.claim_id =:claimId \n" + 
			"ORDER BY\n" + 
			"    claim_trail.claim_id\n" + 
			"DESC\n" + 
			"    "  , nativeQuery = true)
	List<GetClaimTrailStatus> getClaimTrailByClaimId(@Param("claimId") int claimId);


}
