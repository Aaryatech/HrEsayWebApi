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

import com.ats.hrmgt.model.Allowances;
import com.ats.hrmgt.model.EmpDoctype;
import com.ats.hrmgt.model.EmpSalAllowance;
import com.ats.hrmgt.model.EmpSalaryInfo;
import com.ats.hrmgt.model.EmployeDoc;
import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.TblEmpBankInfo;
import com.ats.hrmgt.model.TblEmpInfo;
import com.ats.hrmgt.model.TblEmpNominees;
import com.ats.hrmgt.model.User;
import com.ats.hrmgt.repository.AllowancesRepo;
import com.ats.hrmgt.repository.EmpDoctypeRepo;
import com.ats.hrmgt.repository.EmpSalAllowanceRepo;
import com.ats.hrmgt.repository.EmpSalaryInfoRepo;
import com.ats.hrmgt.repository.EmployeeDocsRepository;
import com.ats.hrmgt.repository.EmployeeMasterRepository;
import com.ats.hrmgt.repository.TblEmpBankInfoRepo;
import com.ats.hrmgt.repository.TblEmpInfoRepo;
import com.ats.hrmgt.repository.TblEmpNomineesRepo;
import com.ats.hrmgt.repository.UserRepo;
@RestController
public class EmployeeApiController {
	@Autowired EmployeeMasterRepository empRepo;
	
	//EmpSupportRepository
	@Autowired TblEmpInfoRepo empInfoRepo;
	
	@Autowired TblEmpBankInfoRepo bankInfoRepo;
	
	@Autowired TblEmpNomineesRepo nomineeRepo;
	
	@Autowired EmpSalaryInfoRepo empSalRepo;
	
	@Autowired UserRepo userRepo;
	
	@Autowired AllowancesRepo allowanceRepo;
	
	@Autowired EmpSalAllowanceRepo empSalAllowanceRepo;
	
	@Autowired EmpDoctypeRepo empDocRepo;
	
	@Autowired	EmployeeDocsRepository employeeDocsRepository;
	
		/**********************************Employee*********************************/
		
		@RequestMapping(value = {"/getAllEmployee"}, method = RequestMethod.POST)
		public List<EmployeeMaster> getAllEmployee(@RequestParam int companyId){
			List<EmployeeMaster> list = new ArrayList<EmployeeMaster>();
			try {
				list = empRepo.findByDelStatusAndCmpCodeOrderByEmpIdDesc(1, companyId);
			}catch (Exception e) {
				System.err.println("Excep in getAllEmployee : "+e.getMessage());
				e.printStackTrace();
			}
			
			return list;
		}
		
		@RequestMapping(value = {"/getEmployeeById"}, method = RequestMethod.POST)
		public EmployeeMaster getEmployeeById(@RequestParam int empId) {
			EmployeeMaster emp = new EmployeeMaster();
			try {
				emp = empRepo.findByEmpIdAndDelStatus(empId, 1);
			}catch (Exception e) {
				System.err.println("Excep in getEmployeeById : "+e.getMessage());
				e.printStackTrace();
			}
			
			return emp;
			
		}
		
		@RequestMapping(value = { "/deleteEmployee" }, method = RequestMethod.POST)
		public Info deleteEmployee(@RequestParam int empId) {

			Info info = new Info();
			try {

				int res = empRepo.deleteEmployee(empId);
				
				if (res > 0) {
					info.setError(false);
					info.setMsg("Sucess");
				}else{
					info.setError(true);
					info.setMsg("Fail");
				}

			} catch (Exception e) {
				System.err.println("Excep in deleteEmployee : " + e.getMessage());
				e.printStackTrace();
			}

			return info;

		}
		
		@RequestMapping(value = {"/saveEmployee"}, method = RequestMethod.POST)
		public EmployeeMaster saveEmployee(@RequestBody EmployeeMaster emp) {
			EmployeeMaster empSave = new EmployeeMaster();
			 
			try {
				empSave = empRepo.save(emp);
							}catch (Exception e) {
				System.err.println("Excep in saveBank : "+e.getMessage());
				e.printStackTrace();
			}
			
			return empSave;
			
		}
		
		/***********************************************************************/
		@RequestMapping(value = {"/saveEmployeeIdInfo"}, method = RequestMethod.POST)
		public TblEmpInfo saveEmployee(@RequestBody TblEmpInfo empInfo) {
			 TblEmpInfo empSave = new TblEmpInfo();
			System.out.println("empInfo--------"+empInfo);
			try {
				empSave = empInfoRepo.save(empInfo);
				
			}catch (Exception e) {
				System.err.println("Excep in saveEmployeeInfo : "+e.getMessage());
				e.printStackTrace();
			}
			
			return empSave;
			
		}
		
		@RequestMapping(value = {"/getEmployeePersonalInfo"}, method = RequestMethod.POST)
		public TblEmpInfo getEmployeePersonalInfo(@RequestParam int empId) {
			TblEmpInfo emp = new TblEmpInfo();
			try {
				emp = empInfoRepo.findByEmpIdAndDelStatus(empId, 1);
			}catch (Exception e) {
				System.err.println("Excep in getEmployeePersonalInfo : "+e.getMessage());
				e.printStackTrace();
			}
			
			return emp;
			
		}
		
		
		@RequestMapping(value = {"/saveEmployeeIdBank"}, method = RequestMethod.POST)
		public TblEmpBankInfo saveEmployee(@RequestBody TblEmpBankInfo empBank) {
			TblEmpBankInfo empSave = new TblEmpBankInfo();
			System.out.println("empBank--------"+empBank);
			try {
				empSave = bankInfoRepo.save(empBank);
				
			}catch (Exception e) {
				System.err.println("Excep in saveEmployeeIdBank : "+e.getMessage());
				e.printStackTrace();
			}
			
			return empSave;
			
		}
		
		@RequestMapping(value = {"/getEmployeeBankInfo"}, method = RequestMethod.POST)
		public TblEmpBankInfo getEmployeeBankInfo(@RequestParam int empId) {
			
			TblEmpBankInfo empBank = new TblEmpBankInfo();			
			try {
				empBank = bankInfoRepo.findByEmpId(empId);
				
			}catch (Exception e) {
				System.err.println("Excep in getEmployeeBankInfo : "+e.getMessage());
				e.printStackTrace();
			}
			
			return empBank;
			
		}
		
		@RequestMapping(value = {"/saveEmployeeIdNominee"}, method = RequestMethod.POST)
		public TblEmpNominees saveEmployeeIdNominee(@RequestBody TblEmpNominees empNominee) {
			TblEmpNominees empSave = new TblEmpNominees();
			System.out.println("empNominee--------"+empNominee);
			try {
				empSave = nomineeRepo.save(empNominee);
				
			}catch (Exception e) {
				System.err.println("Excep in saveEmployeeIdNominee : "+e.getMessage());
				e.printStackTrace();
			}
			
			return empSave;
			
		}
		
		@RequestMapping(value = {"/getEmployeeNominee"}, method = RequestMethod.POST)
		public TblEmpNominees getEmployeeNominee(@RequestParam int empId) {
			TblEmpNominees nominee = new TblEmpNominees();
			System.out.println("empNominee--------"+nominee);
			try {
				nominee = nomineeRepo.findByEmpId(empId);
				
			}catch (Exception e) {
				System.err.println("Excep in getEmployeeNominee : "+e.getMessage());
				e.printStackTrace();
			}
			
			return nominee;
			
		}
		
		
		
		@RequestMapping(value = {"/saveEmployeeIdSalary"}, method = RequestMethod.POST)
		public EmpSalaryInfo saveEmployeeIdNominee(@RequestBody EmpSalaryInfo empSal) {
			EmpSalaryInfo empSave = new EmpSalaryInfo();
			System.out.println("empSal--------"+empSal);
			try {
				empSave = empSalRepo.save(empSal);
				
			}catch (Exception e) {
				System.err.println("Excep in saveEmployeeIdSalary : "+e.getMessage());
				e.printStackTrace();
			}
			
			return empSave;
			
		}
		
		@RequestMapping(value = {"/getEmployeeSalInfo"}, method = RequestMethod.POST)
		public EmpSalaryInfo getEmployeeSalInfo(@RequestParam int empId) {
			EmpSalaryInfo empSal = new EmpSalaryInfo();
			
			try {
				empSal = empSalRepo.findByEmpId(empId);
				
			}catch (Exception e) {
				System.err.println("Excep in saveEmployeeIdSalary : "+e.getMessage());
				e.printStackTrace();
			}
			
			return empSal;
			
		}
		
		
		@RequestMapping(value = {"/saveEmployeeIdUser"}, method = RequestMethod.POST)
		public User saveEmployeeIdNominee(@RequestBody User user) {
			User empSave = new User();
			System.out.println("user--------"+user);
			try {
				empSave = userRepo.save(user);
				
			}catch (Exception e) {
				System.err.println("Excep in saveEmployeeIdUser : "+e.getMessage());
				e.printStackTrace();
			}
			
			return empSave;
			
		}
		
		
		@RequestMapping(value = {"/getAllAllowances"}, method = RequestMethod.GET)
		public List<Allowances> getAllAllowances() {
			List<Allowances> list = new ArrayList<Allowances>();
			
			try {
				list = allowanceRepo.findBydelStatusAndIsActive(0, 1);
				
			}catch (Exception e) {
				System.err.println("Excep in getAllAllowances : "+e.getMessage());
				e.printStackTrace();
			}
			
			return list;
			
		}
		
		
		
		@RequestMapping(value = {"/saveEmpSalAllowanceInfo"}, method = RequestMethod.POST)
		public List<EmpSalAllowance> saveEmpSalAllowanceInfo(@RequestBody List<EmpSalAllowance> allowncList) {
			List<EmpSalAllowance> empAllowance = new ArrayList<EmpSalAllowance>();
			System.out.println("allowance--------"+allowncList);
			try {
				empAllowance = empSalAllowanceRepo.saveAll(allowncList);
				
			}catch (Exception e) {
				System.err.println("Excep in saveEmployeeIdUser : "+e.getMessage());
				e.printStackTrace();
			}
			
			return empAllowance;
			
		}
		
		
		@RequestMapping(value = { "/deleteEmpSalAllowanceInfo" }, method = RequestMethod.POST)
		public Info deleteEmpSalAllowanceInfo(@RequestParam int empId) {

			Info info = new Info();
			try {

				int res = empSalAllowanceRepo.deleteEmpAllowances(empId);
				
				if (res > 0) {
					info.setError(false);
					info.setMsg("Sucess");
				}else{
					info.setError(true);
					info.setMsg("Fail");
				}

			} catch (Exception e) {
				System.err.println("Excep in deleteEmpSalAllowanceInfo : " + e.getMessage());
				e.printStackTrace();
			}

			return info;

		}
		
		@RequestMapping(value = {"/saveEmpSalAllowanceIds"}, method = RequestMethod.POST)
		public EmpSalAllowance saveEmpSalAllowanceIds(@RequestBody EmpSalAllowance allowance) {
			EmpSalAllowance empAllowanceIds = new EmpSalAllowance();
			System.out.println("allowance--------"+allowance);
			try {
				empAllowanceIds = empSalAllowanceRepo.save(allowance);
				
			}catch (Exception e) {
				System.err.println("Excep in saveEmployeeIdUser : "+e.getMessage());
				e.printStackTrace();
			}
			
			return empAllowanceIds;
			
		}
		
		@RequestMapping(value = {"/getEmployeeSalAllowances"}, method = RequestMethod.POST)
		public List<EmpSalAllowance> getEmployeeSalAllowances(@RequestParam int empId) {
			List<EmpSalAllowance> empAllowance = new ArrayList<EmpSalAllowance>();
			
			try {
				empAllowance = empSalAllowanceRepo.findByEmpId(empId);
				
			}catch (Exception e) {
				System.err.println("Excep in getEmployeeSalAllowances : "+e.getMessage());
				e.printStackTrace();
			}
			
			return empAllowance;
			
		}
		
		
		@RequestMapping(value = {"/getAllEmpDocTypes"}, method = RequestMethod.POST)
		public List<EmpDoctype> getAllEmpDocTypes(@RequestParam int companyId) {
			List<EmpDoctype> empDocTypes = new ArrayList<EmpDoctype>();
			
			try {
				empDocTypes = empDocRepo.findByDelStatusAndCompanyId(0, companyId);
				
			}catch (Exception e) {
				System.err.println("Excep in getAllEmpDocTypes : "+e.getMessage());
				e.printStackTrace();
			}			
			return empDocTypes;
			
		}
		

		@RequestMapping(value = { "/saveEmpDocList" }, method = RequestMethod.POST)
		public @ResponseBody List<EmployeDoc> saveEmpDocList(@RequestBody List<EmployeDoc> employeeDepartment) {

			List<EmployeDoc> save = new ArrayList<>();
			try {

				save = employeeDocsRepository.saveAll(employeeDepartment);

			} catch (Exception e) {

				e.printStackTrace();
			}

			return save;

		}
}
