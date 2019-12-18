package com.ats.hrmgt.model.claim;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GetEmployeeAuthorityWise {
	
	@Id
	private int empId;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	@Override
	public String toString() {
		return "GetEmployeeAuthorityWise [empId=" + empId + ", getEmpId()=" + getEmpId() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	
	

}
