package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.LeaveTrail;

public interface LeaveTrailRepository extends JpaRepository<LeaveTrail, Integer> {

	@Transactional
	@Modifying
	@Query("delete from LeaveTrail where leave_id=:leaveId")
	int deleteByLeaveId(@Param("leaveId") int leaveId);

	

}
