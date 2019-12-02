package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "m_emp_category")
public class EmployeeCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="emp_cat_id")
	private int empCatId ;
	
	@Column(name="company_id")
	private int companyId ;
	
	@Column(name="emp_cat_name")
	private String empCatName;
	
	@Column(name="emp_cat_short_name")
	private String empCatShortName;
	
	@Column(name="emp_cat_remarks")
	private String empCatRemarks;
	
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

	public int getEmpCatId() {
		return empCatId;
	}



	public void setEmpCatId(int empCatId) {
		this.empCatId = empCatId;
	}



	public int getCompanyId() {
		return companyId;
	}



	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}



	public String getEmpCatName() {
		return empCatName;
	}



	public void setEmpCatName(String empCatName) {
		this.empCatName = empCatName;
	}



	public String getEmpCatShortName() {
		return empCatShortName;
	}



	public void setEmpCatShortName(String empCatShortName) {
		this.empCatShortName = empCatShortName;
	}



	public String getEmpCatRemarks() {
		return empCatRemarks;
	}



	public void setEmpCatRemarks(String empCatRemarks) {
		this.empCatRemarks = empCatRemarks;
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
		return "EmployeeCategory [empCatId=" + empCatId + ", companyId=" + companyId + ", empCatName=" + empCatName
				+ ", empCatShortName=" + empCatShortName + ", empCatRemarks=" + empCatRemarks + ", delStatus="
				+ delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime="
				+ makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1="
				+ exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", isError=" + isError + "]";
	}
	
	
}
