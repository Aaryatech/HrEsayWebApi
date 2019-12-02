package com.ats.hrmgt.model;

 import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class GetUserData {

	@Id
	private int empId;
	
	private int userId;
	
	private String userPwd;	
	
 	private String empFname;
	
	private String empMname;
	
	private String empSname;
	
	private String empMobile1;
	
	private String empEmail;
	
	@Transient
	private boolean isError;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
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

	public String getEmpMobile1() {
		return empMobile1;
	}

	public void setEmpMobile1(String empMobile1) {
		this.empMobile1 = empMobile1;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	@Override
	public String toString() {
		return "GetUserData [empId=" + empId + ", userId=" + userId + ", userPwd=" + userPwd + ", empFname=" + empFname
				+ ", empMname=" + empMname + ", empSname=" + empSname + ", empMobile1=" + empMobile1 + ", empEmail="
				+ empEmail + ", getEmpId()=" + getEmpId() + ", getUserId()=" + getUserId() + ", getUserPwd()="
				+ getUserPwd() + ", getEmpFname()=" + getEmpFname() + ", getEmpMname()=" + getEmpMname()
				+ ", getEmpSname()=" + getEmpSname() + ", getEmpMobile1()=" + getEmpMobile1() + ", getEmpEmail()="
				+ getEmpEmail() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
