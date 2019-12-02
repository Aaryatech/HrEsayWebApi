package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class EmployeeProjectWise {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="pallot_id")
	private int pallotId ;
	
	@Column(name="project_id")
	private int projectId ;
	
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="emp_fname")
	private String empFname ;
	 
	@Column(name="emp_sname")
	private String empSname;
	 
	@Column(name="hours")
	private float hours;
	
	@Column(name="ctc")
	private float ctc;
	
	@Column(name="project_title")
	private String projectTitle;
	
	@Column(name="emp_type_name")
	private String empTypeName;
	
	@Column(name="emp_type_short_name")
	private String empTypeShortName;

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

	public float getHours() {
		return hours;
	}

	public void setHours(float hours) {
		this.hours = hours;
	}

	public float getCtc() {
		return ctc;
	}

	public void setCtc(float ctc) {
		this.ctc = ctc;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getEmpTypeName() {
		return empTypeName;
	}

	public void setEmpTypeName(String empTypeName) {
		this.empTypeName = empTypeName;
	}

	public String getEmpTypeShortName() {
		return empTypeShortName;
	}

	public void setEmpTypeShortName(String empTypeShortName) {
		this.empTypeShortName = empTypeShortName;
	}

	@Override
	public String toString() {
		return "EmployeeProjectWise [pallotId=" + pallotId + ", projectId=" + projectId + ", empId=" + empId
				+ ", empFname=" + empFname + ", empSname=" + empSname + ", hours=" + hours + ", ctc=" + ctc
				+ ", projectTitle=" + projectTitle + ", empTypeName=" + empTypeName + ", empTypeShortName="
				+ empTypeShortName + "]";
	}
 
	 

}
