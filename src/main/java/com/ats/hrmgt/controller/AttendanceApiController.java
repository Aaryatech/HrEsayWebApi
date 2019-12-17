package com.ats.hrmgt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.DailyAttendance;
import com.ats.hrmgt.model.DataForUpdateAttendance;
import com.ats.hrmgt.model.EmpInfo;
import com.ats.hrmgt.model.EmpJsonData;
import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.FileUploadedData;
import com.ats.hrmgt.model.GetWeeklyOff;
import com.ats.hrmgt.model.Holiday;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.InfoForUploadAttendance;
import com.ats.hrmgt.model.LoginResponse;
import com.ats.hrmgt.model.LvType;
import com.ats.hrmgt.model.LvmSumUp;
import com.ats.hrmgt.model.MstEmpType;
import com.ats.hrmgt.model.ShiftMaster;
import com.ats.hrmgt.model.SummaryDailyAttendance;
import com.ats.hrmgt.model.VariousList;
import com.ats.hrmgt.model.WeeklyOff;
import com.ats.hrmgt.model.WeeklyOffShit;
import com.ats.hrmgt.repository.AccessRightModuleRepository;
import com.ats.hrmgt.repository.DailyAttendanceRepository;
import com.ats.hrmgt.repository.EmpInfoRepository;
import com.ats.hrmgt.repository.EmployeeMasterRepository;
import com.ats.hrmgt.repository.GetWeeklyOffRepo;
import com.ats.hrmgt.repository.HolidayRepo;
import com.ats.hrmgt.repository.InfoForUploadAttendanceRepository;
import com.ats.hrmgt.repository.LvTypeRepository;
import com.ats.hrmgt.repository.LvmSumUpRepository;
import com.ats.hrmgt.repository.MstEmpTypeRepository;
import com.ats.hrmgt.repository.ShiftMasterRepository;
import com.ats.hrmgt.repository.SummaryDailyAttendanceRepository;
import com.ats.hrmgt.repository.WeeklyOffRepo;
import com.ats.hrmgt.repository.WeeklyOffShitRepository;
import com.ats.hrmgt.service.CommonFunctionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

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

	@Autowired
	InfoForUploadAttendanceRepository infoForUploadAttendanceRepository;

	@Autowired
	EmpInfoRepository empInfoRepository;

	@Autowired
	HolidayRepo holidayRepo;

	@Autowired
	ShiftMasterRepository shiftMasterRepository;

	@Autowired
	WeeklyOffShitRepository weeklyOffShitRepository;

	@Autowired
	LvTypeRepository lvTypeRepository;

	@Autowired
	LvmSumUpRepository lvmSumUpRepository;

	@Autowired
	MstEmpTypeRepository mstEmpTypeRepository;

	@Autowired
	CommonFunctionService commonFunctionService;

	@Autowired
	WeeklyOffRepo weeklyOffRepo;

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

			List<EmpInfo> empList = empInfoRepository.getEmpList();

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
				summaryDailyAttendance.setLoginName(String.valueOf(userId));
				summaryDailyAttendance.setRecStatus("O");
				summaryDailyAttendanceList.add(summaryDailyAttendance);

				EmpJsonData empJsonData = new EmpJsonData();
				empJsonData.setCmpCode(empList.get(i).getCmpCode());
				empJsonData.setCmpJoiningDate(empList.get(i).getCmpJoiningDate());
				empJsonData.setCurrentShiftid(empList.get(i).getCurrentShiftid());
				empJsonData.setDepartId(empList.get(i).getDepartId());
				empJsonData.setDesignationId(empList.get(i).getDepartId());
				empJsonData.setEmpCategory(empList.get(i).getEmpCategory());
				empJsonData.setEmpCode(empList.get(i).getEmpCode());
				empJsonData.setEmpId(empList.get(i).getEmpId());
				empJsonData.setEmpType(empList.get(i).getEmpType());
				empJsonData.setFirstName(empList.get(i).getFirstName());
				empJsonData.setIsEmp(empList.get(i).getIsEmp());
				empJsonData.setLocationId(empList.get(i).getLocationId());
				empJsonData.setMiddleName(empList.get(i).getMiddleName());
				empJsonData.setSalaryTypeId(empList.get(i).getSalaryTypeId());
				empJsonData.setSalBasis(empList.get(i).getSalBasis());
				empJsonData.setSurname(empList.get(i).getSurname());

				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String json = ow.writeValueAsString(empJsonData);

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
					dailyAttendance.setLoginName(String.valueOf(userId));
					dailyAttendance.setEmpJson(json);
					dailyAttendance.setRecStatus("O");
					dailyAttendanceList.add(dailyAttendance);
					j.setTime(j.getTime() + 1000 * 60 * 60 * 24);

				}
				List<DailyAttendance> dailyAttendanceSaveRes = dailyAttendanceRepository.saveAll(dailyAttendanceList);

			}

			List<SummaryDailyAttendance> summaryDailyAttendanceSaveRes = summaryDailyAttendanceRepository
					.saveAll(summaryDailyAttendanceList);
			info.setError(false);
			info.setMsg("success");
		} catch (Exception e) {
			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/getInformationOfUploadedAttendance" }, method = RequestMethod.POST)
	public @ResponseBody InfoForUploadAttendance getInformationOfUploadedAttendance(
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

		InfoForUploadAttendance infoForUploadAttendance = new InfoForUploadAttendance();
		try {

			infoForUploadAttendance = infoForUploadAttendanceRepository.getInformationOfUploadedAttendance(fromDate,
					toDate);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return infoForUploadAttendance;

	}

	@RequestMapping(value = { "/importAttendanceByFileAndUpdate" }, method = RequestMethod.POST)
	public @ResponseBody Info getVariousListForUploadAttendace(
			@RequestBody DataForUpdateAttendance dataForUpdateAttendance) {

		Info info = new Info();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dd = new SimpleDateFormat("dd-MM-yyyy");

		SimpleDateFormat yyDtTm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {

			String fromDate = dataForUpdateAttendance.getFromDate();
			String toDate = dataForUpdateAttendance.getToDate();
			int month = dataForUpdateAttendance.getMonth();
			int year = dataForUpdateAttendance.getYear();

			List<FileUploadedData> fileUploadedDataList = dataForUpdateAttendance.getFileUploadedDataList();
			List<MstEmpType> mstEmpTypeList = mstEmpTypeRepository.findAll();
			List<ShiftMaster> shiftList = shiftMasterRepository.findAll();
			List<Holiday> holidayList = holidayRepo.getholidaybetweendate(fromDate, toDate);
			List<WeeklyOff> weeklyOfflist = weeklyOffRepo.getWeeklyOffList();
			List<WeeklyOffShit> weeklyOffShitList = weeklyOffShitRepository.getWeeklyOffShitList(fromDate, toDate);
			//

			// List<MstWeeklyOff> mstWeeklyOffList = mstWeeklyOffRepository.findAll();
			/*
			 * List<LvType> lvTypeList = lvTypeRepository.findAll(); List<Holiday>
			 * 
			 * List<LvmSumUp> lvmSumUpList = lvmSumUpRepository.findAll();
			 * List<SummaryDailyAttendance> summaryDailyAttendanceList =
			 * summaryDailyAttendanceRepository .summaryDailyAttendanceList(month, year);
			 */

			List<DailyAttendance> dailyAttendanceList = dailyAttendanceRepository.dailyAttendanceList(fromDate, toDate);
			MstEmpType mstEmpType = new MstEmpType();
			ShiftMaster shiftMaster = new ShiftMaster();
			List<ShiftMaster> possibleShiftList = new ArrayList<>();
			String inDttime = new String();
			String outDttime = new String();
			String shiftTime = new String();

			for (int i = 0; i < dailyAttendanceList.size(); i++) {

				possibleShiftList = new ArrayList<>();

				Date defaultDate = sf.parse(dailyAttendanceList.get(i).getAttDate());
				ObjectMapper mapper = new ObjectMapper();
				EmpJsonData employee = mapper.readValue(dailyAttendanceList.get(i).getEmpJson(), EmpJsonData.class);
				dailyAttendanceList.get(i).setEmpType(employee.getEmpType());

				// get emptype record and break;

				for (int j = 0; j < mstEmpTypeList.size(); j++) {

					if (mstEmpTypeList.get(j).getEmpTypeId() == employee.getEmpType()) {
						mstEmpType = mstEmpTypeList.get(j);
						break;
					}

				}

				// get timeShifting record by shiftid
				for (int j = 0; j < shiftList.size(); j++) {

					if (shiftList.get(j).getId() == employee.getCurrentShiftid()) {
						shiftMaster = shiftList.get(j);
						dailyAttendanceList.get(i).setCurrentShiftid(shiftList.get(j).getId());
						break;
					}

				}

				// get possible timeShifting records List by same deptId of employee
				for (int j = 0; j < shiftList.size(); j++) {

					if (shiftList.get(j).getSelfGroupId() == shiftMaster.getSelfGroupId()) {
						possibleShiftList.add(shiftList.get(j));
					}

				}

				// assign in time and out time from uploaded csv to record
				for (int j = 0; j < fileUploadedDataList.size(); j++) {

					Date uploadedDate = dd.parse(fileUploadedDataList.get(j).getLogDate());

					if (dailyAttendanceList.get(i).getEmpCode().equals(fileUploadedDataList.get(j).getEmpCode())
							&& defaultDate.compareTo(uploadedDate) == 0) {

						dailyAttendanceList.get(i).setInTime(fileUploadedDataList.get(j).getInTime());
						dailyAttendanceList.get(i).setOutTime(fileUploadedDataList.get(j).getOutTime());
						break;

					}

				}

				// create default date and time
				inDttime = dailyAttendanceList.get(i).getAttDate() + " " + dailyAttendanceList.get(i).getInTime()
						+ ":00";
				outDttime = dailyAttendanceList.get(i).getAttDate() + " " + dailyAttendanceList.get(i).getOutTime()
						+ ":00";

				long minimumMin = 0;

				// find current shift by thumb
				for (int j = 0; j < possibleShiftList.size(); j++) {

					try {

						long x = 0;
						shiftTime = dailyAttendanceList.get(i).getAttDate() + " "
								+ possibleShiftList.get(j).getFromtime();

						Date startDate = yyDtTm.parse(inDttime);
						Date endDate = yyDtTm.parse(shiftTime);// Set end date

						if (startDate.compareTo(endDate) > 0) {
							long duration = startDate.getTime() - endDate.getTime();
							long diffHours = duration / (60 * 60 * 1000);
							long diffMinutes = (duration / (60 * 1000) % 60) + (diffHours * 60);
							x = diffMinutes;

						} else {
							long duration = endDate.getTime() - startDate.getTime();
							long diffHours = duration / (60 * 60 * 1000);
							long diffMinutes = (duration / (60 * 1000) % 60) + (diffHours * 60);

							x = diffMinutes;

						}

						if (j == 0) {
							minimumMin = x;
							shiftMaster = possibleShiftList.get(j);
							dailyAttendanceList.get(i).setCurrentShiftid(possibleShiftList.get(j).getId());
							dailyAttendanceList.get(i).setCurrentShiftname(possibleShiftList.get(j).getShiftname());
						}

						if (minimumMin > x) {
							minimumMin = x;
							shiftMaster = possibleShiftList.get(j);
							dailyAttendanceList.get(i).setCurrentShiftid(possibleShiftList.get(j).getId());
							dailyAttendanceList.get(i).setCurrentShiftname(possibleShiftList.get(j).getShiftname());
						}

					} catch (Exception e) {
						// e.printStackTrace();
					}

				}

				// calculate working hours
				try {
					Date inDt = yyDtTm.parse(inDttime);// Set start date
					Date outDt = yyDtTm.parse(outDttime);

					long durationBetweenInOut = outDt.getTime() - inDt.getTime();
					long diffHoursBetweenInOut = durationBetweenInOut / (60 * 60 * 1000);
					long diffMinutesBetweenInOut = (durationBetweenInOut / (60 * 1000) % 60)
							+ (diffHoursBetweenInOut * 60);

					dailyAttendanceList.get(i).setWorkingHrs(diffMinutesBetweenInOut);

					if (diffMinutesBetweenInOut > shiftMaster.getShiftOtHour()) {
						dailyAttendanceList.get(i)
								.setOtHr(String.valueOf(diffMinutesBetweenInOut - shiftMaster.getShiftOtHour()));
					} else {
						dailyAttendanceList.get(i).setOtHr("0");
					}

				} catch (Exception e) {
					// e.printStackTrace();
				}

				// calculate late time
				try {

					int allowdLateTime = 0;
					shiftTime = dailyAttendanceList.get(i).getAttDate() + " " + shiftMaster.getFromtime();
					if (mstEmpType.getMaxLateTimeAllowed() > shiftMaster.getMaxLateTimeAllowed()) {
						allowdLateTime = mstEmpType.getMaxLateTimeAllowed();
					} else {
						allowdLateTime = shiftMaster.getMaxLateTimeAllowed();
					}

					Date inDateTime = yyDtTm.parse(inDttime);
					Date shiftDatetime = yyDtTm.parse(shiftTime);// Set end date

					if (inDateTime.compareTo(shiftDatetime) > 0) {
						long durationBetweenInOut = inDateTime.getTime() - shiftDatetime.getTime();
						long diffHoursBetweenInOut = durationBetweenInOut / (60 * 60 * 1000);
						long diffMinutesBetweenInOut = (durationBetweenInOut / (60 * 1000) % 60)
								+ (diffHoursBetweenInOut * 60);
						dailyAttendanceList.get(i).setLateMin((int) diffMinutesBetweenInOut);

						if (diffMinutesBetweenInOut > allowdLateTime) {
							dailyAttendanceList.get(i).setLateMark("1");
						} else {
							dailyAttendanceList.get(i).setLateMark("0");
						}
					} else {
						dailyAttendanceList.get(i).setLateMin(0);
						dailyAttendanceList.get(i).setLateMark("0");
					}

				} catch (Exception e) {
					// e.printStackTrace();
				}
				try {

					// weekEnnd : 		1=Weekly off,2: no weekly off
					// holiday :  		3=holiday	,4: holiday off
					// leave : 			5=leave		,6: no leave
					// presentStatus : 7=present ,8: absent

					int weekEndStatus = commonFunctionService.findDateInWeekEnd(sf.format(defaultDate),
							sf.format(defaultDate), weeklyOfflist, weeklyOffShitList,
							dailyAttendanceList.get(i).getLocationId());

					int holidayStatus = commonFunctionService.findDateInHoliday(sf.format(defaultDate),
							sf.format(defaultDate), holidayList, dailyAttendanceList.get(i).getLocationId());

					int presentStatus = 7;

					if (dailyAttendanceList.get(i).getInTime().equals("0:00")
							|| dailyAttendanceList.get(i).getOutTime().equals("0:00")) {
						presentStatus = 8;
					}

					/*
					 * int findShiftedWeekEnd = commonFunctionService.findShiftedWeekEnd(
					 * dailyAttendanceList.get(i).getEmpId(), sf.format(defaultDate),
					 * weeklyOffShitList, dailyAttendanceList.get(i).getLocationId());
					 */

					if (weekEndStatus == 1) {
						dailyAttendanceList.get(i).setAttStatus("Weekly Off");
					} else {
						dailyAttendanceList.get(i).setAttStatus("Present");
					}

					// System.out.println("defaultDate" + sf.format(defaultDate) + "sts " + sts);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println(dailyAttendanceList);

			info.setError(false);
			info.setMsg("success");

		} catch (Exception e) {

			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}
		return info;
	}

}
