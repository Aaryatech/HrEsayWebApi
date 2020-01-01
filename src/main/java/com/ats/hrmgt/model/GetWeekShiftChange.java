package com.ats.hrmgt.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class GetWeekShiftChange {
	
	@Id
	private int id;

 	private int month;

 	private int year;

 	private Date weekofffromdate;

	private Date weekoffshiftdate;

	private int cmpId;

	private String reason;

	private String loginTime;

	private int locationId;
	
	private int delStatus;
	
	private String locName;
 
	 
	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	 
	public int getCmpId() {
		return cmpId;
	}

	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
 	public Date getWeekofffromdate() {
		return weekofffromdate;
	}

	public void setWeekofffromdate(Date weekofffromdate) {
		this.weekofffromdate = weekofffromdate;
	}

	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
 	public Date getWeekoffshiftdate() {
		return weekoffshiftdate;
	}

	public void setWeekoffshiftdate(Date weekoffshiftdate) {
		this.weekoffshiftdate = weekoffshiftdate;
	}

	public void setCmpId(int cmpId) {
		this.cmpId = cmpId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	@Override
	public String toString() {
		return "GetWeekShiftChange [id=" + id + ", month=" + month + ", year=" + year + ", weekofffromdate="
				+ weekofffromdate + ", weekoffshiftdate=" + weekoffshiftdate + ", cmpId=" + cmpId + ", reason=" + reason
				+ ", loginTime=" + loginTime + ", locationId=" + locationId + ", delStatus=" + delStatus + ", locName="
				+ locName + "]";
	}

	 

}
