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
import com.ats.hrmgt.repository.UserRepo;

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
	@Autowired
	UserRepo userRepo;
     
	@RequestMapping(value = { "/empParamAssignmentUpdate" }, method = RequestMethod.POST)
	public @ResponseBody Info empParamAssignmentUpdate(@RequestParam("empIdList") List<Integer> empIdList,
			@RequestParam("upDateId") String upDateId,@RequestParam("flag") int flag) {

		Info info = new Info();
		try {

			int res = 0;
			
			if(flag==1) {
				res = employeeMasterRepository.assignHoliCat(empIdList, upDateId);
			}else if(flag==2) {
				res = employeeMasterRepository.assignComapny(empIdList, upDateId);
			}else if(flag==3) {
				res = employeeMasterRepository.assignDept(empIdList, upDateId);
			}else if(flag==4) {
				res = employeeMasterRepository.assignDesignation(empIdList, upDateId);
			}else if(flag==5) {
				res = employeeMasterRepository.assignEmpType(empIdList, upDateId);
			}else if(flag==6) {
				res = employeeMasterRepository.assignLocation(empIdList, upDateId);
			}else if(flag==7) {
				res = employeeMasterRepository.assignShift(empIdList, upDateId);
			}else if(flag==8) {
				res = employeeMasterRepository.weekHoliCat(empIdList, upDateId);
			}else if(flag==9) {
				res = userRepo.updateAccLoc(empIdList, upDateId);
			}else if(flag==10) {
				res = employeeMasterRepository.empSkillUpdate(empIdList, upDateId);
			}else if(flag==11) {
				res = employeeMasterRepository.empEmpCategoryUpdate(empIdList, upDateId);
			}else {
				res = 0;
			}
		

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
	
	
	  
		@RequestMapping(value = { "/weekoffCatAssignmentUpdate" }, method = RequestMethod.POST)
		public @ResponseBody Info weekoffCatAssignmentUpdate(@RequestParam("empIdList") List<Integer> empIdList,
				@RequestParam("holiCatId") String holiCatId) {

			Info info = new Info();
			try {
				
				System.err.println("hii");

				int res = 0;
				res = employeeMasterRepository.weekHoliCat(empIdList, holiCatId);

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
 
	@RequestMapping(value = { "/getSalStructCountEmp" }, method = RequestMethod.POST)
	public @ResponseBody Integer getSalStructCountEmp() {

		List<EmployeeMaster> list = new ArrayList<>();
		int a=0;
		try {

			list = employeeMasterRepository.getEmpSalAssign();
			
			a=list.size();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return a;

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
	
	
	///******************************Asiignment of emp Related  Master*****************/
	///********************************************************************************/
	
	@RequestMapping(value = { "/getAllEmployeeDetailAccesibleLoc" }, method = RequestMethod.GET)
	public List<GetEmployeeDetails> getAllEmployeeDetailAccesibleLoc() {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getEmpDetailListForAccessibleLoc();
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
	 
	

}
