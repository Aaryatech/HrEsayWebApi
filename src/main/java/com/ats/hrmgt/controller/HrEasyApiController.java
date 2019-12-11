package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.Contractor;
import com.ats.hrmgt.model.Department;
import com.ats.hrmgt.model.Designation;
import com.ats.hrmgt.model.EmployeeMaster;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.repository.ContractorRepo;
import com.ats.hrmgt.repository.DepartmentRepo;
import com.ats.hrmgt.repository.DesignationRepo;
import com.ats.hrmgt.repository.EmployeeMasterRepository;

@RestController
public class HrEasyApiController {
	
	@Autowired DesignationRepo desigRepo;
	
	@Autowired ContractorRepo contractRepo;
	
	@Autowired EmployeeMasterRepository empRepo;
	
	@Autowired DepartmentRepo deptRepo;
	
	@RequestMapping(value = {"/getAllDesignations"}, method = RequestMethod.GET)
	public List<Designation> getAllDesignations(){
		List<Designation> list = new ArrayList<Designation>();
		try {
			list = desigRepo.findByDelStatusOrderByDesigIdDesc(1);
		}catch (Exception e) {
			System.err.println("Excep in getAllDesignations : "+e.getMessage());
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	@RequestMapping(value = {"/getDesignationById"}, method = RequestMethod.POST)
	public Designation getDesignationById(@RequestParam int desigId) {
		Designation designation = new Designation();
		try {
			designation = desigRepo.findByDesigIdAndDelStatus(desigId, 1);
		}catch (Exception e) {
			System.err.println("Excep in getDesignationById : "+e.getMessage());
			e.printStackTrace();
		}
		
		return designation;
		
	}
	
	@RequestMapping(value = { "/deleteDesignationById" }, method = RequestMethod.POST)
	public Info deleteDesignationById(@RequestParam int desigId) {

		Info info = new Info();
		try {

			int res = desigRepo.deleteDesignation(desigId);
			
			if (res > 0) {
				info.setError(false);
				info.setMsg("Sucess");
			}else{
				info.setError(true);
				info.setMsg("Fail");
			}

		} catch (Exception e) {
			System.err.println("Excep in deleteDesignationById : " + e.getMessage());
			e.printStackTrace();
		}

		return info;

	}
	

	@RequestMapping(value = {"/saveDesignation"}, method = RequestMethod.POST)
	public Designation saveDesignation(@RequestBody Designation desig) {
		Designation designation = new Designation();
		try {
			designation = desigRepo.save(desig);
		}catch (Exception e) {
			System.err.println("Excep in saveDesignation : "+e.getMessage());
			e.printStackTrace();
		}
		
		return designation;
		
	}
	
	@RequestMapping(value = {"/getEmpByDesignationId"}, method = RequestMethod.POST)
	public int getEmpByDesignationId(@RequestParam int desigId) {
		
		int resp = 0;
		try {
			resp = empRepo.getEmpInfoByDesigId(desigId);
		
		}catch (Exception e) {
			System.err.println("Excep in getEmpByDesignationId : "+e.getMessage());
			e.printStackTrace();
		}
		
		return resp;
		
	}
	
	/******************************Contractor********************************/
	@RequestMapping(value = {"/getAllContractors"}, method = RequestMethod.GET)
	public List<Contractor> getAllContractors(){
		List<Contractor> list = new ArrayList<Contractor>();
		try {
			list = contractRepo.findByDelStatusOrderByContractorIdDesc(1);
		}catch (Exception e) {
			System.err.println("Excep in getAllContractors : "+e.getMessage());
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	@RequestMapping(value = {"/getContractorById"}, method = RequestMethod.POST)
	public Contractor getContractorById(@RequestParam int contractorId) {
		Contractor contract = new Contractor();
		try {
			contract = contractRepo.findByContractorIdAndDelStatus(contractorId, 1);
		}catch (Exception e) {
			System.err.println("Excep in getContractorById : "+e.getMessage());
			e.printStackTrace();
		}
		
		return contract;
		
	}
	
	@RequestMapping(value = { "/deleteContractor" }, method = RequestMethod.POST)
	public Info deleteContractor(@RequestParam int contractorId) {

		Info info = new Info();
		try {

			int res = contractRepo.deleteContractorById(contractorId);
			
			if (res > 0) {
				info.setError(false);
				info.setMsg("Sucess");
			}else{
				info.setError(true);
				info.setMsg("Fail");
			}

		} catch (Exception e) {
			System.err.println("Excep in deleteContractor : " + e.getMessage());
			e.printStackTrace();
		}

		return info;

	}
	
	@RequestMapping(value = {"/saveContractor"}, method = RequestMethod.POST)
	public Contractor saveContractor(@RequestBody Contractor contract) {
		Contractor contrctr = new Contractor();
		try {
			contrctr = contractRepo.save(contract);
		}catch (Exception e) {
			System.err.println("Excep in saveDesignation : "+e.getMessage());
			e.printStackTrace();
		}
		
		return contrctr;
		
	}
	
	
	@RequestMapping(value = {"/getEmpByContractorId"}, method = RequestMethod.POST)
	public int getEmpByContractorId(@RequestParam int contractorId) {
		
		int resp = 0;
		try {
			resp = empRepo.getEmpInfoByContractId(contractorId);
		
		}catch (Exception e) {
			System.err.println("Excep in getEmpByContractorId : "+e.getMessage());
			e.printStackTrace();
		}
		
		return resp;
		
	}
	
	/******************************Department********************************/
	@RequestMapping(value = {"/getAllDepartments"}, method = RequestMethod.GET)
	public List<Department> getAllDepartments(){
		List<Department> list = new ArrayList<Department>();
		try {
			list = deptRepo.findBydelStatusOrderByDepartIdDesc(1);
		}catch (Exception e) {
			System.err.println("Excep in getAllDepartments : "+e.getMessage());
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	@RequestMapping(value = {"/getDepartmentById"}, method = RequestMethod.POST)
	public Department getDepartmentById(@RequestParam int deptId) {
		Department depart = new Department();
		try {
			depart = deptRepo.findByDepartIdAndDelStatus(deptId, 1);
		}catch (Exception e) {
			System.err.println("Excep in getDepartmentById : "+e.getMessage());
			e.printStackTrace();
		}
		
		return depart;
		
	}
	
	@RequestMapping(value = { "/deleteDepartment" }, method = RequestMethod.POST)
	public Info deleteDepartment(@RequestParam int deptId) {

		Info info = new Info();
		try {

			int res = deptRepo.deleteDepartment(deptId);
			
			if (res > 0) {
				info.setError(false);
				info.setMsg("Sucess");
			}else{
				info.setError(true);
				info.setMsg("Fail");
			}

		} catch (Exception e) {
			System.err.println("Excep in deleteDepartment : " + e.getMessage());
			e.printStackTrace();
		}

		return info;

	}
	
	@RequestMapping(value = {"/saveDepartment"}, method = RequestMethod.POST)
	public Department saveDepartment(@RequestBody Department dept) {
		Department depart = new Department();
		try {
			depart = deptRepo.save(dept);
		}catch (Exception e) {
			System.err.println("Excep in saveDepartment : "+e.getMessage());
			e.printStackTrace();
		}
		
		return depart;
		
	}
	
	@RequestMapping(value = {"/getEmpByDeptId"}, method = RequestMethod.POST)
	public int getEmpByDeptId(@RequestParam int deptId) {
		
		int resp = 0;
		try {
			resp = empRepo.getEmpInfoByDepartment(deptId);
		
		}catch (Exception e) {
			System.err.println("Excep in /getEmpByDeptId : "+e.getMessage());
			e.printStackTrace();
		}
		
		return resp;
		
	}
}
