package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.PayDeductionDetailList;

public interface PayDeductionDetailListRepo extends JpaRepository<PayDeductionDetailList, Integer> {

	@Query(value="SELECT\n" + 
			"	 emp.emp_id, emp.emp_code, CONCAT(emp.first_name,' ',emp.middle_name,' ',emp.surname) AS emp_name,\n" + 
			"    payDeductDetail.ded_id,\n" + 
			"    payDeductDetail.ded_rate,  payDeductDetail.ded_remark,\n" + 
			"    payDeductDetail.ded_occurence,\n" + 
			"    payDeductDetail.ded_total,\n" + 
			"    payDeductDetail.month,\n" + 
			"    payDeductDetail.year,    \n" + 
			"    payDeductType.type_name,  payDeductType.ded_type_id\n" + 
			"    \n" + 
			"FROM\n" + 
			"    tblm_pay_deduction_details payDeductDetail,\n" + 
			"    m_employees emp,\n" + 
			"    tbl_pay_deduction payDeductType\n" + 
			"    \n" + 
			"WHERE\n" + 
			"    payDeductDetail.emp_id=emp.emp_id AND\n" + 
			"    payDeductDetail.ded_type_id=payDeductType.ded_type_id AND\n" + 
			"    payDeductDetail.del_status=1 Order By payDeductDetail.ded_id Desc",nativeQuery=true)
	List<PayDeductionDetailList> getEmpPayDeductDetail();
	
	
	@Query(value="SELECT\n" + 
			"	emp.emp_id,emp.emp_code,\n" + 
			"	CONCAT(emp.first_name,' ',emp.middle_name,' ',emp.surname) AS emp_name,\n" + 
			"    payDeductDetail.ded_id,\n" + 
			"    payDeductDetail.ded_rate,  payDeductDetail.ded_remark,\n" + 
			"    payDeductDetail.ded_occurence,\n" + 
			"    payDeductDetail.ded_total,\n" + 
			"    payDeductDetail.month,\n" + 
			"    payDeductDetail.year,    \n" + 
			"    payDeductType.type_name,  payDeductType.ded_type_id\n" + 
			"    \n" + 
			"FROM\n" + 
			"    tblm_pay_deduction_details payDeductDetail,\n" + 
			"    m_employees emp,\n" + 
			"    tbl_pay_deduction payDeductType\n" + 
			"    \n" + 
			"WHERE\n" + 
			"    payDeductDetail.emp_id=emp.emp_id AND\n" + 
			"    payDeductDetail.ded_type_id=payDeductType.ded_type_id AND\n" + 
			"    payDeductDetail.del_status=1 AND\n" + 
			"    payDeductDetail.ded_id=:dedId",nativeQuery=true)
	PayDeductionDetailList getEmpPayDeductionById(@Param("dedId") int dedId);
	
}
