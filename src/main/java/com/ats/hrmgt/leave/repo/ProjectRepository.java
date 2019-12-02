package com.ats.hrmgt.leave.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.DashboardCount;
import com.ats.hrmgt.model.ProjectType;

public interface ProjectRepository extends JpaRepository<ProjectType, Integer> {

	List<ProjectType> findByDelStatusOrderByProjectTypeIdDesc(int i);

	ProjectType findByProjectTypeIdAndDelStatus(int projectTypeId, int i);

	@Transactional
	@Modifying
	@Query("update ProjectType set del_status=0  WHERE project_type_id=:projectTypeId")
	int deleteProjectType(int projectTypeId);

	List<ProjectType> findByDelStatusAndCompanyId(int i, int companyId);

}
