package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.LeaveAuthority;

public interface LeaveAuthorityRepository extends JpaRepository<LeaveAuthority, Integer> {

	List<LeaveAuthority> findByDelStatus(int i);

	@Transactional
	@Modifying
	@Query("update LeaveAuthority set del_status=0  WHERE la_pkey=:laPkey")
	int deleteLeaveAuthority(@Param("laPkey") int laPkey);

	LeaveAuthority findByLaPkeyAndDelStatus(int laPkey, int i);

	LeaveAuthority findByDelStatusAndEmpId(int i, int empId);

}
