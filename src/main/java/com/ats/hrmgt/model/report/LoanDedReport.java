package com.ats.hrmgt.model.report;

import java.util.Date;

import javax.persistence.Entity;

/*@Entity*/
public class LoanDedReport {
	
	/* @Id */
	private int empId;
	
	private String empCode;
	
	private String empName;
	
	
	private int loanAmt;
	private int loanEmi;


	private double loanRoi;

	private int loanTenure;

	private Date loanRepayStart;

	private Date loanRepayEnd;

	private int loanRepayAmt;

	
	private int loanEmiIntrest;


	
	
	
	
	

}
