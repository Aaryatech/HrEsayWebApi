package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.ShiftMaster;

public interface ShiftMasterRepository extends JpaRepository<ShiftMaster, Integer> {

	@Query(value = "select * from tbl_shift_timming where location_id in (:locationIds) and status=1", nativeQuery = true)
	List<ShiftMaster> showShiftListByLocationIds(@Param("locationIds") List<Integer> locationIds);

	@Transactional
	@Modifying
	@Query("update ShiftMaster set status=0  WHERE id=:shiftId")
	int deleteShiftTime(@Param("shiftId") int shiftId);

	@Query(value = "select * from tbl_shift_timming where location_id =:locationId and status=1 and self_group_id=:groupId", nativeQuery = true)
	List<ShiftMaster> getShiftListByGroupIdandlocId(@Param("locationId") int locationId, @Param("groupId") int groupId);

	List<ShiftMaster> findByStatus( int i);

	List<ShiftMaster> findBySelfGroupIdAndStatus(int bonusId, int i);

}
