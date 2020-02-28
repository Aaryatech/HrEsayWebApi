package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LeaveCashReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "emp_id")
	private int empId;

	@Column(name = "leave_count")
	private float leaveCount;

	@Column(name = "cash")
	private float cash;

	@Column(name = "emp_code")
	private String empCode;
	
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "surname")
	private String surname;
	
	@Column(name = "paid_date")
	private String paidDate;
	
	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public float getLeaveCount() {
		return leaveCount;
	}

	public void setLeaveCount(float leaveCount) {
		this.leaveCount = leaveCount;
	}

	public float getCash() {
		return cash;
	}

	public void setCash(float cash) {
		this.cash = cash;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}

	@Override
	public String toString() {
		return "LeaveCashReport [empId=" + empId + ", leaveCount=" + leaveCount + ", cash=" + cash + ", empCode="
				+ empCode + ", firstName=" + firstName + ", surname=" + surname + ", paidDate=" + paidDate + "]";
	}
	
	

}
