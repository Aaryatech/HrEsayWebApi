package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.MstCompanySub;

public interface MstCompanySubRepo extends JpaRepository<MstCompanySub, Integer>{


	@Transactional
	@Modifying
	@Query("update MstCompanySub set del_status=0  WHERE company_id=:compId")
	int deleteSubComp(int compId);
	
	
	@Transactional
	@Modifying
	@Query("update MstCompanySub set is_active=:stat  WHERE company_id=:compId")
	int activateSubComp(int compId,int stat);


	MstCompanySub findByCompanyIdAndDelStatus(int companyId, int i);


	MstCompanySub findByCompanyId(int companyId);

	

}
