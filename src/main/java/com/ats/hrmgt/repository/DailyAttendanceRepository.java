package com.ats.hrmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.model.DailyAttendance;

public interface DailyAttendanceRepository extends JpaRepository<DailyAttendance, Integer>{

}
