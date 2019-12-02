package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class ProjectLocationWise {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="pallot_id")
	private int pallotId ;
	
	@Column(name="project_id")
	private int projectId ;
	
	@Column(name="loc_id")
	private int locId;
	
	@Column(name="resource_cost")
	private float resourceCost ;
	 
	@Column(name="project_title")
	private String projectTitle;
	 
	@Column(name="loc_name")
	private String locName;
	
	@Column(name="revenue")
	private float revenue;

	public int getPallotId() {
		return pallotId;
	}

	public void setPallotId(int pallotId) {
		this.pallotId = pallotId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getLocId() {
		return locId;
	}

	public void setLocId(int locId) {
		this.locId = locId;
	}

	public float getResourceCost() {
		return resourceCost;
	}

	public void setResourceCost(float resourceCost) {
		this.resourceCost = resourceCost;
	} 

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public float getRevenue() {
		return revenue;
	}

	public void setRevenue(float revenue) {
		this.revenue = revenue;
	}

	@Override
	public String toString() {
		return "ProjectLocationWise [pallotId=" + pallotId + ", projectId=" + projectId + ", locId=" + locId
				+ ", resourceCost=" + resourceCost + ", projectTitle=" + projectTitle + ", locName=" + locName
				+ ", revenue=" + revenue + "]";
	}
	
	
	 

}
