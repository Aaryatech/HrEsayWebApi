package com.ats.hrmgt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ats.hrmgt.model.Holiday;
import com.ats.hrmgt.model.WeeklyOff;

@Service
public interface CommonFunctionService {

	public Integer findDateInWeekEnd(int empId, String fromDate, String toDate, List<WeeklyOff> weeklyList,
			int locationId);

	public Integer findDateInHoliday(int empId, String fromDate, String toDate, List<Holiday> holidayList,
			int locationId);

}
