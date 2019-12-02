package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
 

@RestController
public class AccessRoleRestController {
 
	 
	
	@RequestMapping(value = { "/initializeWebService" }, method = RequestMethod.GET)
	public @ResponseBody String initializeWebService() {

		 
		 

		return "Success";

	}
	
}
