package com.ats.hrmgt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.DeductionDetails;

public interface DeductionDetailsRepo extends JpaRepository<DeductionDetails, Integer> {

	@Query(value="SELECT \n" + 
			"		d.ded_id,\n" + 
			"        p.type_name,\n" + 
			"        d.ded_rate,\n" + 
			"        d.ded_remark,\n" + 
			"        'NA' as ex_var1,\n" + 
			"        0 as ex_int1\n" + 
			"FROM\n" + 
			"	tblm_pay_deduction_details d,\n" + 
			"    tbl_pay_deduction p\n" + 
			"WHERE\n" + 
			"		d.month=:varMonth AND\n" + 
			"        d.year=:varYear AND\n" + 
			"        d.ded_type_id=p.ded_type_id AND\n" + 
			"        d.del_status=1", nativeQuery=true)
	List<DeductionDetails> getDeductionDetails(@Param("varMonth")String varMonth,
	@Param("varYear") String varYear);
}
