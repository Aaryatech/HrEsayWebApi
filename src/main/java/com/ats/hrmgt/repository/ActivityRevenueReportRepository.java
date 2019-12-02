package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.ActivityRevenueReport;

public interface ActivityRevenueReportRepository extends JpaRepository<ActivityRevenueReport, Integer>{

	/*@Query(value = "select\n" + 
			"        ph.project_type_id ,\n" + 
			"        sum(case when ph.ex_int1=1 then ph.ex_var1*1 else ph.ex_var1 end )  as revenue ,\n" + 
			"        coalesce((select\n" + 
			"            sum(pallot_daily_hrs*(DATEDIFF ( pallot_todt,\n" + 
			"            pallot_fromdt)+1)) \n" + 
			"        from\n" + 
			"            project_allotment,\n" + 
			"            project_header  \n" + 
			"        where\n" + 
			"            project_header.project_type_id =ph.project_type_id \n" + 
			"            and project_header.project_id=project_allotment.project_id),\n" + 
			"        0) as resource_cost,\n" + 
			"        pt.project_type_title,\n" + 
			"        pt.project_type_title_short \n" + 
			"    from\n" + 
			"        project_header ph,\n" + 
			"        project_type pt \n" + 
			"    where\n" + 
			"        pt.project_type_id=ph.project_type_id \n" + 
			"        and ph.company_id=:compId\n" + 
			"        and ph.del_status=1\n" + 
			"        and pt.del_status=1\n" + 
			"    group by\n" + 
			"        ph.project_type_id", nativeQuery = true)*/
	@Query(value = "select\n" + 
			"        ph.project_type_id ,\n" + 
			"        sum(case \n" + 
			"            when ph.ex_int1=1 then ph.ex_var1*((DATEDIFF (ph.project_est_enddt,\n" + 
			"            ph.project_est_startdt)+1)/30) \n" + 
			"            else ph.ex_var1 \n" + 
			"        end )  as revenue ,\n" + 
			"        coalesce((select\n" + 
			"            sum((pallot_daily_hrs*(DATEDIFF ( pallot_todt,\n" + 
			"            pallot_fromdt)+1))*emp_info.emp_rate_perhr)          \n" + 
			"        from\n" + 
			"            project_allotment,\n" + 
			"            project_header,emp_info           \n" + 
			"        where\n" + 
			"            project_header.project_type_id =ph.project_type_id              \n" + 
			"            and project_header.project_id=project_allotment.project_id and emp_info.emp_id=project_allotment.emp_id),\n" + 
			"        0) as resource_cost,\n" + 
			"        pt.project_type_title,\n" + 
			"        pt.project_type_title_short      \n" + 
			"    from\n" + 
			"        project_header ph,\n" + 
			"        project_type pt      \n" + 
			"    where\n" + 
			"        pt.project_type_id=ph.project_type_id          \n" + 
			"        and ph.company_id=:compId        \n" + 
			"        and ph.del_status=1         \n" + 
			"        and pt.del_status=1     \n" + 
			"    group by\n" + 
			"        ph.project_type_id", nativeQuery = true)
	List<ActivityRevenueReport> revenueReportProjectCategoryWise(@Param("compId") int compId);

}
