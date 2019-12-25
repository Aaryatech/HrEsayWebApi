package com.ats.hrmgt.repo.loan;

 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.loan.GetLoan;
import com.ats.hrmgt.model.loan.LoanMain;

public interface LoanMainRepo  extends JpaRepository<LoanMain, Integer>{
	
	
	@Query(value=" SELECT\n" + 
			"    *\n" + 
			"FROM\n" + 
			"    tbl_loan_main\n" + 
			"WHERE\n" + 
			"    tbl_loan_main.del_status = 1 AND tbl_loan_main.emp_id =:empId \n" + 
			"ORDER BY\n" + 
			"    tbl_loan_main.id\n" + 
			"DESC\n" + 
			"LIMIT 1 ",nativeQuery=true)
	LoanMain getEmpLoanDetail(@Param("empId") int empId);
	
	
	@Query(value=" SELECT\n" + 
			"  *\n" + 
			"FROM\n" + 
			"    tbl_loan_main\n" + 
			"WHERE\n" + 
			"    tbl_loan_main.del_status = 1  \n" + 
			"ORDER BY\n" + 
			"    tbl_loan_main.id\n" + 
			"DESC\n" + 
			"LIMIT 1",nativeQuery=true)
	LoanMain getLastApplicationNo();
	
	@Query(value=" SELECT\n" + 
			"    tbl_loan_main.*\n" + 
			" \n" + 
			"FROM\n" + 
			"     tbl_loan_main\n" + 
			" WHERE\n" + 
			"    tbl_loan_main.del_status = 1 AND  YEAR(tbl_loan_main.loan_add_date) =:calYrId AND tbl_loan_main.loan_status =:status AND  tbl_loan_main.cmp_id =:companyId AND tbl_loan_main.emp_id=:empId ",nativeQuery=true)
	List<LoanMain> getLoanHistoryDetail(@Param("companyId") int companyId,@Param("status") String status,@Param("calYrId") String calYrId,@Param("empId") int empId);
	
	@Query(value=" SELECT\n" + 
			"    tbl_loan_main.*\n" + 
			" \n" + 
			"FROM\n" + 
			"     tbl_loan_main\n" + 
			" WHERE\n" + 
			"    tbl_loan_main.del_status = 1  AND  tbl_loan_main.cmp_id =:companyId AND tbl_loan_main.emp_id=:empId ",nativeQuery=true)
	List<LoanMain> getLoanHistoryDetail(@Param("companyId") int companyId,@Param("empId") int empId);

	
	

}
