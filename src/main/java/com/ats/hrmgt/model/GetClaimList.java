package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GetClaimList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ca_head_id")
	private int caHeadId;
	
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="claim_amount")
	private float claimAmount;

	public int getCaHeadId() {
		return caHeadId;
	}

	public void setCaHeadId(int caHeadId) {
		this.caHeadId = caHeadId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public float getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(float claimAmount) {
		this.claimAmount = claimAmount;
	}

	@Override
	public String toString() {
		return "GetClaimList [caHeadId=" + caHeadId + ", empId=" + empId + ", claimAmount=" + claimAmount + "]";
	}
	
	
}
