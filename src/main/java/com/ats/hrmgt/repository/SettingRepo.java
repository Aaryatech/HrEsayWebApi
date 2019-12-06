package com.ats.hrmgt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.Setting;

public interface SettingRepo extends JpaRepository<Setting, Integer>{

	Setting findByKey(String key);
	
	@Transactional
	@Modifying
	@Query("update Setting set value=:val  WHERE setting_id=:settingId")
	int settingUpdate(@Param("settingId") String settingId,@Param("val") String val);

}
