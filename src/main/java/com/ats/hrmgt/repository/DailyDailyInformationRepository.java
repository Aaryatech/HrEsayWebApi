package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.DailyDailyInformation;

public interface DailyDailyInformationRepository extends JpaRepository<DailyDailyInformation, Integer>{

	@Query(value = "select  uuid() as uuid,dl.emp_id,count(*) as daycount,dl.lv_sumup_id,sp.name_sd,sum(dl.working_hrs) as working_min,sum(ot_hr) as ot_min,sum(late_mark) as "
			+ "late_mark,sum(late_min) as  late_min from tbl_attt_daily_daily dl,tbl_lvm_sumup sp where dl.att_date between :fromDate and :toDate "
			+ "and dl.lv_sumup_id=sp.id group by dl.emp_id,dl.lv_sumup_id  ", nativeQuery = true)
	List<DailyDailyInformation> dailyDailyInformationList(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

}
