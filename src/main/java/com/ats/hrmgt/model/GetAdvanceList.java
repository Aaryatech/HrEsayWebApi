package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GetAdvanceList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="adv_amount")
	private float advAmount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public float getAdvAmount() {
		return advAmount;
	}

	public void setAdvAmount(float advAmount) {
		this.advAmount = advAmount;
	}

	@Override
	public String toString() {
		return "GetAdvanceList [id=" + id + ", empId=" + empId + ", advAmount=" + advAmount + "]";
	}

	 
	

}
