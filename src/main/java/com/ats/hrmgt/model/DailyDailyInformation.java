package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DailyDailyInformation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="uuid")
	private String uuid;
	
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="daycount")
	private int daycount;
	
	@Column(name="lv_sumup_id")
	private int lvSumupId ;
	
	@Column(name="name_sd")
	private String nameSd;
	
	@Column(name="working_min")
	private int workingMin;
	
	@Column(name="ot_min")
	private int otMin;
	
	@Column(name="late_mark")
	private int lateMark; 
	
	@Column(name="late_min")
	private int lateMin;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getDaycount() {
		return daycount;
	}

	public void setDaycount(int daycount) {
		this.daycount = daycount;
	}

	public int getLvSumupId() {
		return lvSumupId;
	}

	public void setLvSumupId(int lvSumupId) {
		this.lvSumupId = lvSumupId;
	}

	public String getNameSd() {
		return nameSd;
	}

	public void setNameSd(String nameSd) {
		this.nameSd = nameSd;
	}

	public int getWorkingMin() {
		return workingMin;
	}

	public void setWorkingMin(int workingMin) {
		this.workingMin = workingMin;
	}

	public int getOtMin() {
		return otMin;
	}

	public void setOtMin(int otMin) {
		this.otMin = otMin;
	}

	public int getLateMark() {
		return lateMark;
	}

	public void setLateMark(int lateMark) {
		this.lateMark = lateMark;
	}

	public int getLateMin() {
		return lateMin;
	}

	public void setLateMin(int lateMin) {
		this.lateMin = lateMin;
	}

	@Override
	public String toString() {
		return "DailyDailyInformation [uuid=" + uuid + ", empId=" + empId + ", daycount=" + daycount + ", lvSumupId="
				+ lvSumupId + ", nameSd=" + nameSd + ", workingMin=" + workingMin + ", otMin=" + otMin + ", lateMark="
				+ lateMark + ", lateMin=" + lateMin + "]";
	} 
	
	
	

}
