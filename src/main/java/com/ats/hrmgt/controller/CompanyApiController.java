package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.MstCompany;
import com.ats.hrmgt.model.MstCompanySub;
import com.ats.hrmgt.repository.MstCompanyRepo;
import com.ats.hrmgt.repository.MstCompanySubRepo;

@RestController
public class CompanyApiController {
	
	
	
	//***********************************************Company****************************************

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
	
	
	//***********************************************Sub Company****************************************

		@Autowired MstCompanySubRepo mstCompanySubRepo;
		
		@RequestMapping(value = { "/getAllSubCompanies" }, method = RequestMethod.GET)
		public List<MstCompanySub> getAllSubCompanies() {
			List<MstCompanySub> list = new ArrayList<MstCompanySub>();
			try {
				list = mstCompanySubRepo.findAll();
			} catch (Exception e) {
				System.err.println("Excep in getAllCompanies : " + e.getMessage());
				e.printStackTrace();
			}

			return list;
		}
		
		@RequestMapping(value = { "/getSubCompanyById" }, method = RequestMethod.POST)
		public MstCompany getSubCompanyById(@RequestParam int companyId) {
			MstCompany company = new MstCompany();
			try {
				company = mstCompRepo.findByCompanyIdAndDelStatus(companyId, 1);
			} catch (Exception e) {
				System.err.println("Excep in getCompanyById : " + e.getMessage());
				e.printStackTrace();
			}

			return company;

		}
		
		
		@RequestMapping(value = { "/saveSubNewCompany" }, method = RequestMethod.POST)
		public MstCompanySub saveSubNewCompany(@RequestBody MstCompanySub company) {
			MstCompanySub saveCompany = new MstCompanySub();
			try {
				saveCompany = mstCompanySubRepo.save(company);
			} catch (Exception e) {
				System.err.println("Excep in /saveNewCompany : " + e.getMessage());
				e.printStackTrace();
			}

			return saveCompany;

		}
		
		
		@RequestMapping(value = { "/deleteSubCompany" }, method = RequestMethod.POST)
		public @ResponseBody Info deleteSubCompany(@RequestParam("compId") int compId) {

			Info info = new Info();

			try {

				int delete = mstCompanySubRepo.deleteSubComp(compId);

				if (delete > 0) {
					info.setError(false);
					info.setMsg("deleted");
				} else {
					info.setError(true);
					info.setMsg("failed");
				}

			} catch (Exception e) {

				e.printStackTrace();
				info.setError(true);
				info.setMsg("failed");
			}

			return info;

		}
		
		
		@RequestMapping(value = { "/changeCompActive" }, method = RequestMethod.POST)
		public @ResponseBody Info changeCompActive(@RequestParam("compId") int compId,@RequestParam("stat") int stat) {

			Info info = new Info();

			try {

				int delete = mstCompanySubRepo.activateSubComp(compId,stat);

				if (delete > 0) {
					info.setError(false);
					info.setMsg("deleted");
				} else {
					info.setError(true);
					info.setMsg("failed");
				}

			} catch (Exception e) {

				e.printStackTrace();
				info.setError(true);
				info.setMsg("failed");
			}

			return info;

		}

	
}
