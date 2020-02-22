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
	
	@Query(value = "select * from tbl_attt_daily_daily where att_date between :fromDate and :toDate  AND  company_id=:companyId AND late_mark=1 ORDER BY emp_id ASC ", nativeQuery = true)
	List<DailyAttendance> dailyAttendanceListAll1(int companyId,String fromDate, String toDate);

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
			"    tbl_attt_daily_daily.emp_id =:empId AND tbl_attt_daily_daily.in_time != '00:00:00' \n" + 
			"ORDER BY\n" + 
			"    tbl_attt_daily_daily.id\n" + 
			"DESC\n" + 
			"LIMIT 1", nativeQuery = true)
 	DailyAttendance findLastMonthRecordOfEmp(int empId);

	@Query(value = "select d.* from tbl_attt_daily_daily d,m_employees e where d.att_date = :date and d.emp_id=e.emp_id and e.depart_id in (:departIds)", nativeQuery = true)
	List<DailyAttendance> dailyAttendanceByDeptId(@Param("date") String date,@Param("departIds") List<Integer> departIds);

	 	@Modifying
	    @Query(value = "INSERT INTO tbl_attt_daily_daily (id, company_id, emp_code, emp_name, att_date, att_status,  lv_sumup_id, working_hrs, in_time, rec_status, login_name, login_time, import_date, cmp_code, emp_id, ot_hr,  current_shiftid, late_mark, late_min, reason, current_shiftname, freeze_by_supervisor, comments_supervisor, get_pass_used_count, get_pass_used_hour, get_pass_used_hour_reason, raw_data_inout, manual_ot_hr, full_night, half_night, out_time, early_going_mark, early_going_min, multiple_entries, casetype, is_fixed, by_file_updated, location_id, emp_type, emp_json, atsumm_uid, file_name, row_id) VALUES :string ", nativeQuery = true)
	    @Transactional
	void insert(String string);
	
 
}
