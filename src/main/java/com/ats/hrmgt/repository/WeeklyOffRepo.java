package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 
import com.ats.hrmgt.model.WeeklyOff;

public interface WeeklyOffRepo extends JpaRepository<WeeklyOff, Integer> {

	List<WeeklyOff> findByDelStatusAndIsActive(int i, int j);

	@Transactional
	@Modifying
	@Query("update WeeklyOff set del_status=0  WHERE wo_id=:woId")
	int deleteWeeklyOff(@Param("woId") int woId);

	WeeklyOff findBywoIdAndDelStatus(int woId, int i);

	@Query(value = " select w.* from weekly_off w, m_employees e where e.location_id=w.loc_id and e.emp_id=:empId and w.del_status=1", nativeQuery = true)
	List<WeeklyOff> getWeeklyOffListByEmpId(@Param("empId") int empId);

	@Query(value = " select w.* from weekly_off w  where  w.del_status=1", nativeQuery = true)
	List<WeeklyOff> getWeeklyOffList();
	
	@Query(value = " select w.* from weekly_off w  where  w.del_status=1 AND w.company_id=:companyId AND w.loc_id=:locId", nativeQuery = true)
 	WeeklyOff getRecord(@Param("companyId")  int companyId,@Param("locId")  int locId);

}
