package com.ats.hrmgt.krakpi.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "t_kra")
public class Kra {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "kra_id")
	private int kraId;
	
	@Column(name = "emp_id")
 	private int empId;
	
	@Column(name = "year_id")
 	private int yearId;
	
	@Column(name = "kra_title")
 	private String kraTitle ;
	
	@Column(name = "remark")
	private String remark; 
	
	@Column(name = "accepted_flag")
	private int acceptedFlag ;

	@Column(name = "approved_flag")
	private int approvedFlag ;

	@Column(name = "approved_by")
	private int approvedBy;
	
	@Column(name = "accepted_date")
	private Date acceptedDate ;
	
	@Column(name = "approved_date")
	private Date approvedDate  ;
	

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
	private boolean isError;

	public int getKraId() {
		return kraId;
	}

	public void setKraId(int kraId) {
		this.kraId = kraId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getYearId() {
		return yearId;
	}

	public void setYearId(int yearId) {
		this.yearId = yearId;
	}

	public String getKraTitle() {
		return kraTitle;
	}

	public void setKraTitle(String kraTitle) {
		this.kraTitle = kraTitle;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getAcceptedFlag() {
		return acceptedFlag;
	}

	public void setAcceptedFlag(int acceptedFlag) {
		this.acceptedFlag = acceptedFlag;
	}

	public int getApprovedFlag() {
		return approvedFlag;
	}

	public void setApprovedFlag(int approvedFlag) {
		this.approvedFlag = approvedFlag;
	}

	public int getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(int approvedBy) {
		this.approvedBy = approvedBy;
	}

	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
 	public Date getAcceptedDate() {
		return acceptedDate;
	}

	public void setAcceptedDate(Date acceptedDate) {
		this.acceptedDate = acceptedDate;
	}
	
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
 	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
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
		return "Kra [kraId=" + kraId + ", empId=" + empId + ", yearId=" + yearId + ", kraTitle=" + kraTitle
				+ ", remark=" + remark + ", acceptedFlag=" + acceptedFlag + ", approvedFlag=" + approvedFlag
				+ ", approvedBy=" + approvedBy + ", acceptedDate=" + acceptedDate + ", approvedDate=" + approvedDate
				+ ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId
				+ ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
				+ ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3
				+ ", isError=" + isError + ", getKraId()=" + getKraId() + ", getEmpId()=" + getEmpId()
				+ ", getYearId()=" + getYearId() + ", getKraTitle()=" + getKraTitle() + ", getRemark()=" + getRemark()
				+ ", getAcceptedFlag()=" + getAcceptedFlag() + ", getApprovedFlag()=" + getApprovedFlag()
				+ ", getApprovedBy()=" + getApprovedBy() + ", getAcceptedDate()=" + getAcceptedDate()
				+ ", getApprovedDate()=" + getApprovedDate() + ", getDelStatus()=" + getDelStatus() + ", getIsActive()="
				+ getIsActive() + ", getMakerUserId()=" + getMakerUserId() + ", getMakerEnterDatetime()="
				+ getMakerEnterDatetime() + ", getExInt1()=" + getExInt1() + ", getExInt2()=" + getExInt2()
				+ ", getExInt3()=" + getExInt3() + ", getExVar1()=" + getExVar1() + ", getExVar2()=" + getExVar2()
				+ ", getExVar3()=" + getExVar3() + ", isError()=" + isError() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	


}
