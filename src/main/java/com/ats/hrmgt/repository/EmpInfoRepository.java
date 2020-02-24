package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmpInfo;

public interface EmpInfoRepository extends JpaRepository<EmpInfo, Integer> {

	@Query(value = "SELECT e.*,emp_sal.cmp_joining_date, emp_sal.sal_basis, emp_sal.salary_type_id  FROM m_employees e, tbl_emp_salary_info emp_sal "
			+ "where e.emp_id=emp_sal.emp_id and e.emp_id not in (select emp_id from tbl_attt_daily_daily  where att_date between :fromDate "
			+ "and :toDate group by emp_id) and e.del_status=1 and (emp_sal.cmp_leaving_date IS NULL or emp_sal.cmp_leaving_date='' or emp_sal.cmp_leaving_date=1970-00-00 or  date_format(emp_sal.cmp_leaving_date,'%Y-%m')>=date_format(:fromDate,'%Y-%m'))", nativeQuery = true)
	List<EmpInfo> getEmpList(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	@Query(value = "SELECT e.*,emp_sal.cmp_joining_date, emp_sal.sal_basis, emp_sal.salary_type_id  FROM m_employees e, tbl_emp_salary_info emp_sal "
			+ "where e.emp_id=emp_sal.emp_id and e.del_status=1 and (emp_sal.cmp_leaving_date IS NULL or emp_sal.cmp_leaving_date='' or emp_sal.cmp_leaving_date=1970-00-00 or  date_format(emp_sal.cmp_leaving_date,'%Y-%m')>=date_format(:fromDate,'%Y-%m'))", nativeQuery = true)
	List<EmpInfo> getEmpListAll(@Param("fromDate") String fromDate);
	
	@Query(value = "SELECT e.*,emp_sal.cmp_joining_date, emp_sal.sal_basis, emp_sal.salary_type_id  FROM m_employees e, tbl_emp_salary_info emp_sal "
			+ "where e.emp_id=emp_sal.emp_id and e.del_status=1", nativeQuery = true)
	List<EmpInfo> getEmpListAll();

}
