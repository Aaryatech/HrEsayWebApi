package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "leave_authority")
public class LeaveAuthority {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "la_pkey ")

	private int laPkey;

	@Column(name = "emp_id")
	private int empId;

	@Column(name = "company_id")
	private int companyId;

	@Column(name = "ini_auth_emp_id")
	private int iniAuthEmpId;

	@Column(name = "fin_auth_emp_id")
	private int finAuthEmpId;

	@Column(name = "rep_to_emp_ids")
	private String repToEmpIds;

	@Column(name = "del_status")
	private int delStatus;

	@Column(name = "is_active")
	private int isActive;

	@Column(name = "maker_user_id ")
	private int makerUserId;

	@Column(name = "maker_enter_datetime")
	private String makerEnterDatetime;

	@Column(name = "ex_int1")
	private int exInt1;

	@Column(name = "ex_int2")
	private int exInt2;

	@Column(name = "ex_int3")
	private int exInt3;

	@Column(name = "ex_var1")
	private String exVar1;

	@Column(name = "ex_var2")
	private String exVar2;

	@Column(name = "ex_var3")
	private String exVar3;

	@Transient
	private boolean error;

	public int getLaPkey() {
		return laPkey;
	}

	public void setLaPkey(int laPkey) {
		this.laPkey = laPkey;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getIniAuthEmpId() {
		return iniAuthEmpId;
	}

	public void setIniAuthEmpId(int iniAuthEmpId) {
		this.iniAuthEmpId = iniAuthEmpId;
	}

	public int getFinAuthEmpId() {
		return finAuthEmpId;
	}

	public void setFinAuthEmpId(int finAuthEmpId) {
		this.finAuthEmpId = finAuthEmpId;
	}

	public String getRepToEmpIds() {
		return repToEmpIds;
	}

	public void setRepToEmpIds(String repToEmpIds) {
		this.repToEmpIds = repToEmpIds;
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
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "LeaveAuthority [laPkey=" + laPkey + ", empId=" + empId + ", companyId=" + companyId + ", iniAuthEmpId="
				+ iniAuthEmpId + ", finAuthEmpId=" + finAuthEmpId + ", repToEmpIds=" + repToEmpIds + ", delStatus="
				+ delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime="
				+ makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1="
				+ exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", error=" + error + "]";
	}

}
