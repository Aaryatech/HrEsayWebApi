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

import com.ats.hrmgt.model.Designation;
import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.model.ShiftMaster;
import com.ats.hrmgt.model.WeeklyOff;
import com.ats.hrmgt.repository.EmployeeMasterRepository;
import com.ats.hrmgt.repository.SettingRepo;
import com.ats.hrmgt.repository.ShiftMasterRepository;
import com.ats.hrmgt.repository.WeeklyOffRepo;
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

	@RequestMapping(value = { "/getEmpShiftDetails" }, method = RequestMethod.POST)
	public int getEmpShiftDetails(@RequestParam String date1, @RequestParam int companyId) {
		List<String> datesList = new ArrayList<>();
		try {

			String[] a = "2020-02-14".split("-");
			String year = a[0];
			String month = a[1];

			// to get length of month
			LocalDate date = LocalDate.of(2010, 1, 19);
			int days = date.lengthOfMonth();

			// to get all employees
			List<EmployeeMaster> emplist = new ArrayList<EmployeeMaster>();
			try {
				emplist = empRepo.findByDelStatusAndCmpCodeOrderByEmpIdDesc(1, companyId);
			} catch (Exception e) {
				System.err.println("Excep in getAllEmployee : " + e.getMessage());
				e.printStackTrace();
			}

			// to get weekoff of that month

			/*
			 * String fromDate =
			 * String.valueOf(year).concat("-").concat(String.valueOf(monthN)).concat("-").
			 * concat("01"); String toDate =
			 * String.valueOf(year).concat("-").concat(String.valueOf(monthN)).concat("-")
			 * .concat(String.valueOf(days)); List<WeeklyOff> weeklyOfflist =
			 * weeklyOffRepo.getWeeklyOffList(); datesList =
			 * commonFunctionService.getDatesOfWeeklyOfForShiftingDate(fromDate, toDate,
			 * weeklyOfflist, locId);
			 */

			Setting settingList = new Setting();
			settingList = settingRepo.findByKey("week_start_day");
			String startDay = settingList.getValue();

			for (int i = 0; i < emplist.size(); i++) {
				int empId = emplist.get(i).getEmpId();

				for (int j = 0; j < days; j++) {

					int currShiftId = 1;
					String dateOfMonth = String.valueOf(year).concat("-").concat(String.valueOf(month)).concat("-")
							.concat("01");
					Date date2 = new SimpleDateFormat("yyyy-M-d").parse(dateOfMonth);
					String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date2);
//if dayOfWeek is in holiday  or weeklyoff  then put dash  for shiftname   
					if (startDay.equals(dayOfWeek)) {
						// shift need to change

						ShiftMaster shiftm = shiftMasterRepository.findByIdAndStatus(currShiftId, 1);
						currShiftId = shiftm.getChangewith();

					} else {
						// continue prev shift

					}

				}

			}

		} catch (Exception e) {
			System.err.println("Excep in getDesignationById : " + e.getMessage());
			e.printStackTrace();
		}

		return 0;

	}

}
