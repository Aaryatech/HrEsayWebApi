package com.ats.hrmgt.controller;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.advance.repository.AdvanceRepo;
import com.ats.hrmgt.common.DateConvertor;
import com.ats.hrmgt.model.EmpSalaryInfo;
import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.SalAllownceCal;
import com.ats.hrmgt.model.SalaryCalc;
import com.ats.hrmgt.model.SummaryDailyAttendance;
import com.ats.hrmgt.model.advance.Advance;
import com.ats.hrmgt.model.bonus.BonusCalc;
import com.ats.hrmgt.model.graph.EmpAdvanceGraph;
import com.ats.hrmgt.model.graph.EmpDailyAttendanceGraph;
import com.ats.hrmgt.model.graph.EmpLoanGraph;
import com.ats.hrmgt.model.loan.LoanMain;
import com.ats.hrmgt.repo.loan.LoanMainRepo;
import com.ats.hrmgt.repository.EmpSalaryInfoRepo;
import com.ats.hrmgt.repository.EmployeeMasterRepository;
import com.ats.hrmgt.repository.SalAllownceCalRepo;
import com.ats.hrmgt.repository.SalaryCalcRepo;
import com.ats.hrmgt.repository.SummaryDailyAttendanceRepository;

@RestController
public class GraphApiController {

	@Autowired
	EmpSalaryInfoRepo empSalaryInfoRepo;
	@Autowired
	SalaryCalcRepo salaryCalcRepo;

	@Autowired
	SalAllownceCalRepo salAllownceCalRepo;

	@Autowired
	SummaryDailyAttendanceRepository summaryDailyAttendanceRepository;

	@RequestMapping(value = { "/getEmpSalaryGraph" }, method = RequestMethod.POST)
	public List<BonusCalc> getAllEmployeeDetailForBonus(@RequestParam("empId") int empId,
			@RequestParam("companyId") int companyId) {
		List<BonusCalc> list = new ArrayList<BonusCalc>();
		try {
			EmpSalaryInfo emp = empSalaryInfoRepo.findByEmpIdAndDelStatus(empId, 1);

			List<String> dateList = DateConvertor.getAllMonth(emp.getCmpJoiningDate());

			List<SalaryCalc> calcList = salaryCalcRepo.findAllByEmpId(empId);
			List<SalAllownceCal> allwList = salAllownceCalRepo.findByEmpId(empId);
			/*
			 * for(int i=0;i<dateList.size();i++) {
			 * 
			 * String a[]=dateList.get(i).split("-"); String year=a[1]; String month=a[0];
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * }
			 */

		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	/*
	 * @RequestMapping(value = { "/getEmpAttendanceGraph" }, method =
	 * RequestMethod.POST) public List<EmpDailyAttendanceGraph>
	 * getEmpAttendanceGraph(@RequestParam("empId") int empId,
	 * 
	 * @RequestParam("companyId") int companyId, @RequestParam("year") int year) {
	 * List<EmpDailyAttendanceGraph> list = new
	 * ArrayList<EmpDailyAttendanceGraph>(); try { EmpSalaryInfo emp =
	 * empSalaryInfoRepo.findByEmpIdAndDelStatus(empId, 1);
	 * 
	 * List<String> dateList = DateConvertor.getAllMonth(emp.getCmpJoiningDate());
	 * String start = dateList.get(0); String end = dateList.get(dateList.size() -
	 * 1); System.err.println("start" + start); System.err.println("end" + end);
	 * System.err.println("datelist " + dateList.toString()); String ac[] =
	 * start.split("-"); String b[] = start.split("-");
	 * 
	 * 
	 * List<SummaryDailyAttendance> attList =
	 * summaryDailyAttendanceRepository.summaryDailyAttendanceList(empId,
	 * Integer.parseInt(ac[0]), Integer.parseInt(b[0]), ac[1], b[1]);
	 * 
	 * 
	 * List<SummaryDailyAttendance> attList = summaryDailyAttendanceRepository
	 * .findAllByCompanyIdAndEmpIdAndYear(companyId, empId, year);
	 * 
	 * System.err.println("  attList" + attList.toString()); for (int i = 1; i <=
	 * 12; i++) { EmpDailyAttendanceGraph dailyrec = new EmpDailyAttendanceGraph();
	 * 
	 * double workingDays = 0; double presentdays = 0; double paidHoliday = 0;
	 * double unpaidHoliday = 0; double paidLeave = 0; double unpaidLeave = 0;
	 * double monthDays = 0;
	 * 
	 * for (int j = 0; j < attList.size(); j++) {
	 * 
	 * if (attList.get(j).getMonth() == i && attList.get(j).getYear() == year) {
	 * workingDays = attList.get(j).getWorkingDays(); presentdays =
	 * attList.get(j).getPresentDays(); paidHoliday =
	 * attList.get(j).getPaidHoliday(); paidLeave = attList.get(j).getPaidLeave();
	 * unpaidLeave = attList.get(j).getUnpaidLeave(); monthDays =
	 * attList.get(j).getTotalDaysInmonth(); unpaidHoliday =
	 * attList.get(j).getUnpaidHoliday(); dailyrec.setMonthDays(monthDays);
	 * dailyrec.setPaidHoliday(paidHoliday); dailyrec.setPaidLeave(paidLeave);
	 * dailyrec.setPresentdays(presentdays);
	 * dailyrec.setUnpaidHoliday(unpaidHoliday);
	 * dailyrec.setUnpaidLeave(unpaidLeave); dailyrec.setWorkingDays(workingDays);
	 * break; }
	 * 
	 * } String a = Month.of(i).name();
	 * dailyrec.setDate(a.concat("-").concat(String.valueOf(year)));
	 * dailyrec.setMonth(i); dailyrec.setYear(year); list.add(dailyrec);
	 * 
	 * }
	 * 
	 * } catch (Exception e) { System.err.println("Excep in getAllEmployeeDetail : "
	 * + e.getMessage()); e.printStackTrace(); }
	 * 
	 * return list; }
	 */
	@Autowired
	AdvanceRepo advanceRepo;

	@RequestMapping(value = { "/getEmpAdvanceGraph" }, method = RequestMethod.POST)
	public List<EmpAdvanceGraph> getEmpAdvanceGraph(@RequestParam("empId") int empId,
			@RequestParam("companyId") int companyId) {
		List<EmpAdvanceGraph> list = new ArrayList<EmpAdvanceGraph>();
		try {
			EmpSalaryInfo emp = empSalaryInfoRepo.findByEmpIdAndDelStatus(empId, 1);

			List<String> dateList = DateConvertor.getAllMonth(emp.getCmpJoiningDate());

			List<Advance> attList = advanceRepo.findByEmpIdAndDelStatus(empId, 1);
			System.err.println("  attList" + attList.toString());
			for (int i = 0; i < dateList.size(); i++) {
				EmpAdvanceGraph dailyrec = new EmpAdvanceGraph();

				String a[] = dateList.get(i).split("-");
				int year = Integer.parseInt(a[1]);
				int month = Integer.parseInt(a[0]);
				double advanceAmt = 0;

				for (int j = 0; j < attList.size(); j++) {

					String mon[] = attList.get(j).getAdvDate().split("-");

					if (Integer.parseInt(mon[1]) == month && Integer.parseInt(mon[0]) == year) {
						advanceAmt = attList.get(j).getAdvAmount();
						break;
					}

				}

				dailyrec.setDate(dateList.get(i));
				dailyrec.setMonth(month);
				dailyrec.setYear(year);
				dailyrec.setAdvanceAmt(advanceAmt);

				list.add(dailyrec);

			}

		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	/*
	 * @RequestMapping(value = { "/getEmpAttendanceGraph" }, method =
	 * RequestMethod.POST) public List<EmpDailyAttendanceGraph>
	 * getEmpAttendanceGraph(@RequestParam("empId") int empId,
	 * 
	 * @RequestParam("companyId") int companyId, @RequestParam("year") int year) {
	 * List<EmpDailyAttendanceGraph> list = new
	 * ArrayList<EmpDailyAttendanceGraph>(); try { EmpSalaryInfo emp =
	 * empSalaryInfoRepo.findByEmpIdAndDelStatus(empId, 1);
	 * 
	 * List<String> dateList = DateConvertor.getAllMonth(emp.getCmpJoiningDate());
	 * String start = dateList.get(0); String end = dateList.get(dateList.size() -
	 * 1); System.err.println("start" + start); System.err.println("end" + end);
	 * System.err.println("datelist " + dateList.toString()); String ac[] =
	 * start.split("-"); String b[] = start.split("-");
	 * 
	 * 
	 * List<SummaryDailyAttendance> attList =
	 * summaryDailyAttendanceRepository.summaryDailyAttendanceList(empId,
	 * Integer.parseInt(ac[0]), Integer.parseInt(b[0]), ac[1], b[1]);
	 * 
	 * 
	 * List<SummaryDailyAttendance> attList = summaryDailyAttendanceRepository
	 * .findAllByCompanyIdAndEmpIdAndYear(companyId, empId,year);
	 * 
	 * System.err.println("  attList" + attList.toString()); for (int i = 0; i <
	 * dateList.size(); i++) { EmpDailyAttendanceGraph dailyrec = new
	 * EmpDailyAttendanceGraph();
	 * 
	 * 
	 * String a[] = dateList.get(i).split("-"); int year = Integer.parseInt(a[1]);
	 * int month = Integer.parseInt(a[0]);
	 * 
	 * double workingDays = 0; double presentdays = 0; double paidHoliday = 0;
	 * double unpaidHoliday = 0; double paidLeave = 0; double unpaidLeave = 0;
	 * double monthDays = 0;
	 * 
	 * for (int j = 0; j < attList.size(); j++) {
	 * 
	 * if (attList.get(j).getMonth() == month && attList.get(j).getYear() == year) {
	 * workingDays = attList.get(j).getWorkingDays(); presentdays =
	 * attList.get(j).getPresentDays(); paidHoliday =
	 * attList.get(j).getPaidHoliday(); paidLeave = attList.get(j).getPaidLeave();
	 * unpaidLeave = attList.get(j).getUnpaidLeave(); monthDays =
	 * attList.get(j).getTotalDaysInmonth(); unpaidHoliday =
	 * attList.get(j).getUnpaidHoliday(); dailyrec.setMonthDays(monthDays);
	 * dailyrec.setPaidHoliday(paidHoliday); dailyrec.setPaidLeave(paidLeave);
	 * dailyrec.setPresentdays(presentdays);
	 * dailyrec.setUnpaidHoliday(unpaidHoliday);
	 * dailyrec.setUnpaidLeave(unpaidLeave); dailyrec.setWorkingDays(workingDays);
	 * break; }
	 * 
	 * } dailyrec.setDate(dateList.get(i)); dailyrec.setMonth(month);
	 * dailyrec.setYear(year);
	 * 
	 * list.add(dailyrec);
	 * 
	 * }
	 * 
	 * } catch (Exception e) { System.err.println("Excep in getAllEmployeeDetail : "
	 * + e.getMessage()); e.printStackTrace(); }
	 * 
	 * return list; }
	 */

	// *************************************************New WS for from date to to
	// date **************************

	
	
	//String[] monthNames = {"Jan", "Feb", "March", "April", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
	@RequestMapping(value = { "/getEmpAdvanceGraphNew" }, method = RequestMethod.POST)
	public List<EmpAdvanceGraph> getEmpAdvanceGraphNew(@RequestParam("empId") int empId,
			@RequestParam("companyId") int companyId, @RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) {
		List<EmpAdvanceGraph> list = new ArrayList<EmpAdvanceGraph>();
		try {
 			Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(fromDate);
			Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(toDate);

			List<String> dateList = DateConvertor.getAllMonthBetDates(date1, date2);

			System.err.println("  dateList" + dateList.toString());

			List<Advance> advanceList = advanceRepo.findByEmpIdAndDelStatus(empId, 1);
			System.err.println("  advanceList" + advanceList.toString());
			for (int i = 0; i < dateList.size(); i++) {
				EmpAdvanceGraph dailyrec = new EmpAdvanceGraph();

				String a[] = dateList.get(i).split("-");
				int year = Integer.parseInt(a[1]);
				int month = Integer.parseInt(a[0]);
				double advanceAmt = 0;

				for (int j = 0; j < advanceList.size(); j++) {

					String mon[] = advanceList.get(j).getAdvDate().split("-");

					if (Integer.parseInt(mon[1]) == month && Integer.parseInt(mon[0]) == year) {
						advanceAmt = advanceAmt + advanceList.get(j).getAdvAmount();
						// break;
					}

				}

				String c = Month.of(month).name();
				dailyrec.setDate(c.concat("-").concat(String.valueOf(year)));
				dailyrec.setMonth(month);
				dailyrec.setYear(year);
				dailyrec.setAdvanceAmt(advanceAmt);

				list.add(dailyrec);

			}
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getEmpAttendanceGraphNew" }, method = RequestMethod.POST)
	public List<EmpDailyAttendanceGraph> getEmpAttendanceGraphNew(@RequestParam("empId") int empId,
			@RequestParam("companyId") int companyId, @RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) {
		List<EmpDailyAttendanceGraph> list = new ArrayList<EmpDailyAttendanceGraph>();
		try {

			Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(fromDate);
			Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(toDate);
			List<String> dateList = DateConvertor.getAllMonthBetDates(date1, date2);
			System.err.println("datelist " + dateList.toString());

			List<SummaryDailyAttendance> attList = summaryDailyAttendanceRepository
					.findAllByCompanyIdAndEmpId(companyId, empId);

			System.err.println("  attList" + attList.toString());
			for (int i = 0; i < dateList.size(); i++) {
				EmpDailyAttendanceGraph dailyrec = new EmpDailyAttendanceGraph();

				double workingDays = 0;
				double presentdays = 0;
				double paidHoliday = 0;
				double unpaidHoliday = 0;
				double paidLeave = 0;
				double unpaidLeave = 0;
				double monthDays = 0;
				int lateMarks=0;
				String a[] = dateList.get(i).split("-");
				int year = Integer.parseInt(a[1]);
				int month = Integer.parseInt(a[0]);

				for (int j = 0; j < attList.size(); j++) {

					if (attList.get(j).getMonth() == month && attList.get(j).getYear() == year) {
						workingDays = attList.get(j).getWorkingDays();
						presentdays = attList.get(j).getPresentDays();
						paidHoliday = attList.get(j).getPaidHoliday();
						paidLeave = attList.get(j).getPaidLeave();
						unpaidLeave = attList.get(j).getUnpaidLeave();
						monthDays = attList.get(j).getTotalDaysInmonth();
						unpaidHoliday = attList.get(j).getUnpaidHoliday();
						lateMarks=attList.get(j).getTotLate();
						dailyrec.setMonthDays(monthDays);
						dailyrec.setPaidHoliday(paidHoliday);
						dailyrec.setPaidLeave(paidLeave);
						dailyrec.setPresentdays(presentdays);
						dailyrec.setUnpaidHoliday(unpaidHoliday);
						dailyrec.setUnpaidLeave(unpaidLeave);
						dailyrec.setWorkingDays(workingDays);
						dailyrec.setLateMarks(lateMarks);
						break;
					}

				}
				String c = Month.of(month).name();
				dailyrec.setDate(c.concat("-").concat(String.valueOf(year)));
				dailyrec.setMonth(month);
				dailyrec.setYear(year);
				list.add(dailyrec);

			}

		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
	
	
	@Autowired
	LoanMainRepo loanMainRepo;
	@RequestMapping(value = { "/getEmpLoanGraph" }, method = RequestMethod.POST)
	public List<EmpLoanGraph> getEmpLoanGraph(@RequestParam("empId") int empId,
			@RequestParam("companyId") int companyId, @RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) {
		List<EmpLoanGraph> list = new ArrayList<EmpLoanGraph>();
		try {
			Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(fromDate);
			Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(toDate);

			List<String> dateList = DateConvertor.getAllMonthBetDates(date1, date2);

			System.err.println("  dateList" + dateList.toString());

			List<LoanMain> loanList = loanMainRepo.findByEmpIdAndDelStatus(empId, 1);
			System.err.println("  loanList" + loanList.toString());
			for (int i = 0; i < dateList.size(); i++) {
				EmpLoanGraph dailyrec = new EmpLoanGraph();

				String a[] = dateList.get(i).split("-");
				int year = Integer.parseInt(a[1]);
				int month = Integer.parseInt(a[0]);
				double loanAmt = 0;

				for (int j = 0; j < loanList.size(); j++) {

					String mon[] = String .valueOf(loanList.get(j).getLoanRepayStart()).split("-");

					if (Integer.parseInt(mon[1]) == month && Integer.parseInt(mon[0]) == year) {
						loanAmt = loanAmt + loanList.get(j).getLoanAmt();
						// break;
					}

				}
 
				dailyrec.setMonth(month);
				dailyrec.setYear(year);
				dailyrec.setLoanAmt(loanAmt);
				
				String c = Month.of(month).name();
				dailyrec.setDate(c.concat("-").concat(String.valueOf(year)));
				
				list.add(dailyrec);

			}
		} catch (Exception e) {
			System.err.println("Excep in getAllEmployeeDetail : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

}
