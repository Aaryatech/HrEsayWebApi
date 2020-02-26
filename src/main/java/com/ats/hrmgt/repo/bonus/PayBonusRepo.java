package com.ats.hrmgt.repo.bonus;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.bonus.PayBonus;

public interface PayBonusRepo extends JpaRepository<PayBonus, Integer> {

	List<PayBonus> findByDelStatus(int i);

	PayBonus findByPayTypeIdAndDelStatus(int payBonusId, int i);
	
	
	
	@Transactional
	@Modifying
	@Query("update PayBonus set del_status=0  WHERE pay_type_id=:bonusId")
	int deleteBonusPayType(int bonusId);
	

}
