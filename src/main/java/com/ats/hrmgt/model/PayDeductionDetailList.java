package com.ats.hrmgt.model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class PayDeductionDetailList {
	@Id
	private int dedId; 
	private int empId;
	private String empCode;
	private String typeName;
	private double dedRate;
	private int dedOccurence;
	private int dedTotal;
	private int month;
	private int year;
	private String empName;
	private int dedTypeId;
	private String dedRemark;
	
	public int getDedId() {
		return dedId;
	}
	public void setDedId(int dedId) {
		this.dedId = dedId;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public double getDedRate() {
		return dedRate;
	}
	public void setDedRate(double dedRate) {
		this.dedRate = dedRate;
	}
	public int getDedOccurence() {
		return dedOccurence;
	}
	public void setDedOccurence(int dedOccurence) {
		this.dedOccurence = dedOccurence;
	}
	public int getDedTotal() {
		return dedTotal;
	}
	public void setDedTotal(int dedTotal) {
		this.dedTotal = dedTotal;
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
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getDedTypeId() {
		return dedTypeId;
	}
	public void setDedTypeId(int dedTypeId) {
		this.dedTypeId = dedTypeId;
	}
	public int getEmpId() {
		return empId;
	}
	public String getDedRemark() {
		return dedRemark;
	}
	public void setDedRemark(String dedRemark) {
		this.dedRemark = dedRemark;
	}
	@Override
	public String toString() {
		return "PayDeductionDetailList [dedId=" + dedId + ", empId=" + empId + ", empCode=" + empCode + ", typeName="
				+ typeName + ", dedRate=" + dedRate + ", dedOccurence=" + dedOccurence + ", dedTotal=" + dedTotal
				+ ", month=" + month + ", year=" + year + ", empName=" + empName + ", dedTypeId=" + dedTypeId
				+ ", dedRemark=" + dedRemark + "]";
	}
	
	
}
