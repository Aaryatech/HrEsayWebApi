package com.ats.hrmgt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.LeaveCashReport;

public interface LeaveCashReportRepository extends JpaRepository<LeaveCashReport, Integer>{

	@Query(value = "select lb.emp_id,sum(lb.lv_encash) as leave_count,sum(lb.lv_encash_remarks) as cash,e.emp_code,e.first_name,e.surname,lb.ex_var1 as paid_date from leave_balance_cal lb,m_employees e  "
			+ "where lb.cal_yr_id=:yearId and lb.ex_int1=1 and lb.lv_encash>0 and e.emp_id=lb.emp_id group by lb.emp_id order by e.emp_id", nativeQuery = true)
	List<LeaveCashReport> getPendingListOfleaveCash(int yearId);

	@Query(value = "select lb.emp_id,sum(lb.lv_encash) as leave_count,sum(lb.lv_encash_remarks) as cash,e.emp_code,e.first_name,e.surname,lb.ex_var1 as paid_date from leave_balance_cal lb,m_employees e  "
			+ "where lb.cal_yr_id=:yearId and lb.ex_int1=0 and lb.lv_encash>0 and e.emp_id=lb.emp_id group by lb.emp_id order by e.emp_id", nativeQuery = true)
	List<LeaveCashReport> getPaidListOfleaveCash(int yearId);

}
