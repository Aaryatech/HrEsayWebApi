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
	
	@Column(name="washing")
	private double washing;
	
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
	private String sal_basis;
	
	@Column(name="add_washing")
	private double addWashing;
	
	@Column(name="other1")
	private double other1;
	
	@Column(name="other2")
	private double other2;
	
	@Column(name="other3")
	private double other3;
	
	@Column(name="other4")
	private double other4;
	
	@Column(name="allowance1")
	private double allowance1;
	
	@Column(name="allowance2")
	private double allowance2;
	
	@Column(name="allowance3")
	private double allowance3;
	
	@Column(name="allowance4")
	private double allowance4;
	
	@Column(name="allowance5")
	private double allowance5;
	
	@Column(name="allowance6")
	private double allowance6;
	
	@Column(name="allowance7")
	private double allowance7;
	
	@Column(name="allowance8")
	private double allowance8;
	
	@Column(name="allowance9")
	private double allowance9;
	
	@Column(name="allowance10")
	private double allowance10;
	
	@Column(name="allowance11")
	private double allowance11;
	
	@Column(name="allowance12")
	private double allowance12;
	
	@Column(name="allowance13")
	private double allowance13;
	
	@Column(name="allowance14")
	private double allowance14;
	
	@Column(name="allowance15")
	private double allowance15;
	
	@Column(name="allowance16")
	private double allowance16;
	
	@Column(name="allowance17")
	private double allowance17;
	
	@Column(name="allowance18")
	private double allowance18;
	
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
	
	@Column(name="gross_sallary")
	private double gross_sallary;
	
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

	public double getWashing() {
		return washing;
	}

	public void setWashing(double washing) {
		this.washing = washing;
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

	public String getSal_basis() {
		return sal_basis;
	}

	public void setSal_basis(String sal_basis) {
		this.sal_basis = sal_basis;
	}

	public double getAddWashing() {
		return addWashing;
	}

	public void setAddWashing(double addWashing) {
		this.addWashing = addWashing;
	}

	public double getOther1() {
		return other1;
	}

	public void setOther1(double other1) {
		this.other1 = other1;
	}

	public double getOther2() {
		return other2;
	}

	public void setOther2(double other2) {
		this.other2 = other2;
	}

	public double getOther3() {
		return other3;
	}

	public void setOther3(double other3) {
		this.other3 = other3;
	}

	public double getOther4() {
		return other4;
	}

	public void setOther4(double other4) {
		this.other4 = other4;
	}

	public double getAllowance1() {
		return allowance1;
	}

	public void setAllowance1(double allowance1) {
		this.allowance1 = allowance1;
	}

	public double getAllowance2() {
		return allowance2;
	}

	public void setAllowance2(double allowance2) {
		this.allowance2 = allowance2;
	}

	public double getAllowance3() {
		return allowance3;
	}

	public void setAllowance3(double allowance3) {
		this.allowance3 = allowance3;
	}

	public double getAllowance4() {
		return allowance4;
	}

	public void setAllowance4(double allowance4) {
		this.allowance4 = allowance4;
	}

	public double getAllowance5() {
		return allowance5;
	}

	public void setAllowance5(double allowance5) {
		this.allowance5 = allowance5;
	}

	public double getAllowance6() {
		return allowance6;
	}

	public void setAllowance6(double allowance6) {
		this.allowance6 = allowance6;
	}

	public double getAllowance7() {
		return allowance7;
	}

	public void setAllowance7(double allowance7) {
		this.allowance7 = allowance7;
	}

	public double getAllowance8() {
		return allowance8;
	}

	public void setAllowance8(double allowance8) {
		this.allowance8 = allowance8;
	}

	public double getAllowance9() {
		return allowance9;
	}

	public void setAllowance9(double allowance9) {
		this.allowance9 = allowance9;
	}

	public double getAllowance10() {
		return allowance10;
	}

	public void setAllowance10(double allowance10) {
		this.allowance10 = allowance10;
	}

	public double getAllowance11() {
		return allowance11;
	}

	public void setAllowance11(double allowance11) {
		this.allowance11 = allowance11;
	}

	public double getAllowance12() {
		return allowance12;
	}

	public void setAllowance12(double allowance12) {
		this.allowance12 = allowance12;
	}

	public double getAllowance13() {
		return allowance13;
	}

	public void setAllowance13(double allowance13) {
		this.allowance13 = allowance13;
	}

	public double getAllowance14() {
		return allowance14;
	}

	public void setAllowance14(double allowance14) {
		this.allowance14 = allowance14;
	}

	public double getAllowance15() {
		return allowance15;
	}

	public void setAllowance15(double allowance15) {
		this.allowance15 = allowance15;
	}

	public double getAllowance16() {
		return allowance16;
	}

	public void setAllowance16(double allowance16) {
		this.allowance16 = allowance16;
	}

	public double getAllowance17() {
		return allowance17;
	}

	public void setAllowance17(double allowance17) {
		this.allowance17 = allowance17;
	}

	public double getAllowance18() {
		return allowance18;
	}

	public void setAllowance18(double allowance18) {
		this.allowance18 = allowance18;
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

	public double getGross_sallary() {
		return gross_sallary;
	}

	public void setGross_sallary(double gross_sallary) {
		this.gross_sallary = gross_sallary;
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

	@Override
	public String toString() {
		return "EmpSalaryInfo [salaryInfoId=" + salaryInfoId + ", empId=" + empId + ", salaryTypeId=" + salaryTypeId
				+ ", basic=" + basic + ", da=" + da + ", hra=" + hra + ", spa=" + spa + ", washing=" + washing
				+ ", pfApplicable=" + pfApplicable + ", pfType=" + pfType + ", pfEmpPer=" + pfEmpPer + ", pfEmplrPer="
				+ pfEmplrPer + ", esicApplicable=" + esicApplicable + ", cmpJoiningDate=" + cmpJoiningDate
				+ ", cmpLeavingDate=" + cmpLeavingDate + ", epfJoiningDate=" + epfJoiningDate + ", leavingReason="
				+ leavingReason + ", sal_basis=" + sal_basis + ", addWashing=" + addWashing + ", other1=" + other1
				+ ", other2=" + other2 + ", other3=" + other3 + ", other4=" + other4 + ", allowance1=" + allowance1
				+ ", allowance2=" + allowance2 + ", allowance3=" + allowance3 + ", allowance4=" + allowance4
				+ ", allowance5=" + allowance5 + ", allowance6=" + allowance6 + ", allowance7=" + allowance7
				+ ", allowance8=" + allowance8 + ", allowance9=" + allowance9 + ", allowance10=" + allowance10
				+ ", allowance11=" + allowance11 + ", allowance12=" + allowance12 + ", allowance13=" + allowance13
				+ ", allowance14=" + allowance14 + ", allowance15=" + allowance15 + ", allowance16=" + allowance16
				+ ", allowance17=" + allowance17 + ", allowance18=" + allowance18 + ", ceilingLimitEmpApplicable="
				+ ceilingLimitEmpApplicable + ", ceilingLimitEmployerApplicable=" + ceilingLimitEmployerApplicable
				+ ", leavingReasonEsic=" + leavingReasonEsic + ", leavingReasonPf=" + leavingReasonPf
				+ ", mlwfApplicable=" + mlwfApplicable + ", ptApplicable=" + ptApplicable + ", gross_sallary="
				+ gross_sallary + ", societyContribution=" + societyContribution + ", basicCompany=" + basicCompany
				+ ", hraCompany=" + hraCompany + ", daCompany=" + daCompany + ", employeeEsicPercentage="
				+ employeeEsicPercentage + ", employerEsicPercentage=" + employerEsicPercentage + "]";
	}
	
}
