package com.ats.hrmgt.claim.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.claim.GetClaimStructureAllotment;
 

public interface GetClaimStructureAllotmentRepo extends JpaRepository<GetClaimStructureAllotment, Integer> {
	
	

	
	@Query(value = "   SELECT\n" + "        e.emp_id,\n" + "        e.emp_code,\n" + "        e.emp_fname,\n"
			+ "        e.emp_mname,\n" + "        e.emp_sname    ,\n" + "        d.emp_dept_name,\n"
			+ "        c.emp_cat_name ,\n" + "        l.claim_struct_name  as clms_name  \n" + "    FROM\n" + "        emp_info e     \n"
			+ "    LEFT JOIN\n" + "        claim_structure_allotment lsa \n" + "            ON e.emp_id=lsa.emp_id \n"
			+ "             \n" + "    LEFT JOIN\n" + "        m_emp_department d \n"
			+ "            ON e.emp_dept_id=d.emp_dept_id       \n" + "    LEFT JOIN\n" + "        m_emp_category c    \n"
			+ "            ON e.emp_cat_id =c.emp_cat_id \n" + "    LEFT JOIN\n" + "        claim_structure_header l \n"
			+ "            ON    lsa.clms_id =l.clm_struct_head_id \n" + "            AND l.del_status=1  \n" + "    WHERE\n"
			+ "        e.del_status=1 \n" + "        AND e.is_active=1  \n" + "        AND e.company_id=:companyId\n"
			+ "        AND e.loc_id IN(\n" + "            :locIdList\n" + "        )", nativeQuery = true)

	List<GetClaimStructureAllotment> getStructureAllotment(@Param("companyId") int companyId,
			@Param("locIdList") List<Integer> locIdList );
	
}
