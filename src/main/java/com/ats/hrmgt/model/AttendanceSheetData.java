package com.ats.hrmgt.model;

import java.util.List;

public class AttendanceSheetData {
	
	private List<String> dates;

	public List<String> getDates() {
		return dates;
	}

	public void setDates(List<String> dates) {
		this.dates = dates;
	}

	@Override
	public String toString() {
		return "AttendanceSheetData [dates=" + dates + "]";
	}
	

}
