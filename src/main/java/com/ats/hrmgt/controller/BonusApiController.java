package com.ats.hrmgt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.model.SummaryDailyAttendance;
import com.ats.hrmgt.model.advance.Advance;
import com.ats.hrmgt.model.bonus.BonusCalc;
import com.ats.hrmgt.model.bonus.BonusDates;
import com.ats.hrmgt.model.bonus.BonusMaster;
import com.ats.hrmgt.model.bonus.BonusParam;
import com.ats.hrmgt.repo.bonus.BonusCalcRepo;
import com.ats.hrmgt.repo.bonus.BonusDatesRepo;
import com.ats.hrmgt.repo.bonus.BonusMasterRepo;
import com.ats.hrmgt.repo.bonus.BonusParamRepo;
import com.ats.hrmgt.repository.AllowancesRepo;
import com.ats.hrmgt.repository.EmpSalAllowanceRepo;
import com.ats.hrmgt.repository.GetEmployeeDetailsRepo;
import com.ats.hrmgt.repository.SalaryCalcRepo;
import com.ats.hrmgt.repository.SettingRepo;
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
	@Autowired
	SettingRepo settingRepo;
	@Autowired
	BonusDatesRepo bonusDatesRepo;

	@Autowired
	BonusParamRepo bonusParamRepo;

	@RequestMapping(value = { "/empBonusSave" }, method = RequestMethod.POST)
	public @ResponseBody Info taskAssignmentUpdate(@RequestParam("empIdList") List<Integer> empIdList,
			@RequestParam("bonusId") int bonusId, @RequestParam("companyId") int companyId,
			@RequestParam("userId") int userId) {

		// Note : bonus_sealing_limit_amount_per_month & bonus_app_below_amount remaning
		Info info = new Info();
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// To get bonus details
		BonusMaster bonus = new BonusMaster();
		bonus = bonusMasterRepo.findByBonusIdAndDelStatus(bonusId, 1);
		double bonusPrcnt = bonus.getBonusPercentage();

		BonusDates datesDet = bonusDatesRepo.getDates(bonusId);

		// To get bonus formula
		List<Setting> settingList = new ArrayList<Setting>();
		String bonusFormula = new String();
		try {
			settingList = settingRepo.findByGroup("BONUS");
			if (settingList != null) {
				for (int i = 0; i < settingList.size(); i++) {

					if (settingList.get(i).getKey().equals("bonus_formula")) {
						bonusFormula = settingList.get(i).getValue();
						// System.err.println("bonus formula**" + bonusFormula);
						break;
					} else {
						bonusFormula = "";
					}

				}
			}

		} catch (Exception e) {

			info.setError(true);
			info.setMsg("failed");

		}
		String[] forList = bonusFormula.split("\\+");
		List<String> formulaList = new ArrayList<String>(Arrays.asList(forList));
		// System.err.println("formulaList before**" + formulaList.toString());
		for (int j = 0; j < formulaList.size(); j++) {

			if ((formulaList.get(j).trim()).equals("basic_cal")) {
				formulaList.remove(0);
				break;
			}

		}
		// System.err.println("formulaList after**" + formulaList.toString());
		// System.err.println("empIdList **" + empIdList.toString());
		try {
			for (int i = 0; i < empIdList.size(); i++) {
				int empId = empIdList.get(i);

				BonusParam salDays = bonusParamRepo.getDays(empId, datesDet.getMonthFrom(), datesDet.getMonthTo(),
						datesDet.getYearFrom(), datesDet.getYearTo());
				double bonusAmt = 0;
				double grossBonus = 0;
				String isApplicable=null;

				if (salDays.getTotalBasicCal() <= bonus.getMinDays()) {
					isApplicable="Yes";

					System.err.println("Applicable");
					double basic_calc = 0;

					// to get total from formula
					double formTot = 0.0;
					List<Integer> allIdList = new ArrayList<Integer>();
					for (int j = 0; j < formulaList.size(); j++) {

						//System.err.println("formulaList for  **" + formulaList.get(j));
						Allowances ac = new Allowances();
						try {
							ac = AalowancesRepo.findByShortNameAndDelStatus(formulaList.get(j).trim(), 1);
							allIdList.add(ac.getAllowanceId());
						} catch (Exception e) {

						}
					}
					BonusParam salCal = bonusParamRepo.getBonusParameters(empId, datesDet.getMonthFrom(),
							datesDet.getMonthTo(), datesDet.getYearFrom(), datesDet.getYearTo(), allIdList);

					formTot = salCal.getTotalAllowance() + salCal.getTotalBasicCal();

					try {

						bonusAmt = (formTot * bonusPrcnt) / 100;
						grossBonus = formTot + bonusAmt;

					} catch (Exception e) {
						grossBonus = formTot;
						bonusAmt = (0 * bonusPrcnt) / 100;
					}

				} else {
					isApplicable="No";
					System.err.println("not Applicable");
				}

				// System.err.println("param **" + salCal.toString());

				GetEmployeeDetails list = getEmployeeDetailsRepo.getEmpDetailList(empId);

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
				calcSave.setBonusApplicable(isApplicable);
				calcSave.setGrossBonusAmt(grossBonus);
 
 				calcSave.setDedBonusAdvAmt(0);
				calcSave.setDedBonusLossAmt(0);
				calcSave.setDedBonusPujaAmt(0);
				calcSave.setDedExgretiaAmt(0);
				calcSave.setDedReason("");
				calcSave.setExgretiaApplicable("No");
				calcSave.setExgretiaDetails("");
				calcSave.setfYear(2020);
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
