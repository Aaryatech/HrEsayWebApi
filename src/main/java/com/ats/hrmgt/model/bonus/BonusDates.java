package com.ats.hrmgt.model.bonus;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BonusDates {

	
	@Id
	private String uid;

	private int totalMonth;

	private int monthFrom;

	private int monthTo;

	private String yearFrom;

	private String yearTo;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getTotalMonth() {
		return totalMonth;
	}

	public void setTotalMonth(int totalMonth) {
		this.totalMonth = totalMonth;
	}

	public int getMonthFrom() {
		return monthFrom;
	}

	public void setMonthFrom(int monthFrom) {
		this.monthFrom = monthFrom;
	}

	public int getMonthTo() {
		return monthTo;
	}

	public void setMonthTo(int monthTo) {
		this.monthTo = monthTo;
	}

	public String getYearFrom() {
		return yearFrom;
	}

	public void setYearFrom(String yearFrom) {
		this.yearFrom = yearFrom;
	}

	public String getYearTo() {
		return yearTo;
	}

	public void setYearTo(String yearTo) {
		this.yearTo = yearTo;
	}

	@Override
	public String toString() {
		return "BonusDates [uid=" + uid + ", totalMonth=" + totalMonth + ", monthFrom=" + monthFrom + ", monthTo="
				+ monthTo + ", yearFrom=" + yearFrom + ", yearTo=" + yearTo + "]";
	}
	
	
	

}
