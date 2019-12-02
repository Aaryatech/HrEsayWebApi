package com.ats.hrmgt.leave.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.LeaveStructureHeader;

public interface LeaveStructureHeaderRepo extends JpaRepository<LeaveStructureHeader, Integer> {

	List<LeaveStructureHeader> findByDelStatusAndCompanyId(int i, int companyId);

	LeaveStructureHeader findByLvsIdAndDelStatus(int lvsId, int i);

	@Transactional
	@Modifying
	@Query("update LeaveStructureHeader set del_status=0  WHERE lvs_id=:lvsId")
	int deleteLeaveStructure(@Param("lvsId") int lvsId);

}
