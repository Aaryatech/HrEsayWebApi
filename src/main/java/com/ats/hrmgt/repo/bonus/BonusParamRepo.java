package com.ats.hrmgt.repo.bonus;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

 import com.ats.hrmgt.model.bonus.BonusParam;

public interface BonusParamRepo extends JpaRepository<BonusParam, String> {

	
	@Query(value = " SELECT\n" + "UUID() as uid,\n" + "    SUM(tbl_salary_calc.basic_cal) AS total_basic_cal,\n"
			+ "    SUM(salall.allowance_value_cal) AS total_allowance\n" + "FROM\n" + "    tbl_salary_calc\n"
			+ "LEFT JOIN tbl_salary_calc_allowance_cal salall ON\n"
			+ "    salall.salary_calc_id = tbl_salary_calc.id AND allowance_id IN(:allIdList)\n" + "WHERE\n"
			+ "    tbl_salary_calc.emp_id =:empId AND(\n" + "        (\n"
			+ "            tbl_salary_calc.calc_month >= :monthFrom AND tbl_salary_calc.calc_year =:yearFrom\n" + "        ) OR(\n"
			+ "            tbl_salary_calc.calc_month <=:monthTo AND tbl_salary_calc.calc_year = :yearTo \n" + "        )\n"
			+ "    )", nativeQuery = true)
	BonusParam getBonusParameters(@Param("empId") int empId,@Param("monthFrom") int monthFrom,@Param("monthTo") int monthTo,@Param("yearFrom") String yearFrom,@Param("yearTo") String yearTo,
			@Param("allIdList") List<Integer> allIdList);

	

	@Query(value = " SELECT UUID() as uid, \n" + 
			"    SUM(\n" + 
			"        tbl_attt_summary_daily.payable_days\n" + 
			"    ) AS total_basic_cal,\n" + 
			"    SUM(\n" + 
			"        tbl_attt_summary_daily.ncp_days\n" + 
			"    ) AS total_allowance\n" + 
			"FROM\n" + 
			"    tbl_attt_summary_daily\n" + 
			"WHERE\n" + 
			"    tbl_attt_summary_daily.emp_id =:empId AND(\n" + 
			"        (\n" + 
			"            tbl_attt_summary_daily.month >=:monthFrom AND tbl_attt_summary_daily.year =:yearFrom \n" + 
			"        ) OR(\n" + 
			"            tbl_attt_summary_daily.month <=:monthTo AND tbl_attt_summary_daily.year =:yearTo \n" + 
			"        )\n" + 
			"    )", nativeQuery = true)
	BonusParam getDays(@Param("empId") int empId,@Param("monthFrom") int monthFrom,@Param("monthTo") int monthTo,@Param("yearFrom") String yearFrom,@Param("yearTo") String yearTo
			);


	 

}
