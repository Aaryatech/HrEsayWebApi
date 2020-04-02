package com.ats.hrmgt.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.SkillRates;

public interface SkillRatesRepo extends JpaRepository<SkillRates, Integer> {

	List<SkillRates> findByDelStatus(int i);

	SkillRates findBySkillIdAndDelStatus(int skillId, int i);
	
	@Transactional
	@Modifying
	@Query("update SkillRates set del_status=0  WHERE skill_id=:skillId")
	int deleteSkillRate(@Param("skillId") int skillId);

	
	
	
	

}
