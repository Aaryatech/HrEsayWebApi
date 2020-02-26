package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.GetPayDedList;

public interface GetPayDedListRepo extends JpaRepository<GetPayDedList, Integer> {

	@Query(value = "select uuid() as id,emp_id,sum(ded_rate) as amt from tblm_pay_deduction_details where month=:month and year=:year "
			+ "and del_status=1 and is_deducted=0 and emp_id in (:empIds) group by emp_id", nativeQuery = true)
	List<GetPayDedList> getPayDedList(@Param("month") int month, @Param("year") int year,
			@Param("empIds") List<Integer> empIds);

	@Query(value = "select uuid() as id, emp_id, sum(CASE WHEN current_outstanding<loan_emi_intrest THEN current_outstanding ELSE loan_emi_intrest end ) as amt from tbl_loan_main "
			+ "where :date between loan_repay_start and loan_repay_end and del_status=1 and skip_id=0 and current_outstanding>0 and emp_id in (:empIds) group by emp_id", nativeQuery = true)
	List<GetPayDedList> getLoanList(@Param("date") String date ,
			@Param("empIds") List<Integer> empIds);

	@Query(value = "select\n" + 
			"        uuid() as id,\n" + 
			"        emp_id,\n" + 
			"        sum(pay_rate) as amt \n" + 
			"    from\n" + 
			"        tblm_pay_bonus_details \n" + 
			"    where\n" + 
			"        month=:month \n" + 
			"        and year=:year \n" + 
			"        and del_status=1 \n" + 
			"        and is_paid=0 \n" + 
			"        and emp_id in (:empIds) \n" + 
			"    group by\n" + 
			"        emp_id", nativeQuery = true)
	List<GetPayDedList> getBonusList(@Param("month") int month, @Param("year") int year,
	@Param("empIds") List<Integer> empIds);

}
