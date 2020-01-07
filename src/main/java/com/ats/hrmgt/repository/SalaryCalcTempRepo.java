package com.ats.hrmgt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.SalaryCalcTemp;

public interface SalaryCalcTempRepo extends JpaRepository<SalaryCalcTemp, Integer>{

	
	@Transactional
	@Modifying
	@Query(value = "UPDATE tbl_salary_dynamic_temp SET itded=:itAmt,performance_bonus=:perBonus WHERE id=:tempSalDaynamicId", nativeQuery = true)
	int updateBonusAmt(@Param("tempSalDaynamicId") int tempSalDaynamicId, @Param("itAmt") float itAmt,
			@Param("perBonus") float perBonus);

}
