package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "m_self_grup")
public class SelfGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
 	private int selftGroupId;

 	private String name;
 
 	private int delStatus;
	@Transient
	private boolean error;
	
	
	private int exInt1;
	private int exInt2;

	private String exVar1;;
	private String exVar2;
	
	
	
	
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

	public int getSelftGroupId() {
		return selftGroupId;
	}

	public void setSelftGroupId(int selftGroupId) {
		this.selftGroupId = selftGroupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	
	
	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "SelfGroup [selftGroupId=" + selftGroupId + ", name=" + name + ", delStatus=" + delStatus + ", error="
				+ error + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2
				+ "]";
	}
 

}
