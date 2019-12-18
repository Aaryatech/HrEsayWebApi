package com.ats.hrmgt.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.GetEmployeeDetails;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.ShiftMaster;
import com.ats.hrmgt.repository.EmployeeMasterRepository;
import com.ats.hrmgt.repository.GetEmployeeDetailsRepo;
import com.ats.hrmgt.repository.ShiftMasterRepository;

@RestController
public class EmpShiftAssignApiController {

	@Autowired
	GetEmployeeDetailsRepo getEmployeeDetailsRepo;

	@RequestMapping(value = { "/getAllEmployeeDetail" }, method = RequestMethod.GET)
	public List<GetEmployeeDetails> getAllEmployeeDetail() {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getEmpDetailList();
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@Autowired
	ShiftMasterRepository shiftMasterRepository;

	@RequestMapping(value = { "/getAllShiftList" }, method = RequestMethod.GET)
	public List<ShiftMaster> getAllShiftList() {
		List<ShiftMaster> list = new ArrayList<ShiftMaster>();
		try {
			list = shiftMasterRepository.findAll();
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@Autowired
	EmployeeMasterRepository employeeMasterRepository;

	@RequestMapping(value = { "/shiftAssignmentUpdate" }, method = RequestMethod.POST)
	public @ResponseBody Info taskAssignmentUpdate(@RequestParam("empIdList") List<Integer> empIdList,
			@RequestParam("shiftId") String shiftId) {

		Info info = new Info();
		try {
 
			int res = 0;
			res = employeeMasterRepository.assignShift(empIdList, shiftId);

			if (res > 0) {
				info.setError(false);
				info.setMsg("success");

			} else {
				info.setError(true);
				info.setMsg("failed");

			}
		} catch (Exception e) {

			System.err.println("Exce in deleteService  " + e.getMessage());
			e.printStackTrace();
			info.setError(true);
			info.setMsg("excep");
		}

		return info;

	}

}
