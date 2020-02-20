package com.ats.hrmgt.repo.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

 import com.ats.hrmgt.model.report.StatutoryEsicRep;

public interface StatutoryEsicRepRepo extends JpaRepository<StatutoryEsicRep, Integer> {
	
	
	
	@Query(value="SELECT\n" + 
			"    UUID() AS key_new, a.*\n" + 
			"FROM\n" + 
			"    (\n" + 
			"    SELECT DISTINCT\n" + 
			"        te.emp_code,te.emp_id,\n" + 
			"        CONCAT(\n" + 
			"            te.first_name,\n" + 
			"            ' ',\n" + 
			"            te.middle_name,\n" + 
			"            ' ',\n" + 
			"            te.surname\n" + 
			"        ) AS emp_name,\n" + 
			"        te.esic_no,\n" + 
			"        tsc.net_salary,\n" + 
			"        tsc.esic_wages_cal,\n" + 
			"        tsc.employer_esic,\n" + 
			"        tasd.present_days,\n" + 
			"        tasd.year,\n" + 
			"        tasd.month\n" + 
			"    FROM\n" + 
			"        m_employees AS te\n" + 
			"    INNER JOIN tbl_attt_summary_daily AS tasd\n" + 
			"    ON\n" + 
			"        tasd.emp_id = te.emp_id AND(\n" + 
			"            DATE_FORMAT(\n" + 
			"                CONCAT(tasd.year, '-', tasd.month, '-01'),\n" + 
			"                '%Y-%m-%d'\n" + 
			"            ) >=:fromDate AND DATE_FORMAT(\n" + 
			"                CONCAT(tasd.year, '-', tasd.month, '-01'),\n" + 
			"                '%Y-%m-%d'\n" + 
			"            ) <=:toDate)\n" + 
			"        INNER JOIN tbl_salary_calc AS tsc\n" + 
			"        ON\n" + 
			"            tsc.emp_id = te.emp_id AND(\n" + 
			"                DATE_FORMAT(\n" + 
			"                    CONCAT(\n" + 
			"                        tsc.calc_year,\n" + 
			"                        '-',\n" + 
			"                        tsc.calc_month,\n" + 
			"                        '-01'\n" + 
			"                    ),\n" + 
			"                    '%Y-%m-%d'\n" + 
			"                ) >=:fromDate AND DATE_FORMAT(\n" + 
			"                    CONCAT(\n" + 
			"                        tsc.calc_year,\n" + 
			"                        '-',\n" + 
			"                        tsc.calc_month,\n" + 
			"                        '-01'\n" + 
			"                    ),\n" + 
			"                    '%Y-%m-%d'\n" + 
			"                ) <=:toDate)\n" + 
			"            WHERE\n" + 
			"                te.is_emp = '1' AND tsc.cmp_id =:companyId \n" + 
			"            ) AS a",nativeQuery=true)
	List<StatutoryEsicRep> getStatutoryEsic(@Param("fromDate") String fromDate,@Param("toDate") String toDate,@Param("companyId") int companyId);

	
	

	@Query(value="SELECT\n" + 
			"    UUID() AS key_new, a.*\n" + 
			"FROM\n" + 
			"    (\n" + 
			"    SELECT DISTINCT\n" + 
			"        te.emp_code,te.emp_id,\n" + 
			"        CONCAT(\n" + 
			"            te.first_name,\n" + 
			"            ' ',\n" + 
			"            te.middle_name,\n" + 
			"            ' ',\n" + 
			"            te.surname\n" + 
			"        ) AS emp_name,\n" + 
			"        te.esic_no,\n" + 
			"        tsc.net_salary,\n" + 
			"        tsc.esic_wages_cal,\n" + 
			"        tsc.employer_esic,\n" + 
			"        tasd.present_days,\n" + 
			"        tasd.year,\n" + 
			"        tasd.month\n" + 
			"    FROM\n" + 
			"        m_employees AS te\n" + 
			"    INNER JOIN tbl_attt_summary_daily AS tasd\n" + 
			"    ON\n" + 
			"        tasd.emp_id = te.emp_id AND(\n" + 
			"            DATE_FORMAT(\n" + 
			"                CONCAT(tasd.year, '-', tasd.month, '-01'),\n" + 
			"                '%Y-%m-%d'\n" + 
			"            ) >=:fromDate AND DATE_FORMAT(\n" + 
			"                CONCAT(tasd.year, '-', tasd.month, '-01'),\n" + 
			"                '%Y-%m-%d'\n" + 
			"            ) <=:toDate )\n" + 
			"        INNER JOIN tbl_salary_calc AS tsc\n" + 
			"        ON\n" + 
			"            tsc.emp_id = te.emp_id AND(\n" + 
			"                DATE_FORMAT(\n" + 
			"                    CONCAT(\n" + 
			"                        tsc.calc_year,\n" + 
			"                        '-',\n" + 
			"                        tsc.calc_month,\n" + 
			"                        '-01'\n" + 
			"                    ),\n" + 
			"                    '%Y-%m-%d'\n" + 
			"                ) >=:fromDate AND DATE_FORMAT(\n" + 
			"                    CONCAT(\n" + 
			"                        tsc.calc_year,\n" + 
			"                        '-',\n" + 
			"                        tsc.calc_month,\n" + 
			"                        '-01'\n" + 
			"                    ),\n" + 
			"                    '%Y-%m-%d'\n" + 
			"                ) <=:toDate )\n" + 
			"            WHERE\n" + 
			"                te.is_emp = '1'\n" + 
			"            ) AS a",nativeQuery=true)
	List<StatutoryEsicRep> getStatutoryEsicAll(@Param("fromDate") String fromDate,@Param("toDate") String toDate);

}
