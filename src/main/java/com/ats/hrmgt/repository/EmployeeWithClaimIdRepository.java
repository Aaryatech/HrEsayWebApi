package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmployeeWithClaimId;

public interface EmployeeWithClaimIdRepository extends JpaRepository<EmployeeWithClaimId, Integer>{

	@Query(value = "select c.claim_id,c.ex_int2 as ca_head_id,c.claim_type_id,ch.emp_id,concat(emp_fname,\" \",emp_sname) as emp_name,sum(c.claim_amount) as "
			+ "claim_amount from claim_apply c,claim_apply_header ch,emp_info e where ca_from_dt>=:fromDate and ca_to_dt<=:toDate "
			+ "and c.del_status=1 and c.is_active=1 and c.ex_int2=ch.ca_head_id and e.emp_id=ch.emp_id and claim_status=3 group by c.claim_type_id,e.emp_id", nativeQuery = true)
	List<EmployeeWithClaimId> employeeTypeWiseClaimReport(@Param("fromDate") String fromDate,@Param("toDate") String toDate);

	@Query(value = "select c.claim_id,c.ex_int2 as ca_head_id,ch.proj_id as claim_type_id,ch.emp_id,concat(emp_fname,\" \",emp_sname) as emp_name,"
			+ "sum(c.claim_amount) as claim_amount from claim_apply c,claim_apply_header ch,emp_info e where ca_from_dt>=:fromDate and ca_to_dt<=:toDate "
			+ "and c.del_status=1 and c.is_active=1 and c.ex_int2=ch.ca_head_id and e.emp_id=ch.emp_id and claim_status=3 group by ch.proj_id,e.emp_id", nativeQuery = true)
	List<EmployeeWithClaimId> employeeProjectWiseClaimReport(@Param("fromDate") String fromDate,@Param("toDate") String toDate);

}
