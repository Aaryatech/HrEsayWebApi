package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.SelfGroup;

public interface SelfGroupRepository extends JpaRepository<SelfGroup, Integer> {

	@Query(value = "select * from m_self_grup where del_status=1", nativeQuery = true)
	List<SelfGroup> selfGrouptList();

	
	@Transactional
	@Modifying
	@Query("update SelfGroup set del_status=0  WHERE selft_group_id=:bonusId")
	int deleteSelfGroup(int bonusId);


	SelfGroup findBySelftGroupIdAndDelStatus(int selftGroupId, int i);

}
