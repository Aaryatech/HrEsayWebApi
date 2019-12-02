package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dm_emp_doctype")
public class EmpDocType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="doctype_id")
	private int doctypeId;
	
	@Column(name="company_id")
	private int companyId;
	
	@Column(name="doctype_key")
	private String doctypKey;
	
	@Column(name="doctype_name")
	private String doctypeName;
	
	@Column(name="doctype_note")
	private String doctypeNote;
	
	@Column(name="is_value")
	private int isValue;
	
	@Column(name="is_image")
	private int  isImage ;
	
	@Column(name="image_size_width")
	private int imageSizeWidth;
	
	@Column(name="image_size_length")
	private int imageSizeLength;
	
	@Column(name="is_remarks")
	private int isRemarks;
	
	@Column(name="del_status")
	private int delStatus;
	
	@Column(name="is_active")
	private int isActive;
	
	@Column(name="maker_user_id ")
	private int makerUserId ;
	
	@Column(name="maker_enter_datetime")
	private String makerEnterDatetime;
	
	@Column(name="ex_int1")
	private int exInt1;
	
	@Column(name="ex_int2")
	private int exInt2;
	
	@Column(name="ex_int3")
	private int exInt3;
	
	@Column(name="ex_var1")
	private String exVar1; 
	
	@Column(name="ex_var2")
	private String exVar2; 
	
	@Column(name="ex_var3")
	private String exVar3;

	public int getDoctypeId() {
		return doctypeId;
	}

	public void setDoctypeId(int doctypeId) {
		this.doctypeId = doctypeId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getDoctypKey() {
		return doctypKey;
	}

	public void setDoctypKey(String doctypKey) {
		this.doctypKey = doctypKey;
	}

	public String getDoctypeName() {
		return doctypeName;
	}

	public void setDoctypeName(String doctypeName) {
		this.doctypeName = doctypeName;
	}

	public String getDoctypeNote() {
		return doctypeNote;
	}

	public void setDoctypeNote(String doctypeNote) {
		this.doctypeNote = doctypeNote;
	}

	public int getIsValue() {
		return isValue;
	}

	public void setIsValue(int isValue) {
		this.isValue = isValue;
	}

	public int getIsImage() {
		return isImage;
	}

	public void setIsImage(int isImage) {
		this.isImage = isImage;
	}

	public int getImageSizeWidth() {
		return imageSizeWidth;
	}

	public void setImageSizeWidth(int imageSizeWidth) {
		this.imageSizeWidth = imageSizeWidth;
	}

	public int getImageSizeLength() {
		return imageSizeLength;
	}

	public void setImageSizeLength(int imageSizeLength) {
		this.imageSizeLength = imageSizeLength;
	}

	public int getIsRemarks() {
		return isRemarks;
	}

	public void setIsRemarks(int isRemarks) {
		this.isRemarks = isRemarks;
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

	@Override
	public String toString() {
		return "EmpDocType [doctypeId=" + doctypeId + ", companyId=" + companyId + ", doctypKey=" + doctypKey
				+ ", doctypeName=" + doctypeName + ", doctypeNote=" + doctypeNote + ", isValue=" + isValue
				+ ", isImage=" + isImage + ", imageSizeWidth=" + imageSizeWidth + ", imageSizeLength=" + imageSizeLength
				+ ", isRemarks=" + isRemarks + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId="
				+ makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2="
				+ exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3
				+ "]";
	}

	
}
