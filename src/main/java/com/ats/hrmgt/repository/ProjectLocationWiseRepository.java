package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.ProjectLocationWise;

public interface ProjectLocationWiseRepository extends JpaRepository<ProjectLocationWise, Integer>{

	@Query(value = "select\n" + 
			"        ph.pallot_id,\n" + 
			"        ph.project_id,\n" + 
			"        e.loc_id,\n" + 
			"        sum(((DATEDIFF (ph.pallot_todt,\n" + 
			"        ph.pallot_fromdt)+1)*ph.pallot_daily_hrs)*e.emp_rate_perhr) as resource_cost,\n" + 
			"        p.project_title,\n" + 
			"        l.loc_name,\n" + 
			"        case              \n" + 
			"            when p.ex_int1=1 then p.ex_var1*((DATEDIFF (p.project_est_enddt,\n" + 
			"            p.project_est_startdt)+1)/30)              \n" + 
			"            else p.ex_var1          \n" + 
			"        end as revenue \n" + 
			"       \n" + 
			"    from\n" + 
			"        project_allotment ph,\n" + 
			"        project_header p,\n" + 
			"        m_emp_type pt,\n" + 
			"        emp_info e,\n" + 
			"        m_setting s,m_location l \n" + 
			"    where\n" + 
			"        p.project_id=ph.project_id \n" + 
			"        and e.emp_type_id=pt.emp_type_id \n" + 
			"        and p.company_id=:compId\n" + 
			"        and s.key='CTC' \n" + 
			"        and e.emp_id=ph.emp_id and l.loc_id=e.loc_id\n" + 
			"    group by\n" + 
			"        e.loc_id,\n" + 
			"        ph.project_id \n" + 
			"    order by\n" + 
			"        ph.project_id", nativeQuery = true)
	List<ProjectLocationWise> projectLocationwiseReport(@Param("compId") int compId);

}
