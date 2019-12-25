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

import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.model.advance.GetAdvance;
import com.ats.hrmgt.model.loan.GetLoan;
import com.ats.hrmgt.model.loan.LoanCalculation;
import com.ats.hrmgt.model.loan.LoanMain;
import com.ats.hrmgt.repo.loan.GetLoanRepo;
import com.ats.hrmgt.repo.loan.LoanMainRepo;
import com.ats.hrmgt.repository.SettingRepo;

@RestController
public class LoanApiController {

	@Autowired
	LoanMainRepo loanMainRepo;
	
	
	@RequestMapping(value = { "/saveEmpLoan" }, method = RequestMethod.POST)
	public @ResponseBody LoanMain saveEmpLoan(@RequestBody LoanMain leaveType) {

		LoanMain save = new LoanMain();
		try {

			save = loanMainRepo.saveAndFlush(leaveType);
			if (save == null) {

				save = new LoanMain();

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getEmpLoanHistory" }, method = RequestMethod.POST)
	public @ResponseBody LoanMain getEmpLoanHistory(@RequestParam("empId") int empId) {

		LoanMain list = new LoanMain();
		try {

			list = loanMainRepo.getEmpLoanDetail(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getLastApplicationNumber" }, method = RequestMethod.GET)
	public @ResponseBody LoanMain getLastApplicationNumber() {

		LoanMain list = new LoanMain();
		try {

			list = loanMainRepo.getLastApplicationNo();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	SettingRepo settingRepo;

	@RequestMapping(value = { "/calLoan" }, method = RequestMethod.POST)
	public @ResponseBody LoanCalculation calLoan(@RequestParam("roi") String roi, @RequestParam("tenure") String tenure,
			@RequestParam("loanAmt") String loanAmt) {

		LoanCalculation list = new LoanCalculation();
		try {

			Setting setting = new Setting();
			setting = settingRepo.findByKey("interest_cal_type");
			int type = Integer.parseInt(setting.getValue());
			float principle = Float.parseFloat(loanAmt);
			float rate = Float.parseFloat(roi);
			float period = Float.parseFloat(tenure);
			float si = 0;
			float emi = 0;
			if (type == 1) {
				si = (principle * (period/12) * rate) / 100;
				si = si + principle;
				//System.err.println("rounded   "+emi);
				emi =Math.round (si / period);
				//System.err.println("rounded off "+emi);
				si=emi*period;
				
			} else {
				si = 0;
				emi = 0;
			}

			list.setEmiAmt(emi);
			list.setRepayAmt(si);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	
	@Autowired
	GetLoanRepo getLoanRepo;
	@RequestMapping(value = { "/getLoanHistoryEmpWise" }, method = RequestMethod.POST)
	public @ResponseBody List<GetLoan> getLoanHistoryEmpWise(@RequestParam("status") String status,
			@RequestParam("calYrId") String calYrId, @RequestParam("companyId") int companyId ) {

		List<GetLoan> list = new ArrayList<GetLoan>();
		try {

		 
				list = getLoanRepo.getLoanHistoryEmpwise(companyId, status, calYrId) ;
 
 
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/getLoanHistoryEmpWiseForCompany" }, method = RequestMethod.POST)
	public @ResponseBody List<GetLoan> getLoanHistoryEmpWise(@RequestParam("companyId") int companyId ) {

		List<GetLoan> list = new ArrayList<GetLoan>();
		try {

		 
				list = getLoanRepo.getLoanHistoryEmpwiseComp(companyId) ;
 
 
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/getLoanHistoryEmpWiseSpec" }, method = RequestMethod.POST)
	public @ResponseBody GetLoan getLoanHistoryEmpWise(@RequestParam("status") String status,
			@RequestParam("calYrId") String calYrId, @RequestParam("companyId") int companyId,@RequestParam("empId") int empId ) {

		GetLoan list = new GetLoan();
		try {

		 
				list = getLoanRepo.getLoanHistoryEmpwiseSpec(companyId, status, calYrId, empId);
 
 
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	
	@RequestMapping(value = { "/getLoanHistoryEmpWiseSpecForCompany" }, method = RequestMethod.POST)
	public @ResponseBody GetLoan getLoanHistoryEmpWiseSpecForCompany(@RequestParam("companyId") int companyId,@RequestParam("empId") int empId ) {

		GetLoan list = new GetLoan();
		try {

		 
				list = getLoanRepo.getLoanHistoryEmpwiseSpec(companyId, empId);
 
 
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	 
	
	
	
	@RequestMapping(value = { "/getLoanHistoryEmpWiseDetail" }, method = RequestMethod.POST)
	public @ResponseBody List<LoanMain> getLoanHistoryEmpWiseDetail(@RequestParam("status") String status,
			@RequestParam("calYrId") String calYrId, @RequestParam("companyId") int companyId,@RequestParam("empId") int empId) {

		List<LoanMain> list = new ArrayList<LoanMain>();
		try {

		 
				list = loanMainRepo.getLoanHistoryDetail(companyId, status, calYrId, empId);
 
 
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/getLoanHistoryEmpWiseDetailComp" }, method = RequestMethod.POST)
	public @ResponseBody List<LoanMain> getLoanHistoryEmpWiseDetailComp(
		 @RequestParam("companyId") int companyId,@RequestParam("empId") int empId) {

		List<LoanMain> list = new ArrayList<LoanMain>();
		try {

		 
				list = loanMainRepo.getLoanHistoryDetail(companyId, empId);
 
 
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
}
