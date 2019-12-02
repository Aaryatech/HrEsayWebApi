package com.ats.hrmgt.leave.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.ClaimType;

public interface ClaimRepository extends JpaRepository<ClaimType, Integer> {

	List<ClaimType> findByDelStatusOrderByClaimTypeIdDesc(int i);

	@Transactional
	@Modifying
	@Query("update ClaimType set del_status=0  WHERE claim_type_id=:claimTypeId")
	int deleteClaimType(@Param("claimTypeId") int claimTypeId);

	ClaimType findByClaimTypeIdAndDelStatus(int claimTypeId, int i);

	List<ClaimType> findByDelStatusOrderByCompanyIdDesc(int i);

	List<ClaimType> findByDelStatusAndCompanyIdOrderByCompanyIdDesc(int i, int companyId);

}
