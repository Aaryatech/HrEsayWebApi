package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.ClientWiseClaimReport;

public interface ClientWiseClaimReportRepository extends JpaRepository<ClientWiseClaimReport, Integer>{

	@Query(value = "select \n" + 
			"        p.cust_id,\n" + 
			"        ct.cust_name,\n" + 
			"        sum(c.claim_amount) as claim_amount \n" + 
			"    from\n" + 
			"        claim_apply c,\n" + 
			"        claim_apply_header ch,\n" + 
			"        project_header p,\n" + 
			"        m_customer ct\n" + 
			"    where\n" + 
			"        ca_from_dt>=:fromDate \n" + 
			"        and ca_to_dt<=:toDate \n" + 
			"        and c.del_status=1 \n" + 
			"        and c.is_active=1 \n" + 
			"        and c.ex_int2=ch.ca_head_id \n" + 
			"        and claim_status=3\n" + 
			"        and ch.proj_id=p.project_id\n" + 
			"        and ct.cust_id=p.cust_id\n" + 
			"    group by\n" + 
			"        p.cust_id ", nativeQuery = true)
	List<ClientWiseClaimReport> clientWiseClaimReport(@Param("fromDate") String fromDate,@Param("toDate") String toDate);

}
