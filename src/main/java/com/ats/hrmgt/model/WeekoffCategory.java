package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
 
@Entity
@Table(name = "weekoff_category")

public class WeekoffCategory {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "wo_cat_id")
	private int woCatId;

	@Column(name = "wo_cat_name")
	private String woCatName;

	@Column(name = " wo_cat_short_name ")
	private String woCatShortName;

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

	@Column(name = "ex_var1")
	private String exVar1;

	@Column(name = "ex_var2")
	private String exVar2;

	@Transient
	private boolean isError;

	@Column(name = "company_id ")
	private int companyId;

	@Column(name = "remark")
	private String remark;

	public int getWoCatId() {
		return woCatId;
	}

	public void setWoCatId(int woCatId) {
		this.woCatId = woCatId;
	}

	public String getWoCatName() {
		return woCatName;
	}

	public void setWoCatName(String woCatName) {
		this.woCatName = woCatName;
	}

	public String getWoCatShortName() {
		return woCatShortName;
	}

	public void setWoCatShortName(String woCatShortName) {
		this.woCatShortName = woCatShortName;
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

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "WeekoffCategory [woCatId=" + woCatId + ", woCatName=" + woCatName + ", woCatShortName=" + woCatShortName
				+ ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId
				+ ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
				+ ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", isError=" + isError + ", companyId=" + companyId
				+ ", remark=" + remark + "]";
	}
	
	
	
	

}
