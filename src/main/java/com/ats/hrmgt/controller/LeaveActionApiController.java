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
 
import com.ats.hrmgt.common.Firebase; 
import com.ats.hrmgt.model.AuthorityInformation; 
import com.ats.hrmgt.model.GetAuthorityIds;
import com.ats.hrmgt.model.GetLeaveApplyAuthwise;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.LeaveApply;
import com.ats.hrmgt.model.LeaveHistory;
import com.ats.hrmgt.model.LeaveTrail;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.repository.AuthorityInformationRepository;
import com.ats.hrmgt.repository.GetAuthorityIdsRepo;
import com.ats.hrmgt.repository.GetLeaveApplyAuthwiseRepo;
import com.ats.hrmgt.repository.LeaveApplyRepository;
import com.ats.hrmgt.repository.LeaveHistoryRepo;
import com.ats.hrmgt.repository.LeaveTrailRepository;

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

}
