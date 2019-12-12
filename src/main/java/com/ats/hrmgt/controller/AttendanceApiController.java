package com.ats.hrmgt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.DailyAttendance;
import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.LoginResponse;
import com.ats.hrmgt.model.SummaryDailyAttendance;
import com.ats.hrmgt.repository.AccessRightModuleRepository;
import com.ats.hrmgt.repository.DailyAttendanceRepository;
import com.ats.hrmgt.repository.EmployeeMasterRepository;
import com.ats.hrmgt.repository.SummaryDailyAttendanceRepository;

@RestController
public class AttendanceApiController {

	@Autowired
	AccessRightModuleRepository accessRightModuleRepository;

	@Autowired
	EmployeeMasterRepository employeeMasterRepository;

	@Autowired
	DailyAttendanceRepository dailyAttendanceRepository;

	@Autowired
	SummaryDailyAttendanceRepository summaryDailyAttendanceRepository;

	@RequestMapping(value = { "/initiallyInsertDailyRecord" }, method = RequestMethod.POST)
	public @ResponseBody Info initiallyInsertDailyRecord(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("userId") int userId) {

		Info info = new Info();
		try {

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date fmdt = df.parse(fromDate);
			Date todt = df.parse(toDate);

			System.out.println(fmdt + " " + todt);

			Calendar temp = Calendar.getInstance();
			temp.setTime(fmdt);
			int year = temp.get(Calendar.YEAR);
			int month = temp.get(Calendar.MONTH) + 1;

			List<EmployeeMaster> empList = employeeMasterRepository.getEmplistForAssignAuthority();

			List<SummaryDailyAttendance> summaryDailyAttendanceList = new ArrayList<>();

			SummaryDailyAttendance summaryDailyAttendance = new SummaryDailyAttendance();

			for (int i = 0; i < empList.size(); i++) {

				summaryDailyAttendance = new SummaryDailyAttendance();
				summaryDailyAttendance.setEmpCode(empList.get(i).getEmpCode());
				summaryDailyAttendance.setEmpId(empList.get(i).getEmpId());
				summaryDailyAttendance.setEmpName(empList.get(i).getFirstName() + " " + empList.get(i).getSurname());
				summaryDailyAttendance.setMonth(month);
				summaryDailyAttendance.setYear(year);
				summaryDailyAttendance.setCompanyId(1);
				summaryDailyAttendanceList.add(summaryDailyAttendance);

				fmdt = df.parse(fromDate);
				// System.out.println(empList.get(i).getEmpId() + " - " + fmdt);
				List<DailyAttendance> dailyAttendanceList = new ArrayList<>();
				for (Date j = fmdt; j.compareTo(todt) <= 0;) {

					// System.out.println(j);

					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					temp = Calendar.getInstance();
					temp.setTime(j);
					String attdate = sf.format(j);
					DailyAttendance dailyAttendance = new DailyAttendance();
					dailyAttendance.setEmpCode(summaryDailyAttendance.getEmpCode());
					dailyAttendance.setEmpId(summaryDailyAttendance.getEmpId());
					dailyAttendance.setCompanyId(1);
					dailyAttendance.setLocationId(empList.get(i).getLocationId());
					dailyAttendance.setAttDate(attdate);
					dailyAttendance.setEmpName(empList.get(i).getFirstName() + " " + empList.get(i).getSurname());
					dailyAttendanceList.add(dailyAttendance);
					j.setTime(j.getTime() + 1000 * 60 * 60 * 24);

				}
				List<DailyAttendance> dailyAttendanceSaveRes = dailyAttendanceRepository.saveAll(dailyAttendanceList);

			}

			List<SummaryDailyAttendance> summaryDailyAttendanceSaveRes = summaryDailyAttendanceRepository
					.saveAll(summaryDailyAttendanceList);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return info;

	}

}
