package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.LeaveType;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer> {

	List<LeaveType> findByDelStatus(int i);

	@Transactional
	@Modifying
	@Query("update LeaveType set del_status=0  WHERE lv_type_id=:lvTypeId")
	int deleteLeaveType(int lvTypeId);

	LeaveType findByLvTypeIdAndDelStatus(int lvTypeId, int i);


	List<LeaveType> findByDelStatusAndIsStructuredAndCompanyId(int i, int j, int companyId);

	//int findByLvTitleShortAndCompanyId(String lvTitleShort, int companyId);

	LeaveType findByCompanyIdAndLvTitleShort(int compId, String valueType);

}
