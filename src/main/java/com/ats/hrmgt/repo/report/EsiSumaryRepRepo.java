package com.ats.hrmgt.repo.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.report.EsiSumaryRep;
 

public interface EsiSumaryRepRepo extends JpaRepository<EsiSumaryRep, String> {
	
	
	@Query(value="SELECT\n" + 
			"    UUID() AS key_id, CONCAT(\n" + 
			"        emp.first_name,\n" + 
			"        ' ',\n" + 
			"        emp.middle_name,\n" + 
			"        ' ',\n" + 
			"        emp.surname\n" + 
			"    ) AS emp_name,\n" + 
			"    emp.emp_code,\n" + 
			"    SUM(tfc.esic) AS emp_contribution,\n" + 
			"    SUM(tfc.esic_wages_cal) AS emper_contribution,\n" + 
			"    SUM(tfc.esic + tfc.employer_esic) AS total_contribution,\n" + 
			"    tfc.calc_month,\n" + 
			"    tfc.calc_year\n" + 
			"FROM\n" + 
			"    tbl_salary_calc tfc,\n" + 
			"    m_employees emp\n" + 
			"WHERE\n" + 
			"    tfc.esic_status = 1 AND tfc.cmp_id =:companyId AND tfc.esic > 0 AND DATE_FORMAT(\n" + 
			"        CONCAT(\n" + 
			"            tfc.calc_year,\n" + 
			"            '-',\n" + 
			"            tfc.calc_month,\n" + 
			"            '-01'\n" + 
			"        ),\n" + 
			"        '%Y-%m-%d'\n" + 
			"    ) >= DATE_FORMAT(\n" + 
			"        CONCAT(:fromYear, '-', :fromMonth, '-01'),\n" + 
			"        '%Y-%m-%d'\n" + 
			"    ) AND DATE_FORMAT(\n" + 
			"        CONCAT(\n" + 
			"            tfc.calc_year,\n" + 
			"            '-',\n" + 
			"            tfc.calc_month,\n" + 
			"            '-01'\n" + 
			"        ),\n" + 
			"        '%Y-%m-%d'\n" + 
			"    ) <= DATE_FORMAT(\n" + 
			"        CONCAT(:toYear, '-', :toMonth, '-01'),\n" + 
			"        '%Y-%m-%d'\n" + 
			"    ) AND tfc.emp_id = emp.emp_id\n" + 
			"GROUP BY\n" + 
			"    tfc.emp_id,\n" + 
			"    DATE_FORMAT(\n" + 
			"        CONCAT(\n" + 
			"            tfc.calc_year,\n" + 
			"            '-',\n" + 
			"            tfc.calc_month,\n" + 
			"            '-01'\n" + 
			"        ),\n" + 
			"        '%Y-%m'\n" + 
			"    )",nativeQuery=true)
	List<EsiSumaryRep> getEsiSumm(@Param("fromYear") String fromYear,@Param("fromMonth") String fromMonth,@Param("toYear") String toYear,@Param("toMonth") String toMonth,@Param("companyId") int companyId);

	@Query(value="SELECT\n" + 
			"    UUID() AS key_id, CONCAT(\n" + 
			"        emp.first_name,\n" + 
			"        ' ',\n" + 
			"        emp.middle_name,\n" + 
			"        ' ',\n" + 
			"        emp.surname\n" + 
			"    ) AS emp_name,\n" + 
			"    emp.emp_code,\n" + 
			"    SUM(tfc.esic) AS emp_contribution,\n" + 
			"    SUM(tfc.esic_wages_cal) AS emper_contribution,\n" + 
			"    SUM(tfc.esic + tfc.employer_esic) AS total_contribution,\n" + 
			"    tfc.calc_month,\n" + 
			"    tfc.calc_year\n" + 
			"FROM\n" + 
			"    tbl_salary_calc tfc,\n" + 
			"    m_employees emp\n" + 
			"WHERE\n" + 
			"    tfc.esic_status = 1  AND tfc.esic > 0 AND DATE_FORMAT(\n" + 
			"        CONCAT(\n" + 
			"            tfc.calc_year,\n" + 
			"            '-',\n" + 
			"            tfc.calc_month,\n" + 
			"            '-01'\n" + 
			"        ),\n" + 
			"        '%Y-%m-%d'\n" + 
			"    ) >= DATE_FORMAT(\n" + 
			"        CONCAT(:fromYear, '-', :fromMonth, '-01'),\n" + 
			"        '%Y-%m-%d'\n" + 
			"    ) AND DATE_FORMAT(\n" + 
			"        CONCAT(\n" + 
			"            tfc.calc_year,\n" + 
			"            '-',\n" + 
			"            tfc.calc_month,\n" + 
			"            '-01'\n" + 
			"        ),\n" + 
			"        '%Y-%m-%d'\n" + 
			"    ) <= DATE_FORMAT(\n" + 
			"        CONCAT(:toYear, '-', :toMonth, '-01'),\n" + 
			"        '%Y-%m-%d'\n" + 
			"    ) AND tfc.emp_id = emp.emp_id\n" + 
			"GROUP BY\n" + 
			"    tfc.emp_id,\n" + 
			"    DATE_FORMAT(\n" + 
			"        CONCAT(\n" + 
			"            tfc.calc_year,\n" + 
			"            '-',\n" + 
			"            tfc.calc_month,\n" + 
			"            '-01'\n" + 
			"        ),\n" + 
			"        '%Y-%m'\n" + 
			"    )",nativeQuery=true)
	List<EsiSumaryRep> getEsiSummAll(@Param("fromYear") String fromYear,@Param("fromMonth") String fromMonth,@Param("toYear") String toYear,@Param("toMonth") String toMonth);


}
