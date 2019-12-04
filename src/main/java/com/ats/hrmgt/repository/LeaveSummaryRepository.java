package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.LeaveSummary;

public interface LeaveSummaryRepository extends JpaRepository<LeaveSummary, Integer>{

	List<LeaveSummary> findByDelStatusAndCompanyId(int i, int compId);

	List<LeaveSummary> findByDelStatus(int i);

	@Transactional
	@Modifying
	@Query("update LeaveSummary set del_status=0  WHERE lv_sumup_id=:lvSumupId")
	int deleteLeaveSummary(int lvSumupId);

	LeaveSummary findByLvSumupIdAndDelStatus(int lvSumupId, int i);

}
