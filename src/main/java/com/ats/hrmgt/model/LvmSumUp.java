package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_lvm_sumup")
public class LvmSumUp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "name_sd")
	private String nameSd;

	@Column(name = "is_used")
	private String isUsed;

	@Column(name = "ispermanent")
	private int ispermanent;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameSd() {
		return nameSd;
	}

	public void setNameSd(String nameSd) {
		this.nameSd = nameSd;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	public int getIspermanent() {
		return ispermanent;
	}

	public void setIspermanent(int ispermanent) {
		this.ispermanent = ispermanent;
	}

	@Override
	public String toString() {
		return "LvmSumUp [id=" + id + ", name=" + name + ", nameSd=" + nameSd + ", isUsed=" + isUsed + ", ispermanent="
				+ ispermanent + "]";
	}
	
	
 

}
