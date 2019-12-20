package com.ats.hrmgt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.advance.repository.AdvanceRepo;
 import com.ats.hrmgt.model.advance.Advance;

@RestController
public class AdvanceApiController {
	
	
	
	@Autowired
	AdvanceRepo advanceRepo;
	
	@RequestMapping(value = { "/saveMstEmpAdvance" }, method = RequestMethod.POST)
	public @ResponseBody Advance saveMstEmpAdvance(@RequestBody Advance leaveType) {

		Advance save = new Advance();
		try {

			save = advanceRepo.saveAndFlush(leaveType);
			if (save == null) {

				save = new Advance();
 
			}  
		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}
	
	
	

}
