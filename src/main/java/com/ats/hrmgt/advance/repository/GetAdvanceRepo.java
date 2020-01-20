package com.ats.hrmgt.advance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

 import com.ats.hrmgt.model.advance.GetAdvance;

public interface GetAdvanceRepo  extends JpaRepository<GetAdvance, Integer>{
	
	@Query(value=" SELECT\n" + 
			"    tbl_advance.*,\n" + 
			"    m_employees.emp_code,\n" + 
			"    m_employees.first_name,\n" + 
			"    m_employees.middle_name,\n" + 
			"    m_employees.surname,\n" + 
			"    m_designation.name as designation\n" + 
			"FROM\n" + 
			"    m_employees,\n" + 
			"    tbl_advance,\n" + 
			"    m_designation\n" + 
			"WHERE\n" + 
			"    tbl_advance.del_status = 1  AND tbl_advance.is_ded=0 AND  tbl_advance.emp_id = m_employees.emp_id AND m_designation.desig_id = m_employees.designation_id AND tbl_advance.cmp_id=:companyId ",nativeQuery=true)
	List<GetAdvance> getSubModule(@Param("companyId") int companyId);
	
	
	///////
	
	@Query(value=" SELECT\n" + 
			"    tbl_advance.*,\n" + 
			"    m_employees.emp_code,\n" + 
			"    m_employees.first_name,\n" + 
			"    m_employees.middle_name,\n" + 
			"    m_employees.surname,\n" + 
			"    m_designation.name as designation\n" + 
			"FROM\n" + 
			"    m_employees,\n" + 
			"    tbl_advance,\n" + 
			"    m_designation\n" + 
			"WHERE\n" + 
			"    tbl_advance.del_status = 1  AND  tbl_advance.emp_id = m_employees.emp_id AND m_designation.desig_id = m_employees.designation_id AND tbl_advance.cmp_id=:companyId ",nativeQuery=true)
	List<GetAdvance> getAllAdv(@Param("companyId") int companyId);
	
	
	@Query(value=" SELECT\n" + 
			"    tbl_advance.*,\n" + 
			"    m_employees.emp_code,\n" + 
			"    m_employees.first_name,\n" + 
			"    m_employees.middle_name,\n" + 
			"    m_employees.surname,\n" + 
			"    m_designation.name as designation\n" + 
			"FROM\n" + 
			"    m_employees,\n" + 
			"    tbl_advance,\n" + 
			"    m_designation\n" + 
			"WHERE\n" + 
			"    tbl_advance.del_status = 1  AND  tbl_advance.emp_id = m_employees.emp_id AND m_designation.desig_id = m_employees.designation_id AND tbl_advance.cmp_id=:companyId AND YEAR(tbl_advance.adv_date)=:calYrId AND tbl_advance.emp_id=:empId",nativeQuery=true)
	List<GetAdvance> getSpecAdv(@Param("companyId") int companyId,@Param("empId") int empId,@Param("calYrId") String calYrId);
	
	

	@Query(value=" SELECT\n" + 
			"    tbl_advance.*,\n" + 
			"    m_employees.emp_code,\n" + 
			"    m_employees.first_name,\n" + 
			"    m_employees.middle_name,\n" + 
			"    m_employees.surname,\n" + 
			"    m_designation.name as designation\n" + 
			"FROM\n" + 
			"    m_employees,\n" + 
			"    tbl_advance,\n" + 
			"    m_designation\n" + 
			"WHERE\n" + 
			"    tbl_advance.del_status = 1  AND  tbl_advance.emp_id = m_employees.emp_id AND m_designation.desig_id = m_employees.designation_id AND tbl_advance.cmp_id=:companyId  AND YEAR(tbl_advance.adv_date)=:calYrId",nativeQuery=true)
	List<GetAdvance> getSpecYearAdv(@Param("companyId") int companyId,@Param("calYrId") String calYrId);
	
	
	
	@Query(value=" SELECT\n" + 
			"    tbl_advance.*,\n" + 
			"    m_employees.emp_code,\n" + 
			"    m_employees.first_name,\n" + 
			"    m_employees.middle_name,\n" + 
			"    m_employees.surname,\n" + 
			"    m_designation.name as designation\n" + 
			"FROM\n" + 
			"    m_employees,\n" + 
			"    tbl_advance,\n" + 
			"    m_designation\n" + 
			"WHERE\n" + 
			"    tbl_advance.del_status = 1  AND  tbl_advance.emp_id = m_employees.emp_id AND m_designation.desig_id = m_employees.designation_id AND tbl_advance.cmp_id=:companyId AND tbl_advance.emp_id=:empId",nativeQuery=true)
	List<GetAdvance> getSpecEmpAdv(@Param("companyId") int companyId,@Param("empId") int empId);
	
	

	@Query(value=" SELECT\n" + 
			"    tbl_advance.*,\n" + 
			"    m_employees.emp_code,\n" + 
			"    m_employees.first_name,\n" + 
			"    m_employees.middle_name,\n" + 
			"    m_employees.surname,\n" + 
			"    m_designation.name as designation\n" + 
			"FROM\n" + 
			"    m_employees,\n" + 
			"    tbl_advance,\n" + 
			"    m_designation\n" + 
			"WHERE\n" + 
			"    tbl_advance.del_status = 1  AND  tbl_advance.emp_id = m_employees.emp_id AND m_designation.desig_id = m_employees.designation_id AND tbl_advance.cmp_id=:companyId AND MONTH(tbl_advance.adv_date)=:month AND YEAR(tbl_advance.adv_date)=:year ORDER BY tbl_advance.emp_id ASC",nativeQuery=true)
	List<GetAdvance> getSpecEmpAdvForReport(@Param("companyId") int companyId,@Param("month") int month,@Param("year") int year);
	
	
	 
	
	

}
