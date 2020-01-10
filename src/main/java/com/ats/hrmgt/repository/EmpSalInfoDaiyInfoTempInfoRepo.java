package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmpSalInfoDaiyInfoTempInfo;

public interface EmpSalInfoDaiyInfoTempInfoRepo extends JpaRepository<EmpSalInfoDaiyInfoTempInfo, Integer>{

	@Query(value = "select\n" + 
			"        uuid() as uuid ,\n" + 
			"        sdt.id,\n" + 
			"        sdt.cmp_id,\n" + 
			"        sdt.emp_id,\n" + 
			"        sdt.emp_code,\n" + 
			"        sdt.emp_type,\n" + 
			"        sdt.contractor_id,\n" + 
			"        sdt.depart_id,\n" + 
			"        sdt.designation_id,\n" + 
			"        sdt.location_id,\n" + 
			"        sdt.calc_month,\n" + 
			"        sdt.calc_year,\n" + 
			"        sdt.sal_type_id,\n" + 
			"        sdt.att_sum_id,\n" + 
			"        sdt.basic_cal,\n" + 
			"        sdt.performance_bonus,\n" + 
			"        sdt.ot_wages,\n" + 
			"        sdt.misc_exp_add,\n" + 
			"        sdt.bonus_cal,\n" + 
			"        sdt.exgretia_cal,\n" + 
			"        sdt.da_arreas_cal,\n" + 
			"        sdt.increment_arreas_cal,\n" + 
			"        sdt.epf_wages,\n" + 
			"        sdt.epf_wages_employer,\n" + 
			"        sdt.esic_wages_cal,\n" + 
			"        sdt.gross_salary as gross_salary_dytemp,\n" + 
			"        sdt.eps_wages,\n" + 
			"        sdt.esic_wages_dec,\n" + 
			"        sdt.employee_pf,\n" + 
			"        sdt.employer_eps,\n" + 
			"        sdt.employer_pf,\n" + 
			"        sdt.esic,\n" + 
			"        sdt.employer_esic,\n" + 
			"        sdt.esic_status,\n" + 
			"        sdt.pf_status,\n" + 
			"        sdt.mlwf,\n" + 
			"        sdt.tds,\n" + 
			"        sdt.itded,\n" + 
			"        sdt.fund,\n" + 
			"        sdt.tot_pf_admin_ch,\n" + 
			"        sdt.tot_edli_ch,\n" + 
			"        sdt.tot_edli_admin_ch,\n" + 
			"        sdt.ncp_days as ncp_days_dytemp,\n" + 
			"        sdt.status as status_dytemp,\n" + 
			"        sdt.pt_ded,\n" + 
			"        sdt.advance_ded,\n" + 
			"        sdt.loan_ded,\n" + 
			"        sdt.misc_exp_ded,\n" + 
			"        sdt.misc_exp_ded_deduct,\n" + 
			"        sdt.net_salary,\n" + 
			"        sdt.is_locked,\n" + 
			"        sdt.mlwf_applicable as mlwf_applicable_dytemp,\n" + 
			"        sdt.pt_applicable as pt_applicable_dytemp,\n" + 
			"        sdt.pay_ded,\n" + 
			"        sdt.comments_for_it_bonus,\n" + 
			"        sdt.society_contribution as society_contribution_dytemp,\n" + 
			"        sdt.emp_category,\n" + 
			"        sdt.basic_default,\n" + 
			"        si.salary_info_id,\n" + 
			"        si.salary_type_id,\n" + 
			"        si.basic,\n" + 
			"        si.da,\n" + 
			"        si.hra,\n" + 
			"        si.spa,\n" + 
			"        si.pf_applicable,\n" + 
			"        si.pf_type,\n" + 
			"        si.pf_emp_per,\n" + 
			"        si.pf_emplr_per,\n" + 
			"        si.esic_applicable,\n" + 
			"        si.cmp_joining_date,\n" + 
			"        si.cmp_leaving_date,\n" + 
			"        si.epf_joining_date,\n" + 
			"        si.leaving_reason,\n" + 
			"        si.sal_basis,\n" + 
			"        si.ceiling_limit_emp_applicable,\n" + 
			"        si.ceiling_limit_employer_applicable,\n" + 
			"        si.leaving_reason_esic,\n" + 
			"        si.leaving_reason_pf,\n" + 
			"        si.mlwf_applicable,\n" + 
			"        si.pt_applicable,\n" + 
			"        si.gross_salary,\n" + 
			"        si.society_contribution,\n" + 
			"        si.basic_company,\n" + 
			"        si.hra_company,\n" + 
			"        si.da_company,\n" + 
			"        si.employee_esic_percentage,\n" + 
			"        si.employer_esic_percentage,\n" + 
			"        si.del_status,\n" + 
			"        sd.id as sum_daily_id,\n" + 
			"        sd.company_id,\n" + 
			"        sd.emp_name,\n" + 
			"        sd.month,\n" + 
			"        sd.year,\n" + 
			"        sd.working_days,\n" + 
			"        sd.present_days,\n" + 
			"        sd.weekly_off,\n" + 
			"        sd.paid_holiday,\n" + 
			"        sd.paid_leave,\n" + 
			"        sd.legal_strike,\n" + 
			"        sd.lay_off,\n" + 
			"        sd.unpaid_holiday,\n" + 
			"        sd.unpaid_leave,\n" + 
			"        sd.absent_days,\n" + 
			"        sd.payable_days,\n" + 
			"        sd.ncp_days,\n" + 
			"        sd.totlate_mins,\n" + 
			"        sd.totlate_days,\n" + 
			"        sd.totout_mins,\n" + 
			"        sd.totworking_hrs,\n" + 
			"        sd.totot_hrs,\n" + 
			"        sd.tot_othr,\n" + 
			"        sd.tot_late,\n" + 
			"        sd.rec_status,\n" + 
			"        sd.login_name,\n" + 
			"        sd.login_time,\n" + 
			"        sd.status,\n" + 
			"        sd.import_date,\n" + 
			"        sd.cmp_code,\n" + 
			"        sd.rec_status_paid,\n" + 
			"        sd.total_days_inmonth,\n" + 
			"        sd.late_ded_leave_paid,\n" + 
			"        sd.holiday_present,\n" + 
			"        sd.weekly_off_present,\n" + 
			"        sd.full_night,\n" + 
			"        sd.half_night,\n" + 
			"        sd.holiday_present_half,\n" + 
			"        sd.weekly_off_present_half,\n" + 
			"        sd.weekly_off_holiday_off,\n" + 
			"        sd.weekly_off_holiday_off_present,\n" + 
			"        sd.weekly_off_holiday_off_present_halfday,\n" + 
			"        sd.hdpresent_hdleave,\n" + 
			"        sd.tot_early_going,\n" + 
			"        sd.atsumm_uid,\n" + 
			"        sd.calculation_done,\n" + 
			"        ef.dob\n" + 
			"    from\n" + 
			"        tbl_salary_dynamic_temp sdt,\n" + 
			"        tbl_emp_salary_info si,\n" + 
			"        tbl_attt_summary_daily sd,\n" + 
			"        tbl_emp_info ef\n" + 
			"    where\n" + 
			"        sd.emp_id=si.emp_id          \n" + 
			"        and sdt.emp_id=si.emp_id          \n" + 
			"        and sd.month=:month          \n" + 
			"        and sd.year=:year         \n" + 
			"        and sd.month=sdt.calc_month          \n" + 
			"        and sd.year=sdt.calc_year         \n" + 
			"        and sdt.emp_id in (:empIds)\n" + 
			"        and ef.emp_id=sdt.emp_id\n" + 
			"    order by\n" + 
			"        sd.emp_id", nativeQuery = true)
	List<EmpSalInfoDaiyInfoTempInfo> getSalaryTempList(@Param("month") int month, @Param("year") int year,
			@Param("empIds") List<Integer> empIds);

}
