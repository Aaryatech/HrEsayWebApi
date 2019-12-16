package com.ats.hrmgt.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tbl_emp_salary_info")
public class EmpSalaryInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="salary_info_id")
	private int salaryInfoId;
	
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="salary_type_id")
	private int salaryTypeId;
	
	@Column(name="basic")
	private double basic;
	
	@Column(name="da")
	private double da;
	
	@Column(name="hra")
	private double hra;
	
	@Column(name="spa")
	private double spa;
	
	@Column(name="pf_applicable")
	private String pfApplicable;
	
	@Column(name="pf_type")
	private String pfType;
	
	@Column(name="pf_emp_per")
	private double pfEmpPer;
	
	@Column(name="pf_emplr_per")
	private double pfEmplrPer;
	
	@Column(name="esic_applicable")
	private String esicApplicable;
	
	@Column(name="cmp_joining_date")
	private Date cmpJoiningDate;
	
	@Column(name="cmp_leaving_date")
	private Date cmpLeavingDate;
	
	@Column(name="epf_joining_date")
	private Date epfJoiningDate;
	
	@Column(name="leaving_reason")
	private String leavingReason;
	
	@Column(name="salBasis")
	private String salBasis;
	
	@Column(name="ceiling_limit_emp_applicable")
	private String ceilingLimitEmpApplicable;
	
	@Column(name="ceiling_limit_employer_applicable")
	private String ceilingLimitEmployerApplicable;
	
	@Column(name="leaving_reason_esic")
	private String leavingReasonEsic;
	
	@Column(name="leaving_reason_pf")
	private String leavingReasonPf;
	
	@Column(name="mlwf_applicable")
	private String mlwfApplicable;
	
	@Column(name="pt_applicable")
	private String ptApplicable;
	
	@Column(name="gross_salary")
	private double grossSalary;
	
	@Column(name="society_contribution")
	private double societyContribution;
	
	@Column(name="basic_company")
	private double basicCompany;
	
	@Column(name="hra_company")
	private double hraCompany;
	
	@Column(name="da_company")	
	private double daCompany;
	
	@Column(name="employee_esic_percentage")
	private double employeeEsicPercentage;
	
	@Column(name="employer_esic_percentage")
	private double employerEsicPercentage;

	public int getSalaryInfoId() {
		return salaryInfoId;
	}

	public void setSalaryInfoId(int salaryInfoId) {
		this.salaryInfoId = salaryInfoId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getSalaryTypeId() {
		return salaryTypeId;
	}

	public void setSalaryTypeId(int salaryTypeId) {
		this.salaryTypeId = salaryTypeId;
	}

	public double getBasic() {
		return basic;
	}

	public void setBasic(double basic) {
		this.basic = basic;
	}

	public double getDa() {
		return da;
	}

	public void setDa(double da) {
		this.da = da;
	}

	public double getHra() {
		return hra;
	}

	public void setHra(double hra) {
		this.hra = hra;
	}

	public double getSpa() {
		return spa;
	}

	public void setSpa(double spa) {
		this.spa = spa;
	}

	public String getPfApplicable() {
		return pfApplicable;
	}

	public void setPfApplicable(String pfApplicable) {
		this.pfApplicable = pfApplicable;
	}

	public String getPfType() {
		return pfType;
	}

	public void setPfType(String pfType) {
		this.pfType = pfType;
	}

	public double getPfEmpPer() {
		return pfEmpPer;
	}

	public void setPfEmpPer(double pfEmpPer) {
		this.pfEmpPer = pfEmpPer;
	}

	public double getPfEmplrPer() {
		return pfEmplrPer;
	}

	public void setPfEmplrPer(double pfEmplrPer) {
		this.pfEmplrPer = pfEmplrPer;
	}

	public String getEsicApplicable() {
		return esicApplicable;
	}

	public void setEsicApplicable(String esicApplicable) {
		this.esicApplicable = esicApplicable;
	}

	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getCmpJoiningDate() {
		return cmpJoiningDate;
	}

	public void setCmpJoiningDate(Date cmpJoiningDate) {
		this.cmpJoiningDate = cmpJoiningDate;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getCmpLeavingDate() {
		return cmpLeavingDate;
	}

	public void setCmpLeavingDate(Date cmpLeavingDate) {
		this.cmpLeavingDate = cmpLeavingDate;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getEpfJoiningDate() {
		return epfJoiningDate;
	}

	public void setEpfJoiningDate(Date epfJoiningDate) {
		this.epfJoiningDate = epfJoiningDate;
	}

	public String getLeavingReason() {
		return leavingReason;
	}

	public void setLeavingReason(String leavingReason) {
		this.leavingReason = leavingReason;
	}


	public String getCeilingLimitEmpApplicable() {
		return ceilingLimitEmpApplicable;
	}

	public void setCeilingLimitEmpApplicable(String ceilingLimitEmpApplicable) {
		this.ceilingLimitEmpApplicable = ceilingLimitEmpApplicable;
	}

	public String getCeilingLimitEmployerApplicable() {
		return ceilingLimitEmployerApplicable;
	}

	public void setCeilingLimitEmployerApplicable(String ceilingLimitEmployerApplicable) {
		this.ceilingLimitEmployerApplicable = ceilingLimitEmployerApplicable;
	}

	public String getLeavingReasonEsic() {
		return leavingReasonEsic;
	}

	public void setLeavingReasonEsic(String leavingReasonEsic) {
		this.leavingReasonEsic = leavingReasonEsic;
	}

	public String getLeavingReasonPf() {
		return leavingReasonPf;
	}

	public void setLeavingReasonPf(String leavingReasonPf) {
		this.leavingReasonPf = leavingReasonPf;
	}

	public String getMlwfApplicable() {
		return mlwfApplicable;
	}

	public void setMlwfApplicable(String mlwfApplicable) {
		this.mlwfApplicable = mlwfApplicable;
	}

	public String getPtApplicable() {
		return ptApplicable;
	}

	public void setPtApplicable(String ptApplicable) {
		this.ptApplicable = ptApplicable;
	}

	public double getSocietyContribution() {
		return societyContribution;
	}

	public void setSocietyContribution(double societyContribution) {
		this.societyContribution = societyContribution;
	}

	public double getBasicCompany() {
		return basicCompany;
	}

	public void setBasicCompany(double basicCompany) {
		this.basicCompany = basicCompany;
	}

	public double getHraCompany() {
		return hraCompany;
	}

	public void setHraCompany(double hraCompany) {
		this.hraCompany = hraCompany;
	}

	public double getDaCompany() {
		return daCompany;
	}

	public void setDaCompany(double daCompany) {
		this.daCompany = daCompany;
	}

	public double getEmployeeEsicPercentage() {
		return employeeEsicPercentage;
	}

	public void setEmployeeEsicPercentage(double employeeEsicPercentage) {
		this.employeeEsicPercentage = employeeEsicPercentage;
	}

	public double getEmployerEsicPercentage() {
		return employerEsicPercentage;
	}

	public void setEmployerEsicPercentage(double employerEsicPercentage) {
		this.employerEsicPercentage = employerEsicPercentage;
	}

	public String getSalBasis() {
		return salBasis;
	}

	public void setSalBasis(String salBasis) {
		this.salBasis = salBasis;
	}

	public double getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(double grossSalary) {
		this.grossSalary = grossSalary;
	}

	@Override
	public String toString() {
		return "EmpSalaryInfo [salaryInfoId=" + salaryInfoId + ", empId=" + empId + ", salaryTypeId=" + salaryTypeId
				+ ", basic=" + basic + ", da=" + da + ", hra=" + hra + ", spa=" + spa + ", pfApplicable=" + pfApplicable
				+ ", pfType=" + pfType + ", pfEmpPer=" + pfEmpPer + ", pfEmplrPer=" + pfEmplrPer + ", esicApplicable="
				+ esicApplicable + ", cmpJoiningDate=" + cmpJoiningDate + ", cmpLeavingDate=" + cmpLeavingDate
				+ ", epfJoiningDate=" + epfJoiningDate + ", leavingReason=" + leavingReason + ", salBasis=" + salBasis
				+ ", ceilingLimitEmpApplicable=" + ceilingLimitEmpApplicable + ", ceilingLimitEmployerApplicable="
				+ ceilingLimitEmployerApplicable + ", leavingReasonEsic=" + leavingReasonEsic + ", leavingReasonPf="
				+ leavingReasonPf + ", mlwfApplicable=" + mlwfApplicable + ", ptApplicable=" + ptApplicable
				+ ", grossSalary=" + grossSalary + ", societyContribution=" + societyContribution + ", basicCompany="
				+ basicCompany + ", hraCompany=" + hraCompany + ", daCompany=" + daCompany + ", employeeEsicPercentage="
				+ employeeEsicPercentage + ", employerEsicPercentage=" + employerEsicPercentage + "]";
	}

}
