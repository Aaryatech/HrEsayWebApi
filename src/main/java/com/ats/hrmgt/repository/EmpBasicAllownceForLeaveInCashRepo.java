package com.ats.hrmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.EmpBasicAllownceForLeaveInCash;

public interface EmpBasicAllownceForLeaveInCashRepo extends JpaRepository<EmpBasicAllownceForLeaveInCash, Integer> {

	@Query(value = "select e.emp_id,coalesce(sf.basic,0) as basic,coalesce(sum(es.allowance_value),0) as allowance_value from m_employees e,tbl_emp_salary_info sf,"
			+ "emp_sal_allowance es where e.emp_id=:empId and sf.emp_id=e.emp_id and es.emp_id=e.emp_id and find_in_set "
			+ "( es.allowance_id , (select ex_var1 from  leave_structure_header where lvs_id=:lvsId))", nativeQuery = true) 
	EmpBasicAllownceForLeaveInCash getEmployeeBasicAndAllownceValueByEmpIdAndStructId(int empId, int lvsId);
}
