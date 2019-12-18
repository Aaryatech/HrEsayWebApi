package com.ats.hrmgt.claim.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.claim.GetClaimAuthority;

 
public interface GetClaimAuthorityRepo extends JpaRepository<GetClaimAuthority, Integer> {

	@Query(value = " SELECT auth.* \n"
			+ ",coalesce((SELECT e.emp_fname  FROM emp_info e WHERE auth.del_status=1 AND e.emp_id=auth.emp_id),null) as emp_fname\n"
			+ ",coalesce((SELECT e.emp_mname FROM emp_info e WHERE auth.del_status=1 AND e.emp_id=auth.emp_id),null) as emp_mname\n"
			+ ",coalesce((SELECT e.emp_sname FROM emp_info e WHERE auth.del_status=1 AND e.emp_id=auth.emp_id),null) as emp_sname\n"
			+ ",coalesce((SELECT e.emp_code FROM emp_info e WHERE auth.del_status=1 AND e.emp_id=auth.emp_id),null) as emp_code\n"
			+ "\n"
			+ ",coalesce((SELECT e.emp_fname FROM emp_info e WHERE auth.del_status=1 AND e.emp_id=auth.ca_ini_auth_emp_id),null) as ini_emp_fname\n"
			+ ",coalesce((SELECT e.emp_mname FROM emp_info e WHERE auth.del_status=1 AND e.emp_id=auth.ca_ini_auth_emp_id),null) as ini_emp_mname\n"
			+ ",coalesce((SELECT e.emp_sname FROM emp_info e WHERE auth.del_status=1 AND e.emp_id=auth.ca_ini_auth_emp_id),null) as ini_emp_sname\n"
			+ ",coalesce((SELECT e.emp_code FROM emp_info e WHERE auth.del_status=1 AND e.emp_id=auth.ca_ini_auth_emp_id),null) as ini_emp_code\n"
			+ "\n" + "\n" + "\n"
			+ ",coalesce((SELECT e.emp_fname FROM emp_info e WHERE auth.del_status=1 AND e.emp_id=auth.ca_fin_auth_emp_id),null) as fini_emp_fname\n"
			+ ",coalesce((SELECT e.emp_mname FROM emp_info e WHERE auth.del_status=1 AND e.emp_id=auth.ca_fin_auth_emp_id),null) as fini_emp_mname\n"
			+ ",coalesce((SELECT e.emp_sname FROM emp_info e WHERE auth.del_status=1 AND e.emp_id=auth.ca_fin_auth_emp_id),null) as fini_emp_sname\n"
			+ ",coalesce((SELECT e.emp_sname FROM emp_info e WHERE auth.del_status=1 AND e.emp_id=auth.ca_fin_auth_emp_id),null) as fini_emp_code\n"
			+ "\n" + "FROM claim_authority auth ,emp_info ei\n"
			+ "WHERE auth.del_status=1 AND auth.company_id=:companyId AND  ei.emp_id=auth.emp_id AND ei.loc_id IN(:locIdList) AND ei.del_status=1\n"
			+ " ", nativeQuery = true)

	List<GetClaimAuthority> getClaimAuth(@Param("companyId") int companyId,
			@Param("locIdList") List<Integer> locIdList);

}
