package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.model.LeaveStructureDetails;
 

public interface LeaveStructureDetailsRepo extends JpaRepository<LeaveStructureDetails, Integer> {

	List<LeaveStructureDetails> findByLvsIdAndDelStatus(int lvsId, int i);

}
