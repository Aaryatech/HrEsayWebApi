package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private int user_id;
	
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="emp_type_id")
	private int empTypeId;
	
	@Column(name="loc_id")
	private int locId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_pwd")
	private String user_pwd;
	
	@Column(name="del_status")
	private int delStatus;
	
	@Column(name="is_active")
	private int isActive;
	
	@Column(name="maker_user_id")
	private int makerUserId;
	
	@Column(name="maker_enter_datetime")
	private String makerEnterDatetime;
	
	@Column(name="exInt1")
	private int exInt1;
	
	@Column(name="ex_int2")
	private int ex_int2;
	
	@Column(name="ex_int3")
	private int exInt3;
	
	@Column(name="ex_var1")
	private int exVar1;
	
	@Column(name="ex_var2")
	private String exVar2;
	
	@Column(name="ex_var3")
	private String exVar3;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getEmpTypeId() {
		return empTypeId;
	}

	public void setEmpTypeId(int empTypeId) {
		this.empTypeId = empTypeId;
	}

	public int getLocId() {
		return locId;
	}

	public void setLocId(int locId) {
		this.locId = locId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
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

	public int getEx_int2() {
		return ex_int2;
	}

	public void setEx_int2(int ex_int2) {
		this.ex_int2 = ex_int2;
	}

	public int getExInt3() {
		return exInt3;
	}

	public void setExInt3(int exInt3) {
		this.exInt3 = exInt3;
	}

	public int getExVar1() {
		return exVar1;
	}

	public void setExVar1(int exVar1) {
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

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", empId=" + empId + ", empTypeId=" + empTypeId + ", locId=" + locId
				+ ", userName=" + userName + ", user_pwd=" + user_pwd + ", delStatus=" + delStatus + ", isActive="
				+ isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1="
				+ exInt1 + ", ex_int2=" + ex_int2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2
				+ ", exVar3=" + exVar3 + "]";
	}
	
	
}
