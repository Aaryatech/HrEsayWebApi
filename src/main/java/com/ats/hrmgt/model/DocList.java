package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DocList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name="doctype_id")
	private int doctypeId;
	
	@Column(name="doctype_key")
	private String doctypeKey;
	
	@Column(name="doctype_name")
	private String doctypeName;	
	
	@Column(name="image_name")
	private String imageName;
	
	@Column(name="doc_id")
	private int docId ;
	
	@Column(name="is_required")
	private int isRequired ;

	public int getDoctypeId() {
		return doctypeId;
	}

	public void setDoctypeId(int doctypeId) {
		this.doctypeId = doctypeId;
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

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public int getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(int isRequired) {
		this.isRequired = isRequired;
	}

	public int getDocId() {
		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
	}

	@Override
	public String toString() {
		return "DocList [doctypeId=" + doctypeId + ", doctypeKey=" + doctypeKey + ", doctypeName=" + doctypeName
				+ ", imageName=" + imageName + ", docId=" + docId + ", isRequired=" + isRequired + "]";
	}
 
	

}
