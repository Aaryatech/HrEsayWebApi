package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.MstCompanySub;
import com.ats.hrmgt.model.PayableDayAndPresentDays;

public interface MstCompanySubRepo extends JpaRepository<MstCompanySub, Integer> {

 
	@Transactional
	@Modifying
	@Query("update MstCompanySub set is_active=:stat  WHERE company_id=:compId")
	int activateSubComp(int compId, int stat);
	
	
	@Transactional
	@Modifying
	@Query("delete from MstCompanySub where company_id=:compId")
	int deleteSubComp(@Param("compId") int compId);

	MstCompanySub findByCompanyIdAndDelStatus(int companyId, int i);

	MstCompanySub findByCompanyId(int companyId);

	List<MstCompanySub> findByIsActive(int i);

}
