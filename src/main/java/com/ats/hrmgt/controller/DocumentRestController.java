package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.DocList; 
import com.ats.hrmgt.repository.DocListRepo; 

@RestController
public class DocumentRestController {
	
	@Autowired
	DocListRepo docListRepo;
	
	@RequestMapping(value = { "/getdocListByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody List<DocList> getdocListByEmpId(@RequestParam("empId") int empId) {

		List<DocList> list = new ArrayList<DocList>();
		try {

			list = docListRepo.getdocListByEmpId(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

}
