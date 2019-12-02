package com.ats.hrmgt.krakpi.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "m_financial_yr")
public class FinancialYear {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fin_yr_id")
	private int finYrId;
	
	@Column(name = "fin_yr_from")
	private Date finYrFrom;
	
	@Column(name = "fin_yr_to ")
	private Date finYrTo ;
	
	@Column(name = "is_current")
	private int isCurrent;
	
	@Column(name = "del_status")
	private int delStatus;

	@Column(name = "is_active")
	private int isActive;
	
	@Column(name = "ex_int1")
	private int exInt1;

	@Column(name = "ex_int2")
	private int exInt2;

 	@Column(name = "ex_var1")
	private String exVar1;

	@Column(name = "ex_var2")
	private String exVar2;
 
	@Transient
	private boolean isError;
 
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getIsActive() {
		return isActive;
	}

	public int getFinYrId() {
		return finYrId;
	}

	public void setFinYrId(int finYrId) {
		this.finYrId = finYrId;
	}
	
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getFinYrFrom() {
		return finYrFrom;
	}

	public void setFinYrFrom(Date finYrFrom) {
		this.finYrFrom = finYrFrom;
	}

	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getFinYrTo() {
		return finYrTo;
	}

	public void setFinYrTo(Date finYrTo) {
		this.finYrTo = finYrTo;
	}

	public int getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(int isCurrent) {
		this.isCurrent = isCurrent;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getExInt1() {
		return exInt1;
	}

	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}

	public int getExInt2() {
		return exInt2;
	}

	public void setExInt2(int exInt2) {
		this.exInt2 = exInt2;
	}

	public String getExVar1() {
		return exVar1;
	}

	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}

	public String getExVar2() {
		return exVar2;
	}

	public void setExVar2(String exVar2) {
		this.exVar2 = exVar2;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	@Override
	public String toString() {
		return "FinancialYear [finYrId=" + finYrId + ", finYrFrom=" + finYrFrom + ", finYrTo=" + finYrTo
				+ ", isCurrent=" + isCurrent + ", delStatus=" + delStatus + ", isActive=" + isActive + ", exInt1="
				+ exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", isError=" + isError
				+ ", getIsActive()=" + getIsActive() + ", getFinYrId()=" + getFinYrId() + ", getFinYrFrom()="
				+ getFinYrFrom() + ", getFinYrTo()=" + getFinYrTo() + ", getIsCurrent()=" + getIsCurrent()
				+ ", getDelStatus()=" + getDelStatus() + ", getExInt1()=" + getExInt1() + ", getExInt2()=" + getExInt2()
				+ ", getExVar1()=" + getExVar1() + ", getExVar2()=" + getExVar2() + ", isError()=" + isError()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	 
}
