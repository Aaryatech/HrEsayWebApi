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

	@Query(value = "SELECT * from t_bonus_calc WHERE t_bonus_calc.del_status=1 AND t_bonus_calc.bonus_id=:bonusId ", nativeQuery = true)
	List<BonusCalc> getEmpDetailListForBonus(@Param("bonusId") int bonusId);
	
	
	@Query(value = "SELECT * from t_bonus_calc WHERE t_bonus_calc.del_status=1 AND t_bonus_calc.bonus_id=:bonusId AND t_bonus_calc.ex_int1=1", nativeQuery = true)
	List<BonusCalc> getEmpDetailListForBonusEx(@Param("bonusId") int bonusId);

	@Transactional
	@Modifying
	@Query("update BonusCalc set  is_bonussheet_finalized='Yes',paid_bonus_date =:paidDate  WHERE bonus_id=:bonusId")
	int updateCalcFinalize(@Param("bonusId") int bonusId, @Param("paidDate") String paidDate);
	
	@Transactional
	@Modifying
	@Query("update BonusCalc set  is_exgretia_finalized ='Yes',paid_exgretia_date =:paidDate  WHERE bonus_id=:bonusId")
	int updateCalcFinalizeExgratia(@Param("bonusId") int bonusId, @Param("paidDate") String paidDate);

	@Transactional
	@Modifying
	@Query("Delete From  BonusCalc  WHERE bonus_calc_id=:bonusCalcId")
	int deleteBonus(int bonusCalcId);

	@Transactional
	@Modifying
	@Query("update BonusCalc set  total_exgretia_days =:payableDays,total_exgretia_wages=:formTot ,exgretia_applicable =:isApp,gross_exgretia_amt=:grossExgratiaAmt,ded_exgretia_amt=:dedExgratiaAmt,net_exgretia_amt=:exgratiaAmt,login_id_exgretia=:userId,login_time_exgretia=:dateTime ,ex_int1=1, 	exgratia_prcnt =:exgretia_percentage  WHERE bonus_calc_id=:bonusCalcId")
	int updateExgratiaAmts(@Param("formTot") double formTot, @Param("grossExgratiaAmt") double grossExgratiaAmt,
			@Param("exgratiaAmt") double exgratiaAmt, @Param("dedExgratiaAmt") double dedExgratiaAmt,
			@Param("payableDays") double payableDays, @Param("dateTime") String dateTime, @Param("userId") int userId,
			@Param("isApp") String isApp, @Param("bonusCalcId") int bonusCalcId,@Param("exgretia_percentage") double exgretia_percentage);

	@Transactional
	@Modifying
	@Query("update BonusCalc set  exgretia_details=:json  WHERE bonus_calc_id=:bonusCalcId")
	int updateExgratiaDetails(@Param("json") String json, @Param("bonusCalcId") int bonusCalcId);
	
	@Transactional
	@Modifying
	@Query("update BonusCalc set  total_exgretia_days =0,total_exgretia_wages=0 ,exgretia_applicable =0,gross_exgretia_amt=0,ded_exgretia_amt=0,net_exgretia_amt=0,ex_int1=0  WHERE bonus_calc_id=:bonusCalcId")
	int updateExgratiaAmtsDelete(@Param("bonusCalcId") int bonusCalcId);
 

	BonusCalc findByBonusCalcIdAndDelStatus(int bonusCalcId, int i);
	
	
	
	List<BonusCalc> findByDelStatusAndBonusId(int i,int bonusId);
	
	 

}
