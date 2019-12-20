package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.LeaveApply;
 


public interface LeaveApplyRepository extends JpaRepository<LeaveApply, Integer> {

	List<LeaveApply> findByDelStatus(int i);

	@Transactional
	@Modifying
	@Query("update LeaveApply set del_status=0  WHERE leave_id=:leaveId")
	int deleteLeaveApply(int leaveId);

	LeaveApply findByLeaveIdAndDelStatus(int leaveId, int i);
	
	
	@Transactional
	@Modifying
	@Query("update LeaveApply set ex_int2=:trailId  WHERE leave_id=:leaveId")
	int updateLeaveApply(int leaveId,int trailId);

	
	
	@Transactional
	@Modifying
	@Query("update LeaveApply set ex_int1=:status  WHERE leave_id=:leaveId")
	int updateLeaveStatus(int leaveId,int status);

	@Query(value="SELECT * FROM leave_apply WHERE ((:fromDate between leave_fromdt and leave_todt) or (:toDate between leave_fromdt and leave_todt) or "
			+ "(leave_fromdt between :fromDate and :toDate)or (leave_todt between :fromDate and :toDate)) and ex_int1 in (1,2,3) and emp_id=:empId",nativeQuery=true)
	List<LeaveApply> checkDateForRepetedLeaveValidation(@Param("fromDate") String fromDate,@Param("toDate") String toDate,@Param("empId") int empId);

	@Query(value="SELECT * FROM leave_apply WHERE ((DATE(:fromDate + INTERVAL 1 DAY) between leave_fromdt and leave_todt) or (DATE(:toDate- INTERVAL 1 DAY) between "
			+ "leave_fromdt and leave_todt))  and ex_int1 in (1,2,3) and emp_id=:empId and lv_type_id!=:leaveTypeId",nativeQuery=true)
	List<LeaveApply> checkContinueDateLeave(@Param("fromDate") String fromDate,@Param("toDate") String toDate,@Param("empId") int empId,@Param("leaveTypeId") int leaveTypeId);

	@Query(value="select * from leave_apply where ((leave_fromdt between :fromDate and :toDate) or "
			+ "(leave_todt between :fromDate and :toDate) ) and ex_int1=3 ",nativeQuery=true)
	List<LeaveApply> getleavetList(@Param("fromDate") String fromDate,@Param("toDate") String toDate);

	@Transactional
	@Modifying
	@Query("update LeaveApply set leave_num_days=:updateNoOfDays,leave_cancle_remark=:reason  WHERE leave_id=:leaveId")
	int updateNoOfDaysInLeave(@Param("leaveId")int leaveId,@Param("updateNoOfDays") float updateNoOfDays,@Param("reason") String reason);

	


}
