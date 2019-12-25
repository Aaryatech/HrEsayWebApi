package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SummaryAttendance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	  
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="emp_code")
	private String empCode;
	
	@Column(name="emp_name")
	private String empName ; 
	
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
	private String totlateMins;
	
	@Column(name="totlate_days")
	private float totlateDays;
	
	@Column(name="totout_mins")
	private String totoutMins;
	
	@Column(name="totworking_hrs")
	private String totworkingHrs;
	
	@Column(name="totot_hrs")
	private String tototHrs;
	
	@Column(name="tot_othr")
	private String totOthr;
	
	@Column(name="tot_late")
	private int totLate;
	      
	@Column(name="hdpresent_hdleave")
	private float hdpresentHdleave;
 
	@Column(name="sal_basis")
	private String salBasis;
	
	@Column(name="total_days_inmonth")
	private int totalDaysInmonth;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
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

	public String getTotlateMins() {
		return totlateMins;
	}

	public void setTotlateMins(String totlateMins) {
		this.totlateMins = totlateMins;
	}

	public float getTotlateDays() {
		return totlateDays;
	}

	public void setTotlateDays(float totlateDays) {
		this.totlateDays = totlateDays;
	}

	public String getTotoutMins() {
		return totoutMins;
	}

	public void setTotoutMins(String totoutMins) {
		this.totoutMins = totoutMins;
	}

	public String getTotworkingHrs() {
		return totworkingHrs;
	}

	public void setTotworkingHrs(String totworkingHrs) {
		this.totworkingHrs = totworkingHrs;
	}

	public String getTototHrs() {
		return tototHrs;
	}

	public void setTototHrs(String tototHrs) {
		this.tototHrs = tototHrs;
	}

	public String getTotOthr() {
		return totOthr;
	}

	public void setTotOthr(String totOthr) {
		this.totOthr = totOthr;
	}

	public int getTotLate() {
		return totLate;
	}

	public void setTotLate(int totLate) {
		this.totLate = totLate;
	}

	public float getHdpresentHdleave() {
		return hdpresentHdleave;
	}

	public void setHdpresentHdleave(float hdpresentHdleave) {
		this.hdpresentHdleave = hdpresentHdleave;
	}

	public String getSalBasis() {
		return salBasis;
	}

	public void setSalBasis(String salBasis) {
		this.salBasis = salBasis;
	}

	public int getTotalDaysInmonth() {
		return totalDaysInmonth;
	}

	public void setTotalDaysInmonth(int totalDaysInmonth) {
		this.totalDaysInmonth = totalDaysInmonth;
	}

	@Override
	public String toString() {
		return "SummaryAttendance [id=" + id + ", empId=" + empId + ", empCode=" + empCode + ", empName=" + empName
				+ ", workingDays=" + workingDays + ", presentDays=" + presentDays + ", weeklyOff=" + weeklyOff
				+ ", paidHoliday=" + paidHoliday + ", paidLeave=" + paidLeave + ", legalStrike=" + legalStrike
				+ ", layOff=" + layOff + ", unpaidHoliday=" + unpaidHoliday + ", unpaidLeave=" + unpaidLeave
				+ ", absentDays=" + absentDays + ", payableDays=" + payableDays + ", ncpDays=" + ncpDays
				+ ", totlateMins=" + totlateMins + ", totlateDays=" + totlateDays + ", totoutMins=" + totoutMins
				+ ", totworkingHrs=" + totworkingHrs + ", tototHrs=" + tototHrs + ", totOthr=" + totOthr + ", totLate="
				+ totLate + ", hdpresentHdleave=" + hdpresentHdleave + ", salBasis=" + salBasis + ", totalDaysInmonth="
				+ totalDaysInmonth + "]";
	}
 
	
	
	
}
