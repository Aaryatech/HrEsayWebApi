package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_slabs")
public class SlabMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="slab_id")
	private int slabId ;
	
	@Column(name="sal_term_id")
	private int salTermId ;
	  
	@Column(name="min_val")
	private int minVal ;
	
	@Column(name="max_val")
	private int maxVal;
	
	@Column(name="amount")
	private int amount;
	
	@Column(name="gender")
	private int gender;

	public int getSlabId() {
		return slabId;
	}

	public void setSlabId(int slabId) {
		this.slabId = slabId;
	}

	public int getSalTermId() {
		return salTermId;
	}

	public void setSalTermId(int salTermId) {
		this.salTermId = salTermId;
	}

	public int getMinVal() {
		return minVal;
	}

	public void setMinVal(int minVal) {
		this.minVal = minVal;
	}

	public int getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(int maxVal) {
		this.maxVal = maxVal;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "SlabMaster [slabId=" + slabId + ", salTermId=" + salTermId + ", minVal=" + minVal + ", maxVal=" + maxVal
				+ ", amount=" + amount + ", gender=" + gender + "]";
	}
	   
	

}
