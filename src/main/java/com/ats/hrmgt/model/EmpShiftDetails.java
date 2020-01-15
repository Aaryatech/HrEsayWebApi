package com.ats.hrmgt.model;
 
public class EmpShiftDetails {
	
	private int empId;
	
	private String empName;
	
	
	private String empCode;
	
	
	private int shiftId;
	
	private int day;
	
	private int locationId;
	
	private String shiftName;
	
	private String  dateOfMonth;
	
	
	private String type;
	
	
	

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

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
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

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "EmpShiftDetails [empId=" + empId + ", empName=" + empName + ", empCode=" + empCode + ", shiftId="
				+ shiftId + ", day=" + day + ", locationId=" + locationId + ", shiftName=" + shiftName
				+ ", dateOfMonth=" + dateOfMonth + ", type=" + type + "]";
	}

	 
	 
}
