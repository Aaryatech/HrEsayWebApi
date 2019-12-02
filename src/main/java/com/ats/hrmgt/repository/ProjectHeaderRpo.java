package com.ats.hrmgt.repository;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

 import com.ats.hrmgt.model.ProjectHeader;

public interface ProjectHeaderRpo extends JpaRepository<ProjectHeader, Integer> {

	List<ProjectHeader> findByDelStatusAndCompanyId(int i, int companyId);

	@Transactional
	@Modifying
	@Query("update ProjectHeader set del_status=0  WHERE project_id=:projectId")
	int deleteProjectHeader(int projectId);
	
	@Transactional
	@Modifying
	@Query("update ProjectHeader set project_status=:status,project_completion=:proComp,"
			+ "maker_user_id=:userId,maker_enter_datetime=:dateTime"
			+ "  WHERE project_id=:projectId")
	int updateProjectHeader(int projectId,String status,int proComp,int userId,String dateTime);

	ProjectHeader findByProjectIdAndDelStatus(int projectId, int i);
 
	@Query(value = " select project_header.* from project_header,project_allotment where  "
			+ "project_allotment.project_id=project_header.project_id AND project_header.del_status=1"
			+ "  AND project_allotment.del_status=1 AND project_allotment.emp_id=:empId AND "
			+ "project_header.is_active=1 AND project_allotment.is_active=1 AND  project_header.company_id=:companyId   ", nativeQuery = true)

	List<ProjectHeader> getEmpListByCompanyIdAndEmpId(@Param("companyId") int companyId,@Param("empId") int empId
		 );

	ProjectHeader findByProjectId(int projId);

	
	@Query(value = "select\n" + 
			"        project_header.* " + 
			"    from\n" + 
			"        project_header,\n" + 
			"        project_allotment \n" + 
			"    where\n" + 
			"        project_allotment.project_id=project_header.project_id \n" + 
			"        AND project_header.del_status=1  \n" + 
			"        AND project_allotment.del_status=1 \n" + 
			"        AND project_allotment.emp_id=:empId \n" + 
			"        AND project_header.is_active=1 \n" + 
			"        AND project_allotment.is_active=1 \n" + 
			"        AND  project_header.company_id=:companyId\n" + 
			"        and (select DATEDIFF(:toDay,MAX(pallot_todt)) from project_allotment where project_allotment.emp_id=1  )<30", nativeQuery = true)
	List<ProjectHeader> getEmpListByCompanyIdAndEmpId(@Param("companyId") int companyId,@Param("empId") int empId,@Param("toDay") String toDay);
}
