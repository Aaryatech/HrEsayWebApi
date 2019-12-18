package com.ats.hrmgt.model.claim;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "claim_structure_details")
public class ClaimStructureDetail {
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "clm_struct_details_id")
	private int clmStructDetailsId;

	@Column(name = "clm_struct_head_id")
	private int clmStructHeadId;

	@Column(name = "clm_type_id")
	private int clmTypeId;

	@Column(name = "clm_amt ")
	private float clmAmt;

	@Column(name = "del_status")
	private int delStatus;

	@Column(name = "is_active")
	private int isActive;

	@Column(name = "maker_user_id ")
	private int makerUserId;

	@Column(name = "maker_datetime")
	private String makerDatetime;

	@Column(name = "ex_int1")
	private int exInt1;

	@Column(name = "ex_int2")
	private int exInt2;

	@Column(name = "ex_var1")
	private String exVar1;

	@Column(name = "ex_var2")
	private String exVar2;

	public int getClmStructDetailsId() {
		return clmStructDetailsId;
	}

	public void setClmStructDetailsId(int clmStructDetailsId) {
		this.clmStructDetailsId = clmStructDetailsId;
	}

	public int getClmStructHeadId() {
		return clmStructHeadId;
	}

	public void setClmStructHeadId(int clmStructHeadId) {
		this.clmStructHeadId = clmStructHeadId;
	}

	public int getClmTypeId() {
		return clmTypeId;
	}

	public void setClmTypeId(int clmTypeId) {
		this.clmTypeId = clmTypeId;
	}

	public float getClmAmt() {
		return clmAmt;
	}

	public void setClmAmt(float clmAmt) {
		this.clmAmt = clmAmt;
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

	public String getMakerDatetime() {
		return makerDatetime;
	}

	public void setMakerDatetime(String makerDatetime) {
		this.makerDatetime = makerDatetime;
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

	@Override
	public String toString() {
		return "ClaimStructureDetail [clmStructDetailsId=" + clmStructDetailsId + ", clmStructHeadId=" + clmStructHeadId
				+ ", clmTypeId=" + clmTypeId + ", clmAmt=" + clmAmt + ", delStatus=" + delStatus + ", isActive="
				+ isActive + ", makerUserId=" + makerUserId + ", makerDatetime=" + makerDatetime + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
	
	

}
