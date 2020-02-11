package com.ats.hrmgt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ats.hrmgt.model.Holiday;
import com.ats.hrmgt.model.LeaveApply;
import com.ats.hrmgt.model.LeaveStsAndLeaveId;
import com.ats.hrmgt.model.WeeklyOff;
import com.ats.hrmgt.model.WeeklyOffShit;

@Service
public interface CommonFunctionService {

	public Integer findDateInWeekEnd(String fromDate, String toDate, List<WeeklyOff> weeklyList,
			List<WeeklyOffShit> weeklyOffShitList, int locationId, int weekendCatId);

	public Integer findDateInHoliday(String fromDate, String toDate, List<Holiday> holidayList, int locationId, int holidayCatId);

	public LeaveStsAndLeaveId findDateInLeave(String fromDate, List<LeaveApply> leavetList, int empId);

	public List<String> getDatesOfWeeklyOfForShiftingDate(String fromDate, String toDate, List<WeeklyOff> weeklyOfflist,
			int locationId, int holidayCatId);

}
