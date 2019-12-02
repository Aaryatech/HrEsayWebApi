package com.ats.hrmgt.krakpi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.ats.hrmgt.krakpi.model.GetEmpKra;
 
 
public interface GetEmpKraRepo extends JpaRepository<GetEmpKra, Integer>  {
	
	@Query(value = " \n" + 
			"	SELECT\n" + 
			"    t_kra.kra_id,\n" + 
			"    t_kra.kra_title,\n" + 
			"    t_kra.remark,0 as exvar1, \n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        COUNT(t_kpi.kpi_id)\n" + 
			"    FROM\n" + 
			"        t_kpi\n" + 
			"    WHERE\n" + 
			"        t_kpi.kra_id = t_kra.kra_id AND t_kpi.del_status = 1\n" + 
			") AS kpi_count\n" + 
			"FROM\n" + 
			"    t_kra\n" + 
			"WHERE\n" + 
			"    t_kra.emp_id =:empId AND t_kra.year_id =:finYrId AND t_kra.del_status = 1\n" + 
			"	"
			+ " ", nativeQuery = true)

	List<GetEmpKra> getEmpKra(@Param("empId") int empId,@Param("finYrId") int finYrId
		);
	

}
