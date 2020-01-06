package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.SummaryDailyAttendance;

public interface SummaryDailyAttendanceRepository extends JpaRepository<SummaryDailyAttendance, Integer> {

	@Query(value = "select * from tbl_attt_summary_daily where month=:month and year=:year and rec_status='o'", nativeQuery = true)
	List<SummaryDailyAttendance> summaryDailyAttendanceList(@Param("month") int month, @Param("year") int year);

	@Query(value = "select * from tbl_attt_summary_daily where month=:month and year=:year ", nativeQuery = true)
	List<SummaryDailyAttendance> summaryDailyAttendanceListAll(@Param("month") int month, @Param("year") int year);

	@Query(value = "select * from tbl_attt_summary_daily where month=:month and year=:year and emp_id=:empId", nativeQuery = true)
	List<SummaryDailyAttendance> summaryDailyAttendanceList(@Param("month") int month, @Param("year") int year,  @Param("empId") int empId);

	SummaryDailyAttendance findByCompanyIdAndEmpId(int companyId, int empId);

}
