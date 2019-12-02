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



	@Override
	public String toString() {
		return "Setting [settingId=" + settingId + ", group=" + group + ", key=" + key + ", value=" + value
				+ ", serialized=" + serialized + ", editable=" + editable + ", getSettingId()=" + getSettingId()
				+ ", getGroup()=" + getGroup() + ", getKey()=" + getKey() + ", getValue()=" + getValue()
				+ ", getSerialized()=" + getSerialized() + ", getEditable()=" + getEditable() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	

}
