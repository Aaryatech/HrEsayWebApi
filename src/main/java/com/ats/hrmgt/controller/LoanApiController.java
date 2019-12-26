package com.ats.hrmgt.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.model.advance.GetAdvance;
import com.ats.hrmgt.model.loan.GetLoan;
import com.ats.hrmgt.model.loan.LoanCalculation;
import com.ats.hrmgt.model.loan.LoanDetails;
import com.ats.hrmgt.model.loan.LoanMain;
import com.ats.hrmgt.repo.loan.GetLoanRepo;
import com.ats.hrmgt.repo.loan.LoanDetailsRepo;
import com.ats.hrmgt.repo.loan.LoanMainRepo;
import com.ats.hrmgt.repository.SettingRepo;

@RestController
public class LoanApiController {

	@Autowired
	LoanMainRepo loanMainRepo;

	@Autowired
	LoanDetailsRepo loanDetailsRepo;

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

	@RequestMapping(value = { "/saveLoanDetail" }, method = RequestMethod.POST)
	public @ResponseBody LoanDetails saveLoanDetail(@RequestBody LoanDetails leaveType) {

		LoanDetails save = new LoanDetails();
		try {

			save = loanDetailsRepo.saveAndFlush(leaveType);
			if (save == null) {

				save = new LoanDetails();

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/updateLoanMainAfterForeclose" }, method = RequestMethod.POST)
	public @ResponseBody Info updateLoanMainAfterForeclose(@RequestParam("dateTimeUpdate") String dateTimeUpdate,
			@RequestParam("userId") int userId, @RequestParam("loanId") int loanId,
			@RequestParam("closeDate") String closeDate, @RequestParam("currentTotpaid") String currentTotpaid,
			@RequestParam("currentOut") String currentOut, @RequestParam("repayAmt") String repayAmt) {

		Info info = new Info();
		String status = null;
		int currentOutstanding1 = Integer.parseInt(currentOut);
		int repayAmt1 = Integer.parseInt(repayAmt);
		if ((repayAmt1 - currentOutstanding1) == 0) {
			status = "Paid";
		} else {
			status = "Active";
		}

		try {

			int delete = loanMainRepo.forecloseLoan(loanId, userId, closeDate, currentTotpaid, currentOut,
					dateTimeUpdate, status);

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
				si = (principle * (period / 12) * rate) / 100;
				si = si + principle;
				// System.err.println("rounded "+emi);
				emi = Math.round(si / period);
				// System.err.println("rounded off "+emi);
				si = emi * period;

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
			@RequestParam("calYrId") String calYrId, @RequestParam("companyId") int companyId) {

		List<GetLoan> list = new ArrayList<GetLoan>();
		try {

			list = getLoanRepo.getLoanHistoryEmpwise(companyId, status, calYrId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getLoanHistoryEmpWiseForCompany" }, method = RequestMethod.POST)
	public @ResponseBody List<GetLoan> getLoanHistoryEmpWise(@RequestParam("companyId") int companyId) {

		List<GetLoan> list = new ArrayList<GetLoan>();
		try {

			list = getLoanRepo.getLoanHistoryEmpwiseComp(companyId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getLoanHistoryEmpWiseSpec" }, method = RequestMethod.POST)
	public @ResponseBody GetLoan getLoanHistoryEmpWise(@RequestParam("status") String status,
			@RequestParam("calYrId") String calYrId, @RequestParam("companyId") int companyId,
			@RequestParam("empId") int empId) {

		GetLoan list = new GetLoan();
		try {

			list = getLoanRepo.getLoanHistoryEmpwiseSpec(companyId, status, calYrId, empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getLoanHistoryEmpWiseSpecForCompany" }, method = RequestMethod.POST)
	public @ResponseBody GetLoan getLoanHistoryEmpWiseSpecForCompany(@RequestParam("companyId") int companyId,
			@RequestParam("empId") int empId) {

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
			@RequestParam("calYrId") String calYrId, @RequestParam("companyId") int companyId,
			@RequestParam("empId") int empId) {

		List<LoanMain> list = new ArrayList<LoanMain>();
		try {

			list = loanMainRepo.getLoanHistoryDetail(companyId, status, calYrId, empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getLoanHistoryEmpWiseDetailComp" }, method = RequestMethod.POST)
	public @ResponseBody List<LoanMain> getLoanHistoryEmpWiseDetailComp(@RequestParam("companyId") int companyId,
			@RequestParam("empId") int empId) {

		List<LoanMain> list = new ArrayList<LoanMain>();
		try {

			list = loanMainRepo.getLoanHistoryDetail(companyId, empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getLoanById" }, method = RequestMethod.POST)
	public @ResponseBody LoanMain getLoanHistoryEmpWiseDetailComp(@RequestParam("id") int id) {

		LoanMain list = new LoanMain();
		try {

			list = loanMainRepo.findById(id);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/updateSkipLoan" }, method = RequestMethod.POST)
	public @ResponseBody Info updateSkipLoan(@RequestParam("dateTimeUpdate") String dateTimeUpdate,
			@RequestParam("userId") int userId, @RequestParam("skipStr") String skipStr,
			@RequestParam("count") int count, @RequestParam("advId") int advId) {

		Info info = new Info();

		try {

			int delete = loanMainRepo.skipLoan(advId, userId, count, skipStr, dateTimeUpdate);

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

	@RequestMapping(value = { "/calDatePartialPay" }, method = RequestMethod.POST)
	public @ResponseBody Info calDatePartialPay(@RequestParam("currentOutstanding") String currentOutstanding,
			@RequestParam("loanEmi") String loanEmi, @RequestParam("partialAmt") String partialAmt,
			@RequestParam("endDate") String endDate) {

		Info info = new Info();
		LocalDate localDate = LocalDate.parse(endDate);

		System.out.println("bef" + localDate);
		LocalDate oneMonthLater = localDate.plusMonths(3);
		System.out.println("aft" + oneMonthLater);

		try {
			int currentOutstanding1 = Integer.parseInt(currentOutstanding);
			int loanEmi1 = Integer.parseInt(loanEmi);
			int partialAmt1 = Integer.parseInt(partialAmt);

			if (partialAmt1 <= currentOutstanding1) {

				int n = currentOutstanding1 - partialAmt1;
				int x = n / loanEmi1;
				// Date firstDay = new GregorianCalendar(year, month + x, 1).getTime();

				// default, ISO_LOCAL_DATE

				info.setError(false);
				info.setMsg("success");
			} else {
				info.setError(true);
				info.setMsg("Partial Pay Amount Should be Less than or Equal to Currentoutstanding Amount");
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("failed");
		}

		return info;

	}
}
