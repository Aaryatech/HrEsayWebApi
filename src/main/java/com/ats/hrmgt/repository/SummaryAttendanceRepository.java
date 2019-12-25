package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.SummaryAttendance;

public interface SummaryAttendanceRepository extends JpaRepository<SummaryAttendance, Integer> {

	@Query(value = "select ds.id,ds.emp_id,ds.emp_code,ds.emp_name,working_days,present_days,weekly_off,paid_holiday,paid_leave,legal_strike,lay_off,unpaid_holiday,"
			+ "unpaid_leave,absent_days,payable_days,ncp_days,CONCAT(FLOOR(totlate_mins/60),'.',LPAD(MOD(totlate_mins,60), 2, '0')) as totlate_mins,totlate_days,"
			+ "CONCAT(FLOOR(totout_mins/60),'.',LPAD(MOD(totout_mins,60), 2, '0')) as totout_mins,CONCAT(FLOOR(totworking_hrs/60),'.',LPAD(MOD(totworking_hrs,60), 2, '0')) "
			+ "as totworking_hrs,CONCAT(FLOOR(totot_hrs/60),'.',LPAD(MOD(totot_hrs,60), 2, '0')) as totot_hrs,CONCAT(FLOOR(tot_othr/60),'.',LPAD(MOD(tot_othr,60), 2, '0')) "
			+ "as tot_othr,tot_late,hdpresent_hdleave,es.sal_basis,ds.total_days_inmonth from tbl_attt_summary_daily ds,tbl_emp_salary_info es where month=:month "
			+ "and year=:year and es.emp_id=ds.emp_id", nativeQuery = true)
	List<SummaryAttendance> summaryDailyAttendanceListAll(@Param("month") int month, @Param("year") int year);

	@Query(value = "select ds.id,ds.emp_id,ds.emp_code,ds.emp_name,working_days,present_days,weekly_off,paid_holiday,paid_leave,legal_strike,lay_off,unpaid_holiday,"
			+ "unpaid_leave,absent_days,payable_days,ncp_days,CONCAT(FLOOR(totlate_mins/60),'.',LPAD(MOD(totlate_mins,60), 2, '0')) as totlate_mins,totlate_days,"
			+ "CONCAT(FLOOR(totout_mins/60),'.',LPAD(MOD(totout_mins,60), 2, '0')) as totout_mins,CONCAT(FLOOR(totworking_hrs/60),'.',LPAD(MOD(totworking_hrs,60), 2, '0')) "
			+ "as totworking_hrs,CONCAT(FLOOR(totot_hrs/60),'.',LPAD(MOD(totot_hrs,60), 2, '0')) as totot_hrs,CONCAT(FLOOR(tot_othr/60),'.',LPAD(MOD(tot_othr,60), 2, '0')) "
			+ "as tot_othr,tot_late,hdpresent_hdleave,es.sal_basis,ds.total_days_inmonth from tbl_attt_summary_daily ds,tbl_emp_salary_info es where month=:month "
			+ "and year=:year and es.emp_id=ds.emp_id and ds.emp_id=:empId", nativeQuery = true)
	SummaryAttendance summaryDailyAttendanceListAll(@Param("month") int month, @Param("year") int year,
			@Param("empId") int empId);

}
