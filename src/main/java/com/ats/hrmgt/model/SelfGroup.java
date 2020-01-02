package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_self_grup")
public class SelfGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "selft_group_id")
	private int selftGroupId;

	@Column(name = "name")
	private String name;
 
	@Column(name = "del_status")
	private int delStatus;

	public int getSelftGroupId() {
		return selftGroupId;
	}

	public void setSelftGroupId(int selftGroupId) {
		this.selftGroupId = selftGroupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "SelfGroup [selftGroupId=" + selftGroupId + ", name=" + name + ", delStatus=" + delStatus + "]";
	}
	
	

}
