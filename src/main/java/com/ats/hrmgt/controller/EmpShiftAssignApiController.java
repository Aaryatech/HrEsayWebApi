package com.ats.hrmgt.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.GetEmployeeDetails;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.LeaveType;
import com.ats.hrmgt.model.MstEmpType;
import com.ats.hrmgt.model.SalaryTypesMaster;
import com.ats.hrmgt.model.ShiftMaster;
import com.ats.hrmgt.repository.EmpSalaryInfoRepo;
import com.ats.hrmgt.repository.EmployeeMasterRepository;
import com.ats.hrmgt.repository.GetEmployeeDetailsRepo;
import com.ats.hrmgt.repository.MstEmpTypeRepository;
import com.ats.hrmgt.repository.SalaryTypesMasterRepo;
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

	@RequestMapping(value = { "/getAllEmplistForHolidayCatAssign" }, method = RequestMethod.GET)
	public List<GetEmployeeDetails> getAllEmplistForHolidayCatAssign() {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getAllEmplistForHolidayCatAssign();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getEmpDetailListByLocId" }, method = RequestMethod.POST)
	public List<GetEmployeeDetails> getEmpDetailListByLocId(@RequestParam("locationIds") List<Integer> locationIds) {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getEmpDetailListByLocId(locationIds);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getAllEmployeeDetailByEmpId" }, method = RequestMethod.POST)
	public GetEmployeeDetails getAllEmployeeDetailByEmpId(@RequestParam("empId") int empId) {
		GetEmployeeDetails list = new GetEmployeeDetails();
		try {
			list = getEmployeeDetailsRepo.getEmpDetailList(empId);
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetailByEmpId : " + e.getMessage());
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

	@Autowired
	SalaryTypesMasterRepo salaryTypesMasterRepo;

	@RequestMapping(value = { "/getSalryTypesMst" }, method = RequestMethod.GET)
	public @ResponseBody List<SalaryTypesMaster> getSalryTypesMst() {

		List<SalaryTypesMaster> list = new ArrayList<SalaryTypesMaster>();
		try {

			list = salaryTypesMasterRepo.findAllByDelStatus(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	EmpSalaryInfoRepo empSalaryInfoRepo;

	@RequestMapping(value = { "/salStructAssignmentUpdate" }, method = RequestMethod.POST)
	public @ResponseBody Info salStructAssignmentUpdate(@RequestParam("empIdList") List<Integer> empIdList,
			@RequestParam("structId") String structId) {

		Info info = new Info();
		try {

			int res = 0;
			res = empSalaryInfoRepo.assignsalStruct(empIdList, structId);

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

	// **************************MstEmpType*******************************************
	@Autowired
	MstEmpTypeRepository mstEmpTypeRepository;

	@RequestMapping(value = { "/saveMstEmpType" }, method = RequestMethod.POST)
	public @ResponseBody MstEmpType saveLeaveType(@RequestBody MstEmpType leaveType) {

		MstEmpType save = new MstEmpType();
		try {

			save = mstEmpTypeRepository.saveAndFlush(leaveType);
			if (save == null) {

				save = new MstEmpType();

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getMstEmpTypeList" }, method = RequestMethod.POST)
	public @ResponseBody List<MstEmpType> getLeaveTypeListIsStructure(@RequestParam("companyId") int companyId) {

		List<MstEmpType> list = new ArrayList<MstEmpType>();
		try {

			list = mstEmpTypeRepository.findByDelStatusAndCompanyId(1, companyId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getMstEmpTypeById" }, method = RequestMethod.POST)
	public @ResponseBody MstEmpType getLeaveTypeList(@RequestParam("empTypeId") int empTypeId) {

		MstEmpType list = new MstEmpType();
		try {

			list = mstEmpTypeRepository.findByDelStatusAndEmpTypeId(1, empTypeId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	EmployeeMasterRepository empRepo;

	@RequestMapping(value = { "/deleteLMstEmpType" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteLMstEmpType(@RequestParam("empTypeId") int empTypeId) {

		Info info = new Info();
		try {

			List<EmployeeMaster> empList = empRepo.findByEmpTypeAndDelStatus(empTypeId, 1);

			if (empList.size() <= 0) {
				int delete = mstEmpTypeRepository.deleteMstType(empTypeId);
				if (delete > 0) {
					info.setError(false);
					info.setMsg("Employee Type Deleted Successfully");
				} else {
					info.setError(true);
					info.setMsg("Failed to Delete Employee Type");
				}
			} else {
				info.setError(true);
				info.setMsg("Employee Type Can't Be Deleted as it is Assigned to Employee");
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("Failed to Delete Employee Type");
		}

		return info;

	}

}
