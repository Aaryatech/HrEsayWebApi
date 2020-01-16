package com.ats.hrmgt.model;

import java.util.List;

public class EmpShiftDetails {

	private int empId;

 

	private int shiftId;

	private int day;

	private int locationId;

	private String shiftName;

	private String dateOfMonth;

	private int monthType;

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getDateOfMonth() {
		return dateOfMonth;
	}

	public void setDateOfMonth(String dateOfMonth) {
		this.dateOfMonth = dateOfMonth;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}
 
	public int getShiftId() {
		return shiftId;
	}

	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonthType() {
		return monthType;
	}

	public void setMonthType(int monthType) {
		this.monthType = monthType;
	}

	@Override
	public String toString() {
		return "EmpShiftDetails [empId=" + empId + ", shiftId=" + shiftId + ", day=" + day + ", locationId="
				+ locationId + ", shiftName=" + shiftName + ", dateOfMonth=" + dateOfMonth + ", monthType=" + monthType
				+ "]";
	}

	 
}
