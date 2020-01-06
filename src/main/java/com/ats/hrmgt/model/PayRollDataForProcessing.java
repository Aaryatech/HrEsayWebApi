package com.ats.hrmgt.model;

import java.util.List;

public class PayRollDataForProcessing {
	
	private List<EmpSalaryInfoForPayroll> list ;
	private List<Allowances> allowancelist ;
	public List<EmpSalaryInfoForPayroll> getList() {
		return list;
	}
	public void setList(List<EmpSalaryInfoForPayroll> list) {
		this.list = list;
	}
	public List<Allowances> getAllowancelist() {
		return allowancelist;
	}
	public void setAllowancelist(List<Allowances> allowancelist) {
		this.allowancelist = allowancelist;
	}
	@Override
	public String toString() {
		return "PayRollDataForProcessing [list=" + list + ", allowancelist=" + allowancelist + "]";
	}

	
}
