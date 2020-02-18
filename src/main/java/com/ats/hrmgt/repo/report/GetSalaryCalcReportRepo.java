package com.ats.hrmgt.repo.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.report.GetSalaryCalcReport;
import com.ats.hrmgt.model.report.GetYearlyAdvance;

public interface GetSalaryCalcReportRepo extends JpaRepository<GetSalaryCalcReport, Integer> {
	
	

	@Query(value=" SELECT\n" + 
			"    salc.*,\n" + 
			"    CONCAT(\n" + 
			"        emp.first_name,\n" + 
			"        ' ',\n" + 
			"        emp.middle_name,\n" + 
			"        ' ',\n" + 
			"        emp.surname\n" + 
			"    ) AS emp_name,\n" + 
			"    company_name,\n" + 
			"    name_sd\n" + 
			"FROM\n" + 
			"    tbl_salary_calc salc\n" + 
			"INNER JOIN m_employees emp ON\n" + 
			"    salc.emp_id = emp.emp_id\n" + 
			"LEFT JOIN tbl_mst_sub_company subcomp ON\n" + 
			"    salc.cmp_id = subcomp.company_id\n" + 
			"WHERE\n" + 
			"    salc.pf_status = 1 AND (\n" + 
			"        salc.calc_month >= :fromMonth AND salc.calc_year = :fromYear\n" + 
			"    ) OR(\n" + 
			"        salc.calc_month <=:toMonth AND salc.calc_year = :toYear\n" + 
			"    )  ",nativeQuery=true)
	List<GetSalaryCalcReport> getSpecEmpAdvForReport(@Param("fromYear") String fromYear,@Param("fromMonth") String fromMonth,@Param("toYear") String toYear,@Param("toMonth") String toMonth);
	
	
	@Query(value=" SELECT\n" + 
			"    salc.*,\n" + 
			"    CONCAT(\n" + 
			"        emp.first_name,\n" + 
			"        ' ',\n" + 
			"        emp.middle_name,\n" + 
			"        ' ',\n" + 
			"        emp.surname\n" + 
			"    ) AS emp_name,\n" + 
			"    company_name,\n" + 
			"    name_sd\n" + 
			"FROM\n" + 
			"    tbl_salary_calc salc\n" + 
			"INNER JOIN m_employees emp ON\n" + 
			"    salc.emp_id = emp.emp_id\n" + 
			"LEFT JOIN tbl_mst_sub_company subcomp ON\n" + 
			"    salc.cmp_id = subcomp.company_id\n" + 
			"WHERE\n" + 
			"    salc.pf_status = 1 AND (\n" + 
			"        salc.calc_month >=:fromMonth AND salc.calc_year = :fromYear\n" + 
			"    ) OR(\n" + 
			"        salc.calc_month <=:toMonth AND salc.calc_year = :toYear\n" + 
			"    )  AND salc.cmp_id =:companyId",nativeQuery=true)
	List<GetSalaryCalcReport> getSpecEmpAdvForReportSunCmpwise(@Param("companyId") int companyId,@Param("fromYear") String fromYear,@Param("fromMonth") String fromMonth,@Param("toYear") String toYear,@Param("toMonth") String toMonth);
	
	
	@Query(value="SELECT salc\n" + 
			"    .*,\n" + 
			"    CONCAT(\n" + 
			"        emp.first_name,\n" + 
			"        ' ',\n" + 
			"        emp.middle_name,\n" + 
			"        ' ',\n" + 
			"        emp.surname\n" + 
			"    ) AS emp_name,\n" + 
			"    company_name,\n" + 
			"    name_sd\n" + 
			"FROM\n" + 
			"    tbl_salary_calc salc\n" + 
			"INNER JOIN m_employees emp ON\n" + 
			"    salc.emp_id = emp.emp_id\n" + 
			"LEFT JOIN tbl_mst_sub_company subcomp ON\n" + 
			"    salc.cmp_id = subcomp.company_id\n" + 
			"WHERE\n" + 
			"    salc.pf_status = 1 AND(\n" + 
			"        salc.calc_month >=:fromMonth AND salc.calc_year =:fromYear\n" + 
			"    ) OR(\n" + 
			"        salc.calc_month <=:toMonth AND salc.calc_year =:toYear\n" + 
			"    ) AND salc.emp_id=:empId",nativeQuery=true)
	List<GetSalaryCalcReport> getSpecEmpAdvForReportEmpWise(@Param("fromYear") String fromYear,@Param("fromMonth") String fromMonth,@Param("toYear") String toYear,@Param("toMonth") String toMonth,@Param("empId") int empId);

}
