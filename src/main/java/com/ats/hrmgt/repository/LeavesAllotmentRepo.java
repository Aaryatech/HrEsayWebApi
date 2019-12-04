package com.ats.hrmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.model.LeavesAllotment;

public interface LeavesAllotmentRepo extends JpaRepository<LeavesAllotment, Integer>  {

	LeavesAllotment findByDelStatusAndEmpIdAndCalYrId(int i, int empId,int calYrId);

}
