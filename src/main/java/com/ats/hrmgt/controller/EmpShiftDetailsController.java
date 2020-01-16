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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.DailyAttendance;
import com.ats.hrmgt.model.Designation;
import com.ats.hrmgt.model.EmpShiftDetails;
import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.Holiday;
import com.ats.hrmgt.model.LeaveApply;
import com.ats.hrmgt.model.LeaveStsAndLeaveId;
import com.ats.hrmgt.model.LeaveType;
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
	public List<EmpShiftDetails> getEmpShiftDetails(@RequestParam String nextMonthDay, @RequestParam String today,
			@RequestParam int companyId, @RequestParam String empRes, @RequestParam int daysNext,
			@RequestParam int daysToday) {
		List<EmpShiftDetails> empShiftList = new ArrayList<EmpShiftDetails>();
 		try {

			String[] a = today.split("-");
			String year = a[0];
			String month = a[1];
			if (month.length() == 1) {
				month = "0".concat(month);
			}
			
			String[] b = nextMonthDay.split("-");
			String year1 = b[0];
			String month1 = b[1];
			if (month1.length() == 1) {
				month1 = "0".concat(month1);
			}
			
 			
			String fromDate = String.valueOf(year).concat("-").concat(String.valueOf(month)).concat("-")
					.concat("01"); 

			/*
			 * String toDate =
			 * String.valueOf(year).concat("-").concat(String.valueOf(month)).concat("-")
			 * .concat(String.valueOf(daysToday));
			 * 
			 * String fromDate1 =
			 * String.valueOf(year1).concat("-").concat(String.valueOf(month1)).concat("-")
			 * .concat("01");
			 */

			String toDate1 = String.valueOf(year1).concat("-").concat(String.valueOf(month1)).concat("-")
					.concat(String.valueOf(daysNext)); 

			List<ShiftMaster> shiftmList = shiftMasterRepository.findByStatus(1);
			/*
			 * LocalDate localDate = LocalDate.parse((today)); LocalDate oneMonthBef =
			 * localDate.plusMonths(-1);
			 */
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
				//System.err.println("empId " + empId);
				// String empName = emplist.get(i).getSurname().concat("
				// ").concat(emplist.get(i).getFirstName());
				// String empCode = emplist.get(i).getEmpCode();
				int currShiftId = 0;
				String currShiftName = null;

				/*
				 * DailyAttendance dailyRec =
				 * dailyAttendanceRepository.findLastMonthRecordOfEmp(empId,
				 * oneMonthBef.getMonthValue(), oneMonthBef.getYear());
				 * 
				 * if (dailyRec != null) { currShiftId = dailyRec.getCurrentShiftid();
				 * currShiftName = dailyRec.getCurrentShiftname(); } else {
				 */
				DailyAttendance dailyRec = dailyAttendanceRepository.findLastMonthRecordOfEmp(empId);
				if (dailyRec != null) {
					currShiftId = dailyRec.getCurrentShiftid();
					currShiftName = dailyRec.getCurrentShiftname();
				} else {
					currShiftId = emplist.get(i).getCurrentShiftid();
					ShiftMaster shiftm = new ShiftMaster();
					for (int s = 0; s < shiftmList.size(); s++) {
						if (shiftmList.get(s).getId() == currShiftId) {
							shiftm = shiftmList.get(s);
							break;
						}
					}
					currShiftName = shiftm.getShiftname();

				}

				for (int j = 1; j <= daysToday; j++) {

				//	System.out.println("emp "+empId+" day " + j);
					EmpShiftDetails empDet = new EmpShiftDetails();
					String day = null;
					if (String.valueOf(j).length() == 1) {
						day = "0".concat(String.valueOf(j));
					} else {
						day = String.valueOf(j);
					}
					String dateOfMonth = String.valueOf(year).concat("-").concat(String.valueOf(month)).concat("-")
							.concat(day);

					Date date2 = new SimpleDateFormat("yyyy-M-d").parse(dateOfMonth);
					String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date2);
					//System.err.println("dayOfWeek " + dayOfWeek);
//if dayOfWeek is in holiday  or weeklyoff  then put dash  for shiftname   
					if (startDay.equals(dayOfWeek)) {
						// shift need to change
						//System.err.println("changegable ");

						ShiftMaster shiftm = new ShiftMaster();
						for (int s = 0; s < shiftmList.size(); s++) {
							if (shiftmList.get(s).getId() == currShiftId) {
								shiftm = shiftmList.get(s);
								break;
							}
						}
						if (shiftm.getChangeable() == 1) {

							currShiftId = shiftm.getChangewith();
							for (int s = 0; s < shiftmList.size(); s++) {
								if (shiftmList.get(s).getId() == currShiftId) {
									shiftm = shiftmList.get(s);
									break;
								}
							}
							currShiftName = shiftm.getShiftname();
						}

					}
				 
					empDet.setEmpId(empId);
					empDet.setShiftId(currShiftId);
					empDet.setShiftName(currShiftName);
					empDet.setDay(j);
					empDet.setDateOfMonth(dateOfMonth);
					empDet.setLocationId(locId);
					empDet.setMonth(Integer.parseInt(month));
					empDet.setYear(Integer.parseInt(year));
					
					//System.err.println("empDet in list" + empDet.toString());
 					empShiftList.add(empDet);

				}
				//System.err.println("1 st ends ");
				
				for (int j = 1; j <= daysNext; j++) {

					//System.out.println("emp "+empId+" day " + j);
					EmpShiftDetails empDet = new EmpShiftDetails();
					String day = null;
					if (String.valueOf(j).length() == 1) {
						day = "0".concat(String.valueOf(j));
					} else {
						day = String.valueOf(j);
					}
					String dateOfMonth = String.valueOf(year1).concat("-").concat(String.valueOf(month1)).concat("-")
							.concat(day);

					Date date2 = new SimpleDateFormat("yyyy-M-d").parse(dateOfMonth);
					String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date2);
				//	System.err.println("dayOfWeek " + dayOfWeek);
//if dayOfWeek is in holiday  or weeklyoff  then put dash  for shiftname   
					if (startDay.equals(dayOfWeek)) {
						// shift need to change
						//System.err.println("changegable ");

						ShiftMaster shiftm = new ShiftMaster();
						for (int s = 0; s < shiftmList.size(); s++) {
							if (shiftmList.get(s).getId() == currShiftId) {
								shiftm = shiftmList.get(s);
								break;
							}
						}
						if (shiftm.getChangeable() == 1) {

							currShiftId = shiftm.getChangewith();
							for (int s = 0; s < shiftmList.size(); s++) {
								if (shiftmList.get(s).getId() == currShiftId) {
									shiftm = shiftmList.get(s);
									break;
								}
							}
							currShiftName = shiftm.getShiftname();
						}

					}
					
				 
					empDet.setEmpId(empId);
					empDet.setShiftId(currShiftId);
					empDet.setShiftName(currShiftName);
					empDet.setDay(j);
					empDet.setDateOfMonth(dateOfMonth);
					empDet.setLocationId(locId);
					empDet.setMonth(Integer.parseInt(month1));
					empDet.setYear(Integer.parseInt(year1));
					 
					//System.err.println("empDet in list" + empDet.toString());

					empShiftList.add(empDet);

				}
				//System.err.println("2 nd  ends ");
			}
		
			
			// same ************************
		 
			// to get weekoff of that month
			List<WeeklyOff> weeklyOfflist = weeklyOffRepo1.getWeeklyOffList();
			// to get leaves of that month
			List<LeaveApply> leavetList = leaveApplyRepository.getleavetList(fromDate, toDate1);
			//System.err.println("leavetList" + leavetList.toString());
			// to get holidey of that month
			List<Holiday> holidayList = holidayRepo.getholidaybetweendate(fromDate, toDate1);
			List<WeeklyOffShit> weeklyOffShitList = weeklyOffShitRepository.getWeeklyOffShitList(fromDate, toDate1);
			for (int m = 0; m < empShiftList.size(); m++) {

 				int empIdNew = empShiftList.get(m).getEmpId();
				String calcDate = empShiftList.get(m).getDateOfMonth();
				int location = empShiftList.get(m).getLocationId();
				List<LeaveApply> leavetListMain = new ArrayList<LeaveApply>();

				if (leavetList != null) {
					for (int p = 0; p < leavetList.size(); p++) {
						if (leavetList.get(p).getEmpId() == empIdNew) {
							leavetListMain.add(leavetList.get(p));
						}

					}
				}

				int weekEndStatus = commonFunctionService.findDateInWeekEnd(calcDate, calcDate, weeklyOfflist,
						weeklyOffShitList, empShiftList.get(m).getLocationId());

				int holidayStatus = commonFunctionService.findDateInHoliday(calcDate, calcDate, holidayList, location);

				LeaveStsAndLeaveId stsInfo = commonFunctionService.findDateInLeave(calcDate, leavetListMain, empIdNew);

				if (holidayStatus == 3) {

				//	System.err.println("holday"+calcDate);
					empShiftList.get(m).setShiftName("Holiday");
				}else if(weekEndStatus == 1) {
					//System.err.println("holday"+calcDate);
					empShiftList.get(m).setShiftName("Weeklyoff");
				}else if(stsInfo.getSts() == 5) {
					//System.err.println("Leave"+calcDate);
					empShiftList.get(m).setShiftName("Leave");
				}

			}
			//System.err.println("daysList"+empShiftList.toString());
			
			
		} catch (Exception e) {
			System.err.println("Excep in getDesignationById : " + e.getMessage());
			e.printStackTrace();
		}

		return empShiftList;

	}

	@RequestMapping(value = { "/getAllEmp" }, method = RequestMethod.POST)
	public @ResponseBody List<EmployeeMaster> getLeaveTypeById(@RequestParam("empRes") String empRes,
			@RequestParam("companyId") int companyId) {

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
		return emplist;

	}

}
