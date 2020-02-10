package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.Designation;
import com.ats.hrmgt.model.HolidayCategory;

public interface HolidayCategoryRepo  extends JpaRepository<HolidayCategory, Integer>{

	List<HolidayCategory> findByDelStatus(int i);

	
	
	@Transactional
	@Modifying
	@Query("update HolidayCategory set del_status=0  WHERE ho_cat_id=:hoCatId")
	int deleteHoliCat(@Param("hoCatId") int hoCatId);



	HolidayCategory findByHoCatIdAndDelStatus(int hoCatId, int i);



	List<HolidayCategory> findByHoCatNameAndCompanyId(String inputValue, int compId);



 


	List<HolidayCategory> findByHoCatNameAndCompanyIdAndHoCatIdNot(String trim, int compId, int primaryKey);
 

 	
	
	

}
