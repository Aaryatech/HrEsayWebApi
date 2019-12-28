package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.WeeklyOffShit;

public interface WeeklyOffShitRepository extends JpaRepository<WeeklyOffShit, Integer>{
	
	@Query(value="select * from tbl_weekoffshift where weekoffshiftdate between :fromDate and :toDate AND del_status=1 ",nativeQuery=true)
	List<WeeklyOffShit> getWeeklyOffShitList(@Param("fromDate") String fromDate,@Param("toDate") String toDate);

	
	@Query(value="select * from tbl_weekoffshift where tbl_weekoffshift.month=:month AND tbl_weekoffshift.year=:year AND tbl_weekoffshift.location_id=:locId AND del_status=1 AND tbl_weekoffshift.cmp_id=:companyId ",nativeQuery=true)
 	List<WeeklyOffShit> getRecord(@Param("companyId") int companyId,@Param("month") int month,@Param("year") int year,@Param("locId") int locId);

}
