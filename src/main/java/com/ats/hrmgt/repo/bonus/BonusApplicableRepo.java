package com.ats.hrmgt.repo.bonus;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.bonus.BonusApplicable;

public interface BonusApplicableRepo  extends JpaRepository<BonusApplicable, Integer>{

	BonusApplicable findByBonusId(int bonusId);

	
	
	@Transactional
	@Modifying
	@Query("update BonusApplicable set  is_bonussheet_finalized='1', payroll_month=:month ,payroll_year =:year  WHERE bapp_no=:bonusAppId")
	int updateBonus(@Param("bonusAppId") int bonusAppId, String month, String year);

 	
	
	

}
