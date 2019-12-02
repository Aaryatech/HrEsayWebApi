package com.ats.hrmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.model.CalenderYear;

public interface CalculateYearRepository extends JpaRepository<CalenderYear, Integer> {

	CalenderYear findByCalYrId(int calYrId);

	CalenderYear findByIsCurrent(int i);

}
