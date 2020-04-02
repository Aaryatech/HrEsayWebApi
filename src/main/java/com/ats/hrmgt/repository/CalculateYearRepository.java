package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.CalenderYear;
 

public interface CalculateYearRepository extends JpaRepository<CalenderYear, Integer> {

	CalenderYear findByCalYrId(int calYrId);

	CalenderYear findByIsCurrent(int i);

	List<CalenderYear> findByCalYrFromDate(String inputValue);
	List<CalenderYear> findByCalYrFromDateAndCalYrIdNot(String inputValue, int primaryKey);

	List<CalenderYear> findByCalYrToDate(String inputValue);
	List<CalenderYear> findByCalYrToDateAndCalYrIdNot(String inputValue, int primaryKey);

	@Modifying
	@Transactional
	@Query(value="UPDATE `dm_cal_year` SET is_current=0 WHERE cal_yr_id !=:calYrId",nativeQuery=true)
	int updateOtherIds(@Param("calYrId") int calYrId);

	@Query(value="SELECT * FROM `dm_cal_year` ORDER BY cal_yr_id DESC", nativeQuery=true)
	List<CalenderYear> getAllCalYearOrderByDesc();

	@Query(value="SELECT COUNT(*) FROM `dm_cal_year`", nativeQuery=true)
	int countCalyear();
}
