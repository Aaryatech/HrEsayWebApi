package com.ats.hrmgt.repository;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.TblEmpBankInfo;

import java.lang.String;

public interface EmployeeMasterRepository extends JpaRepository<EmployeeMaster, Integer> {

	@Query(value = " SELECT * FROM m_employees ", nativeQuery = true)
	List<EmployeeMaster> getEmplistForAssignAuthorityAll();
	
	 
	 
 

	@Query(value = "SELECT e.* from m_employees e, leave_authority au where au.emp_id=e.emp_id and (au.ini_auth_emp_id=:empId or au.fin_auth_emp_id=:empId or e.emp_id=:empId)", nativeQuery = true)
	List<EmployeeMaster> getAuthorityWiseEmpListByEmpId(@Param("empId")int empId);

	@Query(value = "select\n" + 
			"        * \n" + 
			"    from\n" + 
			"        m_employees \n" + 
			"    where\n" + 
			"        emp_id not in (\n" + 
			"            select\n" + 
			"                emp_id \n" + 
			"            from\n" + 
			"                leave_balance_cal \n" + 
			"            where\n" + 
			"                cal_yr_id=(\n" + 
			"                    select\n" + 
			"                        cal_yr_id \n" + 
			"                    from\n" + 
			"                        dm_cal_year \n" + 
			"                    where\n" + 
			"                        is_current=1\n" + 
			"                )\n" + 
			"            ) \n" + 
			"            ", nativeQuery = true)
	List<EmployeeMaster> getemplistwhichisnotyearend();

	@Query(value = "SELECT e.* from m_employees e  where e.emp_id=:empId", nativeQuery = true)
	EmployeeMaster getEmpInfoByEmpId(@Param("empId") int empId);
	
	@Query(value = "SELECT count(emp_id) FROM m_employees WHERE cmp_code=:companyId AND contractor_id=:contractId", nativeQuery = true)
	int getEmpInfoByContractId(@Param("contractId") int contractId, @Param("companyId") int companyId);
	
	@Query(value = "SELECT count(emp_id) FROM m_employees WHERE cmp_code=:companyId AND depart_id=:deptId",nativeQuery=true)
	int getEmpInfoByDepartment(@Param("deptId") int deptId, @Param("companyId") int companyId);
	
	
	@Query(value = "SELECT count(emp_id) FROM m_employees WHERE cmp_code=:companyId AND designation_id=:desigId",nativeQuery=true)
	int getEmpInfoByDesigId(@Param("desigId") int desigId, @Param("companyId") int companyId);

	public List<EmployeeMaster> findByCmpCodeAndDelStatusOrderByEmpIdDesc(int companyId, int del);
	
	public List<EmployeeMaster> findByDelStatusAndCmpCodeOrderByEmpIdDesc(int del, int companyId);
	
	
	public List<EmployeeMaster> findByDelStatusAndCmpCodeAndSubCmpIdOrderByEmpIdDesc(int del, int companyId,int subCompId);
	public List<EmployeeMaster> findByDelStatusAndCmpCodeAndLocationIdOrderByEmpIdDesc(int del, int companyId,int locationId);
	
	public List<EmployeeMaster> findByDelStatusAndEmpId(int del, int empId);
	
	public EmployeeMaster findByEmpIdAndDelStatus(int empId, int del);

	public EmployeeMaster findByEmpCodeAndDelStatus(int empCode, int del);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_employees SET del_status=0 WHERE emp_id=:empId",nativeQuery=true)
	public int deleteEmployee(@Param("empId") int empId);
	

	@Transactional
	@Modifying
	@Query(value = "UPDATE m_employees SET current_shiftid =:shiftId WHERE emp_id IN(:empIdList)", nativeQuery = true)
	int assignShift(@Param("empIdList") List<Integer> empIdList, @Param("shiftId") String shiftId);

	
	EmployeeMaster findByEmpCode(String empcode);

	 

	List<EmployeeMaster> findByEmpTypeAndDelStatus(int empTypeId, int i);

	List<EmployeeMaster> findByLocationIdAndDelStatus(int locId, int i);
	
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE m_employees SET  holiday_category   =:holiCatId WHERE emp_id IN(:empIdList)", nativeQuery = true)
	int assignHoliCat(@Param("empIdList") List<Integer> empIdList, @Param("holiCatId") String holiCatId);
	
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE m_employees SET  weekend_category =:holiCatId WHERE emp_id IN(:empIdList)", nativeQuery = true)
	int weekHoliCat(@Param("empIdList") List<Integer> empIdList, @Param("holiCatId") String holiCatId);
 
	@Query(value = "SELECT e.* from m_employees e  where e.del_status=1 AND e.current_shiftid=0", nativeQuery = true)
	List<EmployeeMaster> getEmpSalAssign();
}
