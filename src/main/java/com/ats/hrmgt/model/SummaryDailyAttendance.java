package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_attt_summary_daily")
public class SummaryDailyAttendance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="company_id")
	private int companyId;
	
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="emp_code")
	private String empCode;
	
	@Column(name="emp_name")
	private String empName ;
	
	@Column(name="month")
	private int month;
	
	@Column(name="year")
	private int year;
	
	@Column(name="working_days")
	private float workingDays;
	
	@Column(name="present_days")
	private float presentDays; 
	
	@Column(name="weekly_off")
	private float weeklyOff; 
	
	@Column(name="paid_holiday")
	private float paidHoliday;
	
	@Column(name="paid_leave")
	private float paidLeave;
	
	@Column(name="legal_strike")
	private float legalStrike;
	
	@Column(name="lay_off")
	private float layOff;
	
	@Column(name="unpaid_holiday")
	private float unpaidHoliday;
	
	@Column(name="unpaid_leave")
	private float unpaidLeave;
	
	@Column(name="absent_days")
	private int absentDays;
	
	@Column(name="payable_days")
	private float payableDays;
	
	@Column(name="ncp_days")
	private float ncpDays;
	
	@Column(name="totlate_mins")
	private float totlateMins;
	
	@Column(name="totlate_days")
	private float totlateDays;
	
	@Column(name="totout_mins")
	private float totoutMins;
	
	@Column(name="totworking_hrs")
	private float totworkingHrs;
	
	@Column(name="totot_hrs")
	private float tototHrs;
	
	@Column(name="tot_othr")
	private float totOthr;
	
	@Column(name="tot_late")
	private int totLate;
	
	@Column(name="login_name")
	private String loginName;
	
	@Column(name="login_time")
	private String loginTime;
	
	@Column(name="status")
	private int status;
	
	@Column(name="import_date")
	private String importDate;
	 
	@Column(name="rec_status_paid")
	private int recStatusPaid;
	
	@Column(name="total_days_inmonth")
	private int totalDaysInmonth;
	
	@Column(name="late_ded_leave_paid")
	private int lateDedLeavePaid;
	
	@Column(name="holiday_present")
	private float holidayPresent;
	
	@Column(name="weekly_off_present")
	private float weeklyOffPresent;
	
	@Column(name="full_night")
	private float fullNight;
	
	@Column(name="half_night")
	private float halfNight;
	
	@Column(name="holiday_present_half")
	private float holidayPresentHalf;
	
	@Column(name="weekly_off_present_half")
	private float weeklyOffPresentHalf;
	
	@Column(name="weekly_off_holiday_off")
	private float weeklyOffHoliday0ff;
	
	@Column(name="weekly_off_holiday_off_present")
	private float weeklyOffHolidayOffPresent;
	
	@Column(name="weekly_off_holiday_off_present_halfday")
	private float weeklyOffHolidayOffPresentHalfday;
	
	@Column(name="hdpresent_hdleave")
	private float hdpresentHdleave;
	
	@Column(name="tot_early_going")
	private int totEarlyGoing;

	@Column(name="atsumm_uid")
	private String atsummUid;
	
	@Column(name="calculation_done")
	private int calculationDone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public float getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(float workingDays) {
		this.workingDays = workingDays;
	}

	public float getPresentDays() {
		return presentDays;
	}

	public void setPresentDays(float presentDays) {
		this.presentDays = presentDays;
	}

	public float getWeeklyOff() {
		return weeklyOff;
	}

	public void setWeeklyOff(float weeklyOff) {
		this.weeklyOff = weeklyOff;
	}

	public float getPaidHoliday() {
		return paidHoliday;
	}

	public void setPaidHoliday(float paidHoliday) {
		this.paidHoliday = paidHoliday;
	}

	public float getPaidLeave() {
		return paidLeave;
	}

	public void setPaidLeave(float paidLeave) {
		this.paidLeave = paidLeave;
	}

	public float getLegalStrike() {
		return legalStrike;
	}

	public void setLegalStrike(float legalStrike) {
		this.legalStrike = legalStrike;
	}

	public float getLayOff() {
		return layOff;
	}

	public void setLayOff(float layOff) {
		this.layOff = layOff;
	}

	public float getUnpaidHoliday() {
		return unpaidHoliday;
	}

	public void setUnpaidHoliday(float unpaidHoliday) {
		this.unpaidHoliday = unpaidHoliday;
	}

	public float getUnpaidLeave() {
		return unpaidLeave;
	}

	public void setUnpaidLeave(float unpaidLeave) {
		this.unpaidLeave = unpaidLeave;
	}

	public int getAbsentDays() {
		return absentDays;
	}

	public void setAbsentDays(int absentDays) {
		this.absentDays = absentDays;
	}

	public float getPayableDays() {
		return payableDays;
	}

	public void setPayableDays(float payableDays) {
		this.payableDays = payableDays;
	}

	public float getNcpDays() {
		return ncpDays;
	}

	public void setNcpDays(float ncpDays) {
		this.ncpDays = ncpDays;
	}

	public float getTotlateMins() {
		return totlateMins;
	}

	public void setTotlateMins(float totlateMins) {
		this.totlateMins = totlateMins;
	}

	public float getTotlateDays() {
		return totlateDays;
	}

	public void setTotlateDays(float totlateDays) {
		this.totlateDays = totlateDays;
	}

	public float getTotoutMins() {
		return totoutMins;
	}

	public void setTotoutMins(float totoutMins) {
		this.totoutMins = totoutMins;
	}

	public float getTotworkingHrs() {
		return totworkingHrs;
	}

	public void setTotworkingHrs(float totworkingHrs) {
		this.totworkingHrs = totworkingHrs;
	}

	public float getTototHrs() {
		return tototHrs;
	}

	public void setTototHrs(float tototHrs) {
		this.tototHrs = tototHrs;
	}

	public float getTotOthr() {
		return totOthr;
	}

	public void setTotOthr(float totOthr) {
		this.totOthr = totOthr;
	}

	public int getTotLate() {
		return totLate;
	}

	public void setTotLate(int totLate) {
		this.totLate = totLate;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getImportDate() {
		return importDate;
	}

	public void setImportDate(String importDate) {
		this.importDate = importDate;
	}

	public int getRecStatusPaid() {
		return recStatusPaid;
	}

	public void setRecStatusPaid(int recStatusPaid) {
		this.recStatusPaid = recStatusPaid;
	}

	public int getTotalDaysInmonth() {
		return totalDaysInmonth;
	}

	public void setTotalDaysInmonth(int totalDaysInmonth) {
		this.totalDaysInmonth = totalDaysInmonth;
	}

	public int getLateDedLeavePaid() {
		return lateDedLeavePaid;
	}

	public void setLateDedLeavePaid(int lateDedLeavePaid) {
		this.lateDedLeavePaid = lateDedLeavePaid;
	}

	public float getHolidayPresent() {
		return holidayPresent;
	}

	public void setHolidayPresent(float holidayPresent) {
		this.holidayPresent = holidayPresent;
	}

	public float getWeeklyOffPresent() {
		return weeklyOffPresent;
	}

	public void setWeeklyOffPresent(float weeklyOffPresent) {
		this.weeklyOffPresent = weeklyOffPresent;
	}

	public float getFullNight() {
		return fullNight;
	}

	public void setFullNight(float fullNight) {
		this.fullNight = fullNight;
	}

	public float getHalfNight() {
		return halfNight;
	}

	public void setHalfNight(float halfNight) {
		this.halfNight = halfNight;
	}

	public float getHolidayPresentHalf() {
		return holidayPresentHalf;
	}

	public void setHolidayPresentHalf(float holidayPresentHalf) {
		this.holidayPresentHalf = holidayPresentHalf;
	}

	public float getWeeklyOffPresentHalf() {
		return weeklyOffPresentHalf;
	}

	public void setWeeklyOffPresentHalf(float weeklyOffPresentHalf) {
		this.weeklyOffPresentHalf = weeklyOffPresentHalf;
	}

	public float getWeeklyOffHoliday0ff() {
		return weeklyOffHoliday0ff;
	}

	public void setWeeklyOffHoliday0ff(float weeklyOffHoliday0ff) {
		this.weeklyOffHoliday0ff = weeklyOffHoliday0ff;
	}

	public float getWeeklyOffHolidayOffPresent() {
		return weeklyOffHolidayOffPresent;
	}

	public void setWeeklyOffHolidayOffPresent(float weeklyOffHolidayOffPresent) {
		this.weeklyOffHolidayOffPresent = weeklyOffHolidayOffPresent;
	}

	public float getWeeklyOffHolidayOffPresentHalfday() {
		return weeklyOffHolidayOffPresentHalfday;
	}

	public void setWeeklyOffHolidayOffPresentHalfday(float weeklyOffHolidayOffPresentHalfday) {
		this.weeklyOffHolidayOffPresentHalfday = weeklyOffHolidayOffPresentHalfday;
	}

	public float getHdpresentHdleave() {
		return hdpresentHdleave;
	}

	public void setHdpresentHdleave(float hdpresentHdleave) {
		this.hdpresentHdleave = hdpresentHdleave;
	}

	public int getTotEarlyGoing() {
		return totEarlyGoing;
	}

	public void setTotEarlyGoing(int totEarlyGoing) {
		this.totEarlyGoing = totEarlyGoing;
	}

	public String getAtsummUid() {
		return atsummUid;
	}

	public void setAtsummUid(String atsummUid) {
		this.atsummUid = atsummUid;
	}

	public int getCalculationDone() {
		return calculationDone;
	}

	public void setCalculationDone(int calculationDone) {
		this.calculationDone = calculationDone;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	@Override
	public String toString() {
		return "SummaryDailyAttendance [id=" + id + ", companyId=" + companyId + ", empId=" + empId + ", empCode="
				+ empCode + ", empName=" + empName + ", month=" + month + ", year=" + year + ", workingDays="
				+ workingDays + ", presentDays=" + presentDays + ", weeklyOff=" + weeklyOff + ", paidHoliday="
				+ paidHoliday + ", paidLeave=" + paidLeave + ", legalStrike=" + legalStrike + ", layOff=" + layOff
				+ ", unpaidHoliday=" + unpaidHoliday + ", unpaidLeave=" + unpaidLeave + ", absentDays=" + absentDays
				+ ", payableDays=" + payableDays + ", ncpDays=" + ncpDays + ", totlateMins=" + totlateMins
				+ ", totlateDays=" + totlateDays + ", totoutMins=" + totoutMins + ", totworkingHrs=" + totworkingHrs
				+ ", tototHrs=" + tototHrs + ", totOthr=" + totOthr + ", totLate=" + totLate + ", loginName="
				+ loginName + ", loginTime=" + loginTime + ", status=" + status + ", importDate=" + importDate
				+ ", recStatusPaid=" + recStatusPaid + ", totalDaysInmonth=" + totalDaysInmonth + ", lateDedLeavePaid="
				+ lateDedLeavePaid + ", holidayPresent=" + holidayPresent + ", weeklyOffPresent=" + weeklyOffPresent
				+ ", fullNight=" + fullNight + ", halfNight=" + halfNight + ", holidayPresentHalf=" + holidayPresentHalf
				+ ", weeklyOffPresentHalf=" + weeklyOffPresentHalf + ", weeklyOffHoliday0ff=" + weeklyOffHoliday0ff
				+ ", weeklyOffHolidayOffPresent=" + weeklyOffHolidayOffPresent + ", weeklyOffHolidayOffPresentHalfday="
				+ weeklyOffHolidayOffPresentHalfday + ", hdpresentHdleave=" + hdpresentHdleave + ", totEarlyGoing="
				+ totEarlyGoing + ", atsummUid=" + atsummUid + ", calculationDone=" + calculationDone + "]";
	}
	
	
	
}
