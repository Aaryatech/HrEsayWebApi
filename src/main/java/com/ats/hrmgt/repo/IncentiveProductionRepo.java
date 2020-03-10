package com.ats.hrmgt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.IncentiveProduction;

public interface IncentiveProductionRepo extends JpaRepository<IncentiveProduction, Integer> {

	@Query(value="SELECT \n" + 
			"       id ,\n" + 
			"       DATE_FORMAT(att_date, '%d-%m-%Y') AS att_date, \n" + 
			"       att_status AS ex_Var1, \n" + 
			"       0 as qty,\n" + 
			"       0 as amt,\n" + 
			"       0 as sal_db_cr,\n" + 
			"       0 as ex_int1\n" + 
			"FROM \n" + 
			"	 tbl_attt_daily_daily \n" + 
			"WHERE \n" + 
			"		att_status IN ('WO-OT','WO-HD','PH-OT','PH-HD','PH-WO-P','PH-WO-HD') AND \n" + 
			"       att_date BETWEEN :varDate AND LAST_DAY(:varDate) ORDER BY att_date DESC", nativeQuery=true)
	List<IncentiveProduction> getIncentiveProductionDetails(@Param("varDate") String varDate);
	
}
