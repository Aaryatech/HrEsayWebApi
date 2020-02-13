package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_mst_emp_types")
public class MstEmpType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="emp_type_id")
	private int empTypeId ;
	
	@Column(name="name")
	private String name ;
	  
	@Column(name="category")
	private String category ;
	
	@Column(name="att_type")
	private String attType;
	
	@Column(name="lm_applicable")
	private String lmApplicable;
	
	@Column(name="half_day")
	private String halfDay;
	  
	@Column(name="wh_work")
	private String whWork; 
	
	@Column(name="min_work_hr")
	private String minWorkHr; 
	
	@Column(name="minwork_applicable")
	private String minworkApplicable; 
	 

	
	@Column(name="ot_applicable")
	private String otApplicable ;
	 
	@Column(name="ot_time")
	private String otTime;
	
	@Column(name="details")
	private String details;
	
	@Column(name="ot_type")
	private String otType;
	  
	@Column(name="company_id")
	private int companyId; 
	
	@Column(name="weekly_holiday_late_allowed")
	private int weeklyHolidayLateAllowed; 
	
	@Column(name="weekly_holiday_late_allowed_min")
	private int weeklyHolidayLateAllowedMin;
	 
	@Column(name="early_going_allowed")
	private int earlyGoingAllowed;
	
	@Column(name="early_going_min")
	private int earlyGoingMin;
	
	@Column(name="max_late_time_allowed")
	private int maxLateTimeAllowed;
	
	@Column(name="status")
	private int status;
	
	@Column(name="del_status")
	private int delStatus;
	
	@Column(name="ex_int1")
	private int exInt1;
	
	@Column(name="ex_int2")
	private int exInt2;
	
	@Column(name="ex_var1")
	private String exVar1;
	
	@Column(name="ex_var2")
	private String exVar2;

	@Column(name="prod_incentive_app")
	private String prodIncentiveApp ;
	
	

	public String getProdIncentiveApp() {
		return prodIncentiveApp;
	}

	public void setProdIncentiveApp(String prodIncentiveApp) {
		this.prodIncentiveApp = prodIncentiveApp;
	}

	public int getExInt1() {
		return exInt1;
	}

	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}

	public int getExInt2() {
		return exInt2;
	}

	public void setExInt2(int exInt2) {
		this.exInt2 = exInt2;
	}

	public String getExVar1() {
		return exVar1;
	}

	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}

	public String getExVar2() {
		return exVar2;
	}

	public void setExVar2(String exVar2) {
		this.exVar2 = exVar2;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getEmpTypeId() {
		return empTypeId;
	}

	public void setEmpTypeId(int empTypeId) {
		this.empTypeId = empTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAttType() {
		return attType;
	}

	public void setAttType(String attType) {
		this.attType = attType;
	}

	public String getLmApplicable() {
		return lmApplicable;
	}

	public void setLmApplicable(String lmApplicable) {
		this.lmApplicable = lmApplicable;
	}

	public String getHalfDay() {
		return halfDay;
	}

	public void setHalfDay(String halfDay) {
		this.halfDay = halfDay;
	}

	public String getWhWork() {
		return whWork;
	}

	public void setWhWork(String whWork) {
		this.whWork = whWork;
	}

	public String getMinWorkHr() {
		return minWorkHr;
	}

	public void setMinWorkHr(String minWorkHr) {
		this.minWorkHr = minWorkHr;
	}

	public String getMinworkApplicable() {
		return minworkApplicable;
	}

	public void setMinworkApplicable(String minworkApplicable) {
		this.minworkApplicable = minworkApplicable;
	}

	public String getOtApplicable() {
		return otApplicable;
	}

	public void setOtApplicable(String otApplicable) {
		this.otApplicable = otApplicable;
	}

	public String getOtTime() {
		return otTime;
	}

	public void setOtTime(String otTime) {
		this.otTime = otTime;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getOtType() {
		return otType;
	}

	public void setOtType(String otType) {
		this.otType = otType;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getWeeklyHolidayLateAllowed() {
		return weeklyHolidayLateAllowed;
	}

	public void setWeeklyHolidayLateAllowed(int weeklyHolidayLateAllowed) {
		this.weeklyHolidayLateAllowed = weeklyHolidayLateAllowed;
	}

	public int getWeeklyHolidayLateAllowedMin() {
		return weeklyHolidayLateAllowedMin;
	}

	public void setWeeklyHolidayLateAllowedMin(int weeklyHolidayLateAllowedMin) {
		this.weeklyHolidayLateAllowedMin = weeklyHolidayLateAllowedMin;
	}

	public int getEarlyGoingAllowed() {
		return earlyGoingAllowed;
	}

	public void setEarlyGoingAllowed(int earlyGoingAllowed) {
		this.earlyGoingAllowed = earlyGoingAllowed;
	}

	public int getEarlyGoingMin() {
		return earlyGoingMin;
	}

	public void setEarlyGoingMin(int earlyGoingMin) {
		this.earlyGoingMin = earlyGoingMin;
	}

	public int getMaxLateTimeAllowed() {
		return maxLateTimeAllowed;
	}

	public void setMaxLateTimeAllowed(int maxLateTimeAllowed) {
		this.maxLateTimeAllowed = maxLateTimeAllowed;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MstEmpType [empTypeId=" + empTypeId + ", name=" + name + ", category=" + category + ", attType="
				+ attType + ", lmApplicable=" + lmApplicable + ", halfDay=" + halfDay + ", whWork=" + whWork
				+ ", minWorkHr=" + minWorkHr + ", minworkApplicable=" + minworkApplicable + ", prodIncentiveApp="
				+ prodIncentiveApp + ", otApplicable=" + otApplicable + ", otTime=" + otTime + ", details=" + details
				+ ", otType=" + otType + ", companyId=" + companyId + ", weeklyHolidayLateAllowed="
				+ weeklyHolidayLateAllowed + ", weeklyHolidayLateAllowedMin=" + weeklyHolidayLateAllowedMin
				+ ", earlyGoingAllowed=" + earlyGoingAllowed + ", earlyGoingMin=" + earlyGoingMin
				+ ", maxLateTimeAllowed=" + maxLateTimeAllowed + ", status=" + status + ", delStatus=" + delStatus
				+ ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
 
}
