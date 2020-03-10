package com.ats.hrmgt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.RewardDetail;

public interface RewardDetailRepo extends JpaRepository<RewardDetail, Integer> {

	@Query(value="SELECT\n" + 
			"    UUID() AS id,\n" + 
			"    'NA' as ex_Var1,\n" + 
			"    0 AS qty,\n" + 
			"    0 AS amt,\n" + 
			"    0 AS sal_db_cr,\n" + 
			"    0 AS ex_int1,\n" + 
			"    b.type_name\n" + 
			"FROM\n" + 
			"    tblm_pay_bonus_details d,\n" + 
			"    tbl_pay_bonus b\n" + 
			"WHERE\n" + 
			"    b.pay_type_id = d.pay_type_id AND \n" + 
			"    d.emp_id = :empId AND \n" + 
			"    d.month = :varMonth AND d.year = :varYear AND \n" + 
			"    d.del_status=1", nativeQuery=true)
	
	List<RewardDetail> getRewardDetailByEmpId(@Param("varMonth") String varMonth, @Param("varYear") String varYear,
			@Param("empId") int empId);

	@Query(value="SELECT\n" + 
			"    UUID() AS id,\n" + 
			"    'NA' as ex_Var1,\n" + 
			"    0 AS qty,\n" + 
			"    0 AS amt,\n" + 
			"    0 AS sal_db_cr,\n" + 
			"    0 AS ex_int1,\n" + 
			"    b.type_name\n" + 
			"FROM\n" + 
			"    tblm_pay_bonus_details d,\n" + 
			"    tbl_pay_bonus b\n" + 
			"WHERE\n" + 
			"    b.pay_type_id = d.pay_type_id AND \n" +
			"    d.month = :varMonth AND d.year = :varYear AND \n" + 
			"    d.del_status=1", nativeQuery=true)
	List<RewardDetail> getRewardDetail(@Param("varMonth")String varMonth,
			@Param("varYear") String varYear);
}
