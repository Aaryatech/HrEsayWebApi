package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.SalAllownceTemp;

public interface SalAllownceTempRepo extends JpaRepository<SalAllownceTemp, Integer> {

	@Query(value = "select at.* from tbl_salary_dynamic_temp_allowance_cal at,tbl_salary_dynamic_temp st  where at.tbl_salary_dynamic_temp_id=st.id and st.calc_month=:month "
			+ "and st.calc_year=:year and st.emp_id in (:empIds)", nativeQuery = true)
	List<SalAllownceTemp> getAllowanceTempList(@Param("month") int month, @Param("year") int year,
			@Param("empIds") List<Integer> empIds);
	
	@Transactional
	@Modifying
	@Query("delete from SalAllownceTemp ")
	int deleteFromTempAll();

}
