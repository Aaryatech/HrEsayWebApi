package com.ats.hrmgt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "holiday_master")
public class HolidayMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "holiday_id")
	private int holidayId;

	@Column(name = "holiday_date")
	private Date holidayDate;

	@Column(name = "holiday_name")
	private String holidayName;

	@Column(name = "del_status")
	private int delStatus;

	public int getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(int holidayId) {
		this.holidayId = holidayId;
	} 
	
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy") 
	public Date getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}

	public String getHolidayName() {
		return holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "HolidayMaster [holidayId=" + holidayId + ", holidayDate=" + holidayDate + ", holidayName=" + holidayName
				+ ", delStatus=" + delStatus + "]";
	}
	
	

}
