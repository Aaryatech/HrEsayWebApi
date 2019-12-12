package com.ats.hrmgt.model;
 

public class EmpJsonData {
	 
	private int empId ; 
	private String empCode ; 
	private String cmpCode; 
	private int empType; 
	private int departId; 
	private int designationId; 
	private int locationId; 
	private String firstName ; 
	private String middleName; 
	private String surname; 
	private int isEmp;  
	private int currentShiftid;  
	private String empCategory; 
	private int salaryTypeId;  
	private String salBasis;  
	private String cmpJoiningDate;
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getCmpCode() {
		return cmpCode;
	}
	public void setCmpCode(String cmpCode) {
		this.cmpCode = cmpCode;
	}
	public int getEmpType() {
		return empType;
	}
	public void setEmpType(int empType) {
		this.empType = empType;
	}
	public int getDepartId() {
		return departId;
	}
	public void setDepartId(int departId) {
		this.departId = departId;
	}
	public int getDesignationId() {
		return designationId;
	}
	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getIsEmp() {
		return isEmp;
	}
	public void setIsEmp(int isEmp) {
		this.isEmp = isEmp;
	}
	public int getCurrentShiftid() {
		return currentShiftid;
	}
	public void setCurrentShiftid(int currentShiftid) {
		this.currentShiftid = currentShiftid;
	}
	public String getEmpCategory() {
		return empCategory;
	}
	public void setEmpCategory(String empCategory) {
		this.empCategory = empCategory;
	}
	public int getSalaryTypeId() {
		return salaryTypeId;
	}
	public void setSalaryTypeId(int salaryTypeId) {
		this.salaryTypeId = salaryTypeId;
	}
	public String getSalBasis() {
		return salBasis;
	}
	public void setSalBasis(String salBasis) {
		this.salBasis = salBasis;
	}
	public String getCmpJoiningDate() {
		return cmpJoiningDate;
	}
	public void setCmpJoiningDate(String cmpJoiningDate) {
		this.cmpJoiningDate = cmpJoiningDate;
	}
	@Override
	public String toString() {
		return "EmpJsonData [empId=" + empId + ", empCode=" + empCode + ", cmpCode=" + cmpCode + ", empType=" + empType
				+ ", departId=" + departId + ", designationId=" + designationId + ", locationId=" + locationId
				+ ", firstName=" + firstName + ", middleName=" + middleName + ", surname=" + surname + ", isEmp="
				+ isEmp + ", currentShiftid=" + currentShiftid + ", empCategory=" + empCategory + ", salaryTypeId="
				+ salaryTypeId + ", salBasis=" + salBasis + ", cmpJoiningDate=" + cmpJoiningDate + "]";
	}
	
	

}
