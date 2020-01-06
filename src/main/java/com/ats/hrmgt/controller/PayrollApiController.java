package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.Allowances;
import com.ats.hrmgt.model.EmpAllowanceList;
import com.ats.hrmgt.model.EmpSalAllowance;
import com.ats.hrmgt.model.EmpSalaryInfoForPayroll;
import com.ats.hrmgt.model.PayRollDataForProcessing;
import com.ats.hrmgt.repository.AllowancesRepo;
import com.ats.hrmgt.repository.EmpSalAllowanceRepo;
import com.ats.hrmgt.repository.EmpSalaryInfoForPayrollRepository;

@RestController
public class PayrollApiController {

	@Autowired
	EmpSalaryInfoForPayrollRepository empSalaryInfoForPayrollRepository;

	@Autowired
	EmpSalAllowanceRepo empSalAllowanceRepo;

	@Autowired
	AllowancesRepo allowanceRepo;

	@RequestMapping(value = { "/getEmployeeListWithEmpSalEnfoForPayRoll" }, method = RequestMethod.POST)
	public PayRollDataForProcessing getEmployeeListWithEmpSalEnfoForPayRoll(@RequestParam("month") int month,
			@RequestParam("year") int year) {

		PayRollDataForProcessing payRollDataForProcessing = new PayRollDataForProcessing();

		try {
			List<EmpSalaryInfoForPayroll> list = empSalaryInfoForPayrollRepository
					.getEmployeeListWithEmpSalEnfoForPayRoll(month, year);
			List<Allowances> allowancelist = allowanceRepo.findBydelStatusAndIsActive(0, 1);
			List<EmpSalAllowance> empAllowanceList = empSalAllowanceRepo.findByDelStatus(1);

			for (int i = 0; i < list.size(); i++) {

				List<EmpAllowanceList> allowlist = new ArrayList<>();

				for (int j = 0; j < allowancelist.size(); j++) {

					int flag = 0;

					for (int k = 0; k < empAllowanceList.size(); k++) {

						if (empAllowanceList.get(k).getEmpId() == list.get(i).getEmpId()
								&& empAllowanceList.get(k).getAllowanceId() == allowancelist.get(j).getAllowanceId()) {

							System.out.println("in if " + empAllowanceList.get(k).getEmpId() + " "
									+ empAllowanceList.get(k).getAllowanceValue());
							EmpAllowanceList empAllowance = new EmpAllowanceList();
							empAllowance.setAllowanceId(allowancelist.get(j).getAllowanceId());
							empAllowance.setAllowanceName(allowancelist.get(j).getName());
							empAllowance.setValue(empAllowanceList.get(k).getAllowanceValue());
							allowlist.add(empAllowance);
							flag = 1;
							break;

						}
					}
					if (flag == 0) {
						EmpAllowanceList empAllowance = new EmpAllowanceList();
						empAllowance.setAllowanceId(allowancelist.get(j).getAllowanceId());
						empAllowance.setAllowanceName(allowancelist.get(j).getName());
						empAllowance.setValue(0);
						allowlist.add(empAllowance);
					}
				}
				list.get(i).setEmpAllowanceList(allowlist);
			}

			payRollDataForProcessing.setAllowancelist(allowancelist);
			payRollDataForProcessing.setList(list);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return payRollDataForProcessing;
	}

}
