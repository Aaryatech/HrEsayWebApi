package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import com.ats.hrmgt.model.EmpSalaryInfoForPayroll; 
import com.ats.hrmgt.repository.EmpSalaryInfoForPayrollRepository;

@RestController
public class PayrollApiController {

	@Autowired
	EmpSalaryInfoForPayrollRepository empSalaryInfoForPayrollRepository;

	@RequestMapping(value = { "/getEmployeeListWithEmpSalEnfoForPayRoll" }, method = RequestMethod.POST)
	public List<EmpSalaryInfoForPayroll> getEmployeeListWithEmpSalEnfoForPayRoll(@RequestParam("month") int month,
			@RequestParam("year") int year) {

		List<EmpSalaryInfoForPayroll> list = new ArrayList<EmpSalaryInfoForPayroll>();
		try {
			list = empSalaryInfoForPayrollRepository.getEmployeeListWithEmpSalEnfoForPayRoll(month, year);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

}
