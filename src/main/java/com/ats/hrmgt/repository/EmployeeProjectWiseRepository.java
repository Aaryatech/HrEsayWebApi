package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmployeeProjectWise;

public interface EmployeeProjectWiseRepository extends JpaRepository<EmployeeProjectWise, Integer>{

	@Query(value = "select ph.pallot_id,ph.project_id,ph.emp_id,sum((DATEDIFF (ph.pallot_todt,  ph.pallot_fromdt)+1)*ph.pallot_daily_hrs) as hours,p.project_title, "
			+ "pt.emp_type_name,pt.emp_type_short_name,e.emp_fname,e.emp_sname, (e.emp_rate_perhr*s.value) as ctc from project_allotment ph,project_header p,m_emp_type pt,"
			+ "emp_info e,m_setting s where p.project_id=ph.project_id and e.emp_type_id=pt.emp_type_id and p.company_id=:compId and s.key='CTC' and e.emp_id=ph.emp_id group by "
			+ "ph.emp_id,ph.project_id order by ph.emp_id", nativeQuery = true)
	List<EmployeeProjectWise> employeeProjectWiseReport(@Param("compId") int compId);

}
