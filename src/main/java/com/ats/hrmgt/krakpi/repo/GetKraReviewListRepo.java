package com.ats.hrmgt.krakpi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

 import com.ats.hrmgt.krakpi.model.GetKraReviewList;
 
public interface GetKraReviewListRepo  extends JpaRepository<GetKraReviewList, Integer> {
	
	
	@Query(value = " SELECT\n" + 
			"    t_kra_review.*,\n" + 
			"    emp_info.emp_id,\n" + 
			"    emp_info.emp_fname,\n" + 
			"    emp_info.emp_sname\n" + 
			"FROM\n" + 
			"    t_kra_review,\n" + 
			"    emp_info\n" + 
			"WHERE\n" + 
			"    t_kra_review.del_status = 1 AND t_kra_review.maker_user_id = emp_info.emp_id AND t_kra_review.kra_id =:kraId \n" + 
			"\n" + 
			""
			+ " ", nativeQuery = true)

	List<GetKraReviewList> getEmpKraReviewList(@Param("kraId") int kraId
		);
	
}
