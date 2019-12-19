package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_emp_bank_info")
public class TblEmpBankInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="bank_info_id")
	private int bankInfoId;
	
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="acc_no")
	private String accNo;
	
	@Column(name="bank_id")
	private int bankId;

	public int getBankInfoId() {
		return bankInfoId;
	}

	public void setBankInfoId(int bankInfoId) {
		this.bankInfoId = bankInfoId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBanKId(int bankId) {
		this.bankId = bankId;
	}

	@Override
	public String toString() {
		return "TblEmpBankInfo [bankInfoId=" + bankInfoId + ", empId=" + empId + ", accNo=" + accNo + ", bankId="
				+ bankId + "]";
	}
	
	
}
