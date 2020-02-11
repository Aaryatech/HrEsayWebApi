package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.HolidayCategory;
import com.ats.hrmgt.model.WeekoffCategory;

public interface WeekoffCategoryRepo extends JpaRepository<WeekoffCategory, Integer>{
	
	
List<WeekoffCategory> findByDelStatus(int i);

	
	
	@Transactional
	@Modifying
	@Query("update WeekoffCategory set del_status=0  WHERE ho_cat_id=:hoCatId")
	int deleteWoCat(@Param("hoCatId") int hoCatId);



	WeekoffCategory findByWoCatIdAndDelStatus(int hoCatId, int i);



	List<WeekoffCategory> findByWoCatNameAndCompanyId(String inputValue, int compId);



 


	List<WeekoffCategory> findByWoCatNameAndCompanyIdAndWoCatIdNot(String trim, int compId, int primaryKey);
 

 	

}
