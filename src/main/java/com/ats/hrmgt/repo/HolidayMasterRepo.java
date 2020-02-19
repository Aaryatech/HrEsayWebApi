package com.ats.hrmgt.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.HolidayMaster;

public interface HolidayMasterRepo extends JpaRepository<HolidayMaster, Integer> {

	List<HolidayMaster> findByDelStatus(int i);

	HolidayMaster findByHolidayIdAndDelStatus(int holidayId, int i);

	@Transactional
	@Modifying
	@Query("update HolidayMaster set del_status=0  WHERE holiday_id=:holidayId")
	int deleteHoliday(int holidayId);

}
