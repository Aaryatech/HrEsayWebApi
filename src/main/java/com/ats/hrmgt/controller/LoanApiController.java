package com.ats.hrmgt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.model.loan.LoanCalculation;
import com.ats.hrmgt.model.loan.LoanMain;
import com.ats.hrmgt.repo.loan.LoanMainRepo;
import com.ats.hrmgt.repository.SettingRepo;

@RestController
public class LoanApiController {

	@Autowired
	LoanMainRepo loanMainRepo;

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
			float principle=Float.parseFloat(loanAmt);
			float rate=Float.parseFloat(roi);
			float period=Float.parseFloat(tenure);
			float si=0;
			float emi=0;
			if(type ==1 ) {
				si=(principle*period*rate)*100;
				emi=si/period;
			}else {
				si=0;
				emi=0;
			}
			
			list.setEmiAmt(emi);
			list.setRepayAmt(si);
			
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

}
