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

import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.MstCompany;
import com.ats.hrmgt.model.MstCompanySub;
import com.ats.hrmgt.repository.EmployeeMasterRepository;
import com.ats.hrmgt.repository.MstCompanyRepo;
import com.ats.hrmgt.repository.MstCompanySubRepo;

@RestController
public class CompanyApiController {

	// ***********************************************Company****************************************

	@Autowired
	MstCompanyRepo mstCompRepo;
	@Autowired
	EmployeeMasterRepository empMastRepo;

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

	// ***********************************************Sub
	// Company****************************************

	@Autowired
	MstCompanySubRepo mstCompanySubRepo;

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

	@RequestMapping(value = { "/getAllActiveSubCompanies" }, method = RequestMethod.GET)
	public List<MstCompanySub> getAllActiveSubCompanies() {
		List<MstCompanySub> list = new ArrayList<MstCompanySub>();
		try {
			list = mstCompanySubRepo.findByIsActive(1);
		} catch (Exception e) {
			System.err.println("Excep in getAllCompanies : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getSubCompanyById" }, method = RequestMethod.POST)
	public MstCompanySub getSubCompanyById(@RequestParam int companyId) {
		MstCompanySub company = new MstCompanySub();
		try {
			company = mstCompanySubRepo.findByCompanyId(companyId);
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
	public @ResponseBody Info deleteSubCompany(@RequestParam("compId") int compId,

			@RequestParam("companyId") int companyId) {

		Info info = new Info();

		try {

			List<EmployeeMaster> emplist = new ArrayList<EmployeeMaster>();

			emplist = empMastRepo.findByDelStatusAndCmpCodeAndSubCmpIdOrderByEmpIdDesc(1, companyId, compId);

			if (emplist.size() <= 0) {
				int delete = mstCompanySubRepo.deleteSubComp(compId);
				System.err.println("delete " + delete);
				if (delete > 0) {
					info.setError(false);
					info.setMsg("Company Deleted Successfully");
				} else {
					info.setError(true);
					info.setMsg("Failed to Delete Company");
				}

			} else {
				info.setError(true);
				info.setMsg("Company Can Not Be Deleted as it is Assigned To Employee");
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("Failed to Delete Company");
		}

		return info;

	}

	@RequestMapping(value = { "/changeCompActive" }, method = RequestMethod.POST)
	public @ResponseBody Info changeCompActive(@RequestParam("compId") int compId,
			@RequestParam("companyId") int companyId) {

		Info info = new Info();

		int status = 0;

		try {

			int flag = 0;
			 
			MstCompanySub company = new MstCompanySub();

			company = mstCompanySubRepo.findByCompanyId(compId);
 			 System.err.println(""+company.toString());
				if (company.getIsActive() == 1) {
					status = 0;
				} else {
					status = 1;
				}
			 

			if (status == 0) {
				List<EmployeeMaster> emplist = new ArrayList<EmployeeMaster>();

				emplist = empMastRepo.findByDelStatusAndCmpCodeAndSubCmpIdOrderByEmpIdDesc(1, companyId, compId);
				if (emplist.size() > 0) {
					flag = 1;
				}
			}

			if (flag == 0) {
				int delete = mstCompanySubRepo.activateSubComp(compId, status);
				if (delete > 0) {
					info.setError(false);
					info.setMsg("Company Updated Successfully");
				} else {
					info.setError(true);
					info.setMsg("Failed to Update");
				}
			} else {
				info.setError(true);
				info.setMsg("Company Can Not Be Deactivated as it is Assigned To Employee");
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("Failed to Update");
		}

		return info;

	}

}
