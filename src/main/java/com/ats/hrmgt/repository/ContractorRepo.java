package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.Contractor;

public interface ContractorRepo extends JpaRepository<Contractor, Integer> {
	
		public List<Contractor> findByDelStatusOrderByContractorIdDesc(int del);
		
		public Contractor findByContractorIdAndDelStatus(int contractorId, int del);
		
		@Transactional
		@Modifying
		@Query(value = "UPDATE m_contractor SET del_status = 0 WHERE contractor_id=:contractorId",nativeQuery=true)
		public int deleteContractorById(@Param("contractorId") int contractorId);
}
