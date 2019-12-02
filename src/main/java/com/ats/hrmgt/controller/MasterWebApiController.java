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

import com.ats.hrmgt.common.EmailUtility;
import com.ats.hrmgt.model.EmpType;
import com.ats.hrmgt.model.EmployeeCategory;
import com.ats.hrmgt.model.GetEmployeeInfo;
import com.ats.hrmgt.model.GetUserData;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.LeaveSummary;
import com.ats.hrmgt.model.Location;
import com.ats.hrmgt.model.LoginResponse;
import com.ats.hrmgt.model.User;
import com.ats.hrmgt.repository.EmpTypeRepository;
import com.ats.hrmgt.repository.GetEmpInfoRepo;
import com.ats.hrmgt.repository.GetUserDataRepo;
import com.ats.hrmgt.repository.LeaveSummaryRepository;
import com.ats.hrmgt.repository.LocationRepository;
import com.ats.hrmgt.repository.LoginResponseRepo;
import com.ats.hrmgt.repository.SettingRepo;
import com.ats.hrmgt.repository.UserRepo;



@RestController
public class MasterWebApiController {
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	SettingRepo settingRepo;


	@Autowired
	GetEmpInfoRepo getEmpInfo;
	
	@Autowired
	GetUserDataRepo getUserDataRepo;
	
	@Autowired
	LocationRepository locationRepository;
	
	
	@Autowired
	EmpTypeRepository empTypeRepository;

	
	@Autowired
	LeaveSummaryRepository leaveSummaryRepository;
	
	@Autowired
	LoginResponseRepo loginResponseRepo;
	

	/*
	 * <dependency> <groupId>javax.mail</groupId> <artifactId>mail</artifactId>
	 * <version>1.4</version> </dependency>
	 */

	static String senderEmail = "atsinfosoft@gmail.com";
	static String senderPassword = "atsinfosoft@123";
	static String mailsubject = " HRMS Password Recovery";

	
	@RequestMapping(value = { "/GetEmployeeInfo" }, method = RequestMethod.POST)
	public @ResponseBody GetEmployeeInfo getEmployeeInfo(@RequestParam("empId") int empId) {

		GetEmployeeInfo company = new GetEmployeeInfo();
		try {

			company = getEmpInfo.getEmpByEmpId(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return company;

	}
	
	@RequestMapping(value = { "/checkUserName" }, method = RequestMethod.POST)
	public @ResponseBody GetUserData checkUserName( @RequestParam("inputValue") String inputValue) {

		GetUserData user = new GetUserData();
		try {
 
				user = getUserDataRepo.getUserByEmailId(inputValue);
 
			if(user==null) {
				user = new GetUserData();
				user.setError(true);
			}else {
				user.setError(false);
				String finalmsg="Your Password is:"+user.getUserPwd()+ 
						"\n DO NOT REPLY to this EMAIL-ID - contact HR.PUNE@infrabeat.com.";
				
				Info emailRes = EmailUtility.sendEmail("atsinfosoft@gmail.com", "atsinfosoft@123", user.getEmpEmail(), " HRMS Password Recovery",
						user.getEmpEmail(),finalmsg );
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return user;

	}
	
	
	
	@RequestMapping(value = { "/getUserInfoByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody User getUserInfoByEmpId(@RequestParam("empId") int empId) {

		User user = new User();
		try {

			user = userRepo.findByEmpIdAndDelStatus(empId,1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return user;

	}
	
	@RequestMapping(value = { "/getUserInfoByEmpIdPass" }, method = RequestMethod.POST)
	public @ResponseBody User getUserInfoByEmpIdPass(@RequestParam("empId") int empId,@RequestParam("password") String password) {

		User user = new User();
		try {

			user = userRepo.getSpecificUserRecord(empId,password);
			
			if(user==null) {
				user = new User();
				user.setError(true);
			}else {
				user.setError(false);
				

			}
			
		  
			
			System.out.println(user);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return user;

	}
	

	@RequestMapping(value = { "/updateUserPass" }, method = RequestMethod.POST)
	public @ResponseBody Info updateUserPass(@RequestParam("empId") int empId,@RequestParam("password") String password) {

		Info info = new Info();

		try {

			int delete = userRepo.updateUserPassword(empId,password);

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

	
	@RequestMapping(value = { "/GetCurrCalYear" }, method = RequestMethod.POST)
	public @ResponseBody GetEmployeeInfo GetCurrCalYear() {

		GetEmployeeInfo company = new GetEmployeeInfo();
		try {

			company = getEmpInfo.getEmpByEmpId(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return company;

	}
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public @ResponseBody LoginResponse loginUser(@RequestParam("username") String userName,
			@RequestParam("userPass") String pass) {

		 
		LoginResponse loginResponse = new LoginResponse();
		try {
			
			 loginResponse = loginResponseRepo.loginUser(userName, pass);
			 
			 if(loginResponse==null) {
				 loginResponse = new LoginResponse();
				 loginResponse.setError(true);
				 loginResponse.setMsg("record Not found");
			 }else {
				 loginResponse.setError(false);
				 loginResponse.setMsg("Record Found");
			 }
			
		}catch(Exception e) {
			e.printStackTrace();
			
			loginResponse = new LoginResponse();
			 loginResponse.setError(true);
			 loginResponse.setMsg("record Not found");
		}
		 
		return loginResponse;

	}
	
	
	
	@RequestMapping(value = { "/checkUniqueEmail" }, method = RequestMethod.POST)
	public @ResponseBody Info checkUniqueEmail(@RequestParam("email") String email) {
  
		Info info=new Info();
	   Location location = new Location();
		try {
			 
			location = locationRepository.findByLocHrContactEmailAndDelStatus(email,1);
			System.out.print("Loction :"+location);
			if(location!=null)
			{
				info.setError(false);
				info.setMsg("Record Found");
			}
			else
			{
				info.setError(true);
				info.setMsg("Record Not Found");
			}
		

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("Record Not Found");
			e.printStackTrace();
		}

		return info;

	}
	@RequestMapping(value = { "/checkUniqueMobileNumber" }, method = RequestMethod.POST)
	public @ResponseBody Info checkUniqueMobileNumber(@RequestParam("mobileNo") String mobileNo) {
  
		Info info=new Info();
	   Location location = new Location();
		try {
			 
			location = locationRepository.findByLocHrContactNumberAndDelStatus(mobileNo,1);
			System.out.print("Loction :"+location);
			if(location!=null)
			{
				info.setError(false);
				info.setMsg("Record Found");
			}
			else
			{
				info.setError(true);
				info.setMsg("Record Not Found");
			}
		

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("Record Not Found");
			e.printStackTrace();
		}

		return info;

	}
	
	@RequestMapping(value = { "/saveLeaveSummary" }, method = RequestMethod.POST)
	public @ResponseBody LeaveSummary saveLeaveSummary(@RequestBody LeaveSummary employeeCategory) {

		 
		LeaveSummary save = new LeaveSummary();
		try {
			 
			save = leaveSummaryRepository.saveAndFlush(employeeCategory);
			
			if(save!=null) {
				save.setError(false);
			}else {
				
				save = new LeaveSummary();
				save.setError(true);
			}
  

		} catch (Exception e) {
			save = new LeaveSummary();
			save.setError(true);
			e.printStackTrace();
		}

		return save;

	}
	
	@RequestMapping(value = { "/getLeaveSummaryList" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveSummary> getLeaveSummaryList(@RequestParam("compId") int compId) {

		 
		List<LeaveSummary> list = new ArrayList<LeaveSummary>();
		try {
			 
			if(compId!=0) {
				
				list = leaveSummaryRepository.findByDelStatusAndCompanyId(1,compId);
				
			}else {
				
				list = leaveSummaryRepository.findByDelStatus(1);
				
			}
			
  

		} catch (Exception e) {
 
			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/deleteLeaveSummary" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteLeaveSummary(@RequestParam("lvSumupId") int lvSumupId) {

		 
		Info info = new Info();
		
		try {
			 
			int delete = leaveSummaryRepository.deleteLeaveSummary(lvSumupId);
  
			if(delete>0) {
				info.setError(false); 
				info.setMsg("deleted");
			}else {
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
	
	@RequestMapping(value = { "/getLeaveSummaryById" }, method = RequestMethod.POST)
	public @ResponseBody LeaveSummary getLeaveSummaryById(@RequestParam("lvSumupId") int lvSumupId) {

		 
		LeaveSummary employeeCategory = new LeaveSummary();
		try {
			 
			employeeCategory = leaveSummaryRepository.findByLvSumupIdAndDelStatus(lvSumupId,1);
  

		} catch (Exception e) {
 
			e.printStackTrace();
		}

		return employeeCategory;

	}
}
