package com.ats.hrmgt.claim.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.model.claim.ClaimStructureDetail;

 
public interface ClaimStructureDetailRepo  extends JpaRepository<ClaimStructureDetail, Integer>{

	List<ClaimStructureDetail> findByClmStructHeadIdAndDelStatus(int headId, int i);
	

}
