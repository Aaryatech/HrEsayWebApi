package com.ats.hrmgt.repo.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.hrmgt.model.report.LoanStatementDetailsReport;

@Repository
public interface LoanStatementRepo extends JpaRepository<LoanStatementDetailsReport, Integer> {
	

	@Query(value="SELECT DISTINCT\n" + 
			"    te.emp_id,\n" + 
			"    te.emp_code,\n" + 
			"    CONCAT(\n" + 
			"        te.first_name,\n" + 
			"        ' ',\n" + 
			"        te.middle_name,\n" + 
			"        ' ',\n" + 
			"        te.surname\n" + 
			"    ) AS emp_name,\n" + 
			"    tlm.loan_appl_no,\n" + 
			"    tlm.loan_amt,\n" + 
			"    tlm.loan_add_date,\n" + 
			"    tlm.current_outstanding,\n" + 
			"    tlm.current_totpaid,\n" + 
			"    tlm.loan_emi_intrest,\n" + 
			"    tlm.loan_emi,\n" + 
			"    tlm.id,\n" + 
			"    DATE_FORMAT(tlm.loan_repay_start, '%d-%m-%Y') AS loan_repay_start,\n" + 
			"    DATE_FORMAT(tlm.loan_repay_end, '%d-%m-%Y') AS loan_repay_end\n" + 
			"FROM\n" + 
			"    m_employees AS te\n" + 
			"INNER JOIN tbl_loan_main AS tlm\n" + 
			"ON\n" + 
			"    te.emp_id = tlm.emp_id\n" + 
			"WHERE\n" + 
			"    te.cmp_code = :companyId  AND tlm.loan_repay_start BETWEEN :fromDate AND :toDate",nativeQuery=true)
	List<LoanStatementDetailsReport> getEmpLoanStateDetails(@Param("companyId") int companyId, @Param("fromDate") String fromDate, 
			@Param("toDate") String toDate);

	
	
	@Query(value="SELECT DISTINCT\n" + 
			"    te.emp_id,\n" + 
			"    te.emp_code,\n" + 
			"    CONCAT(\n" + 
			"        te.first_name,\n" + 
			"        ' ',\n" + 
			"        te.middle_name,\n" + 
			"        ' ',\n" + 
			"        te.surname\n" + 
			"    ) AS emp_name,\n" + 
			"    tlm.loan_appl_no,\n" + 
			"    tlm.loan_amt,\n" + 
			"    tlm.loan_add_date,\n" + 
			"    tlm.current_outstanding,\n" + 
			"    tlm.current_totpaid,\n" + 
			"    tlm.loan_emi_intrest,\n" + 
			"    tlm.loan_emi,\n" + 
			"    tlm.id,\n" + 
			"    DATE_FORMAT(tlm.loan_repay_start, '%d-%m-%Y') AS loan_repay_start,\n" + 
			"    DATE_FORMAT(tlm.loan_repay_end, '%d-%m-%Y') AS loan_repay_end\n" + 
			"FROM\n" + 
			"    m_employees AS te\n" + 
			"INNER JOIN tbl_loan_main AS tlm\n" + 
			"ON\n" + 
			"    te.emp_id = tlm.emp_id\n" + 
			"WHERE\n" + 
			"    te.emp_id = :empId AND tlm.loan_repay_start BETWEEN :newfromDate AND LAST_DAY(:newToDate)",nativeQuery=true)

	List<LoanStatementDetailsReport> getEmpLoanStateDetailsByEmpId(@Param("newfromDate") String newfromDate,
			@Param("newToDate")String newToDate , @Param("empId") int empId);
}
