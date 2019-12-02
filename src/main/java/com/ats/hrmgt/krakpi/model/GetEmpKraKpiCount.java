package com.ats.hrmgt.krakpi.model;

 import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GetEmpKraKpiCount {
	@Id
	private int empId;
	
 	private String empCode;
	 
	private int empCatId;

	private String empCatName;
	
	private int empTypeId;
	
	private String empTypeName;
	
	private int empDeptId;
	
	private String empDeptName;
	
	private String empDeptShortName;
	
	private String empTypeShortName;
	
	private String empCatShortName;
	
	private String empFname;
	
	private String empMname;
	
	private String empSname;
	
	private int kraCount;
	
	private int kpiCount;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public int getEmpCatId() {
		return empCatId;
	}

	public void setEmpCatId(int empCatId) {
		this.empCatId = empCatId;
	}
 

	public int getEmpTypeId() {
		return empTypeId;
	}

	public void setEmpTypeId(int empTypeId) {
		this.empTypeId = empTypeId;
	}

 

	public int getEmpDeptId() {
		return empDeptId;
	}

	public void setEmpDeptId(int empDeptId) {
		this.empDeptId = empDeptId;
	}

	 

	public String getEmpDeptShortName() {
		return empDeptShortName;
	}

	public void setEmpDeptShortName(String empDeptShortName) {
		this.empDeptShortName = empDeptShortName;
	}

	public String getEmpTypeShortName() {
		return empTypeShortName;
	}

	public void setEmpTypeShortName(String empTypeShortName) {
		this.empTypeShortName = empTypeShortName;
	}

	public String getEmpCatShortName() {
		return empCatShortName;
	}

	public void setEmpCatShortName(String empCatShortName) {
		this.empCatShortName = empCatShortName;
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

	public int getKraCount() {
		return kraCount;
	}

	public void setKraCount(int kraCount) {
		this.kraCount = kraCount;
	}

	public int getKpiCount() {
		return kpiCount;
	}

	public void setKpiCount(int kpiCount) {
		this.kpiCount = kpiCount;
	}

	public String getEmpCatName() {
		return empCatName;
	}

	public void setEmpCatName(String empCatName) {
		this.empCatName = empCatName;
	}

	public String getEmpTypeName() {
		return empTypeName;
	}

	public void setEmpTypeName(String empTypeName) {
		this.empTypeName = empTypeName;
	}

	public String getEmpDeptName() {
		return empDeptName;
	}

	public void setEmpDeptName(String empDeptName) {
		this.empDeptName = empDeptName;
	}

	@Override
	public String toString() {
		return "GetEmpKraKpiCount [empId=" + empId + ", empCode=" + empCode + ", empCatId=" + empCatId + ", empCatName="
				+ empCatName + ", empTypeId=" + empTypeId + ", empTypeName=" + empTypeName + ", empDeptId=" + empDeptId
				+ ", empDeptName=" + empDeptName + ", empDeptShortName=" + empDeptShortName + ", empTypeShortName="
				+ empTypeShortName + ", empCatShortName=" + empCatShortName + ", empFname=" + empFname + ", empMname="
				+ empMname + ", empSname=" + empSname + ", kraCount=" + kraCount + ", kpiCount=" + kpiCount
				+ ", getEmpId()=" + getEmpId() + ", getEmpCode()=" + getEmpCode() + ", getEmpCatId()=" + getEmpCatId()
				+ ", getEmpTypeId()=" + getEmpTypeId() + ", getEmpDeptId()=" + getEmpDeptId()
				+ ", getEmpDeptShortName()=" + getEmpDeptShortName() + ", getEmpTypeShortName()="
				+ getEmpTypeShortName() + ", getEmpCatShortName()=" + getEmpCatShortName() + ", getEmpFname()="
				+ getEmpFname() + ", getEmpMname()=" + getEmpMname() + ", getEmpSname()=" + getEmpSname()
				+ ", getKraCount()=" + getKraCount() + ", getKpiCount()=" + getKpiCount() + ", getEmpCatName()="
				+ getEmpCatName() + ", getEmpTypeName()=" + getEmpTypeName() + ", getEmpDeptName()=" + getEmpDeptName()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	 
	
	

}
