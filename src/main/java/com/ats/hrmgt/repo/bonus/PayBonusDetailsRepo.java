package com.ats.hrmgt.repo.bonus;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.bonus.PayBonusDetails;

public interface PayBonusDetailsRepo  extends JpaRepository<PayBonusDetails, Integer>{

	PayBonusDetails findByPayIdAndDelStatus(int payDetId, int i);

	List<PayBonusDetails> findByDelStatus(int i);
	
	
	@Transactional
	@Modifying
	@Query("update PayBonusDetails set del_status=0  WHERE pay_id=:bonusId")
	int deleteBonusPay(int bonusId);

	@Transactional
	@Modifying
	@Query(value = "update tblm_pay_bonus_details set is_paid=1 where month=:month and year=:year and del_status=1  and is_paid=0 and emp_id in (:empIds) ",nativeQuery=true)
	int updateBonus(int month, int year, List<Integer> empIds);

}
