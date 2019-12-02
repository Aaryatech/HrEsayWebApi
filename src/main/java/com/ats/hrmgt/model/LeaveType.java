package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "leave_type")
public class LeaveType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="lv_type_id")
	private int lvTypeId ;
	
	@Column(name="company_id")
	private int companyId ;
	
	@Column(name="lv_title")
	private String lvTitle;
	
	@Column(name="lv_title_short")
	private String lvTitleShort;
	
	@Column(name="lv_sumup_id")
	private int lvSumupId;
	
	@Column(name="lv_working_hrs")
	private int lvWorkingHrs;
	
	@Column(name="lv_color")
	private String lvColor;
	
	@Column(name="is_structured")
	private int isStructured;
	
	@Column(name="lv_remarks")
	private String lvRmarks;
	
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
	public int getLvTypeId() {
		return lvTypeId;
	}

	public void setLvTypeId(int lvTypeId) {
		this.lvTypeId = lvTypeId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}


	public void setLvSumupId(int lvSumupId) {
		this.lvSumupId = lvSumupId;
	}

	public int getLvWorkingHrs() {
		return lvWorkingHrs;
	}

	public void setLvWorkingHrs(int lvWorkingHrs) {
		this.lvWorkingHrs = lvWorkingHrs;
	}


	public String getLvTitle() {
		return lvTitle;
	}

	public void setLvTitle(String lvTitle) {
		this.lvTitle = lvTitle;
	}

	public String getLvTitleShort() {
		return lvTitleShort;
	}

	public void setLvTitleShort(String lvTitleShort) {
		this.lvTitleShort = lvTitleShort;
	}

	public String getLvColor() {
		return lvColor;
	}

	public void setLvColor(String lvColor) {
		this.lvColor = lvColor;
	}

	public int getLvSumupId() {
		return lvSumupId;
	}

	public int getIsStructured() {
		return isStructured;
	}

	public void setIsStructured(int isStructured) {
		this.isStructured = isStructured;
	}

	
	public String getLvRmarks() {
		return lvRmarks;
	}

	public void setLvRmarks(String lvRmarks) {
		this.lvRmarks = lvRmarks;
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
		return "LeaveType [lvTypeId=" + lvTypeId + ", companyId=" + companyId + ", lvTitle=" + lvTitle
				+ ", lvTitleShort=" + lvTitleShort + ", lvSumupId=" + lvSumupId + ", lvWorkingHrs=" + lvWorkingHrs
				+ ", lvColor=" + lvColor + ", isStructured=" + isStructured + ", lvRmarks=" + lvRmarks + ", delStatus="
				+ delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime="
				+ makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1="
				+ exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + "]";
	}
	
	
}
	