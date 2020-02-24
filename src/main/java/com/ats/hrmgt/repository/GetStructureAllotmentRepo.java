package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.GetStructureAllotment;
 

public interface GetStructureAllotmentRepo extends JpaRepository<GetStructureAllotment, Integer> {

	@Query(value = "SELECT\n" + 
			"    e.emp_id,\n" + 
			"    e.emp_code,\n" + 
			"    e.first_name AS emp_fname,\n" + 
			"    e.middle_name AS emp_mname,\n" + 
			"    e.surname AS emp_sname,\n" + 
			"    dep.name AS emp_dept_name,\n" + 
			"    dg.name AS emp_cat_name,\n" + 
			"    l.lvs_name\n" + 
			"FROM\n" + 
			"    m_employees e\n" + 
			"LEFT JOIN leave_structure_allotment lsa ON\n" + 
			"    e.emp_id = lsa.emp_id AND lsa.cal_yr_id = 2\n" + 
			"LEFT JOIN leave_structure_header l ON\n" + 
			"    lsa.lvs_id = l.lvs_id AND l.del_status = 1\n" + 
			"LEFT JOIN m_designation dg ON\n" + 
			"    e.designation_id = dg.desig_id\n" + 
			"LEFT JOIN m_department dep ON\n" + 
			"    e.depart_id = dep.depart_id ", nativeQuery = true)

	List<GetStructureAllotment> getStructureAllotment(@Param("companyId") int companyId);
	
	

	@Query(value = "  SELECT\n" + 
			"        e.emp_id,\n" + 
			"        e.emp_code,\n" + 
			"        e.emp_fname,\n" + 
			"        e.emp_mname,\n" + 
			"        e.emp_sname    ,\n" + 
			"        d.emp_dept_name,\n" + 
			"        c.emp_cat_name ,\n" + 
			"        l.lvs_name      \n" + 
			"    FROM\n" + 
			"        emp_info e          \n" + 
			"    LEFT JOIN\n" + 
			"        leave_structure_allotment lsa              \n" + 
			"            ON e.emp_id=lsa.emp_id              \n" + 
			"            AND lsa.cal_yr_id=:calYrId      \n" + 
			"    LEFT JOIN\n" + 
			"        m_emp_department d              \n" + 
			"            ON e.emp_dept_id=d.emp_dept_id         \n" + 
			"    LEFT JOIN\n" + 
			"        m_emp_category c                 \n" + 
			"            ON e.emp_cat_id =c.emp_cat_id      \n" + 
			"    LEFT JOIN\n" + 
			"        leave_structure_header l              \n" + 
			"            ON    lsa.lvs_id=l.lvs_id              \n" + 
			"            AND l.del_status=1       \n" + 
			"    WHERE\n" + 
			"        e.del_status=1          \n" + 
			"        AND e.is_active=1           \n" + 
			"        AND e.company_id=:companyId        \n" + 
			"        AND e.loc_id IN(\n" + 
			"            :locIdList       \n" + 
			"        ) AND lsa.lvs_id=:lvsId", nativeQuery = true)

	List<GetStructureAllotment> getStructureAllotmentForProb(@Param("companyId") int companyId,
			@Param("locIdList") List<Integer> locIdList, @Param("calYrId") int calYrId,@Param("lvsId") int lvsId);

	/*
	 * SELECT e.emp_id,e.emp_code,e.emp_fname,e.emp_mname,e.emp_sname
	 * ,d.emp_dept_name, c.emp_cat_name ,l.lvs_name FROM emp_info e LEFT JOIN
	 * leave_structure_allotment lsa ON e.emp_id=lsa.emp_id LEFT JOIN
	 * m_emp_department d ON e.emp_dept_id=d.emp_dept_id LEFT JOIN m_emp_category c
	 * ON e.emp_cat_id =c.emp_cat_id LEFT JOIN leave_structure_header l ON
	 * lsa.lvs_id=l.lvs_id AND l.del_status=1 WHERE e.del_status=1 AND e.is_active=1
	 */

}
