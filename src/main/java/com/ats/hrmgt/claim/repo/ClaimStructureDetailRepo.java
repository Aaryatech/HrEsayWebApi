package com.ats.hrmgt.claim.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.ClaimStructureDetail;

public interface ClaimStructureDetailRepo  extends JpaRepository<ClaimStructureDetail, Integer>{

	List<ClaimStructureDetail> findByClmStructHeadIdAndDelStatus(int headId, int i);

	@Query(value="select * from claim_structure_details sd, claim_structure_header ch,claim_structure_allotment cl where "
			+ "ch.clm_struct_head_id=sd.clm_struct_head_id and cl.clms_id=ch.clm_struct_head_id and cl.emp_id=:empId",nativeQuery=true)
	List<ClaimStructureDetail> getClaimStructureDetailByEmpId(@Param("empId") int empId);
	

}
