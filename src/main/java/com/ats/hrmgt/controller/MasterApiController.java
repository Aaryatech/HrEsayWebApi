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

import com.ats.hrmgt.claim.repository.GetEmpInfoRepo;
import com.ats.hrmgt.common.DateConvertor;
import com.ats.hrmgt.model.CalenderYear;
import com.ats.hrmgt.model.Department;
import com.ats.hrmgt.model.Designation;
import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.GetEmployeeDetails;
import com.ats.hrmgt.model.Holiday;
import com.ats.hrmgt.model.HolidayCategory;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.LeaveStructureDetails;
import com.ats.hrmgt.model.LeaveSummary;
import com.ats.hrmgt.model.LeaveType;
import com.ats.hrmgt.model.Location;
import com.ats.hrmgt.model.SelfGroup;
import com.ats.hrmgt.model.ShiftMaster;
import com.ats.hrmgt.model.WeeklyOff;
import com.ats.hrmgt.model.WeekoffCategory;
import com.ats.hrmgt.model.bonus.BonusMaster;
import com.ats.hrmgt.model.claim.GetEmployeeInfo;
import com.ats.hrmgt.repository.CalculateYearRepository;
import com.ats.hrmgt.repository.DepartmentRepo;
import com.ats.hrmgt.repository.DesignationRepo;
import com.ats.hrmgt.repository.EmployeeMasterRepository;
import com.ats.hrmgt.repository.GetEmployeeDetailsRepo;
import com.ats.hrmgt.repository.HolidayCategoryRepo;
import com.ats.hrmgt.repository.HolidayRepo;
import com.ats.hrmgt.repository.LeaveStructureDetailsRepo;
import com.ats.hrmgt.repository.LeaveSummaryRepository;
import com.ats.hrmgt.repository.LeaveTypeRepository;
import com.ats.hrmgt.repository.LocationRepository;
import com.ats.hrmgt.repository.SelfGroupRepository;
import com.ats.hrmgt.repository.ShiftMasterRepository;
import com.ats.hrmgt.repository.WeeklyOffRepo;
import com.ats.hrmgt.repository.WeekoffCategoryRepo;

@RestController
public class MasterApiController {

	@Autowired
	LeaveTypeRepository leaveTypeRepository;

	@Autowired
	LeaveSummaryRepository leaveSummaryRepository;

	@Autowired
	LocationRepository locationRepository;

	@Autowired
	CalculateYearRepository calculateYearRepository;

	@Autowired
	EmployeeMasterRepository employeeMasterRepository;

	@Autowired
	ShiftMasterRepository shiftMasterRepository;

	@Autowired
	SelfGroupRepository selfGroupRepository;

	@Autowired
	HolidayCategoryRepo holidayCategoryRepo;

	@RequestMapping(value = { "/saveLeaveType" }, method = RequestMethod.POST)
	public @ResponseBody LeaveType saveLeaveType(@RequestBody LeaveType leaveType) {

		LeaveType save = new LeaveType();
		try {

			save = leaveTypeRepository.saveAndFlush(leaveType);
			if (save == null) {

				save = new LeaveType();
				save.setError(true);

			} else {
				save.setError(false);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getLeaveTypeListIsStructure" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveType> getLeaveTypeListIsStructure(@RequestParam("companyId") int companyId) {

		List<LeaveType> list = new ArrayList<LeaveType>();
		try {

			list = leaveTypeRepository.findByDelStatusAndIsStructuredAndCompanyId(1, 1, companyId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getLeaveTypeList" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveType> getLeaveTypeList() {

		List<LeaveType> list = new ArrayList<LeaveType>();
		try {

			list = leaveTypeRepository.findByDelStatus(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	LeaveStructureDetailsRepo leaveStructureDetailsRepo;

	@RequestMapping(value = { "/deleteLeaveType" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteLeaveType(@RequestParam("lvTypeId") int lvTypeId) {

		Info info = new Info();

		try {
			List<LeaveStructureDetails> lvsDet = leaveStructureDetailsRepo.findByLvTypeIdAndDelStatus(lvTypeId);

			if (lvsDet.size() <= 0) {

				int delete = leaveTypeRepository.deleteLeaveType(lvTypeId);

				if (delete > 0) {
					info.setError(false);
					info.setMsg("Leave Type Deleted Successfully");
				} else {
					info.setError(true);
					info.setMsg("Failed To Delete Leave Type");
				}
			}

			else {
				info.setError(true);
				info.setMsg("Leave Type Can't be Deleted as it is Part of Leave Structure ");
			}

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("Failed To Delete Leave Type");
			System.err.println("Excep in deleteBank : " + e.getMessage());
			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/checkUniqueShortName" }, method = RequestMethod.POST)
	public @ResponseBody LeaveType checkUniqueShortName(@RequestParam("valueType") String valueType,
			@RequestParam("compId") int compId) {

		LeaveType leaveType = new LeaveType();

		try {

			leaveType = leaveTypeRepository.findByCompanyIdAndLvTitleShort(compId, valueType);

			/*
			 * if (leaveType==null) { info.setError(false); info.setMsg("deleted"); } else {
			 * info.setError(true); info.setMsg("failed");
			 * 
			 * }
			 */
		} catch (Exception e) {

			e.printStackTrace();

		}

		return leaveType;

	}

	@Autowired
	DesignationRepo designationRepo;

	/*
	 * @RequestMapping(value = { "/checkUniqueDesignation" }, method =
	 * RequestMethod.POST) public @ResponseBody Info
	 * checkUniqueDesignation(@RequestParam("desgn") String desgn,
	 * 
	 * @RequestParam("compId") int compId) {
	 * 
	 * Info info = new Info();
	 * 
	 * try {
	 * 
	 * Designation det = designationRepo.findByNameAndCompanyId(desgn, compId);
	 * 
	 * if (det == null) { info.setError(false); info.setMsg("0"); } else {
	 * info.setError(true); info.setMsg("1");
	 * 
	 * }
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace();
	 * 
	 * }
	 * 
	 * return info;
	 * 
	 * }
	 */
	@Autowired
	DepartmentRepo departmentRepo;

	/*
	 * @RequestMapping(value = { "/checkUniqueDepartment" }, method =
	 * RequestMethod.POST) public @ResponseBody Info
	 * checkUniqueDepartment(@RequestParam("dept") String dept,
	 * 
	 * @RequestParam("compId") int compId) {
	 * 
	 * Info info = new Info();
	 * 
	 * try {
	 * 
	 * Department det = departmentRepo.findByNameAndCompanyId(dept, compId);
	 * 
	 * if (det == null) { info.setError(false); info.setMsg("0"); } else {
	 * info.setError(true); info.setMsg("1");
	 * 
	 * }
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace();
	 * 
	 * }
	 * 
	 * return info;
	 * 
	 * }
	 */
	
	@Autowired
	WeekoffCategoryRepo weekoffCategoryRepo;
	
	@RequestMapping(value = { "/checkUniqueDeptDesgn" }, method = RequestMethod.POST)
	public @ResponseBody Info checkUniqueField(@RequestParam String inputValue, @RequestParam int valueType,
			@RequestParam int isEditCall, @RequestParam int primaryKey, @RequestParam("compId") int compId) {

		Info info = new Info();
//1- dept,2-desgn
		List<Department> dept = new ArrayList<Department>();
		List<Designation> desgn = new ArrayList<Designation>();
		
		List<HolidayCategory> hoCatList = new ArrayList<HolidayCategory>();
		List<WeekoffCategory> woCatList = new ArrayList<WeekoffCategory>();


		if (valueType == 1) {
			// System.err.println("Its Dept check");
			if (isEditCall == 0) {
				// System.err.println("Its New Record Insert ");
				dept = departmentRepo.findByNameAndCompanyId(inputValue, compId);
			} else {
				// System.err.println("Its Edit Record ");
				dept = departmentRepo.findByNameAndCompanyIdAndDepartIdNot(inputValue.trim(), compId, primaryKey);
			}
			// System.err.println("****"+dept.toString());
			if (dept.size() <= 0) {
				info.setError(false);
				info.setMsg("0");
			} else {
				info.setError(true);
				info.setMsg("1");

			}
		} else if (valueType == 2) {
			// System.err.println("Its Desn check");
			if (isEditCall == 0) {
				// System.err.println("Its New Record Insert ");
				desgn = designationRepo.findByNameAndCompanyId(inputValue, compId);
			} else {
				// System.err.println("Its Edit Record ");
				desgn = designationRepo.findByNameAndCompanyIdAndDesigIdNot(inputValue.trim(), compId, primaryKey);
			}

			if (desgn.size() <= 0) {
				info.setError(false);
				info.setMsg("0");
			} else {
				info.setError(true);
				info.setMsg("1");

			}

		}
		
		else if (valueType == 3) {
 			if (isEditCall == 0) {
		 
				hoCatList = holidayCategoryRepo.findByHoCatNameAndCompanyId(inputValue, compId);
			} else {
				 
				hoCatList = holidayCategoryRepo.findByHoCatNameAndCompanyIdAndHoCatIdNot(inputValue.trim(), compId, primaryKey);
			}

			if (hoCatList.size() <= 0) {
				info.setError(false);
				info.setMsg("0");
			} else {
				info.setError(true);
				info.setMsg("1");

			}

		} 
		else if (valueType == 4) {
			System.err.println("Its Week cat check");
			if (isEditCall == 0) {
			 System.err.println("Its New Record Insert ");
			 woCatList = weekoffCategoryRepo.findByWoCatNameAndCompanyId(inputValue, compId);
			} else {
				System.err.println("Its Edit Record ");
				woCatList = weekoffCategoryRepo.findByWoCatNameAndCompanyIdAndWoCatIdNot(inputValue.trim(), compId, primaryKey);
			}

			if (woCatList.size() <= 0) {
				info.setError(false);
				info.setMsg("0");
			} else {
				info.setError(true);
				info.setMsg("1");

			}

		}


		return info;

	}

	@RequestMapping(value = { "/getLeaveTypeById" }, method = RequestMethod.POST)
	public @ResponseBody LeaveType getLeaveTypeById(@RequestParam("lvTypeId") int lvTypeId) {

		LeaveType leaveType = new LeaveType();
		try {

			leaveType = leaveTypeRepository.findByLvTypeIdAndDelStatus(lvTypeId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return leaveType;

	}

	@RequestMapping(value = { "/getLeaveSummaryList" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveSummary> getLeaveSummaryList(@RequestParam("compId") int compId) {

		List<LeaveSummary> list = new ArrayList<LeaveSummary>();
		try {

			if (compId != 0) {

				list = leaveSummaryRepository.findByDelStatusAndCompanyId(1, compId);

			} else {

				list = leaveSummaryRepository.findByDelStatus(1);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/saveLocation" }, method = RequestMethod.POST)
	public @ResponseBody Location saveLocation(@RequestBody Location location) {

		Location save = new Location();
		try {

			save = locationRepository.saveAndFlush(location);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getLocationList" }, method = RequestMethod.POST)
	public @ResponseBody List<Location> getLocationList(@RequestParam("companyId") int companyId) {

		List<Location> list = new ArrayList<Location>();
		try {

			if (companyId != 0) {
				list = locationRepository.findByDelStatusAndCompIdOrderByLocIdDesc(1, companyId);
			} else {
				list = locationRepository.findByDelStatus(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	EmployeeMasterRepository empRepo;

	@RequestMapping(value = { "/deleteLocation" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteLocation(@RequestParam("locId") int locId) {

		Info info = new Info();

		try {

			List<EmployeeMaster> empList = empRepo.findByLocationIdAndDelStatus(locId, 1);

			if (empList.size() <= 0) {

				int delete = locationRepository.deleteLocation(locId);

				if (delete > 0) {
					info.setError(false);
					info.setMsg("Location Deleted Successfully");
				} else {
					info.setError(true);
					info.setMsg("Failed to Delete Location");
				}
			} else {
				info.setError(true);
				info.setMsg("Loaction Can't Be Deleted as it is Assigned to Employee");
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("Failed to Delete Location");
		}

		return info;

	}

	@RequestMapping(value = { "/getLocationById" }, method = RequestMethod.POST)
	public @ResponseBody Location getLocationById(@RequestParam("locId") int locId) {

		Location location = new Location();
		try {

			location = locationRepository.findByLocIdAndDelStatus(locId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return location;

	}

	@RequestMapping(value = { "/getCalculateYearListIsCurrent" }, method = RequestMethod.GET)
	public @ResponseBody CalenderYear getCalculateYearListIsCurrent() {

		CalenderYear calendearYear = new CalenderYear();
		try {

			calendearYear = calculateYearRepository.findByIsCurrent(1);

			System.out.println(calendearYear);
			calendearYear.setCalYrFromDate(DateConvertor.convertToDMY(calendearYear.getCalYrFromDate()));
			calendearYear.setCalYrToDate(DateConvertor.convertToDMY(calendearYear.getCalYrToDate()));
			System.out.println(calendearYear);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return calendearYear;

	}

	@RequestMapping(value = { "/getcurrentyear" }, method = RequestMethod.GET)
	public @ResponseBody CalenderYear getcurrentyear() {

		CalenderYear calendearYear = new CalenderYear();
		try {

			calendearYear = calculateYearRepository.findByIsCurrent(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return calendearYear;

	}
	
	
	@Autowired
	GetEmployeeDetailsRepo getEmployeeDetailsRepo;

	@RequestMapping(value = { "/getEmplistForAssignAuthority" }, method = RequestMethod.GET)
	public @ResponseBody List<GetEmployeeDetails> getEmplistForAssignAuthority() {

		List<GetEmployeeDetails> list = new ArrayList<>();
		try {

			list = getEmployeeDetailsRepo.getEmplistForAssignAuthority();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/getEmplistForAssignAuthorityAll" }, method = RequestMethod.GET)
	public @ResponseBody List<EmployeeMaster> getEmplistForAssignAuthorityAll() {

		List<EmployeeMaster> list = new ArrayList<>();
		try {

			list = employeeMasterRepository.getEmplistForAssignAuthorityAll();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getEmpInfoListForLeaveAuth" }, method = RequestMethod.GET)
	public @ResponseBody List<GetEmployeeDetails> getEmpInfoListForLeaveAuth() {

		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {

			list = getEmployeeDetailsRepo.getEmpListByCompanyIdForAuth();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getEmpInfoListByEmpIdList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetEmployeeDetails> getEmpInfoListByEmpIdList(
			@RequestParam("empIdList") List<Integer> empIdList) {

		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {

			list = getEmployeeDetailsRepo.getEmpListByCompanyIdAndEmpIdList(empIdList);

		 

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	GetEmpInfoRepo getEmpInfo;

	@RequestMapping(value = { "/getEmpInfoListByEmpIdList1" }, method = RequestMethod.POST)
	public @ResponseBody List<GetEmployeeInfo> getEmpInfoListByEmpIdList(@RequestParam("companyId") int companyId,
			@RequestParam("empIdList") List<Integer> empIdList) {

		List<GetEmployeeInfo> list = new ArrayList<GetEmployeeInfo>();
		try {

			list = getEmpInfo.getEmpListByCompanyIdByEmp(companyId, empIdList);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getAuthorityWiseEmpListByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody List<GetEmployeeDetails>  getAuthorityWiseEmpListByEmpId(@RequestParam("empId") int empId) {

		List<GetEmployeeDetails>  list = new ArrayList<GetEmployeeDetails>();
		try {

			list = getEmployeeDetailsRepo.getAuthorityWiseEmpListByEmpId(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getEmpInfoByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody EmployeeMaster getEmpInfoByEmpId(@RequestParam("empId") int empId) {

		EmployeeMaster employeeMaster = new EmployeeMaster();
		try {

			employeeMaster = employeeMasterRepository.getEmpInfoByEmpId(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return employeeMaster;

	}

	@RequestMapping(value = { "/showShiftListByLocationIds" }, method = RequestMethod.POST)
	public @ResponseBody List<ShiftMaster> showShiftListByLocationIds(
			@RequestParam("locationIds") List<Integer> locationIds) {

		List<ShiftMaster> shiftList = new ArrayList<>();
		try {

			shiftList = shiftMasterRepository.showShiftListByLocationIds(locationIds);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return shiftList;

	}

	@RequestMapping(value = { "/deleteShiftTime" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteShiftTime(@RequestParam("shiftId") int shiftId) {

		Info info = new Info();
		try {

			int delete = shiftMasterRepository.deleteShiftTime(shiftId);
			info.setError(false);
			info.setMsg("success");

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}

		return info;

	}

	// self grp
	@RequestMapping(value = { "/saveSelfGrp" }, method = RequestMethod.POST)
	public @ResponseBody SelfGroup saveSelfGrp(@RequestBody SelfGroup bonusMaster) {

		SelfGroup save = new SelfGroup();
		try {

			save = selfGroupRepository.saveAndFlush(bonusMaster);
			if (save == null) {

				save = new SelfGroup();
				save.setError(true);

			} else {
				save.setError(false);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getSelftGroupList" }, method = RequestMethod.GET)
	public @ResponseBody List<SelfGroup> getSelftGroupList() {

		List<SelfGroup> selfGrouptList = new ArrayList<>();
		try {

			selfGrouptList = selfGroupRepository.selfGrouptList();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return selfGrouptList;

	}

	@Autowired
	ShiftMasterRepository ShiftMasterRepository;

	@RequestMapping(value = { "/deleteSelfGroup" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteSelfGroup(@RequestParam("bonusId") int bonusId) {

		Info info = new Info();

		try {

			List<ShiftMaster> shftList = ShiftMasterRepository.findBySelfGroupIdAndStatus(bonusId, 1);

			if (shftList.size() <= 0) {
				int delete = selfGroupRepository.deleteSelfGroup(bonusId);

				if (delete > 0) {
					info.setError(false);
					info.setMsg("Self Group Deleted Successfully");
				} else {
					info.setError(true);
					info.setMsg("Failed To Delete Self Group");
				}
			}

			else {
				info.setError(true);
				info.setMsg("Self Group Can't be Deleted as it is Asigned");
			}

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("Failed To Delete Self Group");
			System.err.println("Excep in deleteBank : " + e.getMessage());
			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = { "/getSelfGroupById" }, method = RequestMethod.POST)
	public @ResponseBody SelfGroup getSelfGroupById(@RequestParam("bonusId") int selftGroupId) {

		SelfGroup bous = new SelfGroup();
		try {

			bous = selfGroupRepository.findBySelftGroupIdAndDelStatus(selftGroupId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return bous;

	}
	// selfgrp end

	@RequestMapping(value = { "/saveShiftMaster" }, method = RequestMethod.POST)
	public @ResponseBody ShiftMaster getSelftGroupList(@RequestBody ShiftMaster shiftMaster) {

		ShiftMaster save = new ShiftMaster();
		try {

			save = shiftMasterRepository.save(shiftMaster);

			if (shiftMaster.getChangewith() == 0) {
				save.setChangewith(save.getId());
				save = shiftMasterRepository.save(shiftMaster);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getShiftListByGroupIdandlocId" }, method = RequestMethod.POST)
	public @ResponseBody List<ShiftMaster> getShiftListByGroupIdandlocId(@RequestParam("locationId") int locationId,
			@RequestParam("groupId") int groupId) {

		List<ShiftMaster> shiftList = new ArrayList<>();
		try {

			shiftList = shiftMasterRepository.getShiftListByGroupIdandlocId(locationId, groupId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return shiftList;

	}

	// *************Holiday Category***********************

	@RequestMapping(value = { "/saveHolidayCat" }, method = RequestMethod.POST)
	public @ResponseBody HolidayCategory saveHolidayCat(@RequestBody HolidayCategory holiCat) {

		HolidayCategory save = new HolidayCategory();
		try {

			save = holidayCategoryRepo.saveAndFlush(holiCat);
			if (save == null) {

				save = new HolidayCategory();
				save.setError(true);

			} else {
				save.setError(false);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getHolidayCategoryList" }, method = RequestMethod.POST)
	public @ResponseBody List<HolidayCategory> HolidayCategory() {

		List<HolidayCategory> list = new ArrayList<HolidayCategory>();
		try {

			list = holidayCategoryRepo.findByDelStatus(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	
	@RequestMapping(value = { "/getHolidayCategoryHoCatId" }, method = RequestMethod.POST)
	public @ResponseBody HolidayCategory getHolidayCategoryHoCatId(@RequestParam("hoCatId") int hoCatId) {

		HolidayCategory bous = new HolidayCategory();
		try {

			bous = holidayCategoryRepo.findByHoCatIdAndDelStatus(hoCatId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return bous;

	}

	@Autowired
	HolidayRepo holidayRepo;

	@RequestMapping(value = { "/deleteHolidayCategory" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteHolidayCategory(@RequestParam("hoCatId") String hoCatId) {

		Info info = new Info();

		try {
			List<Holiday> lvsDet = holidayRepo.findByExInt1AndDelStatus(Integer.parseInt(hoCatId),1);

			if (lvsDet.size() <= 0) {

				int delete = holidayCategoryRepo.deleteHoliCat(Integer.parseInt(hoCatId));

				if (delete > 0) {
					info.setError(false);
					info.setMsg("Holiday Category Deleted Successfully");
				} else {
					info.setError(true);
					info.setMsg("Failed To Delete Holiday Category");
				}
			}

			else {
				info.setError(true);
				info.setMsg("Holiday Category Can't be Deleted as it is Assigned To Holiday ");
			}

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("Failed To Delete Holiday Category");
			System.err.println("Excep in deleteBank : " + e.getMessage());
			e.printStackTrace();
		}

		return info;

	}
	
	
	

	
	@RequestMapping(value = { "/saveWeekoffCat" }, method = RequestMethod.POST)
	public @ResponseBody WeekoffCategory saveWeekoffCat(@RequestBody WeekoffCategory holiCat) {

		WeekoffCategory save = new WeekoffCategory();
		try {

			save = weekoffCategoryRepo.saveAndFlush(holiCat);
			if (save == null) {

				save = new WeekoffCategory();
				save.setError(true);

			} else {
				save.setError(false);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getWeekoffCategoryList" }, method = RequestMethod.GET)
	public @ResponseBody List<WeekoffCategory> getWeekoffCategoryList() {

		List<WeekoffCategory> list = new ArrayList<WeekoffCategory>();
		try {

			list = weekoffCategoryRepo.findByDelStatus(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	
	@RequestMapping(value = { "/getWeekoffCategoryHoCatId" }, method = RequestMethod.POST)
	public @ResponseBody WeekoffCategory getWeekoffCategoryHoCatId(@RequestParam("woCatId") int woCatId) {

		WeekoffCategory bous = new WeekoffCategory();
		try {

			bous = weekoffCategoryRepo.findByWoCatIdAndDelStatus(woCatId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return bous;

	}

	@Autowired
	WeeklyOffRepo weeklyOffRepo;

	@RequestMapping(value = { "/deleteWeekoffCategory" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteWeekoffCategory(@RequestParam("woCatId") String woCatId) {

		Info info = new Info();

		try {
			List<WeeklyOff> lvsDet = weeklyOffRepo.findByExInt1AndDelStatus(Integer.parseInt(woCatId),1);

			if (lvsDet.size() <= 0) {

				int delete = weekoffCategoryRepo.deleteWoCat(Integer.parseInt(woCatId));

				if (delete > 0) {
					info.setError(false);
					info.setMsg("Weekly Off Category Deleted Successfully");
				} else {
					info.setError(true);
					info.setMsg("Failed To Delete Weekly Off Category");
				}
			}

			else {
				info.setError(true);
				info.setMsg("Weekly Off Category Can't be Deleted as it is Assigned To Week Off ");
			}

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("Failed To Delete Weekly Off Category");
			System.err.println("Excep in deleteBank : " + e.getMessage());
			e.printStackTrace();
		}

		return info;

	}
	
	
}
