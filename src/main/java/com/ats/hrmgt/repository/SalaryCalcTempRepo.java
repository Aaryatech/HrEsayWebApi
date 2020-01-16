package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.SalaryCalcTemp;

public interface SalaryCalcTempRepo extends JpaRepository<SalaryCalcTemp, Integer> {

	@Transactional
	@Modifying
	@Query(value = "UPDATE tbl_salary_dynamic_temp SET itded=:itAmt,performance_bonus=:perBonus WHERE id=:tempSalDaynamicId", nativeQuery = true)
	int updateBonusAmt(@Param("tempSalDaynamicId") int tempSalDaynamicId, @Param("itAmt") float itAmt,
			@Param("perBonus") float perBonus);

	@Query(value = "select * from tbl_salary_dynamic_temp  where calc_month=:month and calc_year=:year and emp_id in (:empIds)", nativeQuery = true)
	List<SalaryCalcTemp> listForUpdatedValue(@Param("month") int month, @Param("year") int year,
			@Param("empIds") List<Integer> empIds);

	@Transactional
	@Modifying
	@Query("delete from SalaryCalcTemp where calc_month=:month and calc_year=:year and emp_id in (:empIds) ")
	int deleteFromTemp(@Param("month") int month, @Param("year") int year, @Param("empIds") List<Integer> empIds);

}
