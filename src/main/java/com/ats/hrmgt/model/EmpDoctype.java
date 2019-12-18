package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dm_emp_doctype")
public class EmpDoctype {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="doctype_id")
	private int doctypeId;
	
	@Column(name="company_id")
	private int companyId;
	
	@Column(name="doctype_key")
	private String doctypeKey;
	
	@Column(name="doctype_name")
	private String doctypeName;
	
	@Column(name="doctype_note")
	private String doctypeNote;
	
	@Column(name="is_value")
	private int isValue;
	
	@Column(name="is_image")
	private int isImage;
	
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
	
	@Column(name="maker_user_id")
	private int makerUserId;
	
	@Column(name="maker_enter_datetime")
	private String makerEnterDatetime;
	
	@Column(name="is_required")
	private int isRequired;
	
	@Column(name="order_by")
	private int orderBy;
	
	@Column(name="ex_int1")
	private int exInt1;
	
	@Column(name="ex_int2")
	private int exInt2;
	
	@Column(name="ex_var1")
	private String exVar1;
	
	@Column(name="ex_var2")
	private String exVar2;

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

	public String getDoctypeKey() {
		return doctypeKey;
	}

	public void setDoctypeKey(String doctypeKey) {
		this.doctypeKey = doctypeKey;
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


	public int getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(int isRequired) {
		this.isRequired = isRequired;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
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
		return "EmpDoctype [doctypeId=" + doctypeId + ", companyId=" + companyId + ", doctypeKey=" + doctypeKey
				+ ", doctypeName=" + doctypeName + ", doctypeNote=" + doctypeNote + ", isValue=" + isValue
				+ ", isImage=" + isImage + ", imageSizeWidth=" + imageSizeWidth + ", imageSizeLength=" + imageSizeLength
				+ ", isRemarks=" + isRemarks + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId="
				+ makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", isRequired=" + isRequired
				+ ", orderBy=" + orderBy + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1
				+ ", exVar2=" + exVar2 + "]";
	}

	
}
