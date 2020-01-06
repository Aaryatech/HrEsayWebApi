package com.ats.hrmgt.controller;

import java.text.SimpleDateFormat;
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

import com.ats.hrmgt.model.Allowances;
import com.ats.hrmgt.model.EmpSalAllowance;
import com.ats.hrmgt.model.GetEmployeeDetails;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.SalaryCalc;
import com.ats.hrmgt.model.SummaryDailyAttendance;
import com.ats.hrmgt.model.advance.Advance;
import com.ats.hrmgt.model.bonus.BonusCalc;
import com.ats.hrmgt.model.bonus.BonusMaster;
import com.ats.hrmgt.repo.bonus.BonusCalcRepo;
import com.ats.hrmgt.repo.bonus.BonusMasterRepo;
import com.ats.hrmgt.repository.AllowancesRepo;
import com.ats.hrmgt.repository.EmpSalAllowanceRepo;
import com.ats.hrmgt.repository.GetEmployeeDetailsRepo;
import com.ats.hrmgt.repository.SalaryCalcRepo;
import com.ats.hrmgt.repository.SummaryDailyAttendanceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
public class BonusApiController {

	@Autowired
	BonusMasterRepo bonusMasterRepo;

	@RequestMapping(value = { "/saveBonus" }, method = RequestMethod.POST)
	public @ResponseBody BonusMaster saveBonus(@RequestBody BonusMaster bonusMaster) {

		BonusMaster save = new BonusMaster();
		try {

			save = bonusMasterRepo.saveAndFlush(bonusMaster);
			if (save == null) {

				save = new BonusMaster();
				save.setError(true);

			} else {
				save.setError(false);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getAllBonusList" }, method = RequestMethod.GET)
	public @ResponseBody List<BonusMaster> getAllBonusList() {

		List<BonusMaster> list = new ArrayList<BonusMaster>();
		try {

			list = bonusMasterRepo.findByDelStatus(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getBonusById" }, method = RequestMethod.POST)
	public @ResponseBody BonusMaster getBonusById(@RequestParam("bonusId") int bonusId) {

		BonusMaster bous = new BonusMaster();
		try {

			bous = bonusMasterRepo.findByBonusIdAndDelStatus(bonusId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return bous;

	}

	@RequestMapping(value = { "/deleteBonus" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteBonus(@RequestParam("bonusId") int bonusId) {

		Info info = new Info();

		try {

			int delete = bonusMasterRepo.deleteBonus(bonusId);

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

	@RequestMapping(value = { "/checkBonusTitle" }, method = RequestMethod.POST)
	public @ResponseBody Info checkBonusTitle(@RequestParam String bonusTitle) {

		Info info = new Info();
		List<BonusMaster> emp = new ArrayList<BonusMaster>();
		try {

			emp = bonusMasterRepo.findByFyTitleAndDelStatus(bonusTitle, 1);

			if (emp.size() > 0) {
				info.setError(true);
			} else {
				info.setError(false);

			}

		} catch (Exception e) {
			System.err.println("Exce in checkEmployeeEmail  " + e.getMessage());
		}

		return info;

	}

	@Autowired
	BonusCalcRepo bonusCalcRepo;

	@RequestMapping(value = { "/getAllBonusCalcList" }, method = RequestMethod.GET)
	public @ResponseBody List<BonusCalc> getAllBonusCalcList() {

		List<BonusCalc> list = new ArrayList<BonusCalc>();
		try {

			list = bonusCalcRepo.findByDelStatus(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	// Bonus Main WS

	@Autowired
	SummaryDailyAttendanceRepository summaryDailyAttendanceRepository;
	@Autowired
	AllowancesRepo AalowancesRepo;

	@Autowired
	EmpSalAllowanceRepo empAllowanceSalCalRepo;
	@Autowired
	GetEmployeeDetailsRepo getEmployeeDetailsRepo;
	@Autowired
	SalaryCalcRepo salaryCalcRepo;

	@RequestMapping(value = { "/empBonusSave" }, method = RequestMethod.POST)
	public @ResponseBody Info taskAssignmentUpdate(@RequestParam("empIdList") List<Integer> empIdList,
			@RequestParam("bonusId") int bonusId, @RequestParam("companyId") int companyId,
			@RequestParam("userId") int userId) {

		Info info = new Info();
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Allowances allw = AalowancesRepo.findByShortNameAndDelStatus("DA", 1);
		try {
			for (int i = 0; i < empIdList.size(); i++) {
				int empId = empIdList.get(i);

				SummaryDailyAttendance summary = summaryDailyAttendanceRepository.findByCompanyIdAndEmpId(companyId,
						empId);
				float payDays = summary.getPayableDays();
				float ncpDays = summary.getNcpDays();

				GetEmployeeDetails list = getEmployeeDetailsRepo.getEmpDetailList(empId);

				BonusMaster bonus = new BonusMaster();
				bonus = bonusMasterRepo.findByBonusIdAndDelStatus(bonusId, empId);
				double bonusPrcnt = bonus.getBonusPercentage();
				double basic_calc = 0;

				SalaryCalc salCal = salaryCalcRepo.findByEmpId(empId);
				if (salCal != null) {
					basic_calc = salCal.getBasicCal(); // get it from
				} else {
					basic_calc = 0;
 
				}

				double bonusAmt = 0;
				try {
					EmpSalAllowance allCalc = empAllowanceSalCalRepo.findByAllowanceIdAndEmpId(allw.getAllowanceId(),
							empId);
					bonusAmt = ((allCalc.getAllowanceValue() + basic_calc) * bonusPrcnt) / 100;
				} catch (Exception e) {

					bonusAmt = ((0 + basic_calc) * bonusPrcnt) / 100;
				}

				BonusCalc calcSave = new BonusCalc();

				calcSave.setBonusId(bonusId);

				calcSave.setCompanyEmpCode(list.getEmpCode());
				calcSave.setCompanyId(companyId);
				calcSave.setCurrAge(0);
				calcSave.setCurrDesignation(list.getEmpDesgn());
				calcSave.setEmpId(empId);
				calcSave.setEmpName(list.getFirstName().concat("").concat(list.getSurname()));
				calcSave.setNetBonusAmt(bonusAmt);
				calcSave.setDelStatus(1);
				calcSave.setExVar2("NA");
				calcSave.setExInt1(0);
				calcSave.setExInt2(0);
				calcSave.setExVar1("NA");
				calcSave.setLoginIdBonus(userId);
				calcSave.setLoginTimeBonus(sf.format(date));
				calcSave.setBonusApplicable("No");
				calcSave.setBonusCalcId(0);
				calcSave.setDedBonusAdvAmt(0);
				calcSave.setDedBonusLossAmt(0);
				calcSave.setDedBonusPujaAmt(0);
				calcSave.setDedExgretiaAmt(0);
				calcSave.setDedReason("");
				calcSave.setExgretiaApplicable("No");
				calcSave.setExgretiaDetails("");
				calcSave.setfYear(2020);
				calcSave.setGrossBonusAmt(0);
				calcSave.setIsBonussheetFinalized("0");
				calcSave.setIsExgretiaFinalized("0");
				calcSave.setLoginIdExgretia(0);
				calcSave.setLoginTimeExgretia("0000-00-00 00:00:00");
				calcSave.setNetExgretiaAmt(0);
				calcSave.setPaidBonusAmt(0);
				// calcSave.setPaidBonusDate(0000-00-00);
				calcSave.setPaidExgretiaAmt(0);
				// calcSave.setPaidExgretiaDate(paidExgretiaDate);
				calcSave.setRecStatus(0);
				calcSave.setTotalBonusWages(0);
				calcSave.setTotalExgretiaDays(0);
				calcSave.setTotalExgretiaWages("0");
				calcSave.setTotalBonusDays(0);

				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String json = ow.writeValueAsString(calcSave);

				calcSave.setBonusDetails(json);
				BonusCalc calcSave1 = bonusCalcRepo.save(calcSave);
			}

			int res = 0;

			if (res > 0) {
				info.setError(false);
				info.setMsg("success");

			} else {
				info.setError(true);
				info.setMsg("failed");

			}
		} catch (Exception e) {

			System.err.println("Exce in deleteService  " + e.getMessage());
			e.printStackTrace();
			info.setError(true);
			info.setMsg("excep");
		}

		return info;

	}

}
