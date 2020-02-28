package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.LeaveBalanceCal;

public interface LeaveBalanceCalRepo extends JpaRepository<LeaveBalanceCal, Integer> {

	List<LeaveBalanceCal> findByCalYrIdAndEmpIdAndDelStatusAndIsActive(int empId, int calYrId, int j, int k);

	@Transactional
	@Modifying
	@Query("update LeaveBalanceCal set op_bal=:noDays  WHERE lv_type_id=:lvTypeId AND emp_id=:empId AND cal_yr_id=:calYrID  ")
	int updateLeaveBalCal(int lvTypeId, int empId, int calYrID, int noDays);

	@Transactional
	@Modifying
	@Query("update LeaveBalanceCal set ex_int1=0,ex_var1=:date  WHERE cal_yr_id=:yearId AND emp_id in (:empId)")
	int updateIsPaidIncash(int yearId, List<Integer> empId,String date);
}
