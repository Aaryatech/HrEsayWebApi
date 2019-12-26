package com.ats.hrmgt.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GetDailyDailyRecordRepository extends JpaRepository<GetDailyDailyRecord, Integer> {

	@Query(value = "SELECT  id ,  company_id ,  emp_code ,  emp_name ,  att_date ,  att_status ,  lv_sumup_id ,  CONCAT(FLOOR(working_hrs/60),'.',LPAD(MOD(working_hrs,60), 2, '0')) as "
			+ "working_hrs ,  in_time ,  rec_status ,  login_name ,  login_time ,  import_date ,  cmp_code ,  emp_id ,  CONCAT(FLOOR(ot_hr/60),'.',LPAD(MOD(ot_hr,60), 2, '0')) as ot_hr ,"
			+ "current_shiftid ,  late_mark ,  late_min ,  reason ,  current_shiftname ,  freeze_by_supervisor ,  comments_supervisor ,  get_pass_used_count ,  get_pass_used_hour ,"
			+ "get_pass_used_hour_reason ,  raw_data_inout ,  manual_ot_hr ,  full_night ,  half_night ,  out_time ,  early_going_mark ,  early_going_min ,  multiple_entries ,"
			+ "casetype ,  is_fixed ,  by_file_updated ,  location_id ,  emp_type ,  emp_json ,  atsumm_uid ,  file_name ,  row_id  FROM  tbl_attt_daily_daily  "
			+ "WHERE att_date between :fromDate and :toDate and emp_id=:empId", nativeQuery = true)
	List<GetDailyDailyRecord> summaryDailyAttendanceListAll(@Param("fromDate") String fromDate,
			@Param("toDate") String toDate, @Param("empId") int empId);

	@Query(value = "SELECT  id ,  company_id ,  emp_code ,  emp_name ,  att_date ,  att_status ,  lv_sumup_id ,  CONCAT(FLOOR(working_hrs/60),':',LPAD(MOD(working_hrs,60), 2, '0')) as "
			+ "working_hrs ,  in_time ,  rec_status ,  login_name ,  login_time ,  import_date ,  cmp_code ,  emp_id ,  CONCAT(FLOOR(ot_hr/60),':',LPAD(MOD(ot_hr,60), 2, '0')) as ot_hr ,"
			+ "current_shiftid ,  late_mark ,  late_min ,  reason ,  current_shiftname ,  freeze_by_supervisor ,  comments_supervisor ,  get_pass_used_count ,  get_pass_used_hour ,"
			+ "get_pass_used_hour_reason ,  raw_data_inout ,  manual_ot_hr ,  full_night ,  half_night ,  out_time ,  early_going_mark ,  early_going_min ,  multiple_entries ,"
			+ "casetype ,  is_fixed ,  by_file_updated ,  location_id ,  emp_type ,  emp_json ,  atsumm_uid ,  file_name ,  row_id  FROM  tbl_attt_daily_daily  "
			+ "WHERE  id=:dailyId", nativeQuery = true)
	GetDailyDailyRecord getDailyDailyRecordByDailyId(@Param("dailyId") int dailyId);

}
