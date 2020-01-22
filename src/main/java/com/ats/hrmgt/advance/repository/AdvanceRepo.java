package com.ats.hrmgt.advance.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.advance.Advance;

public interface AdvanceRepo extends JpaRepository<Advance, Integer> {

	List<Advance> findByDelStatusAndIsDedAndCmpId(int i, int j, int companyId);
	
	@Transactional
	@Modifying
	@Query("update Advance set del_status=0  WHERE id=:advId")
	int deleteAdvance(int advId);
	
	 Advance findById(int i);

	List<Advance> findByVoucherNoAndDelStatus(String voucherNo, int i);
	
	
	List<Advance> findByEmpIdAndDelStatus(int empId,int i);
	
	@Transactional
	@Modifying
	@Query("update Advance set  skip_id =:count,skip_login_name =:userId, skip_login_time=:dateTimeUpdate,skip_remarks =:SkipStr ,ded_month=:dedMonth,ded_year=:dedYear WHERE id=:advId")
	int skipAdvance(@Param("advId") int advId,@Param("userId") int userId,@Param("count") int count,@Param("SkipStr") String SkipStr,@Param("dateTimeUpdate") String dateTimeUpdate,@Param("dedYear") int dedYear,@Param("dedMonth") int dedMonth);

	@Transactional
	@Modifying
	@Query("update Advance set is_ded=1 where ded_month=:month and ded_year=:year and del_status=1 and is_ded=0 and emp_id in (:empIds)")
	int updateAdv(@Param("month")int month, @Param("year") int year, @Param("empIds") List<Integer> empIds);
	
	

}
