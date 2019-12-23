package com.ats.hrmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.InfoForUploadAttendance;

public interface InfoForUploadAttendanceRepository extends JpaRepository<InfoForUploadAttendance, Integer>{

	@Query(value="select COALESCE(( select count(*) from m_employees,tbl_emp_salary_info where m_employees.emp_id=tbl_emp_salary_info.emp_id and m_employees.del_status=1),0)  as total_emp, DATEDIFF(:toDate, :fromDate) as date_diff, "
			+ "COALESCE(( select count(*) from tbl_attt_daily_daily where att_date between :fromDate and :toDate ),0)  as updated_by_step1, "
			+ "COALESCE(( select count(*) from tbl_attt_daily_daily where att_date between :fromDate and :toDate and by_file_updated=1 ),0) "
			+ " as updated_by_file",nativeQuery=true)
	InfoForUploadAttendance getInformationOfUploadedAttendance(@Param("fromDate") String fromDate,
			@Param("toDate") String toDate);

}
