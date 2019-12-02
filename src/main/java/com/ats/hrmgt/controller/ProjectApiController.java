package com.ats.hrmgt.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.leave.repo.ProjectRepository;
import com.ats.hrmgt.model.AllocatedEmpList;
import com.ats.hrmgt.model.ClaimAuthority;
import com.ats.hrmgt.model.ClaimType;
import com.ats.hrmgt.model.EmployeeInfo;
import com.ats.hrmgt.model.GetProjectHeader;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.ProjectAllotment;
import com.ats.hrmgt.model.ProjectHeader;
import com.ats.hrmgt.model.ProjectTrail;
import com.ats.hrmgt.model.ProjectType;
import com.ats.hrmgt.repository.AllocatedEmpListRepository;
import com.ats.hrmgt.repository.EmployeeInfoRepository;
import com.ats.hrmgt.repository.GetProjectHeaderRepo;
import com.ats.hrmgt.repository.ProjectAllotmentRepository;
import com.ats.hrmgt.repository.ProjectHeaderRpo;
import com.ats.hrmgt.repository.ProjectTrailRepo;

@RestController
public class ProjectApiController {

	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	GetProjectHeaderRepo getProjectHeaderRepo;
	
	@Autowired
	EmployeeInfoRepository employeeInfoRepository;
	
	@Autowired
	ProjectAllotmentRepository projectAllotmentRepository;
	
	@Autowired
	AllocatedEmpListRepository allocatedEmpListRepository;

	// -------------Project Type------------------------

	@RequestMapping(value = { "/saveProjectType" }, method = RequestMethod.POST)
	public @ResponseBody ProjectType saveProjectType(@RequestBody ProjectType projectType) {

		ProjectType save = new ProjectType();
		try {

			save = projectRepository.saveAndFlush(projectType);
			if (save != null) {
				save.setError(false);
			} else {

				save = new ProjectType();
				save.setError(true);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getProjectList" }, method = RequestMethod.GET)
	public @ResponseBody List<ProjectType> getProjectList() {

		List<ProjectType> list = new ArrayList<ProjectType>();
		try {

			list = projectRepository.findByDelStatusOrderByProjectTypeIdDesc(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/deleteProjectType" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteProjectType(@RequestParam("projectTypeId") int projectTypeId) {

		Info info = new Info();

		try {

			int delete = projectRepository.deleteProjectType(projectTypeId);

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

	@RequestMapping(value = { "/getProjectById" }, method = RequestMethod.POST)
	public @ResponseBody ProjectType getProjectById(@RequestParam("projectTypeId") int projectTypeId) {

		ProjectType projectType = new ProjectType();
		try {

			projectType = projectRepository.findByProjectTypeIdAndDelStatus(projectTypeId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return projectType;

	}

	@RequestMapping(value = { "/getProjectListByCompanyId" }, method = RequestMethod.POST)
	public @ResponseBody List<ProjectType> getProjectListByCompanyId(@RequestParam("companyId") int companyId) {
		System.out.println(companyId);
		List<ProjectType> list = new ArrayList<ProjectType>();
		try {

			list = projectRepository.findByDelStatusAndCompanyId(1, companyId);
			System.out.println(list.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	// harsha

	// -----------------Project Header------------------

	@Autowired
	ProjectHeaderRpo projectHeaderRpo;

	@Autowired
	ProjectTrailRepo projectTrailRepo;

	@RequestMapping(value = { "/saveProjectTrail" }, method = RequestMethod.POST)
	public @ResponseBody ProjectTrail saveProjectTrail(@RequestBody ProjectTrail projectTrail) {

		ProjectTrail save = new ProjectTrail();
		try {

			save = projectTrailRepo.saveAndFlush(projectTrail);
			if (save != null) {
				save.setError(false);
			} else {

				save = new ProjectTrail();
				save.setError(true);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/saveProjectHeader" }, method = RequestMethod.POST)
	public @ResponseBody ProjectHeader saveProjectHeader(@RequestBody ProjectHeader projectHeader) {
		System.out.println(projectHeader.toString());

		ProjectHeader save = new ProjectHeader();
		try {

			save = projectHeaderRpo.saveAndFlush(projectHeader);
			if (save != null) {
				save.setError(false);
			} else {

				save = new ProjectHeader();
				save.setError(true);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/deleteProjectHeader" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteProjectHeader(@RequestParam("projectId") int projectId) {

		Info info = new Info();

		try {

			int delete = projectHeaderRpo.deleteProjectHeader(projectId);

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
	
	@RequestMapping(value = { "/updateProjectHeader" }, method = RequestMethod.POST)
	public @ResponseBody Info updateProjectHeader(@RequestParam("projectId") int projectId,@RequestParam("status") String status,
			@RequestParam("proComp") int proComp,@RequestParam("userId") int userId,@RequestParam("dateTime") String dateTime) {

		Info info = new Info();

		try {

			int delete = projectHeaderRpo.updateProjectHeader(projectId,status,proComp,userId,dateTime);

			if (delete > 0) {
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

	@RequestMapping(value = { "/getProjectHeaderByProjectId" }, method = RequestMethod.POST)
	public @ResponseBody ProjectHeader getProjectHeaderByProjectId(@RequestParam("projectId") int projectId) {

		ProjectHeader projectHeader = new ProjectHeader();
		try {

			projectHeader = projectHeaderRpo.findByProjectIdAndDelStatus(projectId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return projectHeader;

	}

	@RequestMapping(value = { "/getProjectsListByCompanyId" }, method = RequestMethod.POST)
	public @ResponseBody List<ProjectHeader> getProjectsListByCompanyId(@RequestParam("companyId") int companyId) {
		System.out.println(companyId);
		List<ProjectHeader> list = new ArrayList<ProjectHeader>();
		try {

			list = projectHeaderRpo.findByDelStatusAndCompanyId(1, companyId);
			System.out.println(list.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/getProjectsListByCompanyIdAndEmpId" }, method = RequestMethod.POST)
	public @ResponseBody List<ProjectHeader> getProjectsListByCompanyIdAndEmpId(@RequestParam("companyId") int companyId,@RequestParam("empId") int empId) {
		System.out.println(companyId);
		List<ProjectHeader> list = new ArrayList<ProjectHeader>();
		try {
			
			 Date now=new Date();
			 System.err.println("todays date:"+now);
			 

			list = projectHeaderRpo.getEmpListByCompanyIdAndEmpId(companyId,empId);
			System.out.println(list.toString());
			/*
			 * List<ProjectHeader> list1 = new ArrayList<ProjectHeader>(); for(int
			 * i=0;i<list.size();i++) {
			 * 
			 * 
			 * }
			 */
		 
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/getProjectsListForApplyClaim" }, method = RequestMethod.POST)
	public @ResponseBody List<ProjectHeader> getProjectsListForApplyClaim(@RequestParam("companyId") int companyId,@RequestParam("empId") int empId) {
		 
		List<ProjectHeader> list = new ArrayList<ProjectHeader>();
		try {
			
			 Date now=new Date();
			 SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			 
			list = projectHeaderRpo.getEmpListByCompanyIdAndEmpId(companyId,empId,sf.format(now));
			 
		 
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getProjectAllListByCompanyId" }, method = RequestMethod.POST)
	public @ResponseBody List<GetProjectHeader> getProjectAllListByCompanyId(@RequestParam("companyId") int companyId) {
		System.out.println(companyId);
		List<GetProjectHeader> list = new ArrayList<GetProjectHeader>();
		try {

			list = getProjectHeaderRepo.getProListByCompanyId(companyId);
			System.out.println(list.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	
	@RequestMapping(value = { "/getProjectAllListByCompanyIdForManager" }, method = RequestMethod.POST)
	public @ResponseBody List<GetProjectHeader> getProjectAllListByCompanyIdForManager(@RequestParam("companyId") int companyId,@RequestParam("mangId") int mangId) {
		System.out.println(companyId);
		List<GetProjectHeader> list = new ArrayList<GetProjectHeader>();
		try {

			list = getProjectHeaderRepo.getProListByCompanyIdForManager(companyId,mangId);
			System.out.println(list.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/getFullTimeFreeEmpList" }, method = RequestMethod.POST)
	public @ResponseBody List<EmployeeInfo> getFullTimeFreeEmpList(@RequestParam("fromDate") String fromDate,@RequestParam("toDate") String toDate,
			@RequestParam("catId") int catId,@RequestParam("locationIds") List<Integer> locationIds,@RequestParam("companyId") int companyId,
			@RequestParam("worktime") int worktime) {

		List<EmployeeInfo> list = new ArrayList<EmployeeInfo>();
		List<EmployeeInfo> fulltime = new ArrayList<EmployeeInfo>();
		try {

			if(worktime==1) {
				
				if(locationIds.get(0)==0) {
					list = employeeInfoRepository.getPartialTimeFreeEmpList(fromDate, toDate,catId,companyId);
				}else {
					list = employeeInfoRepository.getPartialTimeFreeEmpList(fromDate, toDate,catId,locationIds);
				}
				
				for(int i=0; i < list.size() ; i++) {
					list.get(i).setExInt1(1);
				}
				
				if(locationIds.get(0)==0) {
					fulltime = employeeInfoRepository.getFullTimeFreeEmpList(fromDate, toDate,catId,companyId);
				}else {
					fulltime = employeeInfoRepository.getFullTimeFreeEmpList(fromDate, toDate,catId,locationIds);
				}
				
				for(int i=0; i < fulltime.size() ; i++) {
					fulltime.get(i).setExInt1(2);
					list.add(fulltime.get(i));
				}
				
			}else {
				
				if(locationIds.get(0)==0) {
					list = employeeInfoRepository.getFullTimeFreeEmpList(fromDate, toDate,catId,companyId);
				}else {
					list = employeeInfoRepository.getFullTimeFreeEmpList(fromDate, toDate,catId,locationIds);
				}
				
				for(int i=0; i < list.size() ; i++) {
					list.get(i).setExInt1(2); 
				}
			}
			
			System.out.println(list);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/getAllocatedEmployeeList" }, method = RequestMethod.POST)
	public @ResponseBody List<AllocatedEmpList> getAllocatedEmployeeList( @RequestParam("projectId") int projectId) {

		List<AllocatedEmpList> list = new ArrayList<AllocatedEmpList>();
		try {

			 
				list = allocatedEmpListRepository.getAllocatedEmployeeList(projectId);
			 
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/getEmployeeAllocatedHistory" }, method = RequestMethod.POST)
	public @ResponseBody List<AllocatedEmpList> getEmployeeAllocatedHistory( @RequestParam("fromDate") String fromDate,@RequestParam("toDate") String toDate,
			@RequestParam("empId") int empId) {

		List<AllocatedEmpList> list = new ArrayList<AllocatedEmpList>();
		try {

			 
				list = allocatedEmpListRepository.getEmployeeAllocatedHistory(fromDate,toDate,empId);
			 
				 
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/projectAllotmentSave" }, method = RequestMethod.POST)
	public @ResponseBody Info projectAllotmentSave(@RequestBody List<ProjectAllotment> list) {

		Info info = new Info();
		try {

			 
				list = projectAllotmentRepository.saveAll(list);
			if(list==null) {
				info.setError(true);
				info.setMsg("failed");
			}else {
				info.setError(false);
				info.setMsg("save successfully");
			}
			

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}

		return info;

	}
	
	@RequestMapping(value = { "/getProjectDetailById" }, method = RequestMethod.POST)
	public @ResponseBody GetProjectHeader getProjectDetailById(@RequestParam("projectId") int projectId) {
	 
		GetProjectHeader detail = new GetProjectHeader();
		try {

			detail = getProjectHeaderRepo.getProjectDetailByProjectId(projectId);
			  
		} catch (Exception e) {

			e.printStackTrace();
		}

		return detail;

	}

}
