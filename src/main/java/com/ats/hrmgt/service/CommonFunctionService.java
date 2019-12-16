package com.ats.hrmgt.service;

import java.util.List;

import org.springframework.stereotype.Service; 

import com.ats.hrmgt.model.Holiday;
import com.ats.hrmgt.model.WeeklyOff;

@Service
public interface CommonFunctionService {

	public  Integer CalculateDayConsideringHolidayAndWeekend(List<Integer> empIds, String fromDate, String toDate,
			List<WeeklyOff> weeklyList,List<Holiday> holidayList,int locationId); 

}
