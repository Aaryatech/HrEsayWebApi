package com.ats.hrmgt.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.AccessRightModule;
import com.ats.hrmgt.model.AccessRightSubModule;
import com.ats.hrmgt.model.EmpType;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.LoginResponse;
import com.ats.hrmgt.repository.AccessRightModuleRepository;
import com.ats.hrmgt.repository.AccessRightSubModuleRepository;
import com.ats.hrmgt.repository.EmpTypeRepository;
import com.ats.hrmgt.repository.LoginResponseRepository;

@RestController
public class AccessRoleRestController {

	@Autowired
	AccessRightModuleRepository accessRightModuleRepository;

	@Autowired
	AccessRightSubModuleRepository accessRightSubModuleRepository;

	@Autowired
	EmpTypeRepository empTypeRepository;

	@Autowired
	LoginResponseRepository loginResponseRepository;

	@RequestMapping(value = { "/loginProcess" }, method = RequestMethod.POST)
	public @ResponseBody LoginResponse loginProcess(@RequestParam("username") String username,
			@RequestParam("password") String password) {

		LoginResponse loginResponse = new LoginResponse();
		try {

			 
	        /*MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] messageDigest = md.digest(password.getBytes());
	        BigInteger number = new BigInteger(1, messageDigest);
	        String hashtext = number.toString(16);*/
	        
			loginResponse = loginResponseRepository.loginProcess(username, password);

			//System.out.println(loginResponse);
			if (loginResponse == null) {
				loginResponse = new LoginResponse();
				loginResponse.setIsError(true);
			} else {
				loginResponse.setIsError(false);
			}

		} catch (Exception e) {
			loginResponse = new LoginResponse();
			loginResponse.setIsError(true);
			e.printStackTrace();
		}

		return loginResponse;

	}

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

	@RequestMapping(value = { "/saveEmpType" }, method = RequestMethod.POST)
	public @ResponseBody EmpType saveEmpType(@RequestBody EmpType empType) {

		EmpType save = new EmpType();
		try {

			save = empTypeRepository.saveAndFlush(empType);

			if (save == null) {

				save = new EmpType();
				save.setError(true);

			} else {
				save.setError(false);
			}

		} catch (Exception e) {

			e.printStackTrace();
			save.setError(true);
		}

		return save;

	}

	@RequestMapping(value = { "/getEmpTypeList" }, method = RequestMethod.POST)
	public @ResponseBody List<EmpType> getEmpTypeList(@RequestParam("compId") int compId) {

		List<EmpType> list = new ArrayList<EmpType>();
		try {

			if (compId != 0) {
				list = empTypeRepository.findByDelStatusAndCompanyId(1, compId);
			} else {
				list = empTypeRepository.findByDelStatus(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/deleteEmpType" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteEmpType(@RequestParam("empTypeId") int empTypeId) {

		Info info = new Info();

		try {

			int delete = empTypeRepository.deleteEmpType(empTypeId);

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

	@RequestMapping(value = { "/getEmpTypeById" }, method = RequestMethod.POST)
	public @ResponseBody EmpType getEmpTypeById(@RequestParam("empTypeId") int empTypeId) {

		EmpType location = new EmpType();
		try {

			location = empTypeRepository.findByEmpTypeIdAndDelStatus(empTypeId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return location;

	}

}
