package com.ats.hrmgt.repo;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.hrmgt.model.RouteDriver;
@Repository
public interface RouteDriverRepo extends JpaRepository<RouteDriver, Integer>{
	
	List<RouteDriver> findAllByDelStatusOrderByRouteIdDesc(int del);
	
	RouteDriver findByRouteId(int rId);

	@Transactional
	@Modifying
	@Query(value="UPDATE route_driver SET del_status=0 WHERE route_Id=:rId",nativeQuery=true)
	int delRoutById(@Param("rId") int rId);
}
