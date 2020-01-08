package com.ats.hrmgt.repo.bonus;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.User;
import com.ats.hrmgt.model.bonus.BonusMaster;

public interface BonusMasterRepo extends JpaRepository<BonusMaster, Integer> {

	/*
	 * List<BonusMaster> findByDelStatus(int i);
	 */
	BonusMaster findByBonusIdAndDelStatus(int bonusId, int i);

	@Transactional
	@Modifying
	@Query("update BonusMaster set del_status=0  WHERE bonus_id=:bonusId")
	int deleteBonus(int bonusId);

	List<BonusMaster> findByFyTitleAndDelStatus(String bonusTitle, int i);

	@Query(value = " SELECT\n" + 
			"    *\n" + 
			"FROM\n" + 
			"    m_bonus_fy\n" + 
			"WHERE\n" + 
			"    m_bonus_fy.del_status = 1 AND m_bonus_fy.bonus_id NOT IN(\n" + 
			"    SELECT\n" + 
			"        t_bonus_applicable.bonus_id\n" + 
			"    FROM\n" + 
			"        t_bonus_applicable\n" + 
			"    WHERE\n" + 
			"        t_bonus_applicable.is_payroll_finalized = 'Yes'\n" + 
			")", nativeQuery = true)
	List<BonusMaster> findByDelStatus();

}
