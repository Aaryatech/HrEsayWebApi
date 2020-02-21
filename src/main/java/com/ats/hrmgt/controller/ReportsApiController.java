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
import com.ats.hrmgt.model.report.EmpLateMarkDetails;
import com.ats.hrmgt.model.report.EmpOtReg;
import com.ats.hrmgt.model.report.LoanStatementDetailsReport;
import com.ats.hrmgt.model.report.PendingLoanReport;
import com.ats.hrmgt.repo.report.EmpLateMarkDetailsRepo;
import com.ats.hrmgt.repo.report.EmpOtRegRepo;
import com.ats.hrmgt.repo.report.LoanStatementRepo;
import com.ats.hrmgt.repo.report.PendingLoanRepo;

@RestController
public class ReportsApiController {

	@Autowired PendingLoanRepo pendLoanRepo;
	
	@Autowired LoanStatementRepo loanStatRepo;
	
	@Autowired EmpOtRegRepo otRepo;
	
	@Autowired EmpLateMarkDetailsRepo empLateRepo;
	
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
	
	@RequestMapping(value = { "/getLoanStatemnetReport" }, method = RequestMethod.POST)
	public @ResponseBody List<LoanStatementDetailsReport> getLoanStatemnetReport(@RequestParam("companyId") int companyId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

		List<LoanStatementDetailsReport> list = new ArrayList<LoanStatementDetailsReport>();

		try {

			list = loanStatRepo.getEmpLoanStateDetails(companyId, fromDate, toDate);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}	
	
	@RequestMapping(value = { "/getEmpOtRegDetails" }, method = RequestMethod.POST)
	public @ResponseBody List<EmpOtReg> getEmpOtRegDetails(@RequestParam("companyId") int companyId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

		List<EmpOtReg> list = new ArrayList<EmpOtReg>();

		try {
			String frmDate = fromDate;
			//System.out.println("From Date Before-----------"+frmDate);
			String[] parts = frmDate.split("-");
			String date = parts[0];
			String month = parts[1];
			String year = parts[2];
			//System.out.println("From Date After-----------"+date+"/"+month+"/"+year);
			
			
			String tDate = toDate;
			//System.out.println("To Date Before-----------"+toDate);
			String[] toparts = tDate.split("-");
			String todate = toparts[0];
			String tomonth = toparts[1];
			String toyear = toparts[2];
			
			list = otRepo.getEmpOtDetails(companyId, month, year, tomonth ,toyear);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}	
	
	@RequestMapping(value = { "/getEmpLateMarkDetails" }, method = RequestMethod.POST)
	public @ResponseBody List<EmpLateMarkDetails> getEmpLateMarkDetails(@RequestParam("companyId") int companyId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

		List<EmpLateMarkDetails> list = new ArrayList<EmpLateMarkDetails>();

		try {
			String frmDate = fromDate;
			//System.out.println("From Date Before-----------"+frmDate);
			String[] parts = frmDate.split("-");
			String date = parts[0];
			String month = parts[1];
			String year = parts[2];
			//System.out.println("From Date After-----------"+date+"/"+month+"/"+year);
			
			
			String tDate = toDate;
		//	System.out.println("To Date Before-----------"+toDate);
			String[] toparts = tDate.split("-");
			String todate = toparts[0];
			String tomonth = toparts[1];
			String toyear = toparts[2];
		//	System.out.println("To Date After-----------"+todate+"/"+tomonth+"/"+toyear);

			list = empLateRepo.getEmpLateMarkDetailReport(companyId, month, year, tomonth ,toyear);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}	
	
}
