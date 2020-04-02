package com.ats.hrmgt.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "m_skill_rates")
public class SkillRates {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int skillId;

	private String name;
	private String rate;

	private Date skillDate;

	private int delStatus;

	private int exInt1;
	private int exInt2;

	private String exVar1;
	private String exVar2;
	private int makerUserId;

	private String makerEnterDatetime;

	@Transient
	private boolean error;

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Date getSkillDate() {
		return skillDate;
	}

	public void setSkillDate(Date skillDate) {
		this.skillDate = skillDate;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
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
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
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

	@Override
	public String toString() {
		return "SkillRates [skillId=" + skillId + ", name=" + name + ", rate=" + rate + ", skillDate=" + skillDate
				+ ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1
				+ ", exVar2=" + exVar2 + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime
				+ ", error=" + error + "]";
	}

	 
}
