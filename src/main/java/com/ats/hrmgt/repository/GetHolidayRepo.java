package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.GetHoliday;
 

public interface GetHolidayRepo extends JpaRepository<GetHoliday, Integer> {

	@Query(value = "  SELECT h.holiday_id,h.holiday_fromdt,h.holiday_todt,h.holiday_remark,h.ex_var1,h.ex_var2,"
			+ "h.cal_yr_id,h.company_id,h.loc_id,c.company_name,l.loc_name,cy.cal_yr_from_date,"
			+ "cy.cal_yr_to_date FROM m_holiday h ,m_company c,m_location l,dm_cal_year cy WHERE"
			+ " h.company_id=:companyId    AND h.del_status=1 AND h.is_active=1 AND h.company_id=c.company_id AND"
			+ " l.loc_id=h.loc_id AND cy.cal_yr_id=h.cal_yr_id AND c.del_status=1 AND l.del_status=1 ", nativeQuery = true)

	List<GetHoliday> getHolidayListByCompany(@Param("companyId") int companyId);

}
