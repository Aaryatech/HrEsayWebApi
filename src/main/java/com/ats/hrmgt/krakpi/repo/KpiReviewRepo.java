package com.ats.hrmgt.krakpi.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.krakpi.model.KpiReview;

 
public interface KpiReviewRepo extends JpaRepository<KpiReview,Integer>  {

	KpiReview findByKpiReviewIdAndDelStatus(int kpiReviewId, int i);
	
	@Transactional
	@Modifying
	@Query("update KpiReview set del_status=0  WHERE kpi_review_id=:kpiReviewId")
	int deleteKpiReview(int kpiReviewId);

}
