package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_salary_dynamic_temp_allowance_cal")
public class SalAllownceTemp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="emp_sal_allowance_id")
	private int empSalAllowanceId;
	 
	@Column(name="tbl_salary_dynamic_temp_id")
	private int tblSalaryDynamicTempId;
	 
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="allowance_id")
	private int allowanceId;
	
	@Column(name="allowance_value")
	private double allowanceValue;
	
	@Column(name="allowance_value_cal")
	private double allowanceValueCal;
	
	@Column(name="maker_enter_datetime")
	private String makerEnterDatetime;
	
	@Column(name="del_status")
	private int delStatus;
	
	@Column(name="ex_int1")
	private int exInt1;
	
	@Column(name="ex_int2")
	private int exInt2;
	
	@Column(name="ex_var1")
	private String exVar1;
	
	@Column(name="ex_var2")
	private String exVar2;

	public int getEmpSalAllowanceId() {
		return empSalAllowanceId;
	}

	public void setEmpSalAllowanceId(int empSalAllowanceId) {
		this.empSalAllowanceId = empSalAllowanceId;
	}

	public int getTblSalaryDynamicTempId() {
		return tblSalaryDynamicTempId;
	}

	public void setTblSalaryDynamicTempId(int tblSalaryDynamicTempId) {
		this.tblSalaryDynamicTempId = tblSalaryDynamicTempId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getAllowanceId() {
		return allowanceId;
	}

	public void setAllowanceId(int allowanceId) {
		this.allowanceId = allowanceId;
	}

	public double getAllowanceValue() {
		return allowanceValue;
	}

	public void setAllowanceValue(double allowanceValue) {
		this.allowanceValue = allowanceValue;
	}

	public double getAllowanceValueCal() {
		return allowanceValueCal;
	}

	public void setAllowanceValueCal(double allowanceValueCal) {
		this.allowanceValueCal = allowanceValueCal;
	}

	public String getMakerEnterDatetime() {
		return makerEnterDatetime;
	}

	public void setMakerEnterDatetime(String makerEnterDatetime) {
		this.makerEnterDatetime = makerEnterDatetime;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
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

	@Override
	public String toString() {
		return "SalAllownceTemp [empSalAllowanceId=" + empSalAllowanceId + ", tblSalaryDynamicTempId="
				+ tblSalaryDynamicTempId + ", empId=" + empId + ", allowanceId=" + allowanceId + ", allowanceValue="
				+ allowanceValue + ", allowanceValueCal=" + allowanceValueCal + ", makerEnterDatetime="
				+ makerEnterDatetime + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
				+ ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
	

}
