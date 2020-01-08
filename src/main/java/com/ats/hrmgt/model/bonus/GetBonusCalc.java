package com.ats.hrmgt.model.bonus;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GetBonusCalc {

	
	@Id
	private int bonusCalcId;

 	private int bonusId;
	
	private int empId;
	
	private String empName;
	
	private double netBonusAmt;
	
	private String bonusTitle;

	private String companyEmpCode;
	
	private Date paidBonusDate;

	public int getBonusCalcId() {
		return bonusCalcId;
	}

	public void setBonusCalcId(int bonusCalcId) {
		this.bonusCalcId = bonusCalcId;
	}

	public int getBonusId() {
		return bonusId;
	}

	public void setBonusId(int bonusId) {
		this.bonusId = bonusId;
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

	public double getNetBonusAmt() {
		return netBonusAmt;
	}

	public void setNetBonusAmt(double netBonusAmt) {
		this.netBonusAmt = netBonusAmt;
	}

	public String getBonusTitle() {
		return bonusTitle;
	}

	public void setBonusTitle(String bonusTitle) {
		this.bonusTitle = bonusTitle;
	}

	public String getCompanyEmpCode() {
		return companyEmpCode;
	}

	public void setCompanyEmpCode(String companyEmpCode) {
		this.companyEmpCode = companyEmpCode;
	}

	public Date getPaidBonusDate() {
		return paidBonusDate;
	}

	public void setPaidBonusDate(Date paidBonusDate) {
		this.paidBonusDate = paidBonusDate;
	}

	@Override
	public String toString() {
		return "GetBonusCalc [bonusCalcId=" + bonusCalcId + ", bonusId=" + bonusId + ", empId=" + empId + ", empName="
				+ empName + ", netBonusAmt=" + netBonusAmt + ", bonusTitle=" + bonusTitle + ", companyEmpCode="
				+ companyEmpCode + ", paidBonusDate=" + paidBonusDate + "]";
	}
	
	
}
