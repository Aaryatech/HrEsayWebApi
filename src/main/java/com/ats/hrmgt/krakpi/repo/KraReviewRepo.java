package com.ats.hrmgt.krakpi.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

 import com.ats.hrmgt.krakpi.model.KraReview;

public interface KraReviewRepo extends JpaRepository<KraReview, Integer>{
	
	@Transactional
	@Modifying
	@Query("update KraReview set del_status=0  WHERE kra_review_id=:kraReviewId")
	int deleteKraReview(@Param("kraReviewId") int kraReviewId);

	KraReview findByKraReviewIdAndDelStatus(int kraReviewId, int i);

 
}
