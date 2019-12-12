package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InfoForUploadAttendance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="total_emp")
	private int totalEmp ;
	
	@Column(name="date_diff")
	private int dateDiff ;
	 
	@Column(name="updated_by_step1")
	private int updatedByStep1; 
	
	@Column(name="updated_by_file")
	private int updatedByFile;

	public int getTotalEmp() {
		return totalEmp;
	}

	public void setTotalEmp(int totalEmp) {
		this.totalEmp = totalEmp;
	}

	public int getDateDiff() {
		return dateDiff;
	}

	public void setDateDiff(int dateDiff) {
		this.dateDiff = dateDiff;
	}
 
	public int getUpdatedByFile() {
		return updatedByFile;
	}

	public void setUpdatedByFile(int updatedByFile) {
		this.updatedByFile = updatedByFile;
	}

	public int getUpdatedByStep1() {
		return updatedByStep1;
	}

	public void setUpdatedByStep1(int updatedByStep1) {
		this.updatedByStep1 = updatedByStep1;
	}

	@Override
	public String toString() {
		return "InfoForUploadAttendance [totalEmp=" + totalEmp + ", dateDiff=" + dateDiff + ", updatedByStep1="
				+ updatedByStep1 + ", updatedByFile=" + updatedByFile + "]";
	}
	
	
}
