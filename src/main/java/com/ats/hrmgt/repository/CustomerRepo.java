package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	List<Customer> findByDelStatusAndCompanyId(int i, int companyId);

	@Transactional
	@Modifying
	@Query("update Customer set del_status=0  WHERE cust_id=:custId")
	int deleteCust(@Param("custId") int custId);

	Customer findByCustIdAndDelStatus(int custId, int i);

}
