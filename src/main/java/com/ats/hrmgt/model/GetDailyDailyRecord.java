package com.ats.hrmgt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class GetDailyDailyRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="company_id")
	private int companyId;
	
	@Column(name="emp_code")
	private String empCode;
	
	@Column(name="emp_name")
	private String empName ;
	
	@Column(name="att_date")
	private Date attDate;
	
	@Column(name="att_status")
	private String attStatus;
	
	@Column(name="lv_sumup_id")
	private int lvSumupId;
	
	@Column(name="working_hrs")
	private String workingHrs; 
	
	@Column(name="in_time")
	private String inTime; 
	
	@Column(name="rec_status")
	private String recStatus;
	
	@Column(name="login_name")
	private String loginName;
	
	@Column(name="login_time")
	private String loginTime;
	
	@Column(name="import_date")
	private String importDate;
	
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="ot_hr")
	private String otHr;
	
	@Column(name="current_shiftid")
	private int currentShiftid;
	
	@Column(name="late_mark")
	private String lateMark;
	
	@Column(name="late_min")
	private int lateMin;
	
	@Column(name="reason")
	private String reason;
	
	@Column(name="current_shiftname")
	private String currentShiftname;
	
	@Column(name="freeze_by_supervisor")
	private int freezeBySupervisor;
	
	@Column(name="comments_supervisor")
	private String commentsSupervisor;
	
	@Column(name="get_pass_used_count")
	private int getPassUsedCount;
	
	@Column(name="get_pass_used_hour")
	private int getPassUsedHour;
	
	@Column(name="get_pass_used_hour_reason")
	private String getPassUsedHourReason;
	
	@Column(name="raw_data_inout")
	private String rawDataInout;
	
	@Column(name="manual_ot_hr")
	private int manualOtHr;
	
	@Column(name="full_night")
	private int fullNight;
	
	@Column(name="half_night")
	private int halfNight;
	 
	@Column(name="out_time")
	private String outTime;
	
	@Column(name="early_going_mark")
	private int earlyGoingMark;
	
	@Column(name="early_going_min")
	private int earlyGoingMin;
	
	@Column(name="multiple_entries")
	private String multipleEntries;
	
	@Column(name="casetype")
	private String casetype;
	
	@Column(name="is_fixed")
	private int isFixed;
	
	@Column(name="by_file_updated")
	private int byFileUpdated;
	
	@Column(name="location_id")
	private int locationId;
	
	@Column(name="emp_type")
	private int empType;
	
	@Column(name="emp_json")
	private String empJson;
	
	@Column(name="atsumm_uid")
	private String atsummUid;
	
	@Column(name="file_name")
	private String fileName;
	
	@Column(name="row_id")
	private int rowId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy") 
	public Date getAttDate() {
		return attDate;
	}

	public void setAttDate(Date attDate) {
		this.attDate = attDate;
	}

	public String getAttStatus() {
		return attStatus;
	}

	public void setAttStatus(String attStatus) {
		this.attStatus = attStatus;
	}

	public int getLvSumupId() {
		return lvSumupId;
	}

	public void setLvSumupId(int lvSumupId) {
		this.lvSumupId = lvSumupId;
	}

	public String getWorkingHrs() {
		return workingHrs;
	}

	public void setWorkingHrs(String workingHrs) {
		this.workingHrs = workingHrs;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getRecStatus() {
		return recStatus;
	}

	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
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

	public String getImportDate() {
		return importDate;
	}

	public void setImportDate(String importDate) {
		this.importDate = importDate;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getOtHr() {
		return otHr;
	}

	public void setOtHr(String otHr) {
		this.otHr = otHr;
	}

	public int getCurrentShiftid() {
		return currentShiftid;
	}

	public void setCurrentShiftid(int currentShiftid) {
		this.currentShiftid = currentShiftid;
	}

	public String getLateMark() {
		return lateMark;
	}

	public void setLateMark(String lateMark) {
		this.lateMark = lateMark;
	}

	public int getLateMin() {
		return lateMin;
	}

	public void setLateMin(int lateMin) {
		this.lateMin = lateMin;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCurrentShiftname() {
		return currentShiftname;
	}

	public void setCurrentShiftname(String currentShiftname) {
		this.currentShiftname = currentShiftname;
	}

	public int getFreezeBySupervisor() {
		return freezeBySupervisor;
	}

	public void setFreezeBySupervisor(int freezeBySupervisor) {
		this.freezeBySupervisor = freezeBySupervisor;
	}

	public String getCommentsSupervisor() {
		return commentsSupervisor;
	}

	public void setCommentsSupervisor(String commentsSupervisor) {
		this.commentsSupervisor = commentsSupervisor;
	}

	public int getGetPassUsedCount() {
		return getPassUsedCount;
	}

	public void setGetPassUsedCount(int getPassUsedCount) {
		this.getPassUsedCount = getPassUsedCount;
	}

	public int getGetPassUsedHour() {
		return getPassUsedHour;
	}

	public void setGetPassUsedHour(int getPassUsedHour) {
		this.getPassUsedHour = getPassUsedHour;
	}

	public String getGetPassUsedHourReason() {
		return getPassUsedHourReason;
	}

	public void setGetPassUsedHourReason(String getPassUsedHourReason) {
		this.getPassUsedHourReason = getPassUsedHourReason;
	}

	public String getRawDataInout() {
		return rawDataInout;
	}

	public void setRawDataInout(String rawDataInout) {
		this.rawDataInout = rawDataInout;
	}

	public int getManualOtHr() {
		return manualOtHr;
	}

	public void setManualOtHr(int manualOtHr) {
		this.manualOtHr = manualOtHr;
	}

	public int getFullNight() {
		return fullNight;
	}

	public void setFullNight(int fullNight) {
		this.fullNight = fullNight;
	}

	public int getHalfNight() {
		return halfNight;
	}

	public void setHalfNight(int halfNight) {
		this.halfNight = halfNight;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public int getEarlyGoingMark() {
		return earlyGoingMark;
	}

	public void setEarlyGoingMark(int earlyGoingMark) {
		this.earlyGoingMark = earlyGoingMark;
	}

	public int getEarlyGoingMin() {
		return earlyGoingMin;
	}

	public void setEarlyGoingMin(int earlyGoingMin) {
		this.earlyGoingMin = earlyGoingMin;
	}

	public String getMultipleEntries() {
		return multipleEntries;
	}

	public void setMultipleEntries(String multipleEntries) {
		this.multipleEntries = multipleEntries;
	}

	public String getCasetype() {
		return casetype;
	}

	public void setCasetype(String casetype) {
		this.casetype = casetype;
	}

	public int getIsFixed() {
		return isFixed;
	}

	public void setIsFixed(int isFixed) {
		this.isFixed = isFixed;
	}

	public int getByFileUpdated() {
		return byFileUpdated;
	}

	public void setByFileUpdated(int byFileUpdated) {
		this.byFileUpdated = byFileUpdated;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public int getEmpType() {
		return empType;
	}

	public void setEmpType(int empType) {
		this.empType = empType;
	}

	public String getEmpJson() {
		return empJson;
	}

	public void setEmpJson(String empJson) {
		this.empJson = empJson;
	}

	public String getAtsummUid() {
		return atsummUid;
	}

	public void setAtsummUid(String atsummUid) {
		this.atsummUid = atsummUid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	@Override
	public String toString() {
		return "GetDailyDailyRecord [id=" + id + ", companyId=" + companyId + ", empCode=" + empCode + ", empName="
				+ empName + ", attDate=" + attDate + ", attStatus=" + attStatus + ", lvSumupId=" + lvSumupId
				+ ", workingHrs=" + workingHrs + ", inTime=" + inTime + ", recStatus=" + recStatus + ", loginName="
				+ loginName + ", loginTime=" + loginTime + ", importDate=" + importDate + ", empId=" + empId + ", otHr="
				+ otHr + ", currentShiftid=" + currentShiftid + ", lateMark=" + lateMark + ", lateMin=" + lateMin
				+ ", reason=" + reason + ", currentShiftname=" + currentShiftname + ", freezeBySupervisor="
				+ freezeBySupervisor + ", commentsSupervisor=" + commentsSupervisor + ", getPassUsedCount="
				+ getPassUsedCount + ", getPassUsedHour=" + getPassUsedHour + ", getPassUsedHourReason="
				+ getPassUsedHourReason + ", rawDataInout=" + rawDataInout + ", manualOtHr=" + manualOtHr
				+ ", fullNight=" + fullNight + ", halfNight=" + halfNight + ", outTime=" + outTime + ", earlyGoingMark="
				+ earlyGoingMark + ", earlyGoingMin=" + earlyGoingMin + ", multipleEntries=" + multipleEntries
				+ ", casetype=" + casetype + ", isFixed=" + isFixed + ", byFileUpdated=" + byFileUpdated
				+ ", locationId=" + locationId + ", empType=" + empType + ", empJson=" + empJson + ", atsummUid="
				+ atsummUid + ", fileName=" + fileName + ", rowId=" + rowId + "]";
	}
	
	

}
