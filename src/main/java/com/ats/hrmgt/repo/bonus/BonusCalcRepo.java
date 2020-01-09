package com.ats.hrmgt.repo.bonus;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.bonus.BonusCalc;

public interface BonusCalcRepo extends JpaRepository<BonusCalc, Integer> {

	List<BonusCalc> findByDelStatus(int i);

	BonusCalc findByEmpIdAndBonusIdAndDelStatus(int empId, int bonusId, int i);

	@Query(value = "SELECT * from t_bonus_calc WHERE t_bonus_calc.del_status=1 AND t_bonus_calc.bonus_id=:bonusId", nativeQuery = true)
	List<BonusCalc> getEmpDetailListForBonus(@Param("bonusId") int bonusId);

	@Transactional
	@Modifying
	@Query("update BonusCalc set  is_bonussheet_finalized='Yes',paid_bonus_date =:paidDate  WHERE bonus_id=:bonusId")
	int updateCalcFinalize(@Param("bonusId") int bonusId, @Param("paidDate") String paidDate);

	@Transactional
	@Modifying
	@Query("update BonusCalc set del_status=0  WHERE bonus_calc_id=:bonusCalcId")
	int deleteBonus(int bonusCalcId);

	BonusCalc findByBonusIdAndEmpIdAndDelStatus();

	BonusCalc findByBonusIdAndEmpIdAndDelStatus(int bonusId, int empId, int i);

	int updateExgratia(double formTot, double grossExgratiaAmt, double exgratiaAmt, double dedExgratiaAmt);

}
