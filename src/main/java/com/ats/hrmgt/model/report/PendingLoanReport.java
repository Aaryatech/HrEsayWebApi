package com.ats.hrmgt.model.report;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PendingLoanReport {

	@Id
	private String id;
	private String empCode;
	private String firstName;
	private String middleName;
	private String surname;
	private String designation;
	private String depatarment;
	private float currentTotpaid;
	private float currentOutstanding;
	private float loanAmt;
	private float loanEmi;
	private Date loanRepayStart;
	private Date loanRepayEnd;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepatarment() {
		return depatarment;
	}
	public void setDepatarment(String depatarment) {
		this.depatarment = depatarment;
	}
	public float getCurrentTotpaid() {
		return currentTotpaid;
	}
	public void setCurrentTotpaid(float currentTotpaid) {
		this.currentTotpaid = currentTotpaid;
	}
	public float getCurrentOutstanding() {
		return currentOutstanding;
	}
	public void setCurrentOutstanding(float currentOutstanding) {
		this.currentOutstanding = currentOutstanding;
	}
	public float getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(float loanAmt) {
		this.loanAmt = loanAmt;
	}
	public float getLoanEmi() {
		return loanEmi;
	}
	public void setLoanEmi(float loanEmi) {
		this.loanEmi = loanEmi;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getLoanRepayStart() {
		return loanRepayStart;
	}
	public void setLoanRepayStart(Date loanRepayStart) {
		this.loanRepayStart = loanRepayStart;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getLoanRepayEnd() {
		return loanRepayEnd;
	}
	public void setLoanRepayEnd(Date loanRepayEnd) {
		this.loanRepayEnd = loanRepayEnd;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	@Override
	public String toString() {
		return "PendingLoanReport [id=" + id + ", empCode=" + empCode + ", firstName=" + firstName + ", middleName="
				+ middleName + ", surname=" + surname + ", designation=" + designation + ", depatarment=" + depatarment
				+ ", currentTotpaid=" + currentTotpaid + ", currentOutstanding=" + currentOutstanding + ", loanAmt="
				+ loanAmt + ", loanEmi=" + loanEmi + ", loanRepayStart=" + loanRepayStart + ", loanRepayEnd="
				+ loanRepayEnd + "]";
	}
	
	
}
