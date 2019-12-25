package com.ats.hrmgt.claim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmployeeRelatedTbls;

public interface EmployeeRelatedTblsRepo extends JpaRepository<EmployeeRelatedTbls, Integer> {
	
	@Query(value = "SELECT\n" + 
			"    emp.emp_id,\n" + 
			"    emp.emp_code, empinfo.emp_info_id,\n" + 
			"    sal.salary_info_id,\n" + 
			"    bank.bank_info_id,\n" + 
			"    nom.nominee_id,\n" + 
			"    GROUP_CONCAT(\n" + 
			"        DISTINCT salall.emp_sal_allowance_id\n" + 
			"    ) AS emp_sal_allowance_id,\n" + 
			"    GROUP_CONCAT(DISTINCT doc.doc_id) AS doc_id,\n" + 
			"    u.user_id\n" + 
			"FROM\n" + 
			"    `m_employees` emp\n" + 
			"LEFT JOIN tbl_emp_salary_info sal ON\n" + 
			"    emp.emp_id = sal.emp_id\n" + 
			"LEFT JOIN tbl_emp_info empinfo ON\n" + 
			"    emp.emp_id = empinfo.emp_id\n" + 
			"LEFT JOIN tbl_emp_bank_info bank ON\n" + 
			"    emp.emp_id = bank.emp_id\n" + 
			"LEFT JOIN tbl_emp_nominees nom ON\n" + 
			"    emp.emp_id = nom.emp_id\n" + 
			"LEFT JOIN emp_sal_allowance salall ON\n" + 
			"    emp.emp_id = salall.emp_id\n" + 
			"LEFT JOIN emp_docs doc ON\n" + 
			"    emp.emp_id = doc.emp_id\n" + 
			"LEFT JOIN m_user u ON\n" + 
			"    emp.emp_id = u.emp_id\n" + 
			"WHERE\n" + 
			"    emp.emp_code = :empCode",nativeQuery=true)
	EmployeeRelatedTbls getAllEmpRelatedInfo(@Param("empCode") String empCode);

}
