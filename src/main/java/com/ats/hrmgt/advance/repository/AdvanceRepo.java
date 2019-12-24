package com.ats.hrmgt.advance.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.advance.Advance;

public interface AdvanceRepo extends JpaRepository<Advance, Integer> {

	List<Advance> findByDelStatusAndIsDedAndCmpId(int i, int j, int companyId);
	
	@Transactional
	@Modifying
	@Query("update Advance set del_status=0  WHERE id=:advId")
	int deleteAdvance(int advId);
	
	 Advance findById(int i);

	List<Advance> findByVoucherNoAndDelStatus(String voucherNo, int i);
	

}
