package com.ats.hrmgt.repo.bonus;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.User;
import com.ats.hrmgt.model.bonus.BonusMaster;

public interface BonusMasterRepo  extends JpaRepository<BonusMaster, Integer>{

	List<BonusMaster> findByDelStatus(int i);

	BonusMaster findByBonusIdAndDelStatus(int bonusId, int i);

	
	@Transactional
	@Modifying
	@Query("update BonusMaster set del_status=0  WHERE bonus_id=:bonusId")
	int deleteBonus(int bonusId);

	List<BonusMaster> findByFyTitleAndDelStatus(String bonusTitle, int i);

	
	/*
	 * @Query(value =
	 * " SELECT * FROM m_bonus_fy WHERE BINARY m_bonus_fy.fy_title=:bonusTitle AND  m_bonus_fy.del_status=1"
	 * , nativeQuery = true) List<BonusMaster>
	 * findByFyTitleAndDelStatus(@Param("bonusTitle")String bonusTitle);
	 */
	
	
	

}
