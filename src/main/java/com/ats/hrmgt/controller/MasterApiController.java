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

import com.ats.hrmgt.common.DateConvertor;
import com.ats.hrmgt.model.CalenderYear;
import com.ats.hrmgt.model.EmployeeMaster; 
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.LeaveSummary;
import com.ats.hrmgt.model.LeaveType;
import com.ats.hrmgt.model.Location;
import com.ats.hrmgt.repository.CalculateYearRepository;
import com.ats.hrmgt.repository.EmployeeMasterRepository;
import com.ats.hrmgt.repository.LeaveSummaryRepository;
import com.ats.hrmgt.repository.LeaveTypeRepository;
import com.ats.hrmgt.repository.LocationRepository; 
 
 
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
	
	@RequestMapping(value = { "/deleteLeaveType" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteLeaveType(@RequestParam("lvTypeId") int lvTypeId) {

		Info info = new Info();

		try {

			int delete = leaveTypeRepository.deleteLeaveType(lvTypeId);

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
			 
			if(compId!=0) {
				
				list = leaveSummaryRepository.findByDelStatusAndCompanyId(1,compId);
				
			}else {
				
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

	@RequestMapping(value = { "/deleteLocation" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteLocation(@RequestParam("locId") int locId) {

		Info info = new Info();

		try {

			int delete = locationRepository.deleteLocation(locId);

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
	
	
	@RequestMapping(value = { "/getEmplistForAssignAuthority" }, method = RequestMethod.GET)
	public @ResponseBody List<EmployeeMaster> getEmplistForAssignAuthority() {

		List<EmployeeMaster> list = new ArrayList<>();
		try {

			list = employeeMasterRepository.getEmplistForAssignAuthority();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/getEmpInfoListForLeaveAuth" }, method = RequestMethod.GET)
	public @ResponseBody List<EmployeeMaster> getEmpInfoListForLeaveAuth() {

		List<EmployeeMaster> list = new ArrayList<EmployeeMaster>();
		try {

			list = employeeMasterRepository.getEmpListByCompanyIdForAuth();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/getEmpInfoListByEmpIdList" }, method = RequestMethod.POST)
	public @ResponseBody List<EmployeeMaster> getEmpInfoListByEmpIdList(@RequestParam("empIdList") List<Integer> empIdList) {

		List<EmployeeMaster> list = new ArrayList<EmployeeMaster>();
		try {

			list = employeeMasterRepository.getEmpListByCompanyIdAndEmpIdList(empIdList);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/getAuthorityWiseEmpListByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody List<EmployeeMaster> getAuthorityWiseEmpListByEmpId(@RequestParam("empId") int empId) {

		List<EmployeeMaster> list = new ArrayList<EmployeeMaster>();
		try {

			list = employeeMasterRepository.getAuthorityWiseEmpListByEmpId(empId);

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
	
}
