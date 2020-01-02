package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.SelfGroup;

public interface SelfGroupRepository extends JpaRepository<SelfGroup, Integer> {

	@Query(value = "select * from m_self_grup where del_status=1", nativeQuery = true)
	List<SelfGroup> selfGrouptList();

}
