package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.EmpSalaryInfo;
import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.TblEmpBankInfo;
import com.ats.hrmgt.model.TblEmpInfo;
import com.ats.hrmgt.model.TblEmpNominees;
import com.ats.hrmgt.repository.EmpSalaryInfoRepo;
import com.ats.hrmgt.repository.EmployeeMasterRepository;
import com.ats.hrmgt.repository.TblEmpBankInfoRepo;
import com.ats.hrmgt.repository.TblEmpInfoRepo;
import com.ats.hrmgt.repository.TblEmpNomineesRepo;
@RestController
public class EmployeeApiController {
	@Autowired EmployeeMasterRepository empRepo;
	
	//EmpSupportRepository
	@Autowired TblEmpInfoRepo empInfoRepo;
	
	@Autowired TblEmpBankInfoRepo bankInfoRepo;
	
	@Autowired TblEmpNomineesRepo nomineeRepo;
	
	@Autowired EmpSalaryInfoRepo empSalRepo;
		/**********************************Employee*********************************/
		
		@RequestMapping(value = {"/getAllEmployee"}, method = RequestMethod.GET)
		public List<EmployeeMaster> getAllEmployee(@RequestParam int companyId){
			List<EmployeeMaster> list = new ArrayList<EmployeeMaster>();
			try {
				list = empRepo.findByCmpCodeAndDelStatusOrderByEmpIdDesc(companyId, 1);
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
			System.out.println("Obj--------"+empInfo);
			try {
				empSave = empInfoRepo.save(empInfo);
				
			}catch (Exception e) {
				System.err.println("Excep in saveEmployeeInfo : "+e.getMessage());
				e.printStackTrace();
			}
			
			return empSave;
			
		}
		
		@RequestMapping(value = {"/saveEmployeeIdBank"}, method = RequestMethod.POST)
		public TblEmpBankInfo saveEmployee(@RequestBody TblEmpBankInfo empBank) {
			TblEmpBankInfo empSave = new TblEmpBankInfo();
			System.out.println("Obj--------"+empBank);
			try {
				empSave = bankInfoRepo.save(empBank);
				
			}catch (Exception e) {
				System.err.println("Excep in saveEmployeeIdBank : "+e.getMessage());
				e.printStackTrace();
			}
			
			return empSave;
			
		}
		
		@RequestMapping(value = {"/saveEmployeeIdNominee"}, method = RequestMethod.POST)
		public TblEmpNominees saveEmployeeIdNominee(@RequestBody TblEmpNominees empNominee) {
			TblEmpNominees empSave = new TblEmpNominees();
			System.out.println("Obj--------"+empNominee);
			try {
				empSave = nomineeRepo.save(empNominee);
				
			}catch (Exception e) {
				System.err.println("Excep in saveEmployeeIdNominee : "+e.getMessage());
				e.printStackTrace();
			}
			
			return empSave;
			
		}
		
		
		
		@RequestMapping(value = {"/saveEmployeeIdSalary"}, method = RequestMethod.POST)
		public EmpSalaryInfo saveEmployeeIdNominee(@RequestBody EmpSalaryInfo empSal) {
			EmpSalaryInfo empSave = new EmpSalaryInfo();
			System.out.println("Obj--------"+empSal);
			try {
				empSave = empSalRepo.save(empSal);
				
			}catch (Exception e) {
				System.err.println("Excep in saveEmployeeIdSalary : "+e.getMessage());
				e.printStackTrace();
			}
			
			return empSave;
			
		}
}
