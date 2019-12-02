package com.ats.hrmgt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class GetProjectHeader {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "project_id")
	private int projectId;

	@Column(name = "company_id")
	private int companyId;

	@Column(name = "loc_id")
	private int locId;

	@Column(name = "project_type_id")
	private int projectTypeId;

	@Column(name = "cust_id")
	private int custId;

	@Column(name = "project_title ")
	private String projectTitle;

	@Column(name = "project_desc")
	private String projectDesc;

	@Column(name = "project_est_startdt")
	private String projectEstStartdt;

	@Column(name = "project_est_enddt")
	private String projectEstEnddt;

	@Column(name = "project_est_manhrs")
	private int projectEstManhrs;

	@Column(name = "project_est_budget ")
	private int projectEstBudget;

	@Column(name = "project_act_startdt")
	private String projectActStartdt;

	@Column(name = "project_act_enddt")
	private String projectActenddt;

	@Column(name = "project_status")
	private String projectStatus;

	@Column(name = "project_completion")
	private int projectCompletion;

	@Column(name = "del_status")
	private int delStatus;

	@Column(name = "is_active")
	private int isActive;

	@Column(name = "maker_user_id ")
	private int makerUserId;

	@Column(name = "maker_enter_datetime")
	private String makerEnterDatetime;

	@Column(name = "ex_int1")
	private int exInt1;

	@Column(name = "ex_int2")
	private int exInt2;

	@Column(name = "ex_int3")
	private int exInt3;

	@Column(name = "ex_var1")
	private String exVar1;

	@Column(name = "ex_var2")
	private String exVar2;

	@Column(name = "ex_var3")
	private String exVar3;

	@Column(name = "project_city")
	private String projectCity;

	@Column(name = "project_manager_emp_id")
	private int projectManagerEmpId;
	
	@Column(name = "po_date")
	private Date poDate;
	
	@Column(name = "po_number")
	private String poNumber;


	private String empCode;
	private String locName;
	private String custName;
	private String empFname;
	private String empMname;
	private String empSname;
	private String projectTypeTitle;
	private String projectTypeTitleShort;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getLocId() {
		return locId;
	}

	public void setLocId(int locId) {
		this.locId = locId;
	}

	public int getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(int projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public String getProjectEstStartdt() {
		return projectEstStartdt;
	}

	public void setProjectEstStartdt(String projectEstStartdt) {
		this.projectEstStartdt = projectEstStartdt;
	}

	public String getProjectEstEnddt() {
		return projectEstEnddt;
	}

	public void setProjectEstEnddt(String projectEstEnddt) {
		this.projectEstEnddt = projectEstEnddt;
	}

	public int getProjectEstManhrs() {
		return projectEstManhrs;
	}

	public void setProjectEstManhrs(int projectEstManhrs) {
		this.projectEstManhrs = projectEstManhrs;
	}

	public int getProjectEstBudget() {
		return projectEstBudget;
	}

	public void setProjectEstBudget(int projectEstBudget) {
		this.projectEstBudget = projectEstBudget;
	}

	public String getProjectActStartdt() {
		return projectActStartdt;
	}

	public void setProjectActStartdt(String projectActStartdt) {
		this.projectActStartdt = projectActStartdt;
	}

	public String getProjectActenddt() {
		return projectActenddt;
	}

	public void setProjectActenddt(String projectActenddt) {
		this.projectActenddt = projectActenddt;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public int getProjectCompletion() {
		return projectCompletion;
	}

	public void setProjectCompletion(int projectCompletion) {
		this.projectCompletion = projectCompletion;
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

	public String getProjectCity() {
		return projectCity;
	}

	public void setProjectCity(String projectCity) {
		this.projectCity = projectCity;
	}

	public int getProjectManagerEmpId() {
		return projectManagerEmpId;
	}

	public void setProjectManagerEmpId(int projectManagerEmpId) {
		this.projectManagerEmpId = projectManagerEmpId;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
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

	public String getProjectTypeTitle() {
		return projectTypeTitle;
	}

	public void setProjectTypeTitle(String projectTypeTitle) {
		this.projectTypeTitle = projectTypeTitle;
	}

	public String getProjectTypeTitleShort() {
		return projectTypeTitleShort;
	}

	public void setProjectTypeTitleShort(String projectTypeTitleShort) {
		this.projectTypeTitleShort = projectTypeTitleShort;
	}

	
	@JsonFormat(locale = "Locale.ENGLISH", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getPoDate() {
		return poDate;
	}

	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	@Override
	public String toString() {
		return "GetProjectHeader [projectId=" + projectId + ", companyId=" + companyId + ", locId=" + locId
				+ ", projectTypeId=" + projectTypeId + ", custId=" + custId + ", projectTitle=" + projectTitle
				+ ", projectDesc=" + projectDesc + ", projectEstStartdt=" + projectEstStartdt + ", projectEstEnddt="
				+ projectEstEnddt + ", projectEstManhrs=" + projectEstManhrs + ", projectEstBudget=" + projectEstBudget
				+ ", projectActStartdt=" + projectActStartdt + ", projectActenddt=" + projectActenddt
				+ ", projectStatus=" + projectStatus + ", projectCompletion=" + projectCompletion + ", delStatus="
				+ delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime="
				+ makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1="
				+ exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", projectCity=" + projectCity
				+ ", projectManagerEmpId=" + projectManagerEmpId + ", poDate=" + poDate + ", poNumber=" + poNumber
				+ ", empCode=" + empCode + ", locName=" + locName + ", custName=" + custName + ", empFname=" + empFname
				+ ", empMname=" + empMname + ", empSname=" + empSname + ", projectTypeTitle=" + projectTypeTitle
				+ ", projectTypeTitleShort=" + projectTypeTitleShort + "]";
	}

	


}
