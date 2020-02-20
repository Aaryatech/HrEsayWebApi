package com.ats.hrmgt.repo.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

 import com.ats.hrmgt.model.report.LoanDedReport;

public interface LoanDedReportRepo  extends JpaRepository<LoanDedReport, String>{
	

	@Query(value=" SELECT\n" + 
			"    UUID() AS unique_id, te.emp_id, te.emp_code, CONCAT(\n" + 
			"        te.first_name,\n" + 
			"        ' ',\n" + 
			"        te.middle_name,\n" + 
			"        ' ',\n" + 
			"        te.surname\n" + 
			"    ) AS emp_name,\n" + 
			"    tlm.loan_amt,\n" + 
			"    tlm.loan_emi,\n" + 
			"    tlm.current_totpaid,\n" + 
			"    tlm.loan_emi_intrest,\n" + 
			"    tlm.current_outstanding,tlm.loan_emi_intrest ,\n" + 
			"    DATE_FORMAT(tlm.loan_repay_end, '%d-%m-%Y') AS loan_repay_end,\n" + 
			"    DATE_FORMAT(tlm.loan_repay_start, '%d-%m-%Y') AS loan_repay_start\n" + 
			"FROM\n" + 
			"    m_employees AS te\n" + 
			"INNER JOIN tbl_loan_main AS tlm\n" + 
			"ON\n" + 
			"    te.emp_id = tlm.emp_id\n" + 
			"WHERE\n" + 
			"    cmp_id = 1 AND  tlm.loan_status = 'Paid'  AND  tlm.loan_repay_start BETWEEN :fromDate AND :toDate" + 
			"   ORDER BY  tlm.emp_id ASC ",nativeQuery=true)
	List<LoanDedReport> getSpecEmpDedLoanReport(@Param("fromDate") String fromDate,@Param("toDate") String toDate);

	

}
