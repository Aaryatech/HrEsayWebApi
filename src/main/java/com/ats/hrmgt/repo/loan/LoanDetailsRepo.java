package com.ats.hrmgt.repo.loan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.loan.LoanDetails;
import com.ats.hrmgt.model.loan.LoanMain;

public interface LoanDetailsRepo extends JpaRepository<LoanDetails, Integer>{

	List<LoanDetails> findByLoanMainIdAndDelStatus(int loanId,int j);

 	
	@Query(value=" SELECT\n" + 
			"    *\n" + 
			"FROM\n" + 
			"    tbl_loan_details\n" + 
			"WHERE\n" + 
			"    tbl_loan_details.loan_main_id =:loanId AND tbl_loan_details.pay_type = 'SP' AND  tbl_loan_details.del_status=1 \n" + 
			"ORDER BY\n" + 
			"    tbl_loan_details.id\n" + 
			"DESC\n" + 
			"LIMIT 1 ",nativeQuery=true)
	LoanDetails getRecord(@Param("loanId") int loanId);
	

}
