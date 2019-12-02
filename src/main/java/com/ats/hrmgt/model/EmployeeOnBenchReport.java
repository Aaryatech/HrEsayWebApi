package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 

@Entity 
public class EmployeeOnBenchReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="emp_id")
	private int empId ;
	
	@Column(name="emp_fname")
	private String empFname ;
	
	@Column(name="emp_mname")
	private String empMname;
	
	@Column(name="emp_sname")
	private String empSname;
	
	@Column(name="emp_rate_perhr")
	private float empRatePerhr;
	
	@Column(name="ctc")
	private float ctc;
	
	@Column(name="category")
	private String category;
	
	@Column(name="type")
	private String type;

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

	public float getEmpRatePerhr() {
		return empRatePerhr;
	}

	public void setEmpRatePerhr(float empRatePerhr) {
		this.empRatePerhr = empRatePerhr;
	}

	public float getCtc() {
		return ctc;
	}

	public void setCtc(float ctc) {
		this.ctc = ctc;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "EmployeeOnBenchReport [empId=" + empId + ", empFname=" + empFname + ", empMname=" + empMname
				+ ", empSname=" + empSname + ", empRatePerhr=" + empRatePerhr + ", ctc=" + ctc + ", category="
				+ category + ", type=" + type + "]";
	}
	
	

}
