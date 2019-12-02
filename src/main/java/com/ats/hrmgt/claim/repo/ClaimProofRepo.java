package com.ats.hrmgt.claim.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.ClaimProof;

public interface ClaimProofRepo extends JpaRepository<ClaimProof, Integer> {

	List<ClaimProof> findByClaimIdAndDelStatus(int claimId, int i);
	
	
	
	@Transactional
	@Modifying
	@Query("update ClaimProof set del_status=0  WHERE cp_id=:cpId")
	int deleteClaimProof(@Param("cpId") int cpId);

}
