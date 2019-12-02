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

import com.ats.hrmgt.claim.repo.ClaimStructureDetailRepo;
import com.ats.hrmgt.claim.repo.ClaimStructureHeaderRepo;
import com.ats.hrmgt.claim.repo.GetClaimStructureAllotmentRepo;
import com.ats.hrmgt.leave.model.GetStructureAllotment;
import com.ats.hrmgt.leave.model.LeaveStructureDetails;
import com.ats.hrmgt.model.CalenderYear;
import com.ats.hrmgt.model.ClaimStructureAllotment;
import com.ats.hrmgt.model.ClaimStructureDetail;
import com.ats.hrmgt.model.ClaimStructureHeader;
import com.ats.hrmgt.model.GetClaimStructureAllotment;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.LeaveBalanceCal;
import com.ats.hrmgt.model.LeavesAllotment;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.repository.ClaimStructureAllotmentRepo;

@RestController
public class ClaimStructureAndAllotApiController {
	@Autowired
	ClaimStructureDetailRepo claimStructureDetailRepo;

	@Autowired
	ClaimStructureHeaderRepo claimStructureHeaderRepo;

	@RequestMapping(value = { "/saveClaimStruture" }, method = RequestMethod.POST)
	public @ResponseBody ClaimStructureHeader saveClaimStruture(@RequestBody ClaimStructureHeader clStructureHeader) {

		ClaimStructureHeader clmHeader = new ClaimStructureHeader();
		try {

			clmHeader = claimStructureHeaderRepo.save(clStructureHeader);

			for (int i = 0; i < clStructureHeader.getDetailList().size(); i++) {

				clStructureHeader.getDetailList().get(i).setClmStructHeadId(clStructureHeader.getClmStructHeadId());

			}

			List<ClaimStructureDetail> docTermDetailsList = claimStructureDetailRepo
					.saveAll(clStructureHeader.getDetailList());
			clmHeader.setDetailList(docTermDetailsList);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return clmHeader;

	}

	@RequestMapping(value = { "/getClaimStructHeadList" }, method = RequestMethod.POST)
	public @ResponseBody List<ClaimStructureHeader> getStructureList(@RequestParam("companyId") int companyId) {

		List<ClaimStructureHeader> list = new ArrayList<ClaimStructureHeader>();
		try {

			list = claimStructureHeaderRepo.findByDelStatusAndCompanyId(1, companyId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getClaimStructDetailList" }, method = RequestMethod.POST)
	public @ResponseBody List<ClaimStructureDetail> getClaimStructDetailList(@RequestParam("headId") int headId) {
		Setting setting = new Setting();
		List<ClaimStructureDetail> list = new ArrayList<ClaimStructureDetail>();
		try {

			list = claimStructureDetailRepo.findByClmStructHeadIdAndDelStatus(headId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getClaimStructureById" }, method = RequestMethod.POST)
	public @ResponseBody ClaimStructureHeader getStructureById(@RequestParam("headId") int headId) {

		ClaimStructureHeader clmss = new ClaimStructureHeader();
		try {

			clmss = claimStructureHeaderRepo.findByClmStructHeadIdAndDelStatus(headId, 1);

			List<ClaimStructureDetail> detailList = claimStructureDetailRepo.findByClmStructHeadIdAndDelStatus(headId,
					1);
			clmss.setDetailList(detailList);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return clmss;

	}

	@RequestMapping(value = { "/deleteClaimStructure" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteLeaveStructure(@RequestParam("clmsId") int clmsId) {

		Info info = new Info();

		try {

			int delete = claimStructureHeaderRepo.deleteClaimStructure(clmsId);

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

	// ******************************Claim Struct
	// Allot*********************************

	@Autowired
	GetClaimStructureAllotmentRepo getClaimStructureAllotment;

	@RequestMapping(value = { "/getClaimStructureAllotmentList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetClaimStructureAllotment> getStructureAllotmentList(
			@RequestParam("companyId") int companyId, @RequestParam("locIdList") List<Integer> locIdList) {

		List<GetClaimStructureAllotment> list = new ArrayList<GetClaimStructureAllotment>();
		try {
			list = getClaimStructureAllotment.getStructureAllotment(companyId, locIdList);

			System.out.println(list.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	ClaimStructureAllotmentRepo claimStructureAllotmentRepo;

	@RequestMapping(value = { "/saveClaimStructAllotment" }, method = RequestMethod.POST)
	public @ResponseBody ClaimStructureAllotment saveClaimStructAllotment(
			@RequestBody ClaimStructureAllotment leavesAllotment) {

		ClaimStructureAllotment save = new ClaimStructureAllotment();

		try {

			save = claimStructureAllotmentRepo.saveAndFlush(leavesAllotment);

		} catch (Exception e) {

			save.setError(true);
			e.printStackTrace();
		}
		return save;

	}

	@RequestMapping(value = { "/checkStructAlloted" }, method = RequestMethod.POST)
	public @ResponseBody ClaimStructureAllotment checkStructAlloted(@RequestParam("empId") int empId) {

		ClaimStructureAllotment info = new ClaimStructureAllotment();

		try {

			info = claimStructureAllotmentRepo.findByEmpIdAndDelStatus(empId, 1);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return info;

	}

	@RequestMapping(value = { "/saveAllotmet" }, method = RequestMethod.POST)
	public @ResponseBody Info saveAllotmet(@RequestParam List<Integer> empList, @RequestParam int userId,
			@RequestParam String dateTime, @RequestParam int lvsId) {

		Info info = new Info();

		try {

			for (int i = 0; i < empList.size(); i++) {
				ClaimStructureAllotment res = new ClaimStructureAllotment();
				
				System.err.println("empId"+empList.get(i));
				res = claimStructureAllotmentRepo.findByEmpIdAndDelStatus(empList.get(i), 1);

				if (res != null) {
					System.err.println("empId"+res.getClmsId());
					System.err.println("exists");
					int a = claimStructureAllotmentRepo.updateClaimStructure(empList.get(i), userId, dateTime, lvsId);
				} else {

					System.err.println("no");

					ClaimStructureAllotment save = new ClaimStructureAllotment();
					ClaimStructureAllotment temp = new ClaimStructureAllotment();

					temp.setDelStatus(1);
					temp.setEmpId(empList.get(i));
					temp.setExVar1("NA");
					temp.setExVar2("NA");
					temp.setExVar3("NA");
					temp.setIsActive(1);
					temp.setMakerUserId(userId);
					temp.setMakerEnterDatetime(dateTime);
					temp.setClmsId(lvsId);

					save = claimStructureAllotmentRepo.saveAndFlush(temp);
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("failed");
		}

		return info;

	}

}
