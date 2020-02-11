package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.GetWeeklyOff;

public interface GetWeeklyOffRepo extends JpaRepository<GetWeeklyOff, Integer> {

	@Query(value = "     SELECT\n" + 
			"        c.company_name,\n" + 
			"        w.*,\n" + 
			"        l.loc_name \n" + 
			"        ,wcat.wo_cat_name as week_off_cat\n" + 
			"    FROM\n" + 
			"        weekly_off w,\n" + 
			"        m_company c,\n" + 
			"        m_location l ,weekoff_category wcat\n" + 
			"    WHERE\n" + 
			"        w.del_status=1 \n" + 
			"        AND c.company_id=w.company_id \n" + 
			"        AND w.loc_id=l.loc_id \n" + 
			"        AND c.company_id=:companyId \n" + 
			"        AND w.loc_id IN(\n" + 
			"           :locIdList \n" + 
			"        ) AND wcat.wo_cat_id=w.ex_int1", nativeQuery = true)

	List<GetWeeklyOff> getListByCompanyId(@Param("companyId") int companyId,
			@Param("locIdList") List<Integer> locIdList);

}
