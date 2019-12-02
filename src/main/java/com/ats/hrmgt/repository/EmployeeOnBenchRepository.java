package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmployeeOnBenchReport;

public interface EmployeeOnBenchRepository extends JpaRepository<EmployeeOnBenchReport, Integer>{

	@Query(value = "select\n" + 
			"        e.emp_id,e.emp_fname,e.emp_mname,e.emp_sname,e.emp_rate_perhr, ( \n" + 
			"        coalesce((select s.value from m_setting s where s.key='CTC'),0) * e.emp_rate_perhr) as ctc,c.emp_cat_short_name as category,t.emp_type_short_name as type\n" + 
			"    from\n" + 
			"        emp_info e,m_emp_category c,m_emp_type t\n" + 
			"    where\n" + 
			"        e.emp_id not in (\n" + 
			"            select\n" + 
			"                emp_id              \n" + 
			"            from\n" + 
			"                project_allotment              \n" + 
			"            where\n" + 
			"                (\n" + 
			"                    :fromDate BETWEEN pallot_fromdt and pallot_todt \n" + 
			"                ) \n" + 
			"                or  (\n" + 
			"                    :toDate BETWEEN pallot_fromdt and pallot_todt \n" + 
			"                ) \n" + 
			"                or (\n" + 
			"                    pallot_fromdt BETWEEN :fromDate and :toDate \n" + 
			"                )                  \n" + 
			"                or (\n" + 
			"                    pallot_todt BETWEEN :fromDate and :toDate \n" + 
			"                )\n" + 
			"        ) \n" + 
			"        and e.del_status=1 \n" + 
			"        and e.is_active=1  \n" + 
			"        and e.loc_id in (:locIds) and c.emp_cat_id=e.emp_cat_id and t.emp_type_id= e.emp_type_id order by emp_id", nativeQuery = true)
	List<EmployeeOnBenchReport> getOnBenchReport(@Param("fromDate") String fromDate,@Param("toDate") String toDate,@Param("locIds") List<Integer> locIds);

}
