package com.ats.hrmgt.claim.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.model.ClaimApply;
import com.ats.hrmgt.model.ClaimTrail;

public interface ClaimTrailRepo extends JpaRepository<ClaimTrail, Integer>  {

}
