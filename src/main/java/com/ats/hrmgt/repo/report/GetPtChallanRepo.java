package com.ats.hrmgt.repo.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.report.GetPtChallan;
import com.ats.hrmgt.model.report.GetSalaryCalcReport;
import com.ats.hrmgt.model.report.GetYearlyLoan;

public interface GetPtChallanRepo extends JpaRepository<GetPtChallan, Integer> {
	
	

	@Query(value=" SELECT\n" + 
			"    tbl_slabs.slab_id,\n" + 
			"    tbl_slabs.min_val,\n" + 
			"    tbl_slabs.max_val,\n" + 
			"    COUNT(tbl_salary_calc.emp_id),\n" + 
			"    SUM(tbl_salary_calc.pt_ded) AS total\n" + 
			"FROM\n" + 
			"    tbl_salary_calc,\n" + 
			"    tbl_slabs,\n" + 
			"    m_employees\n" + 
			"WHERE\n" + 
			"    tbl_salary_calc.gross_salary BETWEEN tbl_slabs.min_val AND tbl_slabs.max_val AND m_employees.emp_id = tbl_salary_calc.emp_id AND tbl_salary_calc.cmp_id = :companyId AND DATE_FORMAT(\n" + 
			"        CONCAT(\n" + 
			"            tbl_salary_calc.calc_year,\n" + 
			"            '-',\n" + 
			"            tbl_salary_calc.calc_month,\n" + 
			"            '-01'\n" + 
			"        ),\n" + 
			"        '%Y-%m-%d'\n" + 
			"    ) >= DATE_FORMAT(\n" + 
			"        CONCAT(:fromYear, '-', :fromMonth, '-01'),\n" + 
			"        '%Y-%m-%d'\n" + 
			"    ) AND DATE_FORMAT(\n" + 
			"        CONCAT(\n" + 
			"            tbl_salary_calc.calc_year,\n" + 
			"            '-',\n" + 
			"            tbl_salary_calc.calc_month,\n" + 
			"            '-01'\n" + 
			"        ),\n" + 
			"        '%Y-%m-%d'\n" + 
			"    ) <= DATE_FORMAT(\n" + 
			"        CONCAT(:toYear, '-', :toMonth, '-01'),\n" + 
			"        '%Y-%m-%d'\n" + 
			"    )",nativeQuery=true)
	List<GetPtChallan> getPtChallan(@Param("fromYear") String fromYear,@Param("fromMonth") String fromMonth,@Param("toYear") String toYear,@Param("toMonth") String toMonth,@Param("companyId") int companyId);

}
