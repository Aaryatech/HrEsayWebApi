package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.Designation;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.repository.DesignationRepo;

@RestController
public class HrEasyApiController {
	
	@Autowired DesignationRepo desigRepo;
	
	@RequestMapping(value = {"/getAllDesignations"}, method = RequestMethod.POST)
	public List<Designation> getAllDesignations(@RequestParam int companyId){
		List<Designation> list = new ArrayList<Designation>();
		try {
			list = desigRepo.findByCompanyIdAndDelStatus(companyId, 1);
		}catch (Exception e) {
			System.err.println("Excep in getAllDesignations : "+e.getMessage());
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	@RequestMapping(value = {"/getDesignationById"}, method = RequestMethod.POST)
	public Designation getDesignationById(@RequestParam int companyId, @RequestParam int desigId) {
		Designation designation = new Designation();
		try {
			designation = desigRepo.findByDesigIdAndCompanyIdAndDelStatus(desigId, companyId, 1);
		}catch (Exception e) {
			System.err.println("Excep in getDesignationById : "+e.getMessage());
			e.printStackTrace();
		}
		
		return designation;
		
	}
	
	@RequestMapping(value = { "/deleteDesignationById" }, method = RequestMethod.POST)
	public Info deleteDesignationById(@RequestParam int desigId, @RequestParam int companyId) {

		Info info = new Info();
		try {

			int res = desigRepo.deleteDesignation(desigId, companyId);
			
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

}
