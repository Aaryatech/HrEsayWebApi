package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmpSalInfoDaiyInfoTempInfo;

public interface EmpSalInfoDaiyInfoTempInfoRepo extends JpaRepository<EmpSalInfoDaiyInfoTempInfo, Integer>{

	@Query(value = "select sdt.id, sdt.cmp_id, sdt.emp_id, sdt.emp_code,sdt.emp_type,sdt.contractor_id,sdt.depart_id,sdt.designation_id,sdt.location_id, sdt.calc_month, "
			+ "sdt.calc_year, sdt.sal_type_id, sdt.att_sum_id, sdt.basic_cal, sdt.performance_bonus, sdt.ot_wages, sdt.misc_exp_add, sdt.bonus_cal, sdt.exgretia_cal, "
			+ "sdt.da_arreas_cal, sdt.increment_arreas_cal, sdt.epf_wages, sdt.epf_wages_employer, sdt.esic_wages_cal, sdt.gross_salary as gross_salary_dytemp, "
			+ "sdt.eps_wages, sdt.esic_wages_dec, sdt.employee_pf, sdt.employer_eps, sdt.employer_pf, sdt.esic, sdt.employer_esic, sdt.esic_status, sdt.pf_status, "
			+ "sdt.mlwf, sdt.tds, sdt.itded, sdt.fund, sdt.tot_pf_admin_ch, sdt.tot_edli_ch, sdt.tot_edli_admin_ch, sdt.ncp_days as ncp_days_dytemp, sdt.status as "
			+ "status_dytemp, sdt.pt_ded, sdt.advance_ded, sdt.loan_ded, sdt.misc_exp_ded, sdt.misc_exp_ded_deduct, sdt.net_salary, sdt.is_locked,  "
			+ "sdt.mlwf_applicable as mlwf_applicable_dytemp, sdt.pt_applicable as pt_applicable_dytemp, sdt.pay_ded, sdt.comments_for_it_bonus, sdt.society_contribution "
			+ "as society_contribution_dytemp, sdt.emp_category, sdt.basic_default,"
			+ "si.salary_info_id,si.salary_type_id, si.basic, si.da, si.hra, si.spa, si.pf_applicable, si.pf_type, si.pf_emp_per, si.pf_emplr_per, si.esic_applicable, "
			+ "si.cmp_joining_date, si.cmp_leaving_date, si.epf_joining_date, si.leaving_reason, si.sal_basis, si.ceiling_limit_emp_applicable, "
			+ "si.ceiling_limit_employer_applicable, si.leaving_reason_esic, si.leaving_reason_pf, si.mlwf_applicable, si.pt_applicable, si.gross_salary, "
			+ "si.society_contribution, si.basic_company, si.hra_company, si.da_company, si.employee_esic_percentage, si.employer_esic_percentage, si.del_status,"
			+ "sd.id as sum_daily_id, sd.company_id,sd.emp_name, sd.month, sd.year, sd.working_days, sd.present_days, sd.weekly_off, sd.paid_holiday, "
			+ "sd.paid_leave, sd.legal_strike, sd.lay_off, sd.unpaid_holiday, sd.unpaid_leave, sd.absent_days, sd.payable_days, sd.ncp_days, sd.totlate_mins, "
			+ "sd.totlate_days, sd.totout_mins, sd.totworking_hrs, sd.totot_hrs, sd.tot_othr, sd.tot_late, sd.rec_status, sd.login_name, sd.login_time, sd.status, "
			+ "sd.import_date, sd.cmp_code, sd.rec_status_paid, sd.total_days_inmonth, sd.late_ded_leave_paid, sd.holiday_present, sd.weekly_off_present, sd.full_night, "
			+ "sd.half_night, sd.holiday_present_half, sd.weekly_off_present_half, sd.weekly_off_holiday_off, sd.weekly_off_holiday_off_present, "
			+ "sd.weekly_off_holiday_off_present_halfday, sd.hdpresent_hdleave, sd.tot_early_going, sd.atsumm_uid, sd.calculation_done from "
			+ "tbl_salary_dynamic_temp sdt, tbl_emp_salary_info si,  tbl_attt_summary_daily sd  where "
			+ "sd.emp_id=si.emp_id \n" + 
			"        and sdt.emp_id=si.emp_id \n" + 
			"        and sd.month=:month \n" + 
			"        and sd.year=:year  \n" + 
			"        and sd.month=sdt.calc_month \n" + 
			"        and sd.year=sdt.calc_year\n" + 
			"        and sd.emp_id in (:empIds)      \n" + 
			"    order by\n" + 
			"        sd.emp_id", nativeQuery = true)
	List<EmpSalInfoDaiyInfoTempInfo> getSalaryTempList(@Param("month") int month, @Param("year") int year,
			@Param("empIds") List<Integer> empIds);

}
