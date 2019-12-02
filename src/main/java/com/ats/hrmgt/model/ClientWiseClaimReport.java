package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ClientWiseClaimReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="cust_id")
	private int custId ; 
	
	@Column(name="cust_name")
	private String custName;
	
	@Column(name="claim_amount")
	private float claimAmount;

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public float getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(float claimAmount) {
		this.claimAmount = claimAmount;
	}

	@Override
	public String toString() {
		return "ClientWiseClaimReport [custId=" + custId + ", custName=" + custName + ", claimAmount=" + claimAmount
				+ "]";
	}
	
	

}
