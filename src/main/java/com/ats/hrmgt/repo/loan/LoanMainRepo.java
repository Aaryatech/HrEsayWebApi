package com.ats.hrmgt.repo.loan;

 
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
			"    tbl_loan_main.del_status = 1  AND  tbl_loan_main.cmp_id =:companyId AND tbl_loan_main.emp_id=:empId AND tbl_loan_main.loan_status='Active' ",nativeQuery=true)
	List<LoanMain> getLoanHistoryDetail(@Param("companyId") int companyId,@Param("empId") int empId);

	LoanMain findById(int i);
	 
	@Transactional
	@Modifying
	@Query("update LoanMain set login_name =:userId,login_time=:dateTimeUpdate ,current_totpaid=:currentTotpaid ,current_outstanding=:currentOut,loan_repay_end=:closeDate,loan_status=:status WHERE id=:loanId")
	int forecloseLoan(@Param("loanId")  int loanId,@Param("userId")  int userId,@Param("closeDate")  String closeDate,@Param("currentTotpaid")  String currentTotpaid,@Param("currentOut")  String currentOut,
			@Param("dateTimeUpdate")  String dateTimeUpdate,	@Param("status")  String status);


	
	@Transactional
	@Modifying
	@Query("update LoanMain set  skip_id =:count,skip_login_name =:userId, skip_login_time=:dateTimeUpdate,skip_remarks =:skipStr,loan_repay_end =:repayEnd  WHERE id=:advId")
	int skipLoan(@Param("advId")  int advId,@Param("userId") int userId,@Param("count") int count,@Param("skipStr") String skipStr,@Param("dateTimeUpdate") String dateTimeUpdate,@Param("repayEnd") String repayEnd);


	@Query(value="select * from tbl_loan_main  where :date between loan_repay_start and loan_repay_end and del_status=1 and current_outstanding>0 and emp_id in (:empIds)  ",nativeQuery=true)
	List<LoanMain> getLoanList(@Param("date")String date, @Param("empIds") List<Integer> empIds);


	List<LoanMain> findByEmpIdAndDelStatus(int empId, int i);
	

}
