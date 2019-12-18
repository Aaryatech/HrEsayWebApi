package com.ats.hrmgt.claim.repository;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.claim.ClaimStructureHeader;

 
public interface ClaimStructureHeaderRepo extends JpaRepository<ClaimStructureHeader, Integer> {

	List<ClaimStructureHeader> findByDelStatusAndCompanyId(int i, int companyId);

 
 	
	
	
	@Transactional
	@Modifying
	@Query("update ClaimStructureHeader set del_status=0  WHERE clm_struct_head_id=:clmsId")
	int deleteClaimStructure(@Param("clmsId") int clmsId);





	ClaimStructureHeader findByClmStructHeadIdAndDelStatus(int headId, int i);
}
