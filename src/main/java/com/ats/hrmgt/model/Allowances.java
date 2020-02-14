package com.ats.hrmgt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_allowances")
public class Allowances {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int allowanceId;
	private String name;
	private String shortName;
	private int shortNo;
	private String description;
	private String isTaxable;
	private int delStatus;
	private int isActive;
	private String makerEnterDatetime;

	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;

	private float grossSalPer;

	private float taxPer;

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

	public float getGrossSalPer() {
		return grossSalPer;
	}

	public void setGrossSalPer(float grossSalPer) {
		this.grossSalPer = grossSalPer;
	}

	public float getTaxPer() {
		return taxPer;
	}

	public void setTaxPer(float taxPer) {
		this.taxPer = taxPer;
	}

	public int getAllowanceId() {
		return allowanceId;
	}

	public void setAllowanceId(int allowanceId) {
		this.allowanceId = allowanceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMakerEnterDatetime() {
		return makerEnterDatetime;
	}

	public void setMakerEnterDatetime(String makerEnterDatetime) {
		this.makerEnterDatetime = makerEnterDatetime;
	}

	public int getShortNo() {
		return shortNo;
	}

	public void setShortNo(int shortNo) {
		this.shortNo = shortNo;
	}

	public String getIsTaxable() {
		return isTaxable;
	}

	public void setIsTaxable(String isTaxable) {
		this.isTaxable = isTaxable;
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

	@Override
	public String toString() {
		return "Allowances [allowanceId=" + allowanceId + ", name=" + name + ", shortName=" + shortName + ", shortNo="
				+ shortNo + ", description=" + description + ", isTaxable=" + isTaxable + ", delStatus=" + delStatus
				+ ", isActive=" + isActive + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", grossSalPer=" + grossSalPer
				+ ", taxPer=" + taxPer + "]";
	}

}
