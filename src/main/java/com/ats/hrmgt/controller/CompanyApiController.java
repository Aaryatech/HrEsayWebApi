package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.MstCompany;
import com.ats.hrmgt.repository.MstCompanyRepo;

@RestController
public class CompanyApiController {

	@Autowired MstCompanyRepo mstCompRepo;
	
	@RequestMapping(value = { "/getAllCompanies" }, method = RequestMethod.GET)
	public List<MstCompany> getAllCompanies() {
		List<MstCompany> list = new ArrayList<MstCompany>();
		try {
			list = mstCompRepo.findByDelStatusOrderByCompanyIdDesc(1);
		} catch (Exception e) {
			System.err.println("Excep in getAllCompanies : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
	
	@RequestMapping(value = { "/getCompanyById" }, method = RequestMethod.POST)
	public MstCompany getPayDeductionById(@RequestParam int companyId) {
		MstCompany company = new MstCompany();
		try {
			company = mstCompRepo.findByCompanyIdAndDelStatus(companyId, 1);
		} catch (Exception e) {
			System.err.println("Excep in getCompanyById : " + e.getMessage());
			e.printStackTrace();
		}

		return company;

	}
	
	
	@RequestMapping(value = { "/saveNewCompany" }, method = RequestMethod.POST)
	public MstCompany saveDeductnPaymentType(@RequestBody MstCompany company) {
		MstCompany saveCompany = new MstCompany();
		try {
			saveCompany = mstCompRepo.save(company);
		} catch (Exception e) {
			System.err.println("Excep in /saveNewCompany : " + e.getMessage());
			e.printStackTrace();
		}

		return saveCompany;

	}
	
}
