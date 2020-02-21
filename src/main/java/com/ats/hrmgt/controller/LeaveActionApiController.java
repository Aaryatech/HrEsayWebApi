package com.ats.hrmgt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.common.DateConvertor;
import com.ats.hrmgt.common.Firebase;
import com.ats.hrmgt.model.AuthorityInformation;
import com.ats.hrmgt.model.CalenderYear;
import com.ats.hrmgt.model.EmpLeaveHistoryRep;
import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.GetAuthorityIds;
import com.ats.hrmgt.model.GetLeaveApplyAuthwise;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.LeaveApply;
import com.ats.hrmgt.model.LeaveHistory;
import com.ats.hrmgt.model.LeaveTrail;
import com.ats.hrmgt.model.LeaveTypeWithLimit;
import com.ats.hrmgt.model.PayableDayAndPresentDays;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.repository.AuthorityInformationRepository;
import com.ats.hrmgt.repository.CalculateYearRepository;
import com.ats.hrmgt.repository.EmpLeaveHistoryRepRepo;
import com.ats.hrmgt.repository.EmployeeMasterRepository;
import com.ats.hrmgt.repository.GetAuthorityIdsRepo;
import com.ats.hrmgt.repository.GetLeaveApplyAuthwiseRepo;
import com.ats.hrmgt.repository.LeaveApplyRepository;
import com.ats.hrmgt.repository.LeaveHistoryRepo;
import com.ats.hrmgt.repository.LeaveTrailRepository;
import com.ats.hrmgt.repository.LeaveTypeWithLimitRepository;
import com.ats.hrmgt.repository.PayableDayAndPresentDaysRepo;
import com.ats.hrmgt.repository.SettingRepo;

@RestController
public class LeaveActionApiController {

	@Autowired
	LeaveApplyRepository leaveApplyRepository;

	@Autowired
	LeaveTrailRepository leaveTrailRepository;

	@Autowired
	LeaveHistoryRepo leaveHistoryRepo;

	@Autowired
	AuthorityInformationRepository authorityInformationRepository;

	@Autowired
	GetAuthorityIdsRepo getAuthorityIdsRepo;

	@Autowired
	GetLeaveApplyAuthwiseRepo getLeaveApplyAuthwiseRepo;

	@Autowired
	CalculateYearRepository calculateYearRepository;

	@Autowired
	EmployeeMasterRepository employeeMasterRepository;

	@Autowired
	SettingRepo settingRepo;

	@Autowired
	PayableDayAndPresentDaysRepo payableDayAndPresentDaysRepo;

	@Autowired
	LeaveTypeWithLimitRepository leaveTypeWithLimitRepository;

	@RequestMapping(value = { "/updateLeaveStatus" }, method = RequestMethod.POST)
	public @ResponseBody Info updateLeaveStatus(@RequestParam("leaveId") int leaveId,
			@RequestParam("status") int status) {

		Info info = new Info();
		// System.err.println("in updateLeaveStatus" + status + leaveId);
		try {

			int update = leaveApplyRepository.updateLeaveStatus(leaveId, status);

			if (update > 0) {
				info.setError(false);
				info.setMsg("updated");
			} else {
				info.setError(true);
				info.setMsg("failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("failed");
		}

		return info;

	}

	@RequestMapping(value = { "/saveLeaveTrail" }, method = RequestMethod.POST)
	public @ResponseBody LeaveTrail saveLeaveTrail(@RequestBody LeaveTrail leaveTrail) {

		LeaveTrail save = new LeaveTrail();
		try {

			save = leaveTrailRepository.saveAndFlush(leaveTrail);

			if (save == null) {

				save = new LeaveTrail();
				save.setError(true);

			} else {
				save.setError(false);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/updateTrailId" }, method = RequestMethod.POST)
	public @ResponseBody Info updateTrailId(@RequestParam("leaveId") int leaveId,
			@RequestParam("trailId") int trailId) {

		Info info = new Info();

		try {

			int delete = leaveApplyRepository.updateLeaveApply(leaveId, trailId);

			if (delete > 0) {
				info.setError(false);
				info.setMsg("updated status");
			} else {
				info.setError(true);
				info.setMsg("failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("failed");
		}

		return info;

	}

	@RequestMapping(value = { "/getLeaveHistoryList" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveHistory> getLeaveHistoryList(@RequestParam("empId") int empId,
			@RequestParam("currYrId") int currYrId) {

		List<LeaveHistory> list = new ArrayList<LeaveHistory>();
		try {

			list = leaveHistoryRepo.getLeaveHistoryByEmpId(empId, currYrId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getPayableDayandPresentDayByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody PayableDayAndPresentDays getPayableDayandPresentDayByEmpId(@RequestParam("empId") int empId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

		PayableDayAndPresentDays payableDayAndPresentDays = new PayableDayAndPresentDays();
		try {

			payableDayAndPresentDays = payableDayAndPresentDaysRepo.getPayableDayandPresentDayByEmpId(empId,
					DateConvertor.convertToYMD(fromDate), DateConvertor.convertToYMD(toDate));

			if (payableDayAndPresentDays == null) {
				payableDayAndPresentDays = new PayableDayAndPresentDays();
				payableDayAndPresentDays.setError(true);
			} else {
				payableDayAndPresentDays.setError(false);
			}

		} catch (Exception e) {
			payableDayAndPresentDays = new PayableDayAndPresentDays();
			payableDayAndPresentDays.setError(true);
			e.printStackTrace();
		}

		return payableDayAndPresentDays;

	}

	@RequestMapping(value = { "/getAuthorityInfoByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody AuthorityInformation getAuthorityInfoByEmpId(@RequestParam("empId") int empId) {

		AuthorityInformation authorityInformation = new AuthorityInformation();
		try {

			authorityInformation = authorityInformationRepository.getAuthorityInfoByEmpId(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return authorityInformation;

	}

	@RequestMapping(value = { "getAuthIdByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody GetAuthorityIds getAuthIdByEmpId(@RequestParam("empId") int empId) {

		System.out.println("emp id is " + empId);
		GetAuthorityIds leaveApply = new GetAuthorityIds();
		try {

			leaveApply = getAuthorityIdsRepo.getAuthIds(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return leaveApply;

	}

	@RequestMapping(value = { "/saveLeaveApply" }, method = RequestMethod.POST)
	public @ResponseBody LeaveApply saveLeaveApply(@RequestBody LeaveApply leave) {

		LeaveApply save = new LeaveApply();
		try {

			save = leaveApplyRepository.saveAndFlush(leave);
			if (save == null) {

				save = new LeaveApply();
				save.setError(true);

			} else {
				save.setError(false);
			}

		} catch (Exception e) {
			save = new LeaveApply();
			save.setError(true);
			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getLeaveApplyListForPending" }, method = RequestMethod.POST)
	public @ResponseBody List<GetLeaveApplyAuthwise> getLeaveApplyAuthwiseList(@RequestParam("empId") int empId,
			@RequestParam("currYrId") int currYrId) {
		List<GetLeaveApplyAuthwise> list = new ArrayList<GetLeaveApplyAuthwise>();

		try {

			list = getLeaveApplyAuthwiseRepo.getLeaveApplyList(empId, currYrId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getLeaveApplyListForInformation" }, method = RequestMethod.POST)
	public @ResponseBody List<GetLeaveApplyAuthwise> getLeaveApplyListForInformation(@RequestParam("empId") int empId,
			@RequestParam("currYrId") int currYrId) {
		List<GetLeaveApplyAuthwise> list = new ArrayList<GetLeaveApplyAuthwise>();

		try {

			list = getLeaveApplyAuthwiseRepo.getLeaveApplyList2(empId, currYrId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getCalculateYearList" }, method = RequestMethod.GET)
	public @ResponseBody List<CalenderYear> getCalculateYearList() {

		List<CalenderYear> list = new ArrayList<CalenderYear>();
		try {

			list = calculateYearRepository.findAll();
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setCalYrFromDate(DateConvertor.convertToDMY(list.get(i).getCalYrFromDate()));
				list.get(i).setCalYrToDate(DateConvertor.convertToDMY(list.get(i).getCalYrToDate()));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	EmpLeaveHistoryRepRepo empLeaveHistoryRepRepo;

	@RequestMapping(value = { "/getLeaveHistoryRep" }, method = RequestMethod.POST)
	public @ResponseBody List<EmpLeaveHistoryRep> getLeaveHistoryRep(@RequestParam("empId") int empId,
			@RequestParam("calYrId") int calYrId) {
		List<EmpLeaveHistoryRep> list = new ArrayList<EmpLeaveHistoryRep>();

		try {
			if (empId == -1) {
				list = empLeaveHistoryRepRepo.getEmpLeaveHistoryRepAll(calYrId);
			} else {
				list = empLeaveHistoryRepRepo.getEmpLeaveHistoryRep(empId, calYrId);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "getemplistwhichisnotyearend" }, method = RequestMethod.GET)
	public @ResponseBody List<EmployeeMaster> getemplistwhichisnotyearend() {

		List<EmployeeMaster> employeeInfo = new ArrayList<EmployeeMaster>();

		try {

			employeeInfo = employeeMasterRepository.getemplistwhichisnotyearend();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return employeeInfo;

	}

	@RequestMapping(value = { "/getPreviousleaveHistory" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveHistory> getPreviousleaveHistory(@RequestParam("empId") int empId) {

		List<LeaveHistory> list = new ArrayList<LeaveHistory>();
		try {

			list = leaveHistoryRepo.getPreviousleaveHistory(empId);

			// System.err.println("LeaveHistory" + list.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/checkDateForRepetedLeaveValidation" }, method = RequestMethod.POST)
	public @ResponseBody Info checkDateForRepetedLeaveValidation(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("empId") int empId,
			@RequestParam("leaveTypeId") int leaveTypeId, @RequestParam("shortName") String shortName,
			@RequestParam("noOfDays") float noOfDays) {

		Info info = new Info();

		try {

			Setting setting = settingRepo.findByKey("CONTILEAVE");

			List<LeaveApply> list = leaveApplyRepository.checkDateForRepetedLeaveValidation(fromDate, toDate, empId);

			if (list.size() > 0) {

				info.setError(true);
				info.setMsg("You Have Already Apply Leave on this Date.");

			} else {

				if (Integer.parseInt(setting.getValue()) == 1) {

					if (shortName.equalsIgnoreCase("LWP") || shortName.equalsIgnoreCase("SL")) {

						info = LeaveTypeValidation(empId, leaveTypeId, shortName, noOfDays);
					} else {
						
						list = leaveApplyRepository.checkContinueDateLeave(fromDate, toDate, empId, leaveTypeId);
						if (list.size() > 0) {

							info.setError(true);
							info.setMsg("You Cannot Apply Continue Leave As Diffrent Type. ");

						} else {
							/*
							 * info.setError(false); info.setMsg("you can apply");
							 */

							info = LeaveTypeValidation(empId, leaveTypeId, shortName, noOfDays);
						}
					}

				} else {
					/*
					 * info.setError(false); info.setMsg("you can apply");
					 */
					info = LeaveTypeValidation(empId, leaveTypeId, shortName, noOfDays);
				}

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return info;

	}

	public Info LeaveTypeValidation(int empId, int leaveTypeId, String shortName, float noOfDays) {
		Info info = new Info();

		try {
			Setting TYPEVALIDATION = settingRepo.findByKey("TYPEVALIDATION");

			if (Integer.parseInt(TYPEVALIDATION.getValue()) == 1) {
				CalenderYear calendearYear = calculateYearRepository.findByIsCurrent(1);
				LeaveTypeWithLimit leaveTypeWithLimit = leaveTypeWithLimitRepository.LeaveTypeWithLimit(empId,
						leaveTypeId, calendearYear.getCalYrId());

				// System.out.println(leaveTypeWithLimit.getMaxNoDays() + " " + noOfDays);
				if (leaveTypeWithLimit.getMaxNoDays() != 0 && leaveTypeWithLimit.getMaxNoDays() < noOfDays) {

					info.setError(true);
					info.setMsg("Your Leave Limit is " + leaveTypeWithLimit.getMaxNoDays());

				} else {
					info.setError(false);
					info.setMsg("you can apply");
				}

			} else {
				info.setError(false);
				info.setMsg("you can apply");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}
}
