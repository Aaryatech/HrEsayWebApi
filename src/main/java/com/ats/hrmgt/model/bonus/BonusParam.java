package com.ats.hrmgt.model.bonus;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BonusParam {
	
	
	@Id
	private String uid;

	private double totalBasicCal;

	private double totalAllowance;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public double getTotalBasicCal() {
		return totalBasicCal;
	}

	public void setTotalBasicCal(double totalBasicCal) {
		this.totalBasicCal = totalBasicCal;
	}

	public double getTotalAllowance() {
		return totalAllowance;
	}

	public void setTotalAllowance(double totalAllowance) {
		this.totalAllowance = totalAllowance;
	}

	@Override
	public String toString() {
		return "BonusParam [uid=" + uid + ", totalBasicCal=" + totalBasicCal + ", totalAllowance=" + totalAllowance
				+ "]";
	}
	
	

}
