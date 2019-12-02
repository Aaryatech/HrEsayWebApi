package com.ats.hrmgt.krakpi.model;
 

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class GetKraReviewList {
	@Id
    private int kraReviewId;
	
 	private int kraId;
	
 	private String review; 
	
	private int delStatus;

	private int isActive;
	
	private int makerUserId;

	private Date makerEnterDatetime;

	private int exInt1;

	private int exInt2;

	private int exInt3;

	private String exVar1;

	private String exVar2;

	private String exVar3;
	
	private int empId;
	
	private String empFname;
	
	private String empSname;
	
	

	public int getKraReviewId() {
		return kraReviewId;
	}



	public void setKraReviewId(int kraReviewId) {
		this.kraReviewId = kraReviewId;
	}



	public int getKraId() {
		return kraId;
	}



	public void setKraId(int kraId) {
		this.kraId = kraId;
	}



	public String getReview() {
		return review;
	}



	public void setReview(String review) {
		this.review = review;
	}



	public int getDelStatus() {
		return delStatus;
	}



	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}



	public int getIsActive() {
		return isActive;
	}



	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}



	public int getMakerUserId() {
		return makerUserId;
	}



	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
	}


	@JsonFormat(locale = "Locale.ENGLISH", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy hh:mm:ss a")// 
 	public Date getMakerEnterDatetime() {
		return makerEnterDatetime;
	}
 
	public void setMakerEnterDatetime(Date makerEnterDatetime) {
		this.makerEnterDatetime = makerEnterDatetime;
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



	public int getExInt3() {
		return exInt3;
	}



	public void setExInt3(int exInt3) {
		this.exInt3 = exInt3;
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



	public String getExVar3() {
		return exVar3;
	}



	public void setExVar3(String exVar3) {
		this.exVar3 = exVar3;
	}



	public int getEmpId() {
		return empId;
	}



	public void setEmpId(int empId) {
		this.empId = empId;
	}



	public String getEmpFname() {
		return empFname;
	}



	public void setEmpFname(String empFname) {
		this.empFname = empFname;
	}



	public String getEmpSname() {
		return empSname;
	}



	public void setEmpSname(String empSname) {
		this.empSname = empSname;
	}



	@Override
	public String toString() {
		return "GetKraReviewList [kraReviewId=" + kraReviewId + ", kraId=" + kraId + ", review=" + review
				+ ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId
				+ ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
				+ ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", empId="
				+ empId + ", empFname=" + empFname + ", empSname=" + empSname + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	


}
