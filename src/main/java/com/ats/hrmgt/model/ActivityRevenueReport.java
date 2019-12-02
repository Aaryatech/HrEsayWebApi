package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class ActivityRevenueReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="project_type_id")
	private int projectTypeId ;
	
	@Column(name="revenue")
	private float revenue ;
	
	@Column(name="resource_cost")
	private float resourceCost;
	
	@Column(name="project_type_title")
	private String projectTypeTitle;
	
	@Column(name="project_type_title_short")
	private String projectTypeTitleShort;

	public int getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(int projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	public float getRevenue() {
		return revenue;
	}

	public void setRevenue(float revenue) {
		this.revenue = revenue;
	}

	public float getResourceCost() {
		return resourceCost;
	}

	public void setResourceCost(float resourceCost) {
		this.resourceCost = resourceCost;
	}

	public String getProjectTypeTitle() {
		return projectTypeTitle;
	}

	public void setProjectTypeTitle(String projectTypeTitle) {
		this.projectTypeTitle = projectTypeTitle;
	}

	public String getProjectTypeTitleShort() {
		return projectTypeTitleShort;
	}

	public void setProjectTypeTitleShort(String projectTypeTitleShort) {
		this.projectTypeTitleShort = projectTypeTitleShort;
	}

	@Override
	public String toString() {
		return "ActivityRevenueReport [projectTypeId=" + projectTypeId + ", revenue=" + revenue + ", resourceCost="
				+ resourceCost + ", projectTypeTitle=" + projectTypeTitle + ", projectTypeTitleShort="
				+ projectTypeTitleShort + "]";
	}
	 
	
	

}
