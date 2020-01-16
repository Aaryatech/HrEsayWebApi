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

	@Query(value = "select uuid() as id,emp_id,sum(loan_emi_intrest) as amt from tbl_loan_main where :date between loan_repay_start and "
			+ "loan_repay_end and del_status=1 and emp_id in (:empIds) group by emp_id", nativeQuery = true)
	List<GetPayDedList> getLoanList(@Param("date") String date ,
			@Param("empIds") List<Integer> empIds);

}
