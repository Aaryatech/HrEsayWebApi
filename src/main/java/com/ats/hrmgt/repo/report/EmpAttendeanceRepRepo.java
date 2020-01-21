package com.ats.hrmgt.repo.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.report.EmpAttendeanceRep;
 
public interface EmpAttendeanceRepRepo extends JpaRepository<EmpAttendeanceRep, String> {
	
	@Query(value=" SELECT\n" + 
			"UUID() as unid,\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        COUNT(tbl_attt_daily_daily.id)\n" + 
			"    FROM\n" + 
			"        tbl_attt_daily_daily\n" + 
			"    WHERE\n" + 
			"        tbl_attt_daily_daily.lv_sumup_id = 5 AND tbl_attt_daily_daily.att_date BETWEEN :fromDate AND :toDate AND tbl_attt_daily_daily.company_id=:companyId\n" + 
			") AS emp_present,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(tbl_attt_daily_daily.id)\n" + 
			"    FROM\n" + 
			"        tbl_attt_daily_daily\n" + 
			"    WHERE\n" + 
			"        tbl_attt_daily_daily.lv_sumup_id = 12 AND tbl_attt_daily_daily.att_date BETWEEN :fromDate AND :toDate AND tbl_attt_daily_daily.company_id=:companyId \n" + 
			") AS week_off,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(tbl_attt_daily_daily.id)\n" + 
			"    FROM\n" + 
			"        tbl_attt_daily_daily\n" + 
			"    WHERE\n" + 
			"        tbl_attt_daily_daily.lv_sumup_id = 7 AND  tbl_attt_daily_daily.att_date BETWEEN :fromDate AND :toDate AND tbl_attt_daily_daily.company_id=:companyId \n" + 
			") AS paid_leave,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(tbl_attt_daily_daily.id)\n" + 
			"    FROM\n" + 
			"        tbl_attt_daily_daily\n" + 
			"    WHERE\n" + 
			"        tbl_attt_daily_daily.lv_sumup_id = 11 AND tbl_attt_daily_daily.att_date BETWEEN :fromDate AND :toDate AND tbl_attt_daily_daily.company_id=:companyId \n" + 
			") AS unpaid_leave",nativeQuery=true)
	List<EmpAttendeanceRep> getSpecEmpAdvForReport(@Param("companyId") int companyId,@Param("fromDate") String fromDate,@Param("toDate") String toDate);
	

}
