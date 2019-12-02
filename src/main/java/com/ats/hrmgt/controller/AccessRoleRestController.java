package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.AccessRightModule;
import com.ats.hrmgt.model.AccessRightSubModule;
import com.ats.hrmgt.model.Company;
import com.ats.hrmgt.repository.AccessRightModuleRepository;
import com.ats.hrmgt.repository.AccessRightSubModuleRepository;
import com.ats.hrmgt.repository.CompanyRepository; 

@RestController
public class AccessRoleRestController {

	@Autowired
	AccessRightModuleRepository accessRightModuleRepository;
	
	@Autowired
	AccessRightSubModuleRepository accessRightSubModuleRepository;
	
	@RequestMapping(value = { "/getModuleAndSubModuleList" }, method = RequestMethod.GET)
	public @ResponseBody List<AccessRightModule> getModuleAndSubModuleList() {

		 
		List<AccessRightModule> accessRightModuleList = new ArrayList<>();
		try {
			 
			 accessRightModuleList = accessRightModuleRepository.getModule();

			for (int i = 0; i < accessRightModuleList.size(); i++) {

				
				List<AccessRightSubModule> accessRightSubModuleList = accessRightSubModuleRepository
						.getSubModule(accessRightModuleList.get(i).getModuleId());
				accessRightModuleList.get(i).setAccessRightSubModuleList(accessRightSubModuleList);

			}
			System.out.println(accessRightModuleList);
		} catch (Exception e) {
 
			e.printStackTrace();
		}

		return accessRightModuleList;

	}
	
}
