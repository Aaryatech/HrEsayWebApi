package com.ats.hrmgt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="t_emp_shift_details")
@Entity
public class EmpShiftDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
 	private int empShiftDetId;

	private int empId;
 	private int shiftId;

	private int day;

	private int locationId;

	private String shiftName;

	private String dateOfMonth;
	
	private String fromDate;
	
	private String toDate;
 
	private int month;
	 
	private int year;


	public int getEmpShiftDetId() {
		return empShiftDetId;
	}

	public void setEmpShiftDetId(int empShiftDetId) {
		this.empShiftDetId = empShiftDetId;
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
	
	

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "EmpShiftDetails [empShiftDetId=" + empShiftDetId + ", empId=" + empId + ", shiftId=" + shiftId
				+ ", day=" + day + ", locationId=" + locationId + ", shiftName=" + shiftName + ", dateOfMonth="
				+ dateOfMonth + ", fromDate=" + fromDate + ", toDate=" + toDate + ", month=" + month + ", year=" + year
				+ "]";
	}

 
}
