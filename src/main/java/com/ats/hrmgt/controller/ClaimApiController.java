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

import com.ats.hrmgt.claim.repository.ClaimAuthorityRepo;
import com.ats.hrmgt.claim.repository.ClaimRepository;
import com.ats.hrmgt.claim.repository.GetClaimApplyAuthRepo;
import com.ats.hrmgt.claim.repository.GetClaimAuthorityRepo;
import com.ats.hrmgt.claim.repository.GetEmployeeAuthorityWiseRepo;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.SalaryTypesMaster;
import com.ats.hrmgt.model.claim.ClaimAuthority;
import com.ats.hrmgt.model.claim.ClaimProof;
import com.ats.hrmgt.model.claim.ClaimProofRepo;
import com.ats.hrmgt.model.claim.ClaimType;
import com.ats.hrmgt.model.claim.Customer;
import com.ats.hrmgt.model.claim.GetClaimApplyAuthWise;
import com.ats.hrmgt.model.claim.GetClaimAuthority;
import com.ats.hrmgt.model.claim.GetEmployeeAuthorityWise;
import com.ats.hrmgt.repository.GetAuthorityIdsRepo;
import com.ats.hrmgt.repository.GetLeaveAuthorityRepo;
import com.ats.hrmgt.repository.SalaryTypesMasterRepo;
 

@RestController
public class ClaimApiController {

	@Autowired
	ClaimRepository claimRepository;

	@Autowired
	ClaimAuthorityRepo claimAuthorityRepo;

	@Autowired
	GetClaimAuthorityRepo getClaimAuthorityRepo;

	@Autowired
	GetClaimApplyAuthRepo getClaimApplyAuthRepo;

	@Autowired
	GetEmployeeAuthorityWiseRepo getEmployeeAuthorityWise;

	@Autowired
	GetAuthorityIdsRepo getAuthorityIdsRepo;
	
	
	@Autowired
 	ClaimProofRepo claimProofRepo;
	
	@Autowired
	GetLeaveAuthorityRepo getLeaveAuthorityRepo;
	
	
	/*
	 * @Autowired EmployeeInfoRepository employeeInfoRepository;
	 * 
	 * @Autowired GetLeaveAuthorityRepo getLeaveAuthorityRepo;
	 * 
	 * @RequestMapping(value = { "/getUserInfoByContcAndEmail" }, method =
	 * RequestMethod.POST) public @ResponseBody EmployeeInfo
	 * getUserInfoByConAndEmail(@RequestParam int checkValue,
	 * 
	 * @RequestParam String inputValue) {
	 * 
	 * EmployeeInfo employeeInfo = null; if (checkValue == 1) { // Its Contact no
	 * check employeeInfo =
	 * employeeInfoRepository.findByDelStatusAndIsActiveAndEmpMobile1(1, 1,
	 * inputValue); } else {
	 * 
	 * // Its Email check employeeInfo =
	 * employeeInfoRepository.findByDelStatusAndIsActiveAndEmpEmail(1, 1,
	 * inputValue); } return employeeInfo;
	 * 
	 * }
	 */

	@RequestMapping(value = { "/saveClaimProof" }, method = RequestMethod.POST)
	public @ResponseBody List<ClaimProof> saveClaimProof(@RequestBody List<ClaimProof> claimProof) {
		System.err.println("res1 claim is");
		List<ClaimProof> save = new ArrayList<ClaimProof>();
		try {

			save = claimProofRepo.saveAll(claimProof);

			/*
			 * if (save != null) { save.setError(false); } else {
			 * 
			 * save = new ClaimProof(); save.setError(true); }
			 */

		} catch (Exception e) {
			/*
			 * save = new ClaimProof(); save.setError(true);
			 */
			e.printStackTrace();
		}

		return save;
	}
	
	

	@RequestMapping(value = { "/saveSingleClaimProof" }, method = RequestMethod.POST)
	public @ResponseBody ClaimProof saveSingleClaimProof(@RequestBody ClaimProof claimProof) {
		System.err.println("res1 claim is");
		ClaimProof save = new ClaimProof();
		try {

			save = claimProofRepo.saveAndFlush(claimProof);

			
			  if (save != null) { save.setError(false); } else {
			  
			  save = new ClaimProof(); save.setError(true); }
			 

		} catch (Exception e) {
			
			  save = new ClaimProof(); save.setError(true);
			 
			e.printStackTrace();
		}

		return save;
	}
	@RequestMapping(value = { "/getClaimProofByClaimId" }, method = RequestMethod.POST)
	public @ResponseBody List<ClaimProof> getClaimProofByClaimId(@RequestParam("claimId") int claimId) {

		List<ClaimProof> list = new ArrayList<ClaimProof>();
		try {

			list = claimProofRepo.findByClaimIdAndDelStatus(claimId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/deleteClaimProof" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteClaimProof(@RequestParam("cpId") int cpId) {

		Info info = new Info();

		try {

			int delete = claimProofRepo.deleteClaimProof(cpId);

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
	// -------------------Customer --------------------

	/*
	 * @RequestMapping(value = { "/saveCustomer" }, method = RequestMethod.POST)
	 * public @ResponseBody Customer saveCustomer(@RequestBody Customer customer) {
	 * 
	 * Customer save = new Customer(); try {
	 * 
	 * save = customerRepo.saveAndFlush(customer);
	 * 
	 * if (save != null) { save.setError(false); } else {
	 * 
	 * save = new Customer(); save.setError(true); }
	 * 
	 * } catch (Exception e) { save = new Customer(); save.setError(true);
	 * e.printStackTrace(); }
	 * 
	 * return save; }
	 * 
	 * @RequestMapping(value = { "/getCustListByCompanyId" }, method =
	 * RequestMethod.POST) public @ResponseBody List<Customer>
	 * getCustListByCompanyId(@RequestParam("companyId") int companyId) {
	 * 
	 * List<Customer> list = new ArrayList<Customer>(); try {
	 * 
	 * list = customerRepo.findByDelStatusAndCompanyId(1, companyId);
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return list;
	 * 
	 * }
	 * 
	 * @RequestMapping(value = { "/getCustByCustId" }, method = RequestMethod.POST)
	 * public @ResponseBody Customer getCustByCustId(@RequestParam("custId") int
	 * custId) {
	 * 
	 * Customer cust = new Customer(); try {
	 * 
	 * cust = customerRepo.findByCustIdAndDelStatus(custId, 1);
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return cust;
	 * 
	 * }
	 * 
	 * @RequestMapping(value = { "/deleteCustomer" }, method = RequestMethod.POST)
	 * public @ResponseBody Info deleteCustomer(@RequestParam("custId") int custId)
	 * {
	 * 
	 * Info info = new Info();
	 * 
	 * try {
	 * 
	 * int delete = customerRepo.deleteCust(custId);
	 * 
	 * if (delete > 0) { info.setError(false); info.setMsg("deleted"); } else {
	 * info.setError(true); info.setMsg("failed"); }
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); info.setError(true); info.setMsg("failed"); }
	 * 
	 * return info;
	 * 
	 * }
	 */

//-------------------Claim Authority--------------------
	@RequestMapping(value = { "/getClaimAuthorityListByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody ClaimAuthority getLeaveAuthorityListByEmpId(@RequestParam("empId") int empId) {

		ClaimAuthority list = new ClaimAuthority();
		try {

			list = claimAuthorityRepo.findByDelStatusAndEmpId(1, empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getClaimAuthorityList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetClaimAuthority> getClaimAuthorityList(@RequestParam("companyId") int companyId,
			@RequestParam("locIdList") List<Integer> locIdList) {

		List<GetClaimAuthority> list = new ArrayList<GetClaimAuthority>();
		try {

			list = getClaimAuthorityRepo.getClaimAuth(companyId, locIdList);

			for (int i = 0; i < list.size(); i++) {

				String[] reportIds = list.get(i).getCaRepToEmpIds().split(",");
				
				System.err.println("rep to ids  "+reportIds.toString());

				List<String> name = getLeaveAuthorityRepo.getEmpReportingName(reportIds);
				list.get(i).setRePortingName(name);
				System.err.println("rep to "+name.toString());
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/saveClaimAuthority" }, method = RequestMethod.POST)
	public @ResponseBody ClaimAuthority saveClaimAuthority(@RequestBody ClaimAuthority claimAuthority) {

		ClaimAuthority save = new ClaimAuthority();
		try {

			save = claimAuthorityRepo.saveAndFlush(claimAuthority);

			if (save != null) {
				save.setError(false);
			} else {

				save = new ClaimAuthority();
				save.setError(true);
			}

		} catch (Exception e) {
			save = new ClaimAuthority();
			save.setError(true);
			e.printStackTrace();
		}

		return save;
	}

	@RequestMapping(value = { "/saveClaimType" }, method = RequestMethod.POST)
	public @ResponseBody ClaimType saveClaimType(@RequestBody ClaimType claimType) {

		ClaimType save = new ClaimType();
		try {

			save = claimRepository.saveAndFlush(claimType);

			if (save != null) {
				save.setError(false);
			} else {

				save = new ClaimType();
				save.setError(true);
			}

		} catch (Exception e) {
			save = new ClaimType();
			save.setError(true);
			e.printStackTrace();
		}

		return save;
	}

	@RequestMapping(value = { "/getClaimList" }, method = RequestMethod.GET)
	public @ResponseBody List<ClaimType> getClaimList() {

		List<ClaimType> list = new ArrayList<ClaimType>();
		try {

			list = claimRepository.findByDelStatusOrderByClaimTypeIdDesc(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getClaimListByCompanyId" }, method = RequestMethod.POST)
	public @ResponseBody List<ClaimType> getClaimListByCompanyId(@RequestParam("companyId") int companyId) {

		List<ClaimType> list = new ArrayList<ClaimType>();
		try {

			list = claimRepository.findByDelStatusAndCompanyIdOrderByCompanyIdDesc(1, companyId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/deleteClaimType" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteClaimType(@RequestParam("claimTypeId") int claimTypeId) {

		Info info = new Info();

		try {

			int delete = claimRepository.deleteClaimType(claimTypeId);

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
	// -------------------Claim Type---------- --------------------

	@RequestMapping(value = { "/getClaimById" }, method = RequestMethod.POST)
	public @ResponseBody ClaimType getClaimById(@RequestParam("claimTypeId") int claimTypeId) {

		ClaimType claimType = new ClaimType();
		try {

			claimType = claimRepository.findByClaimTypeIdAndDelStatus(claimTypeId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return claimType;

	}

	@RequestMapping(value = { "/getClaimApplyListForAuth" }, method = RequestMethod.POST)
	public @ResponseBody List<GetClaimApplyAuthWise> getClaimApplyListForAuth(@RequestParam("empId") int empId,
			@RequestParam("statusList") List<Integer> statusList, @RequestParam("authTypeId") int authTypeId) {
		List<GetClaimApplyAuthWise> list = new ArrayList<GetClaimApplyAuthWise>();
		List<GetEmployeeAuthorityWise> empIdList = new ArrayList<GetEmployeeAuthorityWise>();
		if (authTypeId == 1) {

			empIdList = getEmployeeAuthorityWise.getClaimIdListForInitialAuth(empId);
			System.err.println("empIdList" + empIdList.size());

		} else {

			empIdList = getEmployeeAuthorityWise.getClaimIdListForFinalAuth(empId);
			System.err.println("empIdList" + empIdList.size());

		}

		try {

			list = getClaimApplyAuthRepo.getClaimApplyList(empIdList, statusList);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

}
