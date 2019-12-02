package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.AllocatedEmpList;

public interface AllocatedEmpListRepository extends JpaRepository<AllocatedEmpList, Integer> {

	@Query(value = "select\n" + 
			"                pa.*,\n" + 
			"                e.emp_fname,\n" + 
			"                e.emp_mname,\n" + 
			"                e.emp_sname,p.project_title\n" + 
			"                \n" + 
			"            from\n" + 
			"                project_allotment pa,emp_info e,project_header p  \n" + 
			"            where\n" + 
			"                pa.project_id=:projectId\n" + 
			"                and pa.del_status=1\n" + 
			"                and pa.is_active=1 and e.emp_id=pa.emp_id and p.project_id=pa.project_id", nativeQuery = true)
	List<AllocatedEmpList> getAllocatedEmployeeList(@Param("projectId") int projectId);

	@Query(value = "select\n" + 
			"                pa.*,e.emp_fname, e.emp_mname, e.emp_sname,p.project_title         \n" + 
			"            from\n" + 
			"                project_allotment pa,emp_info e,project_header p               \n" + 
			"            where\n" + 
			"                ((\n" + 
			"                    :fromDate BETWEEN pa.pallot_fromdt and pa.pallot_todt \n" + 
			"                ) \n" + 
			"                or  (\n" + 
			"                    :toDate BETWEEN pa.pallot_fromdt and pa.pallot_todt \n" + 
			"                ) \n" + 
			"                or (\n" + 
			"                    pa.pallot_fromdt BETWEEN :fromDate and :toDate \n" + 
			"                )                  \n" + 
			"                or (\n" + 
			"                    pa.pallot_todt BETWEEN :fromDate and :toDate \n" + 
			"                ))\n" + 
			"       and pa.emp_id=:empId and e.emp_id=pa.emp_id and p.project_id=pa.project_id", nativeQuery = true)
	List<AllocatedEmpList> getEmployeeAllocatedHistory(@Param("fromDate") String fromDate,@Param("toDate") String toDate,@Param("empId") int empId);

}
