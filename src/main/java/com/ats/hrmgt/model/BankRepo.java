package com.ats.hrmgt.model;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BankRepo extends JpaRepository<Bank, Integer> {

		public List<Bank> findByDelStatusOrderByBankIdDesc(int del);
		
		public Bank findByBankIdAndDelStatus(int bankId, int del);
		
		@Transactional
		@Modifying
		@Query(value="UPDATE m_bank SET del_status=0 WHERE bank_id=:bankId",nativeQuery=true)
		public int deleteBankById(@Param("bankId") int bankId);
}
