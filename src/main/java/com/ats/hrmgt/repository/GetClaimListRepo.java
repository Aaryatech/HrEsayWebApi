package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.GetClaimList;

public interface GetClaimListRepo extends JpaRepository<GetClaimList, Integer>{

	@Query(value = "select ca_head_id,emp_id, sum(claim_amount) as claim_amount from claim_apply_header where month=:month and year=:year "
			+ "and claim_status=3 and is_paid=0 and emp_id in (:empIds) group by emp_id", nativeQuery = true)
	List<GetClaimList> getClaimList(@Param("month") int month, @Param("year") int year,
			@Param("empIds") List<Integer> empIds);

}
