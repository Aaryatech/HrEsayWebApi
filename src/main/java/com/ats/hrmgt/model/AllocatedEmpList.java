package com.ats.hrmgt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class AllocatedEmpList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="pallot_id")
	private int pallotId;
	
	@Column(name="project_id")
	private int projectId;
	
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="pallot_fromdt")
	private Date pallotFromdt;	
	
	@Column(name="pallot_todt ")
	private Date pallotTodt;
	
	@Column(name="pallot_daily_hrs")
	private float pallotDailyHrs;	
	
	@Column(name="pallot_remarks")
	private String pallotRemarks;		
		
	@Column(name="del_status")
	private int delStatus;
	
	@Column(name="is_active")
	private int isActive;
	
	@Column(name="maker_user_id ")
	private int makerUserId ;
	
	@Column(name="maker_enter_datetime")
	private String makerEnterDatetime;
	
	@Column(name="ex_int1")
	private int exInt1;
	
	@Column(name="ex_int2")
	private int exInt2;
	
	@Column(name="ex_int3")
	private int exInt3;
	
	@Column(name="ex_var1")
	private String exVar1; 
	
	@Column(name="ex_var2")
	private String exVar2; 
	
	@Column(name="ex_var3")
	private String exVar3;
	
	@Column(name="emp_fname")
	private String empFname;
	
	@Column(name="emp_mname")
	private String empMname;
	
	@Column(name="emp_sname")
	private String empSname;
	
	@Column(name="project_title")
	private String projectTitle;

	public int getPallotId() {
		return pallotId;
	}

	public void setPallotId(int pallotId) {
		this.pallotId = pallotId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getPallotFromdt() {
		return pallotFromdt;
	}

	public void setPallotFromdt(Date pallotFromdt) {
		this.pallotFromdt = pallotFromdt;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getPallotTodt() {
		return pallotTodt;
	}

	public void setPallotTodt(Date pallotTodt) {
		this.pallotTodt = pallotTodt;
	}

	public float getPallotDailyHrs() {
		return pallotDailyHrs;
	}

	public void setPallotDailyHrs(float pallotDailyHrs) {
		this.pallotDailyHrs = pallotDailyHrs;
	}

	public String getPallotRemarks() {
		return pallotRemarks;
	}

	public void setPallotRemarks(String pallotRemarks) {
		this.pallotRemarks = pallotRemarks;
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

	public String getMakerEnterDatetime() {
		return makerEnterDatetime;
	}

	public void setMakerEnterDatetime(String makerEnterDatetime) {
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

	public String getEmpFname() {
		return empFname;
	}

	public void setEmpFname(String empFname) {
		this.empFname = empFname;
	}

	public String getEmpMname() {
		return empMname;
	}

	public void setEmpMname(String empMname) {
		this.empMname = empMname;
	}

	public String getEmpSname() {
		return empSname;
	}

	public void setEmpSname(String empSname) {
		this.empSname = empSname;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AllocatedEmpList [pallotId=").append(pallotId).append(", projectId=").append(projectId)
				.append(", empId=").append(empId).append(", pallotFromdt=").append(pallotFromdt).append(", pallotTodt=")
				.append(pallotTodt).append(", pallotDailyHrs=").append(pallotDailyHrs).append(", pallotRemarks=")
				.append(pallotRemarks).append(", delStatus=").append(delStatus).append(", isActive=").append(isActive)
				.append(", makerUserId=").append(makerUserId).append(", makerEnterDatetime=").append(makerEnterDatetime)
				.append(", exInt1=").append(exInt1).append(", exInt2=").append(exInt2).append(", exInt3=")
				.append(exInt3).append(", exVar1=").append(exVar1).append(", exVar2=").append(exVar2)
				.append(", exVar3=").append(exVar3).append(", empFname=").append(empFname).append(", empMname=")
				.append(empMname).append(", empSname=").append(empSname).append(", projectTitle=").append(projectTitle)
				.append("]");
		return builder.toString();
	}
	
	

}
