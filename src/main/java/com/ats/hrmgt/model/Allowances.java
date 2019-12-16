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
				+ ", isActive=" + isActive + ", makerEnterDatetime=" + makerEnterDatetime + "]";
	}
	
	
}
