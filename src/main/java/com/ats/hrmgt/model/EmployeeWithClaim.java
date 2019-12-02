package com.ats.hrmgt.model;

import java.util.List;

public class EmployeeWithClaim {
	
	private int empId; 
	private String empName;
	List<TypeListWithAmt> list;
	
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
	public List<TypeListWithAmt> getList() {
		return list;
	}
	public void setList(List<TypeListWithAmt> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "EmployeeWithClaim [empId=" + empId + ", empName=" + empName + ", list=" + list + "]";
	}
	 
	
	

}
