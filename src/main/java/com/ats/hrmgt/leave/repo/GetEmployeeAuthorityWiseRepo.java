package com.ats.hrmgt.leave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.GetEmployeeAuthorityWise;

public interface GetEmployeeAuthorityWiseRepo extends JpaRepository<GetEmployeeAuthorityWise, Integer>{


	@Query(value = " SELECT l.emp_id FROM leave_authority l WHERE l.ini_auth_emp_id=:empId OR l.fin_auth_emp_id=:empId", nativeQuery = true)

	List<GetEmployeeAuthorityWise> getEmpIdList(@Param("empId") int empId
			);
	
	
	@Query(value = " SELECT l.emp_id FROM leave_authority l WHERE l.ini_auth_emp_id=:empId ", nativeQuery = true)

	List<GetEmployeeAuthorityWise> getEmpIdListForInitialAuth(@Param("empId") int empId
			);
	
	@Query(value = " SELECT l.emp_id FROM leave_authority l WHERE l.fin_auth_emp_id=:empId", nativeQuery = true)

	List<GetEmployeeAuthorityWise> getEmpIdListForFinalAuth(@Param("empId") int empId
			);
	
	@Query(value = " SELECT ca.emp_id FROM claim_authority ca WHERE ca.ca_ini_auth_emp_id=:empId ", nativeQuery = true)

	List<GetEmployeeAuthorityWise> getClaimIdListForInitialAuth(@Param("empId") int empId
			);
	
	@Query(value = " SELECT ca.emp_id FROM claim_authority ca WHERE l.ca_fin_auth_emp_id=:empId", nativeQuery = true)

	List<GetEmployeeAuthorityWise> getClaimIdListForFinalAuth(@Param("empId") int empId
			);
	
	
	@Query(value = " SELECT l.emp_id FROM claim_authority l WHERE l.ca_ini_auth_emp_id =:empId OR l.ca_fin_auth_emp_id=:empId", nativeQuery = true)

	List<GetEmployeeAuthorityWise> getEmpIdListInClaimAuth(@Param("empId") int empId
			);
	
}
