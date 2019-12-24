package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.DailyDailyInformation;

public interface DailyDailyInformationRepository extends JpaRepository<DailyDailyInformation, Integer>{

	@Query(value = "select\n" + 
			"        uuid() as uuid,\n" + 
			"        dl.emp_id,\n" + 
			"        count(*) as daycount,\n" + 
			"        dl.lv_sumup_id,\n" + 
			"        sp.name_sd,\n" + 
			"        sum(dl.working_hrs) as working_min,\n" + 
			"        sum(ot_hr) as ot_min,\n" + 
			"        sum(late_mark) as late_mark,\n" + 
			"        sum(late_min) as  late_min,\n" + 
			"        es.sal_basis\n" + 
			"    from\n" + 
			"        tbl_attt_daily_daily dl,\n" + 
			"        tbl_lvm_sumup sp,\n" + 
			"        tbl_emp_salary_info es\n" + 
			"    where\n" + 
			"        dl.att_date between :fromDate and :toDate \n" + 
			"        and dl.lv_sumup_id=sp.id\n" + 
			"        and es.emp_id=dl.emp_id\n" + 
			"    group by\n" + 
			"        dl.emp_id,\n" + 
			"        dl.lv_sumup_id ", nativeQuery = true)
	List<DailyDailyInformation> dailyDailyInformationList(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

}
