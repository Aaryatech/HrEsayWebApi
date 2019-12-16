package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.model.Allowances;

public interface AllowancesRepo extends JpaRepository<Allowances, Integer> {

	List<Allowances> findBydelStatus(int del);
	
	List<Allowances> findBydelStatusAndIsActive(int del, int active);
}
