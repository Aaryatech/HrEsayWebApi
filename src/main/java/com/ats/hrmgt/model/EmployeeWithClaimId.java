package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmployeeWithClaimId {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="claim_id")
	private int claimId ;
	
	@Column(name="ca_head_id")
	private int caHeadId ;
	
	@Column(name="claim_type_id")
	private int claimTypeId;
	
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="emp_name")
	private String empName;
	
	@Column(name="claim_amount")
	private float claimAmount;

	public int getClaimId() {
		return claimId;
	}

	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	public int getCaHeadId() {
		return caHeadId;
	}

	public void setCaHeadId(int caHeadId) {
		this.caHeadId = caHeadId;
	}

	public int getClaimTypeId() {
		return claimTypeId;
	}

	public void setClaimTypeId(int claimTypeId) {
		this.claimTypeId = claimTypeId;
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

	public float getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(float claimAmount) {
		this.claimAmount = claimAmount;
	}

	@Override
	public String toString() {
		return "EmployeeWithClaimId [claimId=" + claimId + ", caHeadId=" + caHeadId + ", claimTypeId=" + claimTypeId
				+ ", empId=" + empId + ", empName=" + empName + ", claimAmount=" + claimAmount + "]";
	}
	 
	
	

}
