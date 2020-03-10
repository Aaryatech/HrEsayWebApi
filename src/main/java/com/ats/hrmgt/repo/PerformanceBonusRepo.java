package com.ats.hrmgt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.PerformanceBonus;

public interface PerformanceBonusRepo extends JpaRepository<PerformanceBonus, Integer> {

	@Query(value="SELECT \n" + 
			"	d.id,\n" + 
			"	DATE_FORMAT(d.att_date, '%d-%m-%Y') AS att_date,\n" + 
			"   LPAD(MOD(d.ot_hr, 60), 2, '0') AS ot_hr,\n" + 
			"   0 as ex_int1,\n" + 
			"   'NA' AS ex_var1 \n" +
			"FROM\n" + 
			"	tbl_attt_daily_daily d,\n" + 
			"    tbl_mst_emp_types e,\n" + 
			"    m_employees m\n" + 
			"WHERE\n" + 
			"	 d.ot_hr>0 AND\n" + 
			"    e.ot_applicable LIKE 'Yes' AND\n" + 
			"    e.emp_type_id=m.emp_type AND\n" + 
			"    d.emp_code=m.emp_code AND\n" + 
			"    d.att_date BETWEEN :varDate AND LAST_DAY(:varDate) ORDER BY d.att_date DESC", nativeQuery=true)
	List<PerformanceBonus> getPerformanceBonusDetails(@Param("varDate") String varDate);
}
