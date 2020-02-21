package com.ats.hrmgt.repo.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.report.EmpLateMarkDetails;

public interface EmpLateMarkDetailsRepo extends JpaRepository<EmpLateMarkDetails, Integer> {
	

	@Query(value="SELECT\n" + 
			"UUID() as id,\n" + 
			"    atd.emp_id,\n" + 
			"    atd.emp_code,\n" + 
			"    atd.emp_name AS emp_name,\n" + 
			"    desg.name AS designation,\n" + 
			"    atd.totlate_mins AS late_hr,\n" + 
			"    MONTHNAME(STR_TO_DATE(atd.month, '%m')) AS MONTH\n" + 
			"FROM\n" + 
			"    tbl_attt_summary_daily atd,\n" + 
			"    m_employees emp,\n" + 
			"    m_designation desg\n" + 
			"WHERE\n" + 
			"    emp.emp_id = atd.emp_id AND atd.company_id = :companyId AND emp.designation_id = desg.desig_id AND\n" + 
			"    DATE_FORMAT(\n" + 
			"        CONCAT(atd.year, '-', atd.month, '-01'),\n" + 
			"        '%Y-%m-%d'\n" + 
			"    ) BETWEEN DATE_FORMAT(\n" + 
			"        CONCAT(:year, '-', :month, '-01'),\n" + 
			"        '%Y-%m-%d'\n" + 
			"    ) AND DATE_FORMAT(\n" + 
			"        CONCAT(:toyear, '-', :tomonth, '-31'),\n" + 
			"        '%Y-%m-%d'\n" + 
			"    ) AND atd.totlate_days > 0 AND emp.del_status = 1\n" + 
			"ORDER BY\n" + 
			"    atd.month",nativeQuery=true)
	

	List<EmpLateMarkDetails> getEmpLateMarkDetailReport(@Param("companyId")int companyId, @Param("month")String month,
			@Param("year") String year, @Param("tomonth") String tomonth, @Param("toyear") String toyear);
	
}
