package com.ats.hrmgt.krakpi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.krakpi.model.GetEmpKpiReview;
  
public interface GetEmpKpiReviewRepo extends JpaRepository<GetEmpKpiReview,Integer> {
	

	@Query(value = " SELECT\n" + 
			"    t_kpi_review.*,\n" + 
			"    emp_info.emp_id,\n" + 
			"    emp_info.emp_fname,\n" + 
			"    emp_info.emp_sname\n" + 
			"FROM\n" + 
			"    t_kpi_review,\n" + 
			"    emp_info\n" + 
			"WHERE\n" + 
			"    t_kpi_review.del_status = 1 AND t_kpi_review.maker_user_id = emp_info.emp_id AND t_kpi_review.kpi_id =:kpiId" + 
			"	"
			+ " ", nativeQuery = true)

	List<GetEmpKpiReview> getEmpKpiReviewList(@Param("kpiId") int kpiId 
		);
	
}
