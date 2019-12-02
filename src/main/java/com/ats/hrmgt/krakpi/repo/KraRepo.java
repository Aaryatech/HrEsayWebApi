package com.ats.hrmgt.krakpi.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.krakpi.model.Kra;


public interface KraRepo extends JpaRepository<Kra, Integer> {
	
	@Transactional
	@Modifying
	@Query("update Kra set del_status=0  WHERE kra_id=:kraId")
	int deleteKra(@Param("kraId") int kraId);

	Kra findByKraIdAndDelStatus(int kraId, int i);

}
