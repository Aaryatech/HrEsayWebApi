package com.ats.hrmgt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.repository.SettingRepo; 

@RestController
public class SettingApiController {
	
	@Autowired
	SettingRepo settingRepo;
	
	@RequestMapping(value = { "/getSettingByKey" }, method = RequestMethod.POST)
	public @ResponseBody Setting getSettingByKey(@RequestParam("limitKey") String key) {

		Setting setting = new Setting();
		 
		try {  
					
			setting = settingRepo.findByKey(key);
		
			
		} catch (Exception e) {

			e.printStackTrace();
			 

		}
		return setting;

	}
	
	@RequestMapping(value = { "/saveSetting" }, method = RequestMethod.POST)
	public @ResponseBody Setting saveSetting(@RequestBody Setting setting) {

		Setting save = new Setting();
		try {

			save = settingRepo.saveAndFlush(setting);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}
	
	

	@RequestMapping(value = { "/updateSetting" }, method = RequestMethod.POST)
	public @ResponseBody Info updateSetting(@RequestParam("settingId") String settingId,@RequestParam("val") String val) {

		Info info = new Info();

		try {

			int delete = settingRepo.settingUpdate(settingId,val);

			if (delete > 0) {
				info.setError(false);
				info.setMsg("setting updated");
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
	

}
