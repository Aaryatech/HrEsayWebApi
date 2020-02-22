package com.ats.hrmgt.model.graph;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class EmpDefaultSalaryGraph {
@Id
private String id;
	private double defaultSalAmt;

	private int month;

	private int year;

	

	public double getDefaultSalAmt() {
		return defaultSalAmt;
	}

	public void setDefaultSalAmt(double defaultSalAmt) {
		this.defaultSalAmt = defaultSalAmt;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "EmpDefaultSalaryGraph [id=" + id + ", defaultSalAmt=" + defaultSalAmt + ", month=" + month + ", year="
				+ year + "]";
	}

	
	
}
