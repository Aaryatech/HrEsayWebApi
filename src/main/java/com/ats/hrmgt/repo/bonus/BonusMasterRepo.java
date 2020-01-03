package com.ats.hrmgt.repo.bonus;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.bonus.BonusMaster;

public interface BonusMasterRepo  extends JpaRepository<BonusMaster, Integer>{

	List<BonusMaster> findByDelStatus(int i);

	BonusMaster findByBonusIdAndDelStatus(int bonusId, int i);

	
	@Transactional
	@Modifying
	@Query("update BonusMaster set del_status=0  WHERE bonus_id=:bonusId")
	int deleteBonus(int bonusId);
	
	
	

}
