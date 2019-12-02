package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.ActivityRevenueReport;
import com.ats.hrmgt.model.EmployeeOnBenchReport;
import com.ats.hrmgt.model.EmployeeProjectWise;
import com.ats.hrmgt.model.ProjectLocationWise; 
import com.ats.hrmgt.repository.ActivityRevenueReportRepository;
import com.ats.hrmgt.repository.EmployeeOnBenchRepository;
import com.ats.hrmgt.repository.EmployeeProjectWiseRepository;
import com.ats.hrmgt.repository.ProjectLocationWiseRepository;

@RestController
public class ReportRestController {

	
	@Autowired
	EmployeeOnBenchRepository employeeOnBenchRepository;
	
	@Autowired
	ActivityRevenueReportRepository activityRevenueReportRepository;
	
	@Autowired
	EmployeeProjectWiseRepository employeeProjectWiseRepository;
	
	@Autowired
	ProjectLocationWiseRepository projectLocationWiseRepository;
	
	@RequestMapping(value = { "/getOnBenchReport" }, method = RequestMethod.POST)
	public @ResponseBody List<EmployeeOnBenchReport> getOnBenchReport(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("locIds") List<Integer> locIds) {

		List<EmployeeOnBenchReport> list = new ArrayList<>();
		try {

			list = employeeOnBenchRepository.getOnBenchReport(fromDate,toDate,locIds);
			 

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/revenueReportProjectCategoryWise" }, method = RequestMethod.POST)
	public @ResponseBody List<ActivityRevenueReport> revenueReportProjectCategoryWise(@RequestParam("compId") int compId) {

		List<ActivityRevenueReport> list = new ArrayList<>();
		try {

			list = activityRevenueReportRepository.revenueReportProjectCategoryWise(compId);
			 

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/employeeProjectWiseReport" }, method = RequestMethod.POST)
	public @ResponseBody List<EmployeeProjectWise> employeeProjectWiseReport(@RequestParam("compId") int compId) {

		List<EmployeeProjectWise> list = new ArrayList<>();
		try {

			list = employeeProjectWiseRepository.employeeProjectWiseReport(compId);
			 

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/projectLocationwiseReport" }, method = RequestMethod.POST)
	public @ResponseBody List<ProjectLocationWise> projectLocationwiseReport(@RequestParam("compId") int compId) {

		List<ProjectLocationWise> list = new ArrayList<>();
		try {

			list = projectLocationWiseRepository.projectLocationwiseReport(compId);
			 

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	 
}
