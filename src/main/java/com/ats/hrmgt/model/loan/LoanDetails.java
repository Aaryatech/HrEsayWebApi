package com.ats.hrmgt.model.loan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_loan_details")

public class LoanDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int loanMainId;

	private int months;

	private int years;

	private String payType;

	private int amountEmi;
	private String remarks;

	private String loginTime;
	private String loginName;

	private String skippMonthYear;
	private double skippAmoount;
	private String skippRemark;
	
	private int delStatus;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLoanMainId() {
		return loanMainId;
	}
	public void setLoanMainId(int loanMainId) {
		this.loanMainId = loanMainId;
	}
	public int getMonths() {
		return months;
	}
	public void setMonths(int months) {
		this.months = months;
	}
	public int getYears() {
		return years;
	}
	public void setYears(int years) {
		this.years = years;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public int getAmountEmi() {
		return amountEmi;
	}
	public void setAmountEmi(int amountEmi) {
		this.amountEmi = amountEmi;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getSkippMonthYear() {
		return skippMonthYear;
	}
	public void setSkippMonthYear(String skippMonthYear) {
		this.skippMonthYear = skippMonthYear;
	}
	public double getSkippAmoount() {
		return skippAmoount;
	}
	public void setSkippAmoount(double skippAmoount) {
		this.skippAmoount = skippAmoount;
	}
	public String getSkippRemark() {
		return skippRemark;
	}
	public void setSkippRemark(String skippRemark) {
		this.skippRemark = skippRemark;
	}
	
	
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	@Override
	public String toString() {
		return "LoanDetails [id=" + id + ", loanMainId=" + loanMainId + ", months=" + months + ", years=" + years
				+ ", payType=" + payType + ", amountEmi=" + amountEmi + ", remarks=" + remarks + ", loginTime="
				+ loginTime + ", loginName=" + loginName + ", skippMonthYear=" + skippMonthYear + ", skippAmoount="
				+ skippAmoount + ", skippRemark=" + skippRemark + ", delStatus=" + delStatus + ", getId()=" + getId()
				+ ", getLoanMainId()=" + getLoanMainId() + ", getMonths()=" + getMonths() + ", getYears()=" + getYears()
				+ ", getPayType()=" + getPayType() + ", getAmountEmi()=" + getAmountEmi() + ", getRemarks()="
				+ getRemarks() + ", getLoginTime()=" + getLoginTime() + ", getLoginName()=" + getLoginName()
				+ ", getSkippMonthYear()=" + getSkippMonthYear() + ", getSkippAmoount()=" + getSkippAmoount()
				+ ", getSkippRemark()=" + getSkippRemark() + ", getDelStatus()=" + getDelStatus() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	 

}
