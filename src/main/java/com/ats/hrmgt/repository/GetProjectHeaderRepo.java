package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.GetProjectHeader;

public interface GetProjectHeaderRepo extends JpaRepository<GetProjectHeader, Integer> {

	@Query(value = "SELECT p.*,l.loc_name,pt.project_type_title,pt.project_type_title_short ,c.cust_name ,"
			+ "e.emp_code,e.emp_fname,e.emp_mname,e.emp_sname FROM project_header p,m_location l ,"
			+ "project_type pt,m_customer c,emp_info e WHERE p.del_status=1 AND p.company_id=:companyId AND "
			+ "p.loc_id=l.loc_id AND pt.project_type_id=p.project_type_id AND c.cust_id=p.cust_id AND "
			+ "e.emp_id=p.project_manager_emp_id ", nativeQuery = true)

	List<GetProjectHeader> getProListByCompanyId(@Param("companyId") int companyId);

	
	
	@Query(value = "SELECT p.*,l.loc_name,pt.project_type_title,pt.project_type_title_short ,c.cust_name ,"
			+ "e.emp_code,e.emp_fname,e.emp_mname,e.emp_sname FROM project_header p,m_location l ,"
			+ "project_type pt,m_customer c,emp_info e WHERE p.del_status=1 AND p.company_id=:companyId AND "
			+ "p.loc_id=l.loc_id AND pt.project_type_id=p.project_type_id AND c.cust_id=p.cust_id AND "
			+ "e.emp_id=p.project_manager_emp_id  AND p.project_manager_emp_id=:mangId", nativeQuery = true)

	List<GetProjectHeader> getProListByCompanyIdForManager(@Param("companyId") int companyId,@Param("mangId") int mangId);

	
	@Query(value = "SELECT\n" + 
			"        p.*,\n" + 
			"        l.loc_name,\n" + 
			"        pt.project_type_title,\n" + 
			"        pt.project_type_title_short ,\n" + 
			"        c.cust_name ,\n" + 
			"        e.emp_code,\n" + 
			"        e.emp_fname,\n" + 
			"        e.emp_mname,\n" + 
			"        e.emp_sname \n" + 
			"    FROM\n" + 
			"        project_header p,\n" + 
			"        m_location l ,\n" + 
			"        project_type pt,\n" + 
			"        m_customer c,\n" + 
			"        emp_info e \n" + 
			"    WHERE\n" + 
			"        p.del_status=1  \n" + 
			"        AND p.loc_id=l.loc_id \n" + 
			"        AND pt.project_type_id=p.project_type_id \n" + 
			"        AND c.cust_id=p.cust_id \n" + 
			"        AND e.emp_id=p.project_manager_emp_id and p.project_id=:projectId", nativeQuery = true)
	GetProjectHeader getProjectDetailByProjectId(@Param("projectId") int projectId);

}
