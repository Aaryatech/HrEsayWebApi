package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "emp_info")
public class EmployeeInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="emp_code")
	private String empCode;
	
	@Column(name="company_id")
	private int companyId;
	
	@Column(name="emp_cat_id")
	private int empCatId;
	
	@Column(name="emp_type_id")
	private int empTypeId;
	
	@Column(name="emp_dept_id")
	private int empDeptId;
	
	@Column(name="loc_id")
	private int locId ;
	
	@Column(name="emp_fname")
	private String empFname;
	
	@Column(name="emp_mname")
	private String empMname;
	
	@Column(name="emp_sname")
	private String empSname;
	
	@Column(name="emp_photo")
	private String empPhoto;
	
	@Column(name="emp_mobile1")
	private String empMobile1;
	
	@Column(name="emp_mobile2")
	private String empMobile2;
	
	@Column(name="emp_email")
	private String empEmail;
	
	@Column(name="emp_address_temp")
	private String empAddressTemp;
	
	@Column(name="emp_address_perm")
	private String empAddressPerm;
	
	@Column(name="emp_bloodgrp")
	private String empBloodgrp;
	
	@Column(name="emp_emergency_person1")
	private String empEmergencyPerson1;
	
	@Column(name="emp_emergency_no1")
	private String empEmergencyNo1;
	
	@Column(name="emp_emergency_person2")
	private String empEmergencyPerson2;
	
	@Column(name="emp_emergency_no2")
	private String empEmergencyNo2;
	
	@Column(name="emp_rate_perhr")
	private float empRatePerhr;
	
	@Column(name="emp_joining_date")
	private String empJoiningDate;
	
	@Column(name="emp_prev_exp_yrs")
	private int empPrevExpYrs;
	
	@Column(name="emp_prev_exp_months")
	private int empPrevExpMonths;
	
	@Column(name="emp_leaving_date")
	private String empLeavingDate;
	
	@Column(name="emp_leaving_reason")
	private String empLeavingReason;
	
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
	private boolean error;


	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

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

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getEmpCatId() {
		return empCatId;
	}

	public void setEmpCatId(int empCatId) {
		this.empCatId = empCatId;
	}

	public int getEmpTypeId() {
		return empTypeId;
	}

	public void setEmpTypeId(int empTypeId) {
		this.empTypeId = empTypeId;
	}

	public int getEmpDeptId() {
		return empDeptId;
	}

	public void setEmpDeptId(int empDeptId) {
		this.empDeptId = empDeptId;
	}

	public int getLocId() {
		return locId;
	}

	public void setLocId(int locId) {
		this.locId = locId;
	}

	public String getEmpFname() {
		return empFname;
	}

	public void setEmpFname(String empFname) {
		this.empFname = empFname;
	}

	public String getEmpMname() {
		return empMname;
	}

	public void setEmpMname(String empMname) {
		this.empMname = empMname;
	}

	public String getEmpSname() {
		return empSname;
	}

	public void setEmpSname(String empSname) {
		this.empSname = empSname;
	}

	public String getEmpPhoto() {
		return empPhoto;
	}

	

	public String getEmpMobile1() {
		return empMobile1;
	}

	public void setEmpMobile1(String empMobile1) {
		this.empMobile1 = empMobile1;
	}

	public String getEmpMobile2() {
		return empMobile2;
	}

	public void setEmpMobile2(String empMobile2) {
		this.empMobile2 = empMobile2;
	}

	public void setEmpPhoto(String empPhoto) {
		this.empPhoto = empPhoto;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpAddressTemp() {
		return empAddressTemp;
	}

	public void setEmpAddressTemp(String empAddressTemp) {
		this.empAddressTemp = empAddressTemp;
	}

	public String getEmpAddressPerm() {
		return empAddressPerm;
	}

	public void setEmpAddressPerm(String empAddressPerm) {
		this.empAddressPerm = empAddressPerm;
	}

	public String getEmpBloodgrp() {
		return empBloodgrp;
	}

	public void setEmpBloodgrp(String empBloodgrp) {
		this.empBloodgrp = empBloodgrp;
	}

	public String getEmpEmergencyPerson1() {
		return empEmergencyPerson1;
	}

	public void setEmpEmergencyPerson1(String empEmergencyPerson1) {
		this.empEmergencyPerson1 = empEmergencyPerson1;
	}

	public String getEmpEmergencyNo1() {
		return empEmergencyNo1;
	}

	public void setEmpEmergencyNo1(String empEmergencyNo1) {
		this.empEmergencyNo1 = empEmergencyNo1;
	}

	public String getEmpEmergencyPerson2() {
		return empEmergencyPerson2;
	}

	public void setEmpEmergencyPerson2(String empEmergencyPerson2) {
		this.empEmergencyPerson2 = empEmergencyPerson2;
	}

	public String getEmpEmergencyNo2() {
		return empEmergencyNo2;
	}

	public void setEmpEmergencyNo2(String empEmergencyNo2) {
		this.empEmergencyNo2 = empEmergencyNo2;
	}

	public float getEmpRatePerhr() {
		return empRatePerhr;
	}

	public void setEmpRatePerhr(float empRatePerhr) {
		this.empRatePerhr = empRatePerhr;
	}

	public String getEmpJoiningDate() {
		return empJoiningDate;
	}

	public void setEmpJoiningDate(String empJoiningDate) {
		this.empJoiningDate = empJoiningDate;
	}

	public int getEmpPrevExpYrs() {
		return empPrevExpYrs;
	}

	public void setEmpPrevExpYrs(int empPrevExpYrs) {
		this.empPrevExpYrs = empPrevExpYrs;
	}

	public int getEmpPrevExpMonths() {
		return empPrevExpMonths;
	}

	public void setEmpPrevExpMonths(int empPrevExpMonths) {
		this.empPrevExpMonths = empPrevExpMonths;
	}

	public String getEmpLeavingDate() {
		return empLeavingDate;
	}

	public void setEmpLeavingDate(String empLeavingDate) {
		this.empLeavingDate = empLeavingDate;
	}

	public String getEmpLeavingReason() {
		return empLeavingReason;
	}

	public void setEmpLeavingReason(String empLeavingReason) {
		this.empLeavingReason = empLeavingReason;
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

	@Override
	public String toString() {
		return "EmployeeInfo [empId=" + empId + ", empCode=" + empCode + ", companyId=" + companyId + ", empCatId="
				+ empCatId + ", empTypeId=" + empTypeId + ", empDeptId=" + empDeptId + ", locId=" + locId
				+ ", empFname=" + empFname + ", empMname=" + empMname + ", empSname=" + empSname + ", empPhoto="
				+ empPhoto + ", empMobile1=" + empMobile1 + ", empMobile2=" + empMobile2 + ", empEmail=" + empEmail
				+ ", empAddressTemp=" + empAddressTemp + ", empAddressPerm=" + empAddressPerm + ", empBloodgrp="
				+ empBloodgrp + ", empEmergencyPerson1=" + empEmergencyPerson1 + ", empEmergencyNo1=" + empEmergencyNo1
				+ ", empEmergencyPerson2=" + empEmergencyPerson2 + ", empEmergencyNo2=" + empEmergencyNo2
				+ ", empRatePerhr=" + empRatePerhr + ", empJoiningDate=" + empJoiningDate + ", empPrevExpYrs="
				+ empPrevExpYrs + ", empPrevExpMonths=" + empPrevExpMonths + ", empLeavingDate=" + empLeavingDate
				+ ", empLeavingReason=" + empLeavingReason + ", delStatus=" + delStatus + ", isActive=" + isActive
				+ ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2
				+ ", exVar3=" + exVar3 + "]";
	}

	
}
