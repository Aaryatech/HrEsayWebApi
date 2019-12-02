package com.ats.hrmgt.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.leave.model.GetLeaveAuthority;
import com.ats.hrmgt.leave.model.GetStructureAllotment;
import com.ats.hrmgt.leave.model.Holiday;
import com.ats.hrmgt.leave.model.LeaveStructureDetails;
import com.ats.hrmgt.leave.model.LeaveStructureHeader;
import com.ats.hrmgt.leave.repo.GetLeaveAuthorityRepo;
import com.ats.hrmgt.leave.repo.GetStructureAllotmentRepo;
import com.ats.hrmgt.leave.repo.LeaveBalanceCalRepo;
import com.ats.hrmgt.leave.repo.LeaveStructureDetailsRepo;
import com.ats.hrmgt.leave.repo.LeaveStructureHeaderRepo;
import com.ats.hrmgt.leave.repo.LeavesAllotmentRepo;
import com.ats.hrmgt.model.CalenderYear;
import com.ats.hrmgt.model.EmployeeInfo;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.LeaveBalanceCal;
import com.ats.hrmgt.model.LeavesAllotment;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.repository.CalculateYearRepository;
import com.ats.hrmgt.repository.EmployeeInfoRepository;
import com.ats.hrmgt.repository.SettingRepo;

@RestController
public class LeaveStructureApiController {

	@Autowired
	LeaveStructureDetailsRepo leaveStructureDetailsRepo;

	@Autowired
	LeaveStructureHeaderRepo leaveStructureHeaderRepo;

	@Autowired
	GetStructureAllotmentRepo getStructureAllotmentRepo;

	@Autowired
	GetLeaveAuthorityRepo getLeaveAuthorityRepo;

	@Autowired
	CalculateYearRepository calculateYearRepository;

	@Autowired
	LeaveBalanceCalRepo leaveBalanceCalRepo;

	// ----------------------Leave balance Cal---------------------

	@RequestMapping(value = { "/saveLeaveBalanceCal" }, method = RequestMethod.POST)
	public @ResponseBody LeaveBalanceCal saveLeaveBalanceCal(@RequestBody LeaveBalanceCal leaveBalanceCal) {

		LeaveBalanceCal save = new LeaveBalanceCal();
		try {

			save = leaveBalanceCalRepo.saveAndFlush(leaveBalanceCal);

			if (save != null) {
				save.setError(false);
			} else {

				save = new LeaveBalanceCal();
				save.setError(true);
			}

		} catch (Exception e) {
			save = new LeaveBalanceCal();
			save.setError(true);
			e.printStackTrace();
		}

		return save;

	}
	
	@RequestMapping(value = { "/getLeavebalByEmpIdList" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveBalanceCal> getLeavebalByEmpIdList(@RequestParam("empId") int empId) {

		CalenderYear calYear = new CalenderYear();
		calYear = calculateYearRepository.findByIsCurrent(1);
 		List<LeaveBalanceCal> list = new ArrayList<LeaveBalanceCal>();
		try {

			list = leaveBalanceCalRepo.findByCalYrIdAndEmpIdAndDelStatusAndIsActive(calYear.getCalYrId() ,empId,1,1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	
	@RequestMapping(value = { "/updateLeaveBalCal" }, method = RequestMethod.POST)
	public @ResponseBody Info updateLeaveBalCal(@RequestParam("lvTypeId") int lvTypeId,@RequestParam("empId") int empId,@RequestParam("noDays") int noDays) {

		Info info = new Info();
		CalenderYear calYear = new CalenderYear();
		calYear = calculateYearRepository.findByIsCurrent(1);
		try {

			int delete = leaveBalanceCalRepo.updateLeaveBalCal(lvTypeId,empId,calYear.getCalYrId(),noDays);

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
 
	@RequestMapping(value = { "/getLeaveAuthorityList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetLeaveAuthority> getLeaveAuthorityList(@RequestParam("companyId") int companyId,
			@RequestParam("locIdList") List<Integer> locIdList) {

		List<GetLeaveAuthority> list = new ArrayList<GetLeaveAuthority>();
		try {

			list = getLeaveAuthorityRepo.getLeaveAuth(companyId, locIdList);

			for (int i = 0; i < list.size(); i++) {

				String[] reportIds = list.get(i).getRepToEmpIds().split(",");

				List<String> name = getLeaveAuthorityRepo.getEmpReportingName(reportIds);
				list.get(i).setRePortingName(name);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	SettingRepo settingRepo;

	@RequestMapping(value = { "/getStructureAllotmentList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetStructureAllotment> getStructureAllotmentList(@RequestParam("companyId") int companyId,
			@RequestParam("locIdList") List<Integer> locIdList, @RequestParam("typeId") int typeId) {

		List<GetStructureAllotment> list = new ArrayList<GetStructureAllotment>();
		try {
			Setting setting = new Setting();
		CalenderYear calYear = new CalenderYear();
			calYear = calculateYearRepository.findByIsCurrent(1);
			System.out.println(companyId);	

			if (typeId == 1) {
				list = getStructureAllotmentRepo.getStructureAllotment(companyId, locIdList, calYear.getCalYrId());

			} else {

				setting = settingRepo.findByKey("probLeaveStructure");
				int lvsId = Integer.parseInt(setting.getValue());
				System.out.println(lvsId);
				list = getStructureAllotmentRepo.getStructureAllotmentForProb(companyId, locIdList,
						calYear.getCalYrId(), lvsId);

			}
			System.out.println(list.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	// ----------------------Leave balance Structure--------------------

	@RequestMapping(value = { "/saveLeaveStruture" }, method = RequestMethod.POST)
	public @ResponseBody LeaveStructureHeader saveLeaveStruture(
			@RequestBody LeaveStructureHeader leaveStructureHeader) {

		LeaveStructureHeader leaveHeader = new LeaveStructureHeader();
		try {

			leaveHeader = leaveStructureHeaderRepo.save(leaveStructureHeader);

			for (int i = 0; i < leaveStructureHeader.getDetailList().size(); i++) {

				leaveStructureHeader.getDetailList().get(i).setLvsId(leaveStructureHeader.getLvsId());

			}

			List<LeaveStructureDetails> docTermDetailsList = leaveStructureDetailsRepo
					.saveAll(leaveStructureHeader.getDetailList());
			leaveHeader.setDetailList(docTermDetailsList);

			if (leaveHeader != null) {
				leaveHeader.setError(false);
			} else {

				leaveHeader = new LeaveStructureHeader();
				leaveHeader.setError(true);
			}

		} catch (Exception e) {
			leaveHeader = new LeaveStructureHeader();
			leaveHeader.setError(true);
			e.printStackTrace();
		}

		return leaveHeader;

	}

	@RequestMapping(value = { "/getStructureList" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveStructureHeader> getStructureList(@RequestParam("companyId") int companyId) {

		List<LeaveStructureHeader> list = new ArrayList<LeaveStructureHeader>();
		try {

			list = leaveStructureHeaderRepo.findByDelStatusAndCompanyId(1, companyId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getStructureDetailsList" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveStructureDetails> getStructureDetailsList(@RequestParam("lvsId") int lvsId) {
		Setting setting = new Setting();
		List<LeaveStructureDetails> list = new ArrayList<LeaveStructureDetails>();
		try {
			
			  setting = settingRepo.findByKey("probLeaveStructure"); int lvsId1 =
			  Integer.parseInt(setting.getValue()); System.out.println(lvsId);
			 
			
			list = leaveStructureDetailsRepo.findByLvsIdAndDelStatus(lvsId1, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	LeavesAllotmentRepo leavesAllotmentRepo;

	@RequestMapping(value = { "/getStructureByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody LeavesAllotment getStructureByEmpId(@RequestParam("empId") int empId,
			@RequestParam("currYrId") int currYrId) {

		LeavesAllotment list = new LeavesAllotment();
		try {

			list = leavesAllotmentRepo.findByDelStatusAndEmpIdAndCalYrId(1, empId, currYrId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getStructureById" }, method = RequestMethod.POST)
	public @ResponseBody LeaveStructureHeader getStructureById(@RequestParam("lvsId") int lvsId) {

		LeaveStructureHeader lvs = new LeaveStructureHeader();
		try {

			lvs = leaveStructureHeaderRepo.findByLvsIdAndDelStatus(lvsId, 1);

			List<LeaveStructureDetails> detailList = leaveStructureDetailsRepo.findByLvsIdAndDelStatus(lvsId, 1);
			lvs.setDetailList(detailList);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return lvs;

	}

	@RequestMapping(value = { "/deleteLeaveStructure" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteLeaveStructure(@RequestParam("lvsId") int lvsId) {

		Info info = new Info();

		try {

			int delete = leaveStructureHeaderRepo.deleteLeaveStructure(lvsId);

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

}
