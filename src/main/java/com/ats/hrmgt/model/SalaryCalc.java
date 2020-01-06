package com.ats.hrmgt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_salary_calc")
public class SalaryCalc {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int cmpId;
	private int empId;
	private String empCode;
	private int empType;
	private int contractorId;
	private int departId;
	private int designationId;
	private int locationId;
	private int calcMonth;
	private int calcYear;
	private int fYear;
	private int attSumId;
 	private double basicCal;
	private double daCal;
	private double vdaCal;
	private double washingAllownceCal;
	private double addWashingCal;
	private double othFcal1;
	private double othFcal2;
	private double othFcal3;
	private double conveyanceAllowanceCa;
	private double hraCal;
	private double medicalAllowanceCal;
	private double performanceBounsCal;
	private double educationAllowanceCal;
	private double vehicleMaintenanceAllowanceCal;
	private double attendanceCal;
	private double canteenAllownceCal;
	private double productionBonusCal;
	private double travellingAllownceCal;
	private double functionalExpertiseAllownceCal;
	private double leaveTravelAllownceCal;
	private double dpCal;
	private double agpCal;
	private double claCal;
	private double newsPaperAndPeriodicalAllownce;
	private double furnitureAllownce;
	private double otWages;
	private double otherAllowance1;
	private double otherAllowance2;
	private double otherAllowance3;
	private double miscExpAdd;
	private double bonusCal;
	private double exgretiaCal;
	private double daArreasCal;
	private double incrementArreasCal;
	private double epfWages;
	private double epfWagesEmployer;
	private double esicWagesCal;
	private double grossSalary;
	private double  epsWages;
	private double esicWagesDec;
	private float employeePf;
	private double employerEps;
	private double employerPf;
 	private float esic;
	private double employerEsic;
	private int  esicStatus;
 	private int  pfStatus;
 	private double mlwf;
	private double tds;
	private double itded;
	private double fund;
	private double totPfAdminCh;
	private double totEdliCh;
	private double totEdliAdminCh;
 	private int  ncpDays;
 	private int  status;
	private double ptDed;
	private double advanceDed;
	private double loanDed;
	private double miscExpDed;
	private int  miscExpDedDeduct;
	private double netSalary;
	private String isLocked;
	private String  loginName;
	private String  loginTime;
	private String  mlwfApplicable;
	private String ptApplicable;
	private double payDed;
	private double defaultBasicSalary;
	private double defaultGroassSalary;
	private String  commentsForItBonus;
	private double societyContribution;
	private String  empCategory;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCmpId() {
		return cmpId;
	}
	public void setCmpId(int cmpId) {
		this.cmpId = cmpId;
	}
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
	public int getEmpType() {
		return empType;
	}
	public void setEmpType(int empType) {
		this.empType = empType;
	}
	public int getContractorId() {
		return contractorId;
	}
	public void setContractorId(int contractorId) {
		this.contractorId = contractorId;
	}
	public int getDepartId() {
		return departId;
	}
	public void setDepartId(int departId) {
		this.departId = departId;
	}
	public int getDesignationId() {
		return designationId;
	}
	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getCalcMonth() {
		return calcMonth;
	}
	public void setCalcMonth(int calcMonth) {
		this.calcMonth = calcMonth;
	}
	public int getCalcYear() {
		return calcYear;
	}
	public void setCalcYear(int calcYear) {
		this.calcYear = calcYear;
	}
	public int getfYear() {
		return fYear;
	}
	public void setfYear(int fYear) {
		this.fYear = fYear;
	}
	public int getAttSumId() {
		return attSumId;
	}
	public void setAttSumId(int attSumId) {
		this.attSumId = attSumId;
	}
	public double getBasicCal() {
		return basicCal;
	}
	public void setBasicCal(double basicCal) {
		this.basicCal = basicCal;
	}
	public double getDaCal() {
		return daCal;
	}
	public void setDaCal(double daCal) {
		this.daCal = daCal;
	}
	public double getVdaCal() {
		return vdaCal;
	}
	public void setVdaCal(double vdaCal) {
		this.vdaCal = vdaCal;
	}
	public double getWashingAllownceCal() {
		return washingAllownceCal;
	}
	public void setWashingAllownceCal(double washingAllownceCal) {
		this.washingAllownceCal = washingAllownceCal;
	}
	public double getAddWashingCal() {
		return addWashingCal;
	}
	public void setAddWashingCal(double addWashingCal) {
		this.addWashingCal = addWashingCal;
	}
 
	public double getConveyanceAllowanceCa() {
		return conveyanceAllowanceCa;
	}
	public void setConveyanceAllowanceCa(double conveyanceAllowanceCa) {
		this.conveyanceAllowanceCa = conveyanceAllowanceCa;
	}
	public double getHraCal() {
		return hraCal;
	}
	public void setHraCal(double hraCal) {
		this.hraCal = hraCal;
	}
	public double getMedicalAllowanceCal() {
		return medicalAllowanceCal;
	}
	public void setMedicalAllowanceCal(double medicalAllowanceCal) {
		this.medicalAllowanceCal = medicalAllowanceCal;
	}
	public double getPerformanceBounsCal() {
		return performanceBounsCal;
	}
	public void setPerformanceBounsCal(double performanceBounsCal) {
		this.performanceBounsCal = performanceBounsCal;
	}
	public double getEducationAllowanceCal() {
		return educationAllowanceCal;
	}
	public void setEducationAllowanceCal(double educationAllowanceCal) {
		this.educationAllowanceCal = educationAllowanceCal;
	}
	public double getVehicleMaintenanceAllowanceCal() {
		return vehicleMaintenanceAllowanceCal;
	}
	public void setVehicleMaintenanceAllowanceCal(double vehicleMaintenanceAllowanceCal) {
		this.vehicleMaintenanceAllowanceCal = vehicleMaintenanceAllowanceCal;
	}
	public double getAttendanceCal() {
		return attendanceCal;
	}
	public void setAttendanceCal(double attendanceCal) {
		this.attendanceCal = attendanceCal;
	}
	public double getCanteenAllownceCal() {
		return canteenAllownceCal;
	}
	public void setCanteenAllownceCal(double canteenAllownceCal) {
		this.canteenAllownceCal = canteenAllownceCal;
	}
	public double getProductionBonusCal() {
		return productionBonusCal;
	}
	public void setProductionBonusCal(double productionBonusCal) {
		this.productionBonusCal = productionBonusCal;
	}
	public double getTravellingAllownceCal() {
		return travellingAllownceCal;
	}
	public void setTravellingAllownceCal(double travellingAllownceCal) {
		this.travellingAllownceCal = travellingAllownceCal;
	}
	public double getFunctionalExpertiseAllownceCal() {
		return functionalExpertiseAllownceCal;
	}
	public void setFunctionalExpertiseAllownceCal(double functionalExpertiseAllownceCal) {
		this.functionalExpertiseAllownceCal = functionalExpertiseAllownceCal;
	}
	public double getLeaveTravelAllownceCal() {
		return leaveTravelAllownceCal;
	}
	public void setLeaveTravelAllownceCal(double leaveTravelAllownceCal) {
		this.leaveTravelAllownceCal = leaveTravelAllownceCal;
	}
	public double getDpCal() {
		return dpCal;
	}
	public void setDpCal(double dpCal) {
		this.dpCal = dpCal;
	}
	public double getAgpCal() {
		return agpCal;
	}
	public void setAgpCal(double agpCal) {
		this.agpCal = agpCal;
	}
	public double getClaCal() {
		return claCal;
	}
	public void setClaCal(double claCal) {
		this.claCal = claCal;
	}
	public double getNewsPaperAndPeriodicalAllownce() {
		return newsPaperAndPeriodicalAllownce;
	}
	public void setNewsPaperAndPeriodicalAllownce(double newsPaperAndPeriodicalAllownce) {
		this.newsPaperAndPeriodicalAllownce = newsPaperAndPeriodicalAllownce;
	}
	public double getFurnitureAllownce() {
		return furnitureAllownce;
	}
	public void setFurnitureAllownce(double furnitureAllownce) {
		this.furnitureAllownce = furnitureAllownce;
	}
	public double getOtWages() {
		return otWages;
	}
	public void setOtWages(double otWages) {
		this.otWages = otWages;
	}
	public double getOtherAllowance1() {
		return otherAllowance1;
	}
	public void setOtherAllowance1(double otherAllowance1) {
		this.otherAllowance1 = otherAllowance1;
	}
	public double getOtherAllowance2() {
		return otherAllowance2;
	}
	public void setOtherAllowance2(double otherAllowance2) {
		this.otherAllowance2 = otherAllowance2;
	}
	public double getOtherAllowance3() {
		return otherAllowance3;
	}
	public void setOtherAllowance3(double otherAllowance3) {
		this.otherAllowance3 = otherAllowance3;
	}
	public double getMiscExpAdd() {
		return miscExpAdd;
	}
	public void setMiscExpAdd(double miscExpAdd) {
		this.miscExpAdd = miscExpAdd;
	}
	public double getBonusCal() {
		return bonusCal;
	}
	public void setBonusCal(double bonusCal) {
		this.bonusCal = bonusCal;
	}
	public double getExgretiaCal() {
		return exgretiaCal;
	}
	public void setExgretiaCal(double exgretiaCal) {
		this.exgretiaCal = exgretiaCal;
	}
	public double getDaArreasCal() {
		return daArreasCal;
	}
	public void setDaArreasCal(double daArreasCal) {
		this.daArreasCal = daArreasCal;
	}
	public double getIncrementArreasCal() {
		return incrementArreasCal;
	}
	public void setIncrementArreasCal(double incrementArreasCal) {
		this.incrementArreasCal = incrementArreasCal;
	}
	public double getEpfWages() {
		return epfWages;
	}
	public void setEpfWages(double epfWages) {
		this.epfWages = epfWages;
	}
	public double getEpfWagesEmployer() {
		return epfWagesEmployer;
	}
	public void setEpfWagesEmployer(double epfWagesEmployer) {
		this.epfWagesEmployer = epfWagesEmployer;
	}
	public double getEsicWagesCal() {
		return esicWagesCal;
	}
	public void setEsicWagesCal(double esicWagesCal) {
		this.esicWagesCal = esicWagesCal;
	}
	public double getGrossSalary() {
		return grossSalary;
	}
	public void setGrossSalary(double grossSalary) {
		this.grossSalary = grossSalary;
	}
	 
	public double getEpsWages() {
		return epsWages;
	}
	public void setEpsWages(double epsWages) {
		this.epsWages = epsWages;
	}
	public double getEsicWagesDec() {
		return esicWagesDec;
	}
	public void setEsicWagesDec(double esicWagesDec) {
		this.esicWagesDec = esicWagesDec;
	}
	public float getEmployeePf() {
		return employeePf;
	}
	public void setEmployeePf(float employeePf) {
		this.employeePf = employeePf;
	}
	public double getEmployerEps() {
		return employerEps;
	}
	public void setEmployerEps(double employerEps) {
		this.employerEps = employerEps;
	}
	public double getEmployerPf() {
		return employerPf;
	}
	public void setEmployerPf(double employerPf) {
		this.employerPf = employerPf;
	}
	public float getEsic() {
		return esic;
	}
	public void setEsic(float esic) {
		this.esic = esic;
	}
	public double getEmployerEsic() {
		return employerEsic;
	}
	public void setEmployerEsic(double employerEsic) {
		this.employerEsic = employerEsic;
	}
	public int getEsicStatus() {
		return esicStatus;
	}
	public void setEsicStatus(int esicStatus) {
		this.esicStatus = esicStatus;
	}
	 
	public double getMlwf() {
		return mlwf;
	}
	public void setMlwf(double mlwf) {
		this.mlwf = mlwf;
	}
	public double getTds() {
		return tds;
	}
	public void setTds(double tds) {
		this.tds = tds;
	}
	public double getItded() {
		return itded;
	}
	public void setItded(double itded) {
		this.itded = itded;
	}
	public double getFund() {
		return fund;
	}
	public void setFund(double fund) {
		this.fund = fund;
	}
	public double getTotPfAdminCh() {
		return totPfAdminCh;
	}
	public void setTotPfAdminCh(double totPfAdminCh) {
		this.totPfAdminCh = totPfAdminCh;
	}
	public double getTotEdliCh() {
		return totEdliCh;
	}
	public void setTotEdliCh(double totEdliCh) {
		this.totEdliCh = totEdliCh;
	}
	public double getTotEdliAdminCh() {
		return totEdliAdminCh;
	}
	public void setTotEdliAdminCh(double totEdliAdminCh) {
		this.totEdliAdminCh = totEdliAdminCh;
	}
	public int getNcpDays() {
		return ncpDays;
	}
	public void setNcpDays(int ncpDays) {
		this.ncpDays = ncpDays;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getPtDed() {
		return ptDed;
	}
	public void setPtDed(double ptDed) {
		this.ptDed = ptDed;
	}
	public double getAdvanceDed() {
		return advanceDed;
	}
	public void setAdvanceDed(double advanceDed) {
		this.advanceDed = advanceDed;
	}
	public double getLoanDed() {
		return loanDed;
	}
	public void setLoanDed(double loanDed) {
		this.loanDed = loanDed;
	}
	public double getMiscExpDed() {
		return miscExpDed;
	}
	public void setMiscExpDed(double miscExpDed) {
		this.miscExpDed = miscExpDed;
	}
	public int getMiscExpDedDeduct() {
		return miscExpDedDeduct;
	}
	public void setMiscExpDedDeduct(int miscExpDedDeduct) {
		this.miscExpDedDeduct = miscExpDedDeduct;
	}
	public double getNetSalary() {
		return netSalary;
	}
	public void setNetSalary(double netSalary) {
		this.netSalary = netSalary;
	}
	public String getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(String isLocked) {
		this.isLocked = isLocked;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
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
	public double getPayDed() {
		return payDed;
	}
	public void setPayDed(double payDed) {
		this.payDed = payDed;
	}
	public double getDefaultBasicSalary() {
		return defaultBasicSalary;
	}
	public void setDefaultBasicSalary(double defaultBasicSalary) {
		this.defaultBasicSalary = defaultBasicSalary;
	}
	public double getDefaultGroassSalary() {
		return defaultGroassSalary;
	}
	public void setDefaultGroassSalary(double defaultGroassSalary) {
		this.defaultGroassSalary = defaultGroassSalary;
	}
	public String getCommentsForItBonus() {
		return commentsForItBonus;
	}
	public void setCommentsForItBonus(String commentsForItBonus) {
		this.commentsForItBonus = commentsForItBonus;
	}
	public double getSocietyContribution() {
		return societyContribution;
	}
	public void setSocietyContribution(double societyContribution) {
		this.societyContribution = societyContribution;
	}
	public String getEmpCategory() {
		return empCategory;
	}
	public void setEmpCategory(String empCategory) {
		this.empCategory = empCategory;
	}
	public double getOthFcal1() {
		return othFcal1;
	}
	public void setOthFcal1(double othFcal1) {
		this.othFcal1 = othFcal1;
	}
	public double getOthFcal2() {
		return othFcal2;
	}
	public void setOthFcal2(double othFcal2) {
		this.othFcal2 = othFcal2;
	}
	public double getOthFcal3() {
		return othFcal3;
	}
	public void setOthFcal3(double othFcal3) {
		this.othFcal3 = othFcal3;
	}
	public int getPfStatus() {
		return pfStatus;
	}
	public void setPfStatus(int pfStatus) {
		this.pfStatus = pfStatus;
	}
	@Override
	public String toString() {
		return "SalaryCalc [id=" + id + ", cmpId=" + cmpId + ", empId=" + empId + ", empCode=" + empCode + ", empType="
				+ empType + ", contractorId=" + contractorId + ", departId=" + departId + ", designationId="
				+ designationId + ", locationId=" + locationId + ", calcMonth=" + calcMonth + ", calcYear=" + calcYear
				+ ", fYear=" + fYear + ", attSumId=" + attSumId + ", basicCal=" + basicCal + ", daCal=" + daCal
				+ ", vdaCal=" + vdaCal + ", washingAllownceCal=" + washingAllownceCal + ", addWashingCal="
				+ addWashingCal + ", othFcal1=" + othFcal1 + ", othFcal2=" + othFcal2 + ", othFcal3=" + othFcal3
				+ ", conveyanceAllowanceCa=" + conveyanceAllowanceCa + ", hraCal=" + hraCal + ", medicalAllowanceCal="
				+ medicalAllowanceCal + ", performanceBounsCal=" + performanceBounsCal + ", educationAllowanceCal="
				+ educationAllowanceCal + ", vehicleMaintenanceAllowanceCal=" + vehicleMaintenanceAllowanceCal
				+ ", attendanceCal=" + attendanceCal + ", canteenAllownceCal=" + canteenAllownceCal
				+ ", productionBonusCal=" + productionBonusCal + ", travellingAllownceCal=" + travellingAllownceCal
				+ ", functionalExpertiseAllownceCal=" + functionalExpertiseAllownceCal + ", leaveTravelAllownceCal="
				+ leaveTravelAllownceCal + ", dpCal=" + dpCal + ", agpCal=" + agpCal + ", claCal=" + claCal
				+ ", newsPaperAndPeriodicalAllownce=" + newsPaperAndPeriodicalAllownce + ", furnitureAllownce="
				+ furnitureAllownce + ", otWages=" + otWages + ", otherAllowance1=" + otherAllowance1
				+ ", otherAllowance2=" + otherAllowance2 + ", otherAllowance3=" + otherAllowance3 + ", miscExpAdd="
				+ miscExpAdd + ", bonusCal=" + bonusCal + ", exgretiaCal=" + exgretiaCal + ", daArreasCal="
				+ daArreasCal + ", incrementArreasCal=" + incrementArreasCal + ", epfWages=" + epfWages
				+ ", epfWagesEmployer=" + epfWagesEmployer + ", esicWagesCal=" + esicWagesCal + ", grossSalary="
				+ grossSalary + ", epsWages=" + epsWages + ", esicWagesDec=" + esicWagesDec + ", employeePf="
				+ employeePf + ", employerEps=" + employerEps + ", employerPf=" + employerPf + ", esic=" + esic
				+ ", employerEsic=" + employerEsic + ", esicStatus=" + esicStatus + ", pfStatus=" + pfStatus + ", mlwf="
				+ mlwf + ", tds=" + tds + ", itded=" + itded + ", fund=" + fund + ", totPfAdminCh=" + totPfAdminCh
				+ ", totEdliCh=" + totEdliCh + ", totEdliAdminCh=" + totEdliAdminCh + ", ncpDays=" + ncpDays
				+ ", status=" + status + ", ptDed=" + ptDed + ", advanceDed=" + advanceDed + ", loanDed=" + loanDed
				+ ", miscExpDed=" + miscExpDed + ", miscExpDedDeduct=" + miscExpDedDeduct + ", netSalary=" + netSalary
				+ ", isLocked=" + isLocked + ", loginName=" + loginName + ", loginTime=" + loginTime
				+ ", mlwfApplicable=" + mlwfApplicable + ", ptApplicable=" + ptApplicable + ", payDed=" + payDed
				+ ", defaultBasicSalary=" + defaultBasicSalary + ", defaultGroassSalary=" + defaultGroassSalary
				+ ", commentsForItBonus=" + commentsForItBonus + ", societyContribution=" + societyContribution
				+ ", empCategory=" + empCategory + "]";
	}
	 
	
	 
	 

}
