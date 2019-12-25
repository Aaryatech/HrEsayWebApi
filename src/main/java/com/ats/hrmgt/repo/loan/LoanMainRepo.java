package com.ats.hrmgt.repo.loan;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

	
	

}
