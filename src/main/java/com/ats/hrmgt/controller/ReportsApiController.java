package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.DailyAttendance;
import com.ats.hrmgt.model.report.PendingLoanReport;
import com.ats.hrmgt.repo.report.PendingLoanRepo;

@RestController
public class ReportsApiController {

	@Autowired PendingLoanRepo pendLoanRepo;
	
	@RequestMapping(value = { "/getEmpPendingLoanReport" }, method = RequestMethod.POST)
	public @ResponseBody List<PendingLoanReport> getEmpPendingLoanReport(@RequestParam("companyId") int companyId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

		List<PendingLoanReport> list = new ArrayList<PendingLoanReport>();

		try {

			list = pendLoanRepo.getEmpPendingLoanDetails(companyId, fromDate, toDate);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
}
