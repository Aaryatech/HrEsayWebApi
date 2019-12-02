package com.ats.hrmgt.leave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.leave.model.LeaveStructureDetails;

public interface LeaveStructureDetailsRepo extends JpaRepository<LeaveStructureDetails, Integer> {

	List<LeaveStructureDetails> findByLvsIdAndDelStatus(int lvsId, int i);

}
