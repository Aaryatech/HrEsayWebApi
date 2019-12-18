package com.ats.hrmgt.model.claim;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "claim_authority")
public class ClaimAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ca_pkey ")

	private int caPkey;

	@Column(name = "emp_id")
	private int empId;

	@Column(name = "company_id")
	private int companyId;

	@Column(name = "ca_ini_auth_emp_id")
	private int caIniAuthEmpId;

	@Column(name = "ca_fin_auth_emp_id")
	private int caFinAuthEmpId;

	@Column(name = "ca_rep_to_emp_ids")
	private String caRepToEmpIds;

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

	public int getCaPkey() {
		return caPkey;
	}

	public void setCaPkey(int caPkey) {
		this.caPkey = caPkey;
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

	public int getCaIniAuthEmpId() {
		return caIniAuthEmpId;
	}

	public void setCaIniAuthEmpId(int caIniAuthEmpId) {
		this.caIniAuthEmpId = caIniAuthEmpId;
	}

	public int getCaFinAuthEmpId() {
		return caFinAuthEmpId;
	}

	public void setCaFinAuthEmpId(int caFinAuthEmpId) {
		this.caFinAuthEmpId = caFinAuthEmpId;
	}

	public String getCaRepToEmpIds() {
		return caRepToEmpIds;
	}

	public void setCaRepToEmpIds(String caRepToEmpIds) {
		this.caRepToEmpIds = caRepToEmpIds;
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
		return "ClaimAuthority [caPkey=" + caPkey + ", empId=" + empId + ", companyId=" + companyId
				+ ", caIniAuthEmpId=" + caIniAuthEmpId + ", caFinAuthEmpId=" + caFinAuthEmpId + ", caRepToEmpIds="
				+ caRepToEmpIds + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId
				+ ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
				+ ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", error="
				+ error + "]";
	}

}
