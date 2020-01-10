package com.ats.hrmgt.repo.bonus;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.bonus.BonusApplicable;

public interface BonusApplicableRepo extends JpaRepository<BonusApplicable, Integer> {

	BonusApplicable findByBonusId(int bonusId);

	@Transactional
	@Modifying
	@Query("update BonusApplicable set  is_bonussheet_finalized='1', payroll_month=:month ,payroll_year =:year  WHERE bapp_no=:bonusAppId")
	int updateBonus(@Param("bonusAppId") int bonusAppId, @Param("month") String month, @Param("year") String year);

	@Transactional
	@Modifying
	@Query("update BonusApplicable set  exgretia_formula =:bonus_formula, exgretia_percentage =:exgretia_percentage ,login_id_exgretia=:userId, login_time_exgretia =:dateTime,ded_exgretia_amt_percentage=:ded_exgretia_amt_percentage,is_exgretia_finalized='1' ,exgretia_remark =:remark WHERE bapp_no=:bonusAppId")
	int updateBonusExgratia(@Param("bonusAppId") int bonusAppId, @Param("bonus_formula") String bonus_formula,
			@Param("exgretia_percentage") double exgretia_percentage,@Param("ded_exgretia_amt_percentage") double ded_exgretia_amt_percentage, @Param("userId") int userId,
			@Param("dateTime") String dateTime,@Param("remark") String remark);
	
 
}
