package com.ats.hrmgt.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DashboardCount {

	@Id
	private int pendingRequest;

	private int myLeave;

	private int info;

	private int isAuthorized;

	private int pendingClaim;

	private int myClaim;

	private int infoClaim;

	private int isAuthorizedClaim;

	public int getPendingRequest() {
		return pendingRequest;
	}

	public void setPendingRequest(int pendingRequest) {
		this.pendingRequest = pendingRequest;
	}

	public int getMyLeave() {
		return myLeave;
	}

	public void setMyLeave(int myLeave) {
		this.myLeave = myLeave;
	}

	public int getInfo() {
		return info;
	}

	public void setInfo(int info) {
		this.info = info;
	}

	public int getIsAuthorized() {
		return isAuthorized;
	}

	public void setIsAuthorized(int isAuthorized) {
		this.isAuthorized = isAuthorized;
	}

	public int getPendingClaim() {
		return pendingClaim;
	}

	public void setPendingClaim(int pendingClaim) {
		this.pendingClaim = pendingClaim;
	}

	public int getMyClaim() {
		return myClaim;
	}

	public void setMyClaim(int myClaim) {
		this.myClaim = myClaim;
	}

	public int getInfoClaim() {
		return infoClaim;
	}

	public void setInfoClaim(int infoClaim) {
		this.infoClaim = infoClaim;
	}

	public int getIsAuthorizedClaim() {
		return isAuthorizedClaim;
	}

	public void setIsAuthorizedClaim(int isAuthorizedClaim) {
		this.isAuthorizedClaim = isAuthorizedClaim;
	}

	@Override
	public String toString() {
		return "DashboardCount [pendingRequest=" + pendingRequest + ", myLeave=" + myLeave + ", info=" + info
				+ ", isAuthorized=" + isAuthorized + ", pendingClaim=" + pendingClaim + ", myClaim=" + myClaim
				+ ", infoClaim=" + infoClaim + ", isAuthorizedClaim=" + isAuthorizedClaim + "]";
	}

}
