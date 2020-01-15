package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.DailyAttendance;

public interface DailyAttendanceRepository extends JpaRepository<DailyAttendance, Integer> {

	@Query(value = "select * from tbl_attt_daily_daily where att_date between :fromDate and :toDate and is_fixed=0 and rec_status='o'", nativeQuery = true)
	List<DailyAttendance> dailyAttendanceList(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	@Query(value = "select * from tbl_attt_daily_daily where att_date between :fromDate and :toDate  ", nativeQuery = true)
	List<DailyAttendance> dailyAttendanceListAll(String fromDate, String toDate);

	@Query(value = "select * from tbl_attt_daily_daily where id=:dailyId", nativeQuery = true)
	DailyAttendance getdailyRecordById(@Param("dailyId") int dailyId);

	@Query(value = "select * from tbl_attt_daily_daily where att_date between :fromDate and :toDate and  emp_id=:empId and is_fixed=0 and rec_status='o'", nativeQuery = true)
	List<DailyAttendance> dailyAttendanceList(@Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("empId") int empId);

	@Transactional
	@Modifying
	@Query("update DailyAttendance set rec_status='F',is_fixed=1 WHERE emp_id in (:empIds) and att_date=:date")
	int fixDailyDailyRecord(@Param("date") String date, @Param("empIds") List<Integer> empIds);
	
	@Transactional
	@Modifying
	@Query("update DailyAttendance set rec_status='F',is_fixed=1 WHERE att_date between :fromDate and :toDate and emp_id in (:empIds) ")
	int fixDailyDailyRecordBetweenDate(@Param("fromDate") String fromDate,@Param("toDate") String toDate, @Param("empIds") List<Integer> empIds);

	
	
	@Query(value = "SELECT\n" + 
			"    tbl_attt_daily_daily.*\n" + 
			"FROM\n" + 
			"    tbl_attt_daily_daily\n" + 
			"WHERE\n" + 
			"    tbl_attt_daily_daily.emp_id =:empId AND YEAR(tbl_attt_daily_daily.att_date) =:year  AND MONTH(tbl_attt_daily_daily.att_date) =:monthValue \n" + 
			"ORDER BY\n" + 
			"    tbl_attt_daily_daily.id\n" + 
			"DESC\n" + 
			"LIMIT 1", nativeQuery = true)
 	DailyAttendance findLastMonthRecordOfEmp(int empId, int monthValue, int year);
	
	
	@Query(value = "SELECT\n" + 
			"    tbl_attt_daily_daily.*\n" + 
			"FROM\n" + 
			"    tbl_attt_daily_daily\n" + 
			"WHERE\n" + 
			"    tbl_attt_daily_daily.emp_id =:empId  \n" + 
			"ORDER BY\n" + 
			"    tbl_attt_daily_daily.id\n" + 
			"DESC\n" + 
			"LIMIT 1", nativeQuery = true)
 	DailyAttendance findLastMonthRecordOfEmp(int empId);
	
 
}
