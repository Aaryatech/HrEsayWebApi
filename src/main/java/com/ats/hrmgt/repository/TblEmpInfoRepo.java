package com.ats.hrmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.hrmgt.model.TblEmpInfo;

@Repository
public interface TblEmpInfoRepo extends JpaRepository<TblEmpInfo, Integer> {

	public TblEmpInfo findByEmpIdAndDelStatus(int empId, int del);
}
