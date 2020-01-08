package com.ats.hrmgt.repo.bonus;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.bonus.BonusCalc;

public interface BonusCalcRepo extends JpaRepository<BonusCalc, Integer> {

	List<BonusCalc> findByDelStatus(int i);

	BonusCalc findByEmpIdAndBonusIdAndDelStatus(int empId, int bonusId, int i);

	

	@Query(value = "SELECT * from t_bonus_calc WHERE t_bonus_calc.del_status=1 AND t_bonus_calc.bonus_id=:bonusId", nativeQuery = true)
	List<BonusCalc> getEmpDetailListForBonus(@Param("bonusId")  int bonusId);

}
