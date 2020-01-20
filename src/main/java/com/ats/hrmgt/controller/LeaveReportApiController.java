package com.ats.hrmgt.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.advance.repository.GetAdvanceRepo;
import com.ats.hrmgt.common.DateConvertor;
import com.ats.hrmgt.model.EmpShiftDetails;
import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.LeaveApply;
import com.ats.hrmgt.model.advance.GetAdvance;
import com.ats.hrmgt.model.report.GetYearlyAdvance;
import com.ats.hrmgt.model.report.GetYearlyAdvanceNew;
import com.ats.hrmgt.repo.report.GetYearlyAdvanceRepo;
import com.ats.hrmgt.repository.EmployeeMasterRepository;
import com.ats.hrmgt.repository.LeaveApplyRepository;

@RestController

public class LeaveReportApiController {

	@Autowired
	LeaveApplyRepository leaveApplyRepository;

	@Autowired
	GetAdvanceRepo getAdvanceRepo;

	@Autowired
	EmployeeMasterRepository empRepo;

	@RequestMapping(value = { "/getAdvanceReport" }, method = RequestMethod.POST)
	public @ResponseBody List<GetAdvance> getAdvanceReport(@RequestParam("companyId") int companyId,
			@RequestParam("month") int month, @RequestParam("year") int year) {

		List<GetAdvance> list = new ArrayList<GetAdvance>();
		try {

			list = getAdvanceRepo.getSpecEmpAdvForReport(companyId, month, year);

			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getIsDed() == 1) {
					list.get(i).setExVar1("Yes");
				} else {
					list.get(i).setExVar1("No");
				}
				list.get(i).setAdvDate(DateConvertor.convertToDMY(list.get(i).getAdvDate()));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	GetYearlyAdvanceRepo getYearlyAdvanceRepo;

	@RequestMapping(value = { "/getAdvanceYearlyReport" }, method = RequestMethod.POST)
	public @ResponseBody List<GetYearlyAdvanceNew> getAdvanceYearlyReport(@RequestParam("companyId") int companyId,
			@RequestParam("year") int year) {

		List<GetYearlyAdvance> advYearList = new ArrayList<GetYearlyAdvance>();
		List<GetYearlyAdvanceNew> newList = new ArrayList<GetYearlyAdvanceNew>();

		try {
			advYearList = getYearlyAdvanceRepo.getSpecEmpAdvForReport(companyId, year);

			List<EmployeeMaster> emplist = empRepo.findByDelStatusAndCmpCodeOrderByEmpIdDesc(1, companyId);
			// System.err.println("advYearList" + advYearList.toString());

			for (int i = 0; i < emplist.size(); i++) {
				GetYearlyAdvanceNew temp = new GetYearlyAdvanceNew();

				String janVal = "0";
				String febVal = "0";
				String marchVal = "0";
				String aprVal = "0";
				String mayVal = "0";
				String junVal = "0";
				String julVal = "0";
				String augVal = "0";
				String sepVal = "0";
				String octVal = "0";
				String novVal = "0";
				String decVal = "0";

				for (int k = 0; k < advYearList.size(); k++) {

					if (advYearList.get(k).getEmpId() == emplist.get(i).getEmpId()
							&& advYearList.get(k).getMonth() == 1) {
						janVal = advYearList.get(k).getAdvAmount();
					} else if (advYearList.get(k).getEmpId() == emplist.get(i).getEmpId()
							&& advYearList.get(k).getMonth() == 2) {
						febVal = advYearList.get(k).getAdvAmount();
						System.err.println("feb" + febVal);
					} else if (advYearList.get(k).getEmpId() == emplist.get(i).getEmpId()
							&& advYearList.get(k).getMonth() == 3) {
						marchVal = advYearList.get(k).getAdvAmount();
					} else if (advYearList.get(k).getEmpId() == emplist.get(i).getEmpId()
							&& advYearList.get(k).getMonth() == 4) {
						aprVal = advYearList.get(k).getAdvAmount();
					} else if (advYearList.get(k).getEmpId() == emplist.get(i).getEmpId()
							&& advYearList.get(k).getMonth() == 5) {
						mayVal = advYearList.get(k).getAdvAmount();
					} else if (advYearList.get(k).getEmpId() == emplist.get(i).getEmpId()
							&& advYearList.get(k).getMonth() == 6) {
						junVal = advYearList.get(k).getAdvAmount();
					} else if (advYearList.get(k).getEmpId() == emplist.get(i).getEmpId()
							&& advYearList.get(k).getMonth() == 7) {
						julVal = advYearList.get(k).getAdvAmount();
					} else if (advYearList.get(k).getEmpId() == emplist.get(i).getEmpId()
							&& advYearList.get(k).getMonth() == 8) {
						augVal = advYearList.get(k).getAdvAmount();
					} else if (advYearList.get(k).getEmpId() == emplist.get(i).getEmpId()
							&& advYearList.get(k).getMonth() == 9) {
						sepVal = advYearList.get(k).getAdvAmount();
					} else if (advYearList.get(k).getEmpId() == emplist.get(i).getEmpId()
							&& advYearList.get(k).getMonth() == 10) {
						octVal = advYearList.get(k).getAdvAmount();
					} else if (advYearList.get(k).getEmpId() == emplist.get(i).getEmpId()
							&& advYearList.get(k).getMonth() == 11) {
						novVal = advYearList.get(k).getAdvAmount();
					} else if (advYearList.get(k).getEmpId() == emplist.get(i).getEmpId()
							&& advYearList.get(k).getMonth() == 12) {
						decVal = advYearList.get(k).getAdvAmount();
					}
				}

				temp.setAprCount(aprVal);
				temp.setAugCount(augVal);
				temp.setDecCount(decVal);
				temp.setEmpCode(emplist.get(i).getEmpCode());
				temp.setEmpId(emplist.get(i).getEmpId());
				temp.setEmpName(emplist.get(i).getFirstName().concat(" ").concat(emplist.get(i).getMiddleName())
						.concat(" ").concat(emplist.get(i).getSurname()));
				temp.setFebCount(febVal);
				temp.setJanCount(janVal);
				temp.setJulCount(julVal);
				temp.setJunCount(junVal);
				temp.setMarchCount(marchVal);
				temp.setMayCount(mayVal);
				temp.setNovCount(novVal);
				temp.setOctCount(octVal);
				temp.setSepCount(sepVal);

				newList.add(temp);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return newList;

	}

}
