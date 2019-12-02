package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.LeaveStructure;
import com.ats.hrmgt.model.LeaveType;

public interface LeaveStructureRepository extends JpaRepository<LeaveStructure, Integer>{

	List<LeaveStructure> findByDelStatus(int i);

	@Transactional
	@Modifying
	@Query("update LeaveStructure set del_status=0  WHERE lvs_pkey=:lvsPkey")
	int deleteLeaveStructure(int lvsPkey);

	LeaveType findByLvsPkeyAndDelStatus(int lvsPkey, int i);

}
