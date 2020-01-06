package com.ats.hrmgt.repo.bonus;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.model.bonus.BonusCalc;

public interface BonusCalcRepo extends JpaRepository<BonusCalc, Integer> {

	List<BonusCalc> findByDelStatus(int i);

}
