package com.ats.hrmgt.model;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_weekoffshift")
public class WeeklyOffShit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "month ")
	private int month;

	@Column(name = "year")
	private int year;

	@Column(name = "weekofffromdate")
	private String weekofffromdate;

	@Column(name = "weekoffshiftdate")
	private String weekoffshiftdate;

	@Column(name = "cmp_id")
	private int cmpId;

	@Column(name = "reason")
	private String reason;

	@Column(name = "login_time")
	private String loginTime;

	@Column(name = "location_id")
	private int locationId;
	
	@Column(name = "del_status")
	private int delStatus;

	@Column(name = "emp_id")
	private int empId;
	
	 
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

	public String getWeekofffromdate() {
		return weekofffromdate;
	}

	public void setWeekofffromdate(String weekofffromdate) {
		this.weekofffromdate = weekofffromdate;
	}

	public String getWeekoffshiftdate() {
		return weekoffshiftdate;
	}

	public void setWeekoffshiftdate(String weekoffshiftdate) {
		this.weekoffshiftdate = weekoffshiftdate;
	}

	public int getCmpId() {
		return cmpId;
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

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	@Override
	public String toString() {
		return "WeeklyOffShit [id=" + id + ", month=" + month + ", year=" + year + ", weekofffromdate="
				+ weekofffromdate + ", weekoffshiftdate=" + weekoffshiftdate + ", cmpId=" + cmpId + ", reason=" + reason
				+ ", loginTime=" + loginTime + ", locationId=" + locationId + ", delStatus=" + delStatus + ", empId="
				+ empId + "]";
	}

	 
	 
}
