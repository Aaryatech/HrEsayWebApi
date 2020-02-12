package com.ats.hrmgt.advance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.model.advance.AdvanceDetails;

public interface AdvanceDetailsRepo extends JpaRepository<AdvanceDetails, Integer>{

	List<AdvanceDetails> findByAdvId(int advId);

}
