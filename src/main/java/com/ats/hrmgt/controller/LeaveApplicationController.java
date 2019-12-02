package com.ats.hrmgt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
import com.ats.hrmgt.common.EmailUtility;
import com.ats.hrmgt.common.Firebase;
import com.ats.hrmgt.leave.model.EmployeeLeaveDetail;
import com.ats.hrmgt.leave.model.GetAuthorityIds;
import com.ats.hrmgt.leave.model.GetLeaveApplyAuthwise;
import com.ats.hrmgt.leave.model.LeaveDetail;
import com.ats.hrmgt.leave.model.LeaveHistory;
import com.ats.hrmgt.leave.repo.EmpLeaveHistoryRepRepo;
import com.ats.hrmgt.leave.repo.GetAuthorityIdsRepo;
import com.ats.hrmgt.leave.repo.GetEmployeeAuthorityWiseRepo;
import com.ats.hrmgt.leave.repo.GetLeaveApplyAuthwiseRepo;
import com.ats.hrmgt.leave.repo.LeaveDetailRepo;
import com.ats.hrmgt.leave.repo.LeaveHistoryRepo;
import com.ats.hrmgt.model.ClaimApply;
import com.ats.hrmgt.model.EmpLeaveHistoryRep;
import com.ats.hrmgt.model.EmployeeInfo;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.LeaveApply;
import com.ats.hrmgt.model.LeaveTrail;
import com.ats.hrmgt.model.LeaveType;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.repository.EmployeeInfoRepository;
import com.ats.hrmgt.repository.EmployeeLeaveDetailRepo;
import com.ats.hrmgt.repository.LeaveApplyRepository;
import com.ats.hrmgt.repository.LeaveTrailRepository;
import com.ats.hrmgt.repository.SettingRepo;

@RestController
public class LeaveApplicationController {

	@Autowired
	LeaveHistoryRepo leaveHistoryRepo;
	@Autowired
	LeaveTrailRepository leaveTrailRepository;

	@Autowired
	LeaveApplyRepository leaveApplyRepository;
	@Autowired
	GetAuthorityIdsRepo getAuthorityIdsRepo;
	@Autowired
	GetEmployeeAuthorityWiseRepo getEmployeeAuthorityWise;

	@Autowired
	GetLeaveApplyAuthwiseRepo getLeaveApplyAuthwiseRepo;

	@Autowired
	SettingRepo settingRepo;

	static String senderEmail = "atsinfosoft@gmail.com";
	static String senderPassword = "atsinfosoft@123";
	static String mailsubject = " HRMS Password Recovery";

	@RequestMapping(value = { "/getLeaveHistoryList" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveHistory> getLeaveHistoryList(@RequestParam("empId") int empId,
			@RequestParam("currYrId") int currYrId) {

		List<LeaveHistory> list = new ArrayList<LeaveHistory>();
		try {

			list = leaveHistoryRepo.getLeaveHistoryByEmpId(empId, currYrId);

			System.err.println("LeaveHistory" + list.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

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

	@RequestMapping(value = { "/getLeaveHistoryByLeaveTypeId" }, method = RequestMethod.POST)
	public @ResponseBody LeaveHistory getLeaveHistoryByLeaveTyprId(@RequestParam("leaveTypeId") int leaveTypeId,
			@RequestParam("lvsId") int lvsId) {

		LeaveHistory list = new LeaveHistory();
		try {

			list = leaveHistoryRepo.getLeaveEarnedByLeaveTypeId(leaveTypeId, lvsId);

			System.err.println("LeaveHistory" + list.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

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

	}///

	/*
	 * @RequestMapping(value = { "/getLeaveTrailList" }, method = RequestMethod.GET)
	 * public @ResponseBody List<LeaveTrail> getLeaveTrailList() {
	 * 
	 * List<LeaveTrail> list = new ArrayList<LeaveTrail>(); try {
	 * 
	 * list = leaveTrailRepository.findByDelStatus(1);
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return list;
	 * 
	 * }
	 */
	/*
	 * @RequestMapping(value = { "/deleteLeaveTrail" }, method = RequestMethod.POST)
	 * public @ResponseBody Info deleteLeaveTrail(@RequestParam("trailPkey") int
	 * trailPkey) {
	 * 
	 * Info info = new Info();
	 * 
	 * try {
	 * 
	 * int delete = leaveTrailRepository.deleteLeaveTrail(trailPkey);
	 * 
	 * if (delete > 0) { info.setError(false); info.setMsg("deleted"); } else {
	 * info.setError(true); info.setMsg("failed"); }
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); info.setError(true); info.setMsg("failed"); }
	 * 
	 * return info;
	 * 
	 * }
	 * 
	 * @RequestMapping(value = { "/getLeaveTrailById" }, method =
	 * RequestMethod.POST) public @ResponseBody LeaveTrail
	 * getLeaveTrailById(@RequestParam("trailPkey") int trailPkey) {
	 * 
	 * LeaveTrail leaveTrail = new LeaveTrail(); try {
	 * 
	 * leaveTrail = leaveTrailRepository.findByTrailPkeyAndDelStatus(trailPkey, 1);
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return leaveTrail;
	 * 
	 * }
	 */

	@Autowired
	EmployeeInfoRepository employeeInfoRepository;

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
				int empId = save.getEmpId();

				EmployeeInfo empInfo1 = new EmployeeInfo();

				empInfo1 = employeeInfoRepository.findByEmpIdAndDelStatus(empId, 1);
				String name = empInfo1.getEmpFname() + " " + empInfo1.getEmpSname();

				GetAuthorityIds leaveApply = new GetAuthorityIds();
				leaveApply = getAuthorityIdsRepo.getAuthIdsDict(empId);

				String empIds = leaveApply.getRepToEmpIds();
				String[] values = empIds.split(",");
				System.err.println("emp ids for notification are::" + empIds);
				List<String> al = new ArrayList<String>(Arrays.asList(values));

				Set<String> set = new HashSet<>(al);
				al.clear();
				al.addAll(set);
				System.err.println("emp ids for notification are:--------------:" + al.toString());

				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");

				String fromDate = "", toDate = "";

				try {

					Date d1 = sdf1.parse(save.getLeaveFromdt());
					Date d2 = sdf1.parse(save.getLeaveTodt());

					fromDate = sdf2.format(d1.getTime());
					toDate = sdf2.format(d2.getTime());

				} catch (Exception e) {
					fromDate = save.getLeaveFromdt();
					toDate = save.getLeaveTodt();
					e.printStackTrace();
				}

				String msg = empInfo1.getEmpFname() + " " + empInfo1.getEmpSname() + " has applied For Leave from "
						+ fromDate + " to " + toDate + " for " + save.getLeaveNumDays() + " days Please Check";

				for (int i = 0; i < al.size(); i++) {

					EmployeeInfo empInfo = new EmployeeInfo();
					empInfo = employeeInfoRepository.findByEmpIdAndDelStatus(Integer.parseInt(al.get(i)), 1);
					try {

						Firebase.sendPushNotification(empInfo.getExVar1(), "HRMS",
								" " + name + " has applied for leave from " + fromDate + " to " + toDate + " for "
										+ save.getLeaveNumDays() + " days, Please check for Approval",
								11);
						Info emailRes1 = EmailUtility.sendEmail("atsinfosoft@gmail.com", "atsinfosoft@123",
								empInfo.getEmpEmail(), " HRMS Leave Application Status", "", msg);

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

				Setting setting = new Setting();
				setting = settingRepo.findByKey("hremail");
				String hrEmail = (setting.getValue());
				System.out.println(hrEmail);
				Info emailRes = EmailUtility.sendEmail("atsinfosoft@gmail.com", "atsinfosoft@123", hrEmail,
						" HRMS Leave Application Status", "", " " + name + " has applied for leave from " + fromDate
								+ " to " + toDate + " for " + save.getLeaveNumDays() + " days");

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getLeaveApplyList" }, method = RequestMethod.GET)
	public @ResponseBody List<LeaveApply> getLeaveApplyList() {

		List<LeaveApply> list = new ArrayList<LeaveApply>();
		try {

			list = leaveApplyRepository.findByDelStatus(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/deleteLeaveApply" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteLeaveApply(@RequestParam("leaveId") int leaveId) {

		Info info = new Info();

		try {

			int delete = leaveApplyRepository.deleteLeaveApply(leaveId);

			if (delete > 0) {
				info.setError(false);
				info.setMsg("deleted");
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

	@RequestMapping(value = { "getLeaveApplyById" }, method = RequestMethod.POST)
	public @ResponseBody LeaveApply getLeaveApplyById(@RequestParam("leaveId") int leaveId) {

		LeaveApply leaveApply = new LeaveApply();
		try {

			leaveApply = leaveApplyRepository.findByLeaveIdAndDelStatus(leaveId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return leaveApply;

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
	// new

	@RequestMapping(value = { "/getLeaveApplyDetailsByLeaveId" }, method = RequestMethod.POST)
	public @ResponseBody GetLeaveApplyAuthwise getLeaveApplyDetailsByLeaveId(@RequestParam("leaveId") int leaveId) {
		GetLeaveApplyAuthwise list = new GetLeaveApplyAuthwise();
		System.out.println("inside getLeaveApplyDetailsByLeaveId");

		try {

			list = getLeaveApplyAuthwiseRepo.getLeaveApplyDetails(leaveId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	LeaveDetailRepo leaveDetailRepo;
	@Autowired
	EmployeeLeaveDetailRepo employeeLeaveDetailRepo;

	@RequestMapping(value = { "getLeaveListByEmp" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveDetail> getLeaveListByLocIdAndEmp(@RequestParam("empId") int empId) {

		List<LeaveDetail> employeeInfo = new ArrayList<LeaveDetail>();

		try {

			employeeInfo = leaveDetailRepo.getLeaveListByEmp(empId);
			for (int i = 0; i < employeeInfo.size(); i++) {
				employeeInfo.get(i).setLeaveFromdt(DateConvertor.convertToDMY(employeeInfo.get(i).getLeaveFromdt()));
				employeeInfo.get(i).setLeaveTodt(DateConvertor.convertToDMY(employeeInfo.get(i).getLeaveTodt()));
			}

			System.out.println(employeeInfo);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return employeeInfo;

	}

	// Akshay new code 20-08-2018
	@RequestMapping(value = { "getLeaveListByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody List<EmployeeLeaveDetail> getLeaveListByEmpId(@RequestParam("empId") int empId) {

		List<EmployeeLeaveDetail> employeeInfo = new ArrayList<EmployeeLeaveDetail>();

		try {

			employeeInfo = employeeLeaveDetailRepo.getLeaveListByEmp(empId);

			System.out.println("info" + employeeInfo);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return employeeInfo;

	}
	// *************WS for leave approvals and updations and
	// rejections*******************************

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

	@RequestMapping(value = { "/updateLeaveStatus" }, method = RequestMethod.POST)
	public @ResponseBody Info updateLeaveStatus(@RequestParam("leaveId") int leaveId,
			@RequestParam("status") int status) {

		Info info = new Info();
		System.err.println("in updateLeaveStatus" + status + leaveId);
		try {

			int delete = leaveApplyRepository.updateLeaveStatus(leaveId, status);

			if (delete > 0) {
				info.setError(false);
				info.setMsg("updated status");

				LeaveApply leaveApply = new LeaveApply();

				leaveApply = leaveApplyRepository.findByLeaveIdAndDelStatus(leaveId, 1);
				int empId = leaveApply.getEmpId();

				GetAuthorityIds authIds = new GetAuthorityIds();
				authIds = getAuthorityIdsRepo.getAuthIdsDict(empId);

				String empIds = authIds.getRepToEmpIds();
				String[] values = empIds.split(",");
				List<String> al = new ArrayList<String>(Arrays.asList(values));

				Set<String> set = new HashSet<>(al);
				al.clear();
				al.addAll(set);
				al.add(String.valueOf(empId));
				System.err.println("emp ids for notification are:--------------:" + al.toString());

				EmployeeInfo emp = new EmployeeInfo();

				emp = employeeInfoRepository.findByEmpIdAndDelStatus(empId, 1);

				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");

				String fromDate = "", toDate = "";

				try {

					Date d1 = sdf1.parse(leaveApply.getLeaveFromdt());
					Date d2 = sdf1.parse(leaveApply.getLeaveTodt());

					fromDate = sdf2.format(d1.getTime());
					toDate = sdf2.format(d2.getTime());

				} catch (Exception e) {
					fromDate = leaveApply.getLeaveFromdt();
					toDate = leaveApply.getLeaveTodt();
					e.printStackTrace();
				}
				String msg = null;

				// Sending mail & Notification to the emp itself
				try {

					if (status == 2) {

						msg = emp.getEmpFname() + " " + emp.getEmpSname() + " your Leave from " + fromDate + " to "
								+ toDate + " for " + leaveApply.getLeaveNumDays()
								+ " days has been Approved By Initial Authority";

					} else if (status == 3) {

						msg = emp.getEmpFname() + " " + emp.getEmpSname() + " your Leave from " + fromDate + " to "
								+ toDate + " for " + leaveApply.getLeaveNumDays()
								+ " days has been Approved By Final Authority";

					} else if (status == 8) {

						msg = emp.getEmpFname() + " " + emp.getEmpSname() + " your Leave from " + fromDate + " to "
								+ toDate + " for " + leaveApply.getLeaveNumDays()
								+ " days has been Rejected By Initial Authority";

					} else if (status == 9) {

						msg = emp.getEmpFname() + " " + emp.getEmpSname() + " your Leave from " + fromDate + " to "
								+ toDate + " for " + leaveApply.getLeaveNumDays()
								+ " days has been Rejected By Final Authority";

					}

					try {
						if (emp.getExVar1() != "" && emp.getExVar1() != null) {
							Firebase.sendPushNotification(emp.getExVar1(), "HRMS", msg, 1);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

					Info emailRes2 = EmailUtility.sendEmail("atsinfosoft@gmail.com", "atsinfosoft@123",
							emp.getEmpEmail(), " HRMS Leave Application Status", "", msg);

				} catch (Exception e) {
					e.printStackTrace();
				}

				// Sending mail & Notification to the Authorities and rep to

				try {

					String claimMsg = new String();
					if (status == 2) {

						claimMsg = emp.getEmpFname() + " " + emp.getEmpSname() + " Leave from " + fromDate + " to "
								+ toDate + " for " + leaveApply.getLeaveNumDays()
								+ " days has been Approved By Initial Authority";

					} else if (status == 3) {

						claimMsg = emp.getEmpFname() + " " + emp.getEmpSname() + " Leave from " + fromDate + " to "
								+ toDate + " for " + leaveApply.getLeaveNumDays()
								+ " days has been Approved By Final Authority";

					} else if (status == 8) {

						claimMsg = emp.getEmpFname() + " " + emp.getEmpSname() + " Leave from " + fromDate + " to "
								+ toDate + " for " + leaveApply.getLeaveNumDays()
								+ " days has been Rejected By Initial Authority";

					} else if (status == 9) {

						claimMsg = emp.getEmpFname() + " " + emp.getEmpSname() + " Leave from " + fromDate + " to "
								+ toDate + " for " + leaveApply.getLeaveNumDays()
								+ " days has been Rejected By Final Authority";

					} else if (status == 7) {

						msg = emp.getEmpFname() + " " + emp.getEmpSname() + "  Leave from " + fromDate + " to " + toDate
								+ " for " + leaveApply.getLeaveNumDays() + " days has been Cancelled ";

					}

					for (int i = 0; i < al.size(); i++) {

						EmployeeInfo empInfo = new EmployeeInfo();

						empInfo = employeeInfoRepository.findByEmpIdAndDelStatus(Integer.parseInt(al.get(i)), 1);

						try {
							if (emp.getExVar1() != "" && emp.getExVar1() != null) {
								Firebase.sendPushNotification(empInfo.getExVar1(), "HRMS", msg, 1);
							}
						} catch (Exception e) {

							e.printStackTrace();

						}

						try {
							Info emailRes1 = EmailUtility.sendEmail("atsinfosoft@gmail.com", "atsinfosoft@123",
									empInfo.getEmpEmail(), " HRMS Leave Application Status", "", msg);
						} catch (Exception e) {

							e.printStackTrace();

						}
					}
					Setting setting = new Setting();
					setting = settingRepo.findByKey("hremail");
					String hrEmail = (setting.getValue());
					System.out.println(hrEmail);
					Info emailRes = EmailUtility.sendEmail("atsinfosoft@gmail.com", "atsinfosoft@123", hrEmail,
							" HRMS Leave Application Status", "", msg);

				} catch (Exception e) {

					e.printStackTrace();

				}

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

}