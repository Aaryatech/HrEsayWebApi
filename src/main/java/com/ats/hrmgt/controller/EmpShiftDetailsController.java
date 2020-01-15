package com.ats.hrmgt.controller;

import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.DailyAttendance;
import com.ats.hrmgt.model.Designation;
import com.ats.hrmgt.model.EmpShiftDetails;
import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.Holiday;
import com.ats.hrmgt.model.LeaveApply;
import com.ats.hrmgt.model.LeaveStsAndLeaveId;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.model.ShiftMaster;
import com.ats.hrmgt.model.WeeklyOff;
import com.ats.hrmgt.model.WeeklyOffShit;
import com.ats.hrmgt.repository.DailyAttendanceRepository;
import com.ats.hrmgt.repository.EmployeeMasterRepository;
import com.ats.hrmgt.repository.HolidayRepo;
import com.ats.hrmgt.repository.LeaveApplyRepository;
import com.ats.hrmgt.repository.SettingRepo;
import com.ats.hrmgt.repository.ShiftMasterRepository;
import com.ats.hrmgt.repository.WeeklyOffRepo;
import com.ats.hrmgt.repository.WeeklyOffShitRepository;
import com.ats.hrmgt.service.CommonFunctionService;

@RestController
public class EmpShiftDetailsController {

	@Autowired
	EmployeeMasterRepository empRepo;

	@Autowired
	WeeklyOffRepo weeklyOffRepo1;

	@Autowired
	CommonFunctionService commonFunctionService;

	@Autowired
	SettingRepo settingRepo;

	@Autowired
	ShiftMasterRepository shiftMasterRepository;

	@Autowired
	DailyAttendanceRepository dailyAttendanceRepository;

	@Autowired
	LeaveApplyRepository leaveApplyRepository;

	@Autowired
	HolidayRepo holidayRepo;

	@Autowired
	WeeklyOffShitRepository weeklyOffShitRepository;

	@RequestMapping(value = { "/getEmpShiftDetails" }, method = RequestMethod.POST)
	public List<EmpShiftDetails> getEmpShiftDetails(@RequestParam String date1, @RequestParam int companyId,
			@RequestParam String empRes,int days ) {
		List<EmpShiftDetails> empShiftList = new ArrayList<EmpShiftDetails>();
		List<String> datesList = new ArrayList<>();
		try {

			String[] a = date1.split("-");
			String year = a[0];
			String month = a[1];
			if (month.length() == 1) {
				month = "0".concat(month);
			}

			LocalDate localDate = LocalDate.parse((date1));
			LocalDate oneMonthBef = localDate.plusMonths(-1);
			// to get length of month
			 
			// to get all employees
			List<EmployeeMaster> emplist = new ArrayList<EmployeeMaster>();

			if (empRes.equals("-1")) {
				try {
					emplist = empRepo.findByDelStatusAndCmpCodeOrderByEmpIdDesc(1, companyId);
				} catch (Exception e) {
					System.err.println("Excep in getAllEmployee : " + e.getMessage());
					e.printStackTrace();
				}
			} else {

				try {
					emplist = empRepo.findByDelStatusAndEmpId(1, Integer.parseInt(empRes));
				} catch (Exception e) {
					System.err.println("Excep in getAllEmployee : " + e.getMessage());
					e.printStackTrace();
				}

			}

			// to get week_start_day
			Setting settingList = new Setting();
			settingList = settingRepo.findByKey("week_start_day");
			String startDay = settingList.getValue();

			for (int i = 0; i < emplist.size(); i++) {

				int empId = emplist.get(i).getEmpId();
				int locId = emplist.get(i).getLocationId();
				System.err.println("empId " + empId);
				String empName = emplist.get(i).getSurname().concat(" ").concat(emplist.get(i).getFirstName());
				String empCode = emplist.get(i).getEmpCode();
				int currShiftId = 0;
				String currShiftName = null;

				DailyAttendance dailyRec = dailyAttendanceRepository.findLastMonthRecordOfEmp(empId,
						oneMonthBef.getMonthValue(), oneMonthBef.getYear());

				if (dailyRec != null) {
					currShiftId = dailyRec.getCurrentShiftid();
					currShiftName = dailyRec.getCurrentShiftname();
				} else {
					dailyRec = dailyAttendanceRepository.findLastMonthRecordOfEmp(empId);
					if (dailyRec != null) {
						currShiftId = dailyRec.getCurrentShiftid();
						currShiftName = dailyRec.getCurrentShiftname();
					} else {
						currShiftId = emplist.get(i).getCurrentShiftid();
						ShiftMaster shiftm = shiftMasterRepository.findByIdAndStatus(currShiftId, 1);

						currShiftName = shiftm.getShiftname();

					}

				}

				for (int j = 1; j <= days; j++) {

					System.out.println("emp 1 day " + j);
					EmpShiftDetails empDet = new EmpShiftDetails();
					String day = null;
					if (String.valueOf(j).length() == 1) {
						day = "0".concat(String.valueOf(j));
					} else {
						day = String.valueOf(j);
					}
					String dateOfMonth = String.valueOf(year).concat("-").concat(String.valueOf(month)).concat("-")
							.concat(day);
					for (int k = 0; k < datesList.size(); i++) {

						if (datesList.get(k).equals(dateOfMonth)) {

						}

					}

					Date date2 = new SimpleDateFormat("yyyy-M-d").parse(dateOfMonth);
					String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date2);
					System.err.println("dayOfWeek " + dayOfWeek);
//if dayOfWeek is in holiday  or weeklyoff  then put dash  for shiftname   
					if (startDay.equals(dayOfWeek)) {
						// shift need to change
						System.err.println("changegable ");
						ShiftMaster shiftm = shiftMasterRepository.findByIdAndStatus(currShiftId, 1);
						if (shiftm.getChangeable() == 1) {

							currShiftId = shiftm.getChangewith();
							shiftm = shiftMasterRepository.findByIdAndStatus(currShiftId, 1);
							currShiftName = shiftm.getShiftname();
						}

					}
					empDet.setEmpCode(empCode);
					empDet.setEmpId(empId);
					empDet.setEmpName(empName);
					empDet.setShiftId(currShiftId);
					empDet.setShiftName(currShiftName);
					empDet.setDay(j);
					empDet.setDateOfMonth(dateOfMonth);
					empDet.setLocationId(locId);
					System.err.println("empDet in list" + empDet.toString());
					empShiftList.add(empDet);

				}

				String fromDate = year.concat("-").concat((month)).concat("-01");
				String toDate = year.concat("-").concat((month)).concat("-").concat(String.valueOf(days));

				// to get weekoff of that month
				List<WeeklyOff> weeklyOfflist = weeklyOffRepo1.getWeeklyOffList();
				// to get leaves of that month
				List<LeaveApply> leavetList = leaveApplyRepository.getleavetList(fromDate, toDate);
				// to get holidey of that month
				List<Holiday> holidayList = holidayRepo.getholidaybetweendate(fromDate, toDate);
				List<WeeklyOffShit> weeklyOffShitList = weeklyOffShitRepository.getWeeklyOffShitList(fromDate, toDate);
				for (int m = 0; m < empShiftList.size(); m++) {

					String type = new String();
					int empIdNew = empShiftList.get(m).getEmpId();
					String calcDate = empShiftList.get(m).getDateOfMonth();
					int location = empShiftList.get(m).getLocationId();
					List<LeaveApply> leavetListMain = new ArrayList<LeaveApply>();
					for (int p = 0; p <= leavetList.size(); p++) {
						if (leavetList.get(p).getEmpId() == empIdNew) {
							leavetListMain.add(leavetList.get(p));
						}

					}
					int weekEndStatus = commonFunctionService.findDateInWeekEnd(calcDate, calcDate, weeklyOfflist,
							weeklyOffShitList, empShiftList.get(m).getLocationId());

					int holidayStatus = commonFunctionService.findDateInHoliday(calcDate, calcDate, holidayList,
							location);

					LeaveStsAndLeaveId stsInfo = commonFunctionService.findDateInLeave(calcDate, leavetListMain,
							empIdNew);

					if (weekEndStatus == 1 || holidayStatus == 3 || stsInfo.getSts() == 5) {

						empShiftList.get(m).setShiftName("Holiday");
					}

				}

			}

		} catch (Exception e) {
			System.err.println("Excep in getDesignationById : " + e.getMessage());
			e.printStackTrace();
		}

		return empShiftList;

	}

}
