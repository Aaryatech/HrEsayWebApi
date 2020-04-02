package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_setting")
public class Setting {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "setting_id")
	private int settingId;

	@Column(name = "group")
	private String group;

	@Column(name = "key")
	private String key;

	@Column(name = "value")
	private String value;

	@Column(name = "serialized ")
	private int serialized;

	@Column(name = "editable")
	private int editable;
	
	@Column(name = "labels")
	private String labels;
	
	@Column(name = "default_value")
	private String defaultValue;
	
	@Column(name = "explaination")
	private String explaination;
	
	@Column(name = "ex_int1")
	private int exInt1;	
	
	@Column(name = "ex_var1")
	private String exVar1;	


	public int getSettingId() {
		return settingId;
	}



	public void setSettingId(int settingId) {
		this.settingId = settingId;
	}



	public String getGroup() {
		return group;
	}



	public void setGroup(String group) {
		this.group = group;
	}



	public String getKey() {
		return key;
	}



	public void setKey(String key) {
		this.key = key;
	}



	public String getValue() {
		return value;
	}



	public void setValue(String value) {
		this.value = value;
	}



	public int getSerialized() {
		return serialized;
	}



	public void setSerialized(int serialized) {
		this.serialized = serialized;
	}



	public int getEditable() {
		return editable;
	}



	public void setEditable(int editable) {
		this.editable = editable;
	}



	public String getLabels() {
		return labels;
	}



	public void setLabels(String labels) {
		this.labels = labels;
	}



	public String getDefaultValue() {
		return defaultValue;
	}



	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}



	public String getExplaination() {
		return explaination;
	}



	public void setExplaination(String explaination) {
		this.explaination = explaination;
	}



	public int getExInt1() {
		return exInt1;
	}



	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}



	public String getExVar1() {
		return exVar1;
	}



	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}



	@Override
	public String toString() {
		return "Setting [settingId=" + settingId + ", group=" + group + ", key=" + key + ", value=" + value
				+ ", serialized=" + serialized + ", editable=" + editable + ", labels=" + labels + ", defaultValue="
				+ defaultValue + ", explaination=" + explaination + ", exInt1=" + exInt1 + ", exVar1=" + exVar1 + "]";
	}


}
