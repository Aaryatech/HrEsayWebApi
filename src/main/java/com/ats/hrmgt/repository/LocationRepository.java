package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.Location;

public interface LocationRepository extends JpaRepository<Location, Integer>{

	List<Location> findByDelStatus(int i);

	@Transactional
	@Modifying
	@Query("update Location set del_status=0  WHERE loc_id=:locId")
	int deleteLocation(@Param("locId") int locId);

	Location findByLocIdAndDelStatus(int locId, int i);

	List<Location> findByDelStatusAndCompId(int i, int companyId);

	Location findByLocHrContactEmailAndDelStatus(String email, int i);

	
	Location findByLocHrContactNumberAndDelStatus(String mobileNo, int i);

	List<Location> findByDelStatusAndIsActiveAndLocIdIn(int i, int j, List<Integer> locIds);

	List<Location> findByDelStatusAndCompIdOrderByLocIdDesc(int i, int companyId);	

}
