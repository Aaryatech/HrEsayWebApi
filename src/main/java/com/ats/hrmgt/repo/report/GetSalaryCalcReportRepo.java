package com.ats.hrmgt.repo.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.report.GetSalaryCalcReport;
import com.ats.hrmgt.model.report.GetYearlyAdvance;

public interface GetSalaryCalcReportRepo extends JpaRepository<GetSalaryCalcReport, Integer> {
	
	

	@Query(value=" SELECT\n" + 
			"    tbl_salary_calc.id,\n" + 
			"    tbl_salary_calc.cmp_id,\n" + 
			"    tbl_salary_calc.emp_id,\n" + 
			"    tbl_salary_calc.emp_code,\n" + 
			"    tbl_salary_calc.calc_month,\n" + 
			"    tbl_salary_calc.calc_year,\n" + 
			"    tbl_salary_calc.epf_wages,\n" + 
			"    tbl_salary_calc.epf_wages_employer,\n" + 
			"    tbl_salary_calc.eps_default,\n" + 
			"    tbl_salary_calc.esic_wages_cal,\n" + 
			"    tbl_salary_calc.gross_salary,\n" + 
			"    tbl_salary_calc.eps_wages,\n" + 
			"    tbl_salary_calc.esic_wages_dec,\n" + 
			"    tbl_salary_calc.employee_pf,\n" + 
			"    tbl_salary_calc.employer_eps,\n" + 
			"    tbl_salary_calc.employer_pf,\n" + 
			"    tbl_salary_calc.employer_esic,\n" + 
			"    tbl_salary_calc.esic_status,\n" + 
			"    tbl_salary_calc.pf_status,\n" + 
			"    tbl_salary_calc.epf_percentage,\n" + 
			"    tbl_salary_calc.eps_employee_percentage,\n" + 
			"    tbl_salary_calc.epf_employer_percentage,\n" + 
			"    tbl_salary_calc.eps_employer_percentage,\n" + 
			"    CONCAT(\n" + 
			"        m_employees.first_name,\n" + 
			"        ' ',\n" + 
			"        m_employees.middle_name,\n" + 
			"        ' ',\n" + 
			"        m_employees.surname\n" + 
			"    ) AS emp_name\n" + 
			"FROM\n" + 
			"    tbl_salary_calc,\n" + 
			"    m_employees\n" + 
			"WHERE\n" + 
			"    tbl_salary_calc.pf_status = 1 AND tbl_salary_calc.calc_month =:month AND tbl_salary_calc.calc_year = :year AND m_employees.emp_id = tbl_salary_calc.emp_id AND tbl_salary_calc.cmp_id =:companyId",nativeQuery=true)
	List<GetSalaryCalcReport> getSpecEmpAdvForReport(@Param("companyId") int companyId,@Param("year") int year,@Param("month") int month);

}
