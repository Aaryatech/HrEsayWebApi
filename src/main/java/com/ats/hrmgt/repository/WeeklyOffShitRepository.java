package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.WeeklyOffShit;

public interface WeeklyOffShitRepository extends JpaRepository<WeeklyOffShit, Integer>{
	
	@Query(value="select * from tbl_weekoffshift where weekoffshiftdate between :fromDate and :toDate ",nativeQuery=true)
	List<WeeklyOffShit> getWeeklyOffShitList(@Param("fromDate") String fromDate,@Param("toDate") String toDate);

}
