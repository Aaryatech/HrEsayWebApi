package com.ats.hrmgt.controller;

import java.text.SimpleDateFormat;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.common.DateConvertor;
import com.ats.hrmgt.model.EmpJsonData;
import com.ats.hrmgt.model.GetEmployeeDetails;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.model.bonus.BonusApplicable;
import com.ats.hrmgt.model.bonus.BonusCalc;
import com.ats.hrmgt.model.bonus.BonusMaster;
import com.ats.hrmgt.repo.bonus.BonusApplicableRepo;
import com.ats.hrmgt.repo.bonus.BonusCalcRepo;
import com.ats.hrmgt.repo.bonus.BonusMasterRepo;
import com.ats.hrmgt.repository.GetEmployeeDetailsRepo;
import com.ats.hrmgt.repository.SettingRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
  @RestController
public class ExgratiaApiController {

	@Autowired
	GetEmployeeDetailsRepo getEmployeeDetailsRepo;

	@Autowired
	SettingRepo settingRepo;

	@Autowired
	BonusMasterRepo bonusMasterRepo;
	@Autowired
	BonusApplicableRepo bonusApplicableRepo;

	@Autowired
	BonusCalcRepo bonusCalcRepo;

	@RequestMapping(value = { "/getAllEmployeeDetailForBonus" }, method = RequestMethod.POST)
	public List<GetEmployeeDetails> getAllEmployeeDetailForBonusUpdate(@RequestParam("bonusId") int bonusId,
			@RequestParam("flag") int flag) {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {

			if (flag == 1) {
				list = getEmployeeDetailsRepo.getEmpDetailListByBonusId(bonusId);

			} else {
				list = getEmployeeDetailsRepo.getEmpDetailListByBonusIdAssignedBonus(bonusId);
			}
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/empExgratiaUpdateToBonusSave" }, method = RequestMethod.POST)
	public @ResponseBody Info empBonusAppSaveOrUpdate(@RequestParam("empIdList") List<Integer> empIdList,
			@RequestParam("bonusId") int bonusId, @RequestParam("companyId") int companyId,
			@RequestParam("userId") int userId) {

		Info info = new Info();
		int flag = 0;
		Date date = new Date();
		SimpleDateFormat yyDtTm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {

			// insert
			double ded_exgretia_amt_percentage = 0.0;
			double exgretia_percentage = 0.0;
			String bonus_formula = null;

			Setting setting = new Setting();

			try {
				setting = settingRepo.findByKey("ded_exgretia_amt_percentage");
				ded_exgretia_amt_percentage = Double.parseDouble(setting.getValue());
			} catch (Exception e) {

				ded_exgretia_amt_percentage = 0;

			}

			try {

				setting = settingRepo.findByKey("bonus_formula");
				bonus_formula = setting.getValue();
			} catch (Exception e) {

				bonus_formula = "";

			}

			BonusMaster bous = new BonusMaster();
			try {

				bous = bonusMasterRepo.findByBonusIdAndDelStatus(bonusId, 1);
				exgretia_percentage = bous.getExgratiaPercentage();
			} catch (Exception e) {

				exgretia_percentage = 0.0;
			}

			// temp.setLoginTimeExgretia("0000-00-00 00:00:00");
			for (int i = 0; i < empIdList.size(); i++) {

				int empId = empIdList.get(i);
				double exgratiaAmt = 0;
				double grossExgratiaAmt = 0;
				double dedExgratiaAmt = 0;
				double payableDays = 0;
				String isApp = null;
				double formTot = 0;
				BonusCalc bonusCalc = bonusCalcRepo.findByEmpIdAndBonusIdAndDelStatus(empId, bonusId, 1);

				if (bonusCalc != null) {
					isApp = bonusCalc.getBonusApplicable();
					if (isApp.equals("Yes")) {
						payableDays = bonusCalc.getTotalBonusDays();
						formTot = bonusCalc.getTotalBonusWages();
						exgratiaAmt = (formTot * exgretia_percentage) / 100;
						grossExgratiaAmt = exgratiaAmt + formTot;
						dedExgratiaAmt = (grossExgratiaAmt * ded_exgretia_amt_percentage) / 100;
						dedExgratiaAmt = dedExgratiaAmt + grossExgratiaAmt;
					} else {
						formTot = 0;
						exgratiaAmt = 0;
						grossExgratiaAmt = 0;
						dedExgratiaAmt = 0;
						payableDays = bonusCalc.getTotalBonusDays();
						isApp = "No";
					}

					int n = bonusCalcRepo.updateExgratiaAmts(formTot, grossExgratiaAmt, exgratiaAmt, dedExgratiaAmt,
							payableDays, yyDtTm.format(date), userId, isApp, bonusCalc.getBonusCalcId(),
							exgretia_percentage);

					if (n > 0) {
						BonusCalc bonusCalc1 = bonusCalcRepo.findByEmpIdAndBonusIdAndDelStatus(empId, bonusId, 1);
						ObjectMapper mapper = new ObjectMapper();
						BonusCalc organisation = mapper.readValue(bonusCalc1.getBonusDetails(), BonusCalc.class);
						
						organisation.setExgratiaPrcnt(exgretia_percentage);
						organisation.setExgretiaApplicable("Yes");
						organisation.setTotalExgretiaDays((int)(payableDays));
						organisation.setTotalExgretiaWages(String.valueOf(formTot));
						organisation.setIsExgretiaFinalized(String.valueOf("0"));
						organisation.setLoginIdExgretia(userId);
						organisation.setLoginTimeExgretia( yyDtTm.format(date));
						organisation.setPaidExgretiaAmt(0);
					 
 						
						ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						String json = ow.writeValueAsString(organisation);

						int p = bonusCalcRepo.updateExgratiaDetails(json, bonusCalc1.getBonusCalcId());

					}

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return info;

	}

	@RequestMapping(value = { "/empBonusAppUpdateForExgratia" }, method = RequestMethod.POST)
	public @ResponseBody Info empBonusAppSaveOrUpdate(@RequestParam("bonusAppId") int bonusAppId,
			@RequestParam("startDate") String startDate, @RequestParam("isFinal") int isFinal,
			@RequestParam("bonusId") int bonusId, @RequestParam("remark") String remark,
			@RequestParam("companyId") int companyId, @RequestParam("dateTime") String dateTime,
			@RequestParam("userId") int userId) {

		Info info = new Info();

		String paidDate = DateConvertor.convertToYMD(startDate);
		String[] a = paidDate.split("-");

		double ded_exgretia_amt_percentage = 0.0;
		double exgretia_percentage = 0.0;
		String bonus_formula = null;

		Setting setting = new Setting();
		BonusMaster bous = new BonusMaster();
		try {

			bous = bonusMasterRepo.findByBonusIdAndDelStatus(bonusId, 1);
			exgretia_percentage = bous.getExgratiaPercentage();
		} catch (Exception e) {

			exgretia_percentage = 0.0;
		}

		try {
			setting = settingRepo.findByKey("ded_exgretia_amt_percentage");
			ded_exgretia_amt_percentage = Double.parseDouble(setting.getValue());
		} catch (Exception e) {

			ded_exgretia_amt_percentage = 0;

		}

		try {

			setting = settingRepo.findByKey("bonus_formula");
			bonus_formula = setting.getValue();
		} catch (Exception e) {

			bonus_formula = "";

		}
		try {

			int n = bonusApplicableRepo.updateBonusExgratia(bonusAppId, bonus_formula, exgretia_percentage,
					ded_exgretia_amt_percentage, userId, dateTime, remark);
			int n1 = bonusCalcRepo.updateCalcFinalizeExgratia(bonusId, paidDate);
		 
 
		} catch (Exception e) {

			e.printStackTrace();

		}

		return info;

	}

	@RequestMapping(value = { "/deleteBonusCalcExratia" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteBonusCalcExratia(@RequestParam("bonusCalcId") int bonusCalcId) {

		Info info = new Info();

		try {

			int n1 = bonusCalcRepo.updateExgratiaAmtsDelete(bonusCalcId);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return info;

	}

	@RequestMapping(value = { "/getBonusCalcByCalcId" }, method = RequestMethod.POST)
	public @ResponseBody BonusCalc getBonusCalcByCalcId(@RequestParam("bonusCalcId") int bonusCalcId) {

		BonusCalc list = new BonusCalc();
		try {

			list = bonusCalcRepo.findByBonusCalcIdAndDelStatus(bonusCalcId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/empBonusCalcUpdateForExgratia" }, method = RequestMethod.POST)
	public @ResponseBody Info empBonusCalcUpdateForExgratia(@RequestParam("bonusId") int bonusId,
			@RequestParam("bonusCalcId") int bonusCalcId, @RequestParam("exPrcnt") double exPrcnt,
			@RequestParam("companyId") int companyId, @RequestParam("dateTime") String dateTime,
			@RequestParam("userId") int userId) {

		Info info = new Info();
		int flag = 0;
		Date date = new Date();
		SimpleDateFormat yyDtTm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {

			// edit

			BonusMaster bous = new BonusMaster();

			// temp.setLoginTimeExgretia("0000-00-00 00:00:00");
			double ded_exgretia_amt_percentage = 0.0;

			Setting setting = new Setting();

			try {
				setting = settingRepo.findByKey("ded_exgretia_amt_percentage");
				ded_exgretia_amt_percentage = Double.parseDouble(setting.getValue());
			} catch (Exception e) {

				ded_exgretia_amt_percentage = 0;

			}
			double exgratiaAmt = 0;
			double grossExgratiaAmt = 0;
			double dedExgratiaAmt = 0;
			double payableDays = 0;
			String isApp = null;
			BonusCalc bonusCalc = bonusCalcRepo.findByBonusCalcIdAndDelStatus(bonusCalcId, 1);
			double exgratiaAmt1 = 0;
			if (bonusCalc != null) {
				isApp = bonusCalc.getBonusApplicable();
				if (isApp.equals("Yes")) {
					payableDays = bonusCalc.getTotalBonusDays();
					exgratiaAmt1 = Double.parseDouble(bonusCalc.getTotalExgretiaWages());
					exgratiaAmt = (exgratiaAmt1 * exPrcnt) / 100;
					grossExgratiaAmt = exgratiaAmt1 + exgratiaAmt;
					dedExgratiaAmt = (grossExgratiaAmt * ded_exgretia_amt_percentage) / 100;
					dedExgratiaAmt = dedExgratiaAmt + grossExgratiaAmt;
				} else {
					exgratiaAmt1 = 0;
					exgratiaAmt = 0;
					grossExgratiaAmt = 0;
					dedExgratiaAmt = 0;
					payableDays = bonusCalc.getTotalBonusDays();
					isApp = "No";
				}

				int n = bonusCalcRepo.updateExgratiaAmts(exgratiaAmt1, grossExgratiaAmt, exgratiaAmt, dedExgratiaAmt,
						payableDays, yyDtTm.format(date), userId, isApp, bonusCalc.getBonusCalcId(), exPrcnt);

				if (n > 0) {
					BonusCalc bonusCalc1 = bonusCalcRepo.findByBonusCalcIdAndDelStatus(bonusCalcId, 1);
					 
					ObjectMapper mapper = new ObjectMapper();
					BonusCalc organisation = mapper.readValue(bonusCalc1.getBonusDetails(), BonusCalc.class);
					
					organisation.setExgratiaPrcnt(exPrcnt);
					organisation.setExgretiaApplicable("Yes");
					organisation.setTotalExgretiaDays((int)(payableDays));
					organisation.setTotalExgretiaWages(String.valueOf(exgratiaAmt1));
					organisation.setIsExgretiaFinalized(String.valueOf("0"));
					organisation.setLoginIdExgretia(userId);
					organisation.setLoginTimeExgretia( yyDtTm.format(date));
					organisation.setPaidExgretiaAmt(0);
				  
					ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
					String json = ow.writeValueAsString(organisation);

				 

					int p = bonusCalcRepo.updateExgratiaDetails(json, bonusCalc1.getBonusCalcId());

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return info;

	}

}
