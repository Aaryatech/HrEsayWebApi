package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "m_emp_department")
public class EmployeeDepartment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="emp_dept_id")
	private int empDeptId ;
	
	@Column(name=" 	company_id")
	private int companyId ;
	
	@Column(name="emp_dept_name")
	private String empDeptName;
	
	@Column(name="emp_dept_short_name")
	private String empDeptShortName;
	
	@Column(name="emp_dept_remarks")
	private String empDeptRemarks;
	
	@Column(name="del_status")
	private int delStatus;
	
	@Column(name="is_active")
	private int isActive;
	
	@Column(name="maker_user_id ")
	private int makerUserId ;
	
	@Column(name="maker_enter_datetime")
	private String makerEnterDatetime;
	
	@Column(name="ex_int1")
	private int exInt1;
	
	@Column(name="ex_int2")
	private int exInt2;
	
	@Column(name="ex_int3")
	private int exInt3;
	
	@Column(name="ex_var1")
	private String exVar1; 
	
	@Column(name="ex_var2")
	private String exVar2; 
	
	@Column(name="ex_var3")
	private String exVar3;

	@Transient
	private boolean isError;
	
	public int getEmpDeptId() {
		return empDeptId;
	}


	public void setEmpDeptId(int empDeptId) {
		this.empDeptId = empDeptId;
	}


	public int getCompanyId() {
		return companyId;
	}


	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}


	public String getEmpDeptName() {
		return empDeptName;
	}


	public void setEmpDeptName(String empDeptName) {
		this.empDeptName = empDeptName;
	}


	public String getEmpDeptShortName() {
		return empDeptShortName;
	}


	public void setEmpDeptShortName(String empDeptShortName) {
		this.empDeptShortName = empDeptShortName;
	}


	public String getEmpDeptRemarks() {
		return empDeptRemarks;
	}


	public void setEmpDeptRemarks(String empDeptRemarks) {
		this.empDeptRemarks = empDeptRemarks;
	}


	public int getDelStatus() {
		return delStatus;
	}


	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}


	public int getIsActive() {
		return isActive;
	}


	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}


	public int getMakerUserId() {
		return makerUserId;
	}


	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
	}


	public String getMakerEnterDatetime() {
		return makerEnterDatetime;
	}


	public void setMakerEnterDatetime(String makerEnterDatetime) {
		this.makerEnterDatetime = makerEnterDatetime;
	}


	public int getExInt1() {
		return exInt1;
	}


	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}


	public int getExInt2() {
		return exInt2;
	}


	public void setExInt2(int exInt2) {
		this.exInt2 = exInt2;
	}


	public int getExInt3() {
		return exInt3;
	}


	public void setExInt3(int exInt3) {
		this.exInt3 = exInt3;
	}


	public String getExVar1() {
		return exVar1;
	}


	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}


	public String getExVar2() {
		return exVar2;
	}


	public void setExVar2(String exVar2) {
		this.exVar2 = exVar2;
	}


	public String getExVar3() {
		return exVar3;
	}


	public void setExVar3(String exVar3) {
		this.exVar3 = exVar3;
	}


	public boolean isError() {
		return isError;
	}


	public void setError(boolean isError) {
		this.isError = isError;
	}


	@Override
	public String toString() {
		return "EmployeeDepartment [empDeptId=" + empDeptId + ", companyId=" + companyId + ", empDeptName="
				+ empDeptName + ", empDeptShortName=" + empDeptShortName + ", empDeptRemarks=" + empDeptRemarks
				+ ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId
				+ ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
				+ ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3
				+ ", isError=" + isError + "]";
	}
	
	
}
