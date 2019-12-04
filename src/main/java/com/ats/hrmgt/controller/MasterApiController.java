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

import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.LeaveSummary;
import com.ats.hrmgt.model.LeaveType;
import com.ats.hrmgt.repository.LeaveSummaryRepository;
import com.ats.hrmgt.repository.LeaveTypeRepository;
 
 
@RestController
public class MasterApiController {
	
	@Autowired
	LeaveTypeRepository leaveTypeRepository;
	
	@Autowired
	LeaveSummaryRepository leaveSummaryRepository;
	
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

}
