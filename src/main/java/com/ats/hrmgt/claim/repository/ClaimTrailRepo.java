package com.ats.hrmgt.claim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.model.claim.ClaimTrail;

 

public interface ClaimTrailRepo extends JpaRepository<ClaimTrail, Integer>  {

}
