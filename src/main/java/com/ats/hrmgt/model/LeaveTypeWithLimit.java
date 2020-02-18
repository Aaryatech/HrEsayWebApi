package com.ats.hrmgt.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LeaveTypeWithLimit {
	
	@Id
    private int lvTypeId; 
	private int lvsId; 
	private String lvTitleShort; 
	private int maxNoDays;
	private int minNoDays;
	
	public int getLvTypeId() {
		return lvTypeId;
	}
	public void setLvTypeId(int lvTypeId) {
		this.lvTypeId = lvTypeId;
	}
	public int getLvsId() {
		return lvsId;
	}
	public void setLvsId(int lvsId) {
		this.lvsId = lvsId;
	}
	public String getLvTitleShort() {
		return lvTitleShort;
	}
	public void setLvTitleShort(String lvTitleShort) {
		this.lvTitleShort = lvTitleShort;
	}
	public int getMaxNoDays() {
		return maxNoDays;
	}
	public void setMaxNoDays(int maxNoDays) {
		this.maxNoDays = maxNoDays;
	}
	public int getMinNoDays() {
		return minNoDays;
	}
	public void setMinNoDays(int minNoDays) {
		this.minNoDays = minNoDays;
	}
	@Override
	public String toString() {
		return "LeaveTypeWithLimit [lvTypeId=" + lvTypeId + ", lvsId=" + lvsId + ", lvTitleShort=" + lvTitleShort
				+ ", maxNoDays=" + maxNoDays + ", minNoDays=" + minNoDays + "]";
	}
	
	
}
