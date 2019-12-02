package com.ats.hrmgt.krakpi.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.krakpi.model.Kpi;

public interface KpiRepo extends JpaRepository<Kpi, Integer> {

	@Transactional
	@Modifying
	@Query("update Kpi set del_status=0  WHERE kra_id=:kraId")
	int updateKpi(int kraId);
	
	@Transactional
	@Modifying
	@Query("update Kpi set del_status=0  WHERE kpi_id=:kpiId")
	int deleteKpi(int kpiId);

	Kpi findByKpiIdAndDelStatus(int kpiId, int i);

	List<Kpi> findByKraIdAndDelStatus(int kraId, int i);

}
