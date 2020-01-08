package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.SalaryTerm;

public interface SalaryTermRepository extends JpaRepository<SalaryTerm, Integer> {

	@Query(value = "select * from tbl_sal_terms  order by sort_order asc", nativeQuery = true)
	List<SalaryTerm> getSalaryTermList();

}
