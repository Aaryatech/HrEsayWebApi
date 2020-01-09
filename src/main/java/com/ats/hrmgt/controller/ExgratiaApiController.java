package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

 import com.ats.hrmgt.model.GetEmployeeDetails;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.Setting;
 import com.ats.hrmgt.model.bonus.BonusCalc;
import com.ats.hrmgt.model.bonus.BonusMaster;
import com.ats.hrmgt.repo.bonus.BonusApplicableRepo;
import com.ats.hrmgt.repo.bonus.BonusCalcRepo;
import com.ats.hrmgt.repo.bonus.BonusMasterRepo;
import com.ats.hrmgt.repository.GetEmployeeDetailsRepo;
import com.ats.hrmgt.repository.SettingRepo;

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
	public List<GetEmployeeDetails> getAllEmployeeDetailForBonusUpdate(@RequestParam("bonusId") int bonusId) {
		List<GetEmployeeDetails> list = new ArrayList<GetEmployeeDetails>();
		try {
			list = getEmployeeDetailsRepo.getEmpDetailListByBonusId(bonusId);
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/empExgratiaUpdateToBonusSave" }, method = RequestMethod.POST)
	public @ResponseBody Info empBonusAppSaveOrUpdate(@RequestParam("empIdList") List<Integer> empIdList,
			@RequestParam("bonusId") int bonusId, @RequestParam("companyId") int companyId,
			@RequestParam("dateTime") String dateTime, @RequestParam("userId") int userId) {

		Info info = new Info();
		int flag = 0;

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

				BonusCalc bonusCalc = bonusCalcRepo.findByBonusIdAndEmpIdAndDelStatus(bonusId, empId, 1);

				if (bonusCalc != null) {
					payableDays = bonusCalc.getTotalBonusDays();
					double formTot = bonusCalc.getTotalBonusWages();
					exgratiaAmt = (formTot * exgretia_percentage) / 100;
					grossExgratiaAmt = exgratiaAmt + grossExgratiaAmt;
					dedExgratiaAmt = (grossExgratiaAmt * ded_exgretia_amt_percentage) / 100;
					dedExgratiaAmt = dedExgratiaAmt + grossExgratiaAmt;

					int n = bonusCalcRepo.updateExgratia(formTot, grossExgratiaAmt, exgratiaAmt, dedExgratiaAmt);

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return info;

	}

}
