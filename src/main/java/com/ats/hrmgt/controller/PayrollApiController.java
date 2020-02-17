package com.ats.hrmgt.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.advance.repository.AdvanceRepo;
import com.ats.hrmgt.claim.repository.ClaimHeaderRepo;
import com.ats.hrmgt.common.EmailUtility;
import com.ats.hrmgt.common.EnglishNumberToWords;
import com.ats.hrmgt.model.Allowances;
import com.ats.hrmgt.model.EmpAllowanceList;
import com.ats.hrmgt.model.EmpSalAllowance;
import com.ats.hrmgt.model.EmpSalInfoDaiyInfoTempInfo;
import com.ats.hrmgt.model.EmpSalaryInfo;
import com.ats.hrmgt.model.EmpSalaryInfoForPayroll;
import com.ats.hrmgt.model.GetAdvanceList;
import com.ats.hrmgt.model.GetClaimList;
import com.ats.hrmgt.model.GetPayDedList;
import com.ats.hrmgt.model.GetPayrollGeneratedList;
import com.ats.hrmgt.model.GetSalDynamicTempRecord;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.MstEmpType;
import com.ats.hrmgt.model.PayRollDataForProcessing;
import com.ats.hrmgt.model.SalAllownceCal;
import com.ats.hrmgt.model.SalAllownceTemp;
import com.ats.hrmgt.model.SalaryCalc;
import com.ats.hrmgt.model.SalaryCalcTemp;
import com.ats.hrmgt.model.SalaryTerm;
import com.ats.hrmgt.model.SalaryTypesMaster;
import com.ats.hrmgt.model.SampleClass;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.model.SlabMaster;
import com.ats.hrmgt.model.loan.LoanDetails;
import com.ats.hrmgt.model.loan.LoanMain;
import com.ats.hrmgt.repo.loan.LoanDetailsRepo;
import com.ats.hrmgt.repo.loan.LoanMainRepo;
import com.ats.hrmgt.repository.AllowancesRepo;
import com.ats.hrmgt.repository.EmpSalAllowanceRepo;
import com.ats.hrmgt.repository.EmpSalInfoDaiyInfoTempInfoRepo;
import com.ats.hrmgt.repository.EmpSalaryInfoForPayrollRepository;
import com.ats.hrmgt.repository.EmpSalaryInfoRepo;
import com.ats.hrmgt.repository.GetAdvanceListRepo;
import com.ats.hrmgt.repository.GetClaimListRepo;
import com.ats.hrmgt.repository.GetPayDedListRepo;
import com.ats.hrmgt.repository.GetPayrollGeneratedListRepo;
import com.ats.hrmgt.repository.GetSalDynamicTempRecordRepository;
import com.ats.hrmgt.repository.MstEmpTypeRepository;
import com.ats.hrmgt.repository.PayDeductionDetailsRepo;
import com.ats.hrmgt.repository.SalAllownceCalRepo;
import com.ats.hrmgt.repository.SalAllownceTempRepo;
import com.ats.hrmgt.repository.SalaryCalcRepo;
import com.ats.hrmgt.repository.SalaryCalcTempRepo;
import com.ats.hrmgt.repository.SalaryTermRepository;
import com.ats.hrmgt.repository.SalaryTypesMasterRepo;
import com.ats.hrmgt.repository.SettingRepo;
import com.ats.hrmgt.repository.SlabMasterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
public class PayrollApiController {

	@Autowired
	EmpSalaryInfoForPayrollRepository empSalaryInfoForPayrollRepository;

	@Autowired
	EmpSalAllowanceRepo empSalAllowanceRepo;

	@Autowired
	AllowancesRepo allowanceRepo;

	@Autowired
	SalaryCalcTempRepo salaryCalcTempRepo;

	@Autowired
	SalAllownceTempRepo salAllownceTempRepo;

	@Autowired
	GetSalDynamicTempRecordRepository getSalDynamicTempRecordRepository;

	@Autowired
	GetAdvanceListRepo getAdvanceListRepo;

	@Autowired
	GetClaimListRepo getClaimListRepo;

	@Autowired
	GetPayDedListRepo getPayDedListRepo;

	@Autowired
	MstEmpTypeRepository mstEmpTypeRepository;

	@Autowired
	SalaryTypesMasterRepo salaryTypesMasterRepo;

	@Autowired
	SlabMasterRepository slabMasterRepository;

	@Autowired
	SalaryTermRepository salaryTermRepository;

	@Autowired
	SettingRepo settingRepo;

	@Autowired
	EmpSalaryInfoRepo empSalaryInfoRepo;

	@Autowired
	EmpSalInfoDaiyInfoTempInfoRepo empSalInfoDaiyInfoTempInfoRepo;

	@Autowired
	SalaryCalcRepo salaryCalcRepo;

	@Autowired
	SalAllownceCalRepo salAllownceCalRepo;

	@Autowired
	GetPayrollGeneratedListRepo getPayrollGeneratedListRepo;

	@Autowired
	AdvanceRepo advanceRepo;

	@Autowired
	ClaimHeaderRepo claimHeaderRepo;

	@Autowired
	PayDeductionDetailsRepo payDeductionDetailsRepo;

	@Autowired
	LoanMainRepo loanMainRepo;

	@Autowired
	LoanDetailsRepo loanDetailsRepo;

	@RequestMapping(value = { "/getEmployeeListWithEmpSalEnfoForPayRoll" }, method = RequestMethod.POST)
	public PayRollDataForProcessing getEmployeeListWithEmpSalEnfoForPayRoll(@RequestParam("month") int month,
			@RequestParam("year") int year) {

		PayRollDataForProcessing payRollDataForProcessing = new PayRollDataForProcessing();

		try {
			List<EmpSalaryInfoForPayroll> list = empSalaryInfoForPayrollRepository
					.getEmployeeListWithEmpSalEnfoForPayRoll(month, year);
			List<Allowances> allowancelist = allowanceRepo.findBydelStatusAndIsActive(0, 1);
			List<EmpSalAllowance> empAllowanceList = empSalAllowanceRepo.findByDelStatus(1);

			for (int i = 0; i < list.size(); i++) {

				List<EmpAllowanceList> allowlist = new ArrayList<>();

				for (int j = 0; j < allowancelist.size(); j++) {

					int flag = 0;

					for (int k = 0; k < empAllowanceList.size(); k++) {

						if (empAllowanceList.get(k).getEmpId() == list.get(i).getEmpId()
								&& empAllowanceList.get(k).getAllowanceId() == allowancelist.get(j).getAllowanceId()) {

							EmpAllowanceList empAllowance = new EmpAllowanceList();
							empAllowance.setAllowanceId(allowancelist.get(j).getAllowanceId());
							empAllowance.setAllowanceName(allowancelist.get(j).getName());
							empAllowance.setValue(empAllowanceList.get(k).getAllowanceValue());
							allowlist.add(empAllowance);
							flag = 1;
							break;

						}
					}
					if (flag == 0) {
						EmpAllowanceList empAllowance = new EmpAllowanceList();
						empAllowance.setAllowanceId(allowancelist.get(j).getAllowanceId());
						empAllowance.setAllowanceName(allowancelist.get(j).getName());
						empAllowance.setValue(0);
						allowlist.add(empAllowance);
					}
				}
				list.get(i).setEmpAllowanceList(allowlist);
			}

			payRollDataForProcessing.setAllowancelist(allowancelist);
			payRollDataForProcessing.setList(list);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return payRollDataForProcessing;
	}

	@RequestMapping(value = { "/insertPayrollIntempTable" }, method = RequestMethod.POST)
	public Info insertPayrollIntempTable(@RequestParam("month") int month, @RequestParam("year") int year,
			@RequestParam("empIds") List<Integer> empIds, @RequestParam("userId") int userId) {

		Info info = new Info();

		try {
			List<EmpSalaryInfoForPayroll> list = empSalaryInfoForPayrollRepository
					.getEmployeeListWithEmpSalEnfoForPayRollForTempInsert(month, year, empIds);
			List<Allowances> allowancelist = allowanceRepo.findBydelStatusAndIsActive(0, 1);
			List<EmpSalAllowance> empAllowanceList = empSalAllowanceRepo.findByDelStatusAndEmpId(1, empIds);
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// insert code if record not inserted in temp table
			for (int i = 0; i < list.size(); i++) {

				SalaryCalcTemp salaryCalcTempsave = new SalaryCalcTemp();
				salaryCalcTempsave.setCalcMonth(month);
				salaryCalcTempsave.setCalcYear(year);
				salaryCalcTempsave.setEmpId(list.get(i).getEmpId());
				salaryCalcTempsave.setEmpCode(list.get(i).getEmpCode());
				salaryCalcTempsave.setContractorId(list.get(i).getContractorId());
				salaryCalcTempsave.setDesignationId(list.get(i).getDesigId());
				salaryCalcTempsave.setLocationId(list.get(i).getLocId());
				salaryCalcTempsave.setEmpType(list.get(i).getEmpTypeId());
				salaryCalcTempsave.setDepartId(list.get(i).getDepartId());
				salaryCalcTempsave.setAttSumId(list.get(i).getSumId());
				salaryCalcTempsave.setSalTypeId(list.get(i).getSalaryTypeId());
				salaryCalcTempsave.setLoginName(String.valueOf(userId));
				salaryCalcTempsave.setLoginTime(sf.format(date));
				salaryCalcTempsave.setCmpId(list.get(i).getSubCmpId());
				SalaryCalcTemp saveres = salaryCalcTempRepo.save(salaryCalcTempsave);

				List<SalAllownceTemp> allowlist = new ArrayList<>();

				for (int j = 0; j < allowancelist.size(); j++) {

					int flag = 0;

					for (int k = 0; k < empAllowanceList.size(); k++) {

						if (empAllowanceList.get(k).getEmpId() == list.get(i).getEmpId()
								&& empAllowanceList.get(k).getAllowanceId() == allowancelist.get(j).getAllowanceId()) {

							SalAllownceTemp empAllowance = new SalAllownceTemp();
							empAllowance.setAllowanceId(allowancelist.get(j).getAllowanceId());
							empAllowance.setAllowanceValue(empAllowanceList.get(k).getAllowanceValue());
							empAllowance.setTblSalaryDynamicTempId(saveres.getId());
							empAllowance.setEmpId(saveres.getEmpId());
							empAllowance.setDelStatus(1);
							empAllowance.setShortName(allowancelist.get(j).getShortName());
							allowlist.add(empAllowance);
							flag = 1;
							break;

						}
					}
					if (flag == 0) {
						SalAllownceTemp empAllowance = new SalAllownceTemp();
						empAllowance.setAllowanceId(allowancelist.get(j).getAllowanceId());
						empAllowance.setAllowanceValue(0);
						empAllowance.setTblSalaryDynamicTempId(saveres.getId());
						empAllowance.setEmpId(saveres.getEmpId());
						empAllowance.setDelStatus(1);
						empAllowance.setShortName(allowancelist.get(j).getShortName());
						allowlist.add(empAllowance);
					}
				}
				List<SalAllownceTemp> saveAllores = salAllownceTempRepo.saveAll(allowlist);
			}

			// update record if some ammount insert after insert temp record
			List<SalaryCalcTemp> listForUpdatedValue = salaryCalcTempRepo.listForUpdatedValue(month, year, empIds);
			List<GetAdvanceList> getAdvanceList = getAdvanceListRepo.getAdvanceList(month, year, empIds);
			List<GetClaimList> getClaimList = getClaimListRepo.getClaimList(month, year, empIds);
			List<GetPayDedList> getPayDedList = getPayDedListRepo.getPayDedList(month, year, empIds);
			List<GetPayDedList> getLoanList = getPayDedListRepo.getLoanList(year + "-" + month + "-01", empIds);

			for (int i = 0; i < listForUpdatedValue.size(); i++) {

				int flag = 0;
				for (int j = 0; j < getAdvanceList.size(); j++) {

					if (getAdvanceList.get(j).getEmpId() == listForUpdatedValue.get(i).getEmpId()) {
						listForUpdatedValue.get(i).setAdvanceDed(getAdvanceList.get(j).getAdvAmount());
						flag = 1;
						break;
					}

				}
				if (flag == 0) {
					listForUpdatedValue.get(i).setAdvanceDed(0);
				}

				flag = 0;
				for (int j = 0; j < getClaimList.size(); j++) {

					if (getClaimList.get(j).getEmpId() == listForUpdatedValue.get(i).getEmpId()) {
						listForUpdatedValue.get(i).setMiscExpAdd(getClaimList.get(j).getClaimAmount());
						flag = 1;
						break;
					}

				}
				if (flag == 0) {
					listForUpdatedValue.get(i).setMiscExpAdd(0);
				}

				flag = 0;
				for (int j = 0; j < getPayDedList.size(); j++) {

					if (getPayDedList.get(j).getEmpId() == listForUpdatedValue.get(i).getEmpId()) {
						listForUpdatedValue.get(i).setPayDed(getPayDedList.get(j).getAmt());
						flag = 1;
						break;
					}

				}
				if (flag == 0) {
					listForUpdatedValue.get(i).setPayDed(0);
				}

				flag = 0;
				for (int j = 0; j < getLoanList.size(); j++) {

					if (getLoanList.get(j).getEmpId() == listForUpdatedValue.get(i).getEmpId()) {
						listForUpdatedValue.get(i).setLoanDed(getLoanList.get(j).getAmt());
						flag = 1;
						break;
					}

				}
				if (flag == 0) {
					listForUpdatedValue.get(i).setLoanDed(0);
				}
			}

			List<SalaryCalcTemp> savereslist = salaryCalcTempRepo.saveAll(listForUpdatedValue);

			info.setError(false);
			info.setMsg("success");
		} catch (Exception e) {
			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}

		return info;
	}

	@RequestMapping(value = { "/getSalDynamicTempRecord" }, method = RequestMethod.POST)
	public List<GetSalDynamicTempRecord> getSalDynamicTempRecord(@RequestParam("month") int month,
			@RequestParam("year") int year, @RequestParam("empIds") List<Integer> empIds) {

		List<GetSalDynamicTempRecord> list = new ArrayList<>();

		try {
			list = getSalDynamicTempRecordRepository.getSalDynamicTempRecord(month, year, empIds);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getSalDynamicTempRecordById" }, method = RequestMethod.POST)
	public GetSalDynamicTempRecord getSalDynamicTempRecordById(
			@RequestParam("tempSalDaynamicId") int tempSalDaynamicId) {

		GetSalDynamicTempRecord getSalDynamicTempRecordById = new GetSalDynamicTempRecord();

		try {
			getSalDynamicTempRecordById = getSalDynamicTempRecordRepository
					.getSalDynamicTempRecordById(tempSalDaynamicId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return getSalDynamicTempRecordById;
	}

	@RequestMapping(value = { "/updateBonusAmt" }, method = RequestMethod.POST)
	public Info updateBonusAmt(@RequestParam("tempSalDaynamicId") int tempSalDaynamicId,
			@RequestParam("itAmt") float itAmt, @RequestParam("perBonus") float perBonus) {

		Info info = new Info();

		try {
			int update = salaryCalcTempRepo.updateBonusAmt(tempSalDaynamicId, itAmt, perBonus);
			info.setError(false);
			info.setMsg("success");

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}

		return info;
	}

	@RequestMapping(value = { "/sampleWebservice" }, method = RequestMethod.GET)
	public List<SampleClass> sampleWebservice() {

		List<SampleClass> list = new ArrayList<>();

		try {
			SampleClass sampleClass = new SampleClass();
			sampleClass.setValue(10);
			sampleClass.setName("akshay");
			list.add(sampleClass);

			sampleClass = new SampleClass();
			sampleClass.setValue(12);
			sampleClass.setName("kishore");
			list.add(sampleClass);

			for (int i = 0; i < list.size(); i++) {

				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String json = ow.writeValueAsString(list.get(i));
				String[] splt = json.substring(1, json.length() - 1).split(",");

				for (int j = 0; j < splt.length; j++) {
					if (splt[j].contains("value")) {
						String[] value = splt[j].split(":");
						System.out.println(value[1].trim());
						break;
					}
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/calculateSalary" }, method = RequestMethod.POST)
	public List<EmpSalInfoDaiyInfoTempInfo> calculateSalary(@RequestParam("month") int month,
			@RequestParam("year") int year, @RequestParam("empIds") List<Integer> empIds,
			@RequestParam("userId") int userId) {

		List<EmpSalInfoDaiyInfoTempInfo> getSalaryTempList = new ArrayList<>();
		try {

			Date dt = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sf.format(dt);
			String[] dates = date.split("-");

			getSalaryTempList = empSalInfoDaiyInfoTempInfoRepo.getSalaryTempList(month, year, empIds);
			List<SalaryTypesMaster> salaryTypeList = salaryTypesMasterRepo.findAllByDelStatus(1);
			List<MstEmpType> mstEmpTypeList = mstEmpTypeRepository.findAll();
			List<SlabMaster> slabMasterList = slabMasterRepository.findAll();// slab
			List<SalaryTerm> salaryTermList = salaryTermRepository.getSalaryTermList();// salary tem
			List<Setting> settingList = settingRepo.findByGroup("PAYROLL");
			List<SalAllownceTemp> getAllowanceTempList = salAllownceTempRepo.getAllowanceTempList(month, year, empIds);

			for (int i = 0; i < getSalaryTempList.size(); i++) {
				List<SalAllownceTemp> list = new ArrayList<>();

				for (int j = 0; j < getAllowanceTempList.size(); j++) {

					if (getAllowanceTempList.get(j).getTblSalaryDynamicTempId() == getSalaryTempList.get(i).getId()) {
						list.add(getAllowanceTempList.get(j));
					}

				}
				getSalaryTempList.get(i).setGetAllowanceTempList(list);
			}

			double epf_wages_employee = 0;
			double epf_wages_employeR = 0;
			double eps_Cal = 0;
			double epf_percentage = 0;
			double cealing_limit_eps_wages = 0;
			double employer_eps_percentage = 0;
			double employer_eps_ceiling_limit = 0;
			double eps_age_limit = 0;
			double esic_limit = 0;
			double employer_epf_percentage = 0;
			double employer_esic_percentage = 0;
			double employee_esic_percentage = 0;
			double tot_pf_admin_ch_percentage = 0;
			double tot_edli_ch_percentage = 0;
			double tot_edli_admin_ch_percentage = 0;
			int ab_deduction = 0;
			int febmonthptamount_condtioncheck = 0;
			double febmonthptamount = 0;
			int amount_round = 0;
			double diff_epfwages_limit = 0;

			for (int k = 0; k < settingList.size(); k++) {
				if (settingList.get(k).getKey().equalsIgnoreCase("ceiling_limit_eps_wages")) {
					cealing_limit_eps_wages = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("employer_eps_percentage")) {
					employer_eps_percentage = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("epf_percentage")) {
					epf_percentage = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("employer_eps_ceiling_limit")) {
					employer_eps_ceiling_limit = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("eps_age_limit")) {
					eps_age_limit = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("esic_limit")) {
					esic_limit = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("employer_esic_percentage")) {
					employer_esic_percentage = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("employee_esic_percentage")) {
					employee_esic_percentage = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("tot_pf_admin_ch")) {
					tot_pf_admin_ch_percentage = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("tot_edli_ch")) {
					tot_edli_ch_percentage = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("tot_edli_admin_ch")) {
					tot_edli_admin_ch_percentage = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("febmonthptamount_condtioncheck")) {
					febmonthptamount_condtioncheck = Integer.parseInt(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("febmonthptamount")) {
					febmonthptamount = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("ammount_format_Insert")) {
					amount_round = Integer.parseInt(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("ab_deduction")) {
					ab_deduction = Integer.parseInt(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("employer_epf_percentage")) {
					employer_epf_percentage = Float.parseFloat(settingList.get(k).getValue());
				}
			}

			MstEmpType mstEmpType = new MstEmpType();
			SalaryTypesMaster salaryType = new SalaryTypesMaster();

			for (int i = 0; i < getSalaryTempList.size(); i++) {

				if (getSalaryTempList.get(i).getPfEmpPer() > 0) {
					epf_percentage = getSalaryTempList.get(i).getPfEmpPer();
				}

				for (int j = 0; j < salaryTermList.size(); j++) {
					salaryTermList.get(j).setValue(0);
				}

				for (int j = 0; j < mstEmpTypeList.size(); j++) {

					if (mstEmpTypeList.get(j).getEmpTypeId() == getSalaryTempList.get(i).getEmpType()) {
						mstEmpType = mstEmpTypeList.get(j);
						break;
					}

				}

				for (int j = 0; j < salaryTypeList.size(); j++) {

					if (salaryTypeList.get(j).getSalTypeId() == getSalaryTempList.get(i).getSalTypeId()) {
						salaryType = salaryTypeList.get(j);
						break;
					}

				}

				for (int j = 0; j < salaryTermList.size(); j++) {

					if (salaryTermList.get(j).getSalTypeId() == salaryType.getSalTypeId()) {

						double ammt = 0;
						int index = 0;
						int findAll = 0;
						if (!salaryTermList.get(j).getFromColumn().equals("")) {
							if (salaryTermList.get(j).getFromColumn().equals("basic")) {
								ammt = getSalaryTempList.get(i).getBasic();
								// System.out.println(ammt);
							} else if (salaryTermList.get(j).getFromColumn().equals("performance_bouns_cal")) {
								ammt = getSalaryTempList.get(i).getPerformanceBonus();
							} else if (salaryTermList.get(j).getFromColumn().equals("society_contribution")) {
								ammt = getSalaryTempList.get(i).getSocietyContribution();
							} else {

								for (int k = 0; k < getSalaryTempList.get(i).getGetAllowanceTempList().size(); k++) {

									if (getSalaryTempList.get(i).getGetAllowanceTempList().get(k).getShortName()
											.equals(salaryTermList.get(j).getFromColumn())) {
										findAll = 1;
										index = k;
										ammt = getSalaryTempList.get(i).getGetAllowanceTempList().get(k)
												.getAllowanceValue();
									}

								}

							}
						}
						switch (salaryTermList.get(j).getFormulaType()) {
						case "F":
							double calculatedValue = calculateFdata(salaryTermList.get(j).getPercentage(),
									getSalaryTempList.get(i).getSalBasis(),
									getSalaryTempList.get(i).getTotalDaysInmonth(),
									getSalaryTempList.get(i).getPayableDays(),
									getSalaryTempList.get(i).getWorkingDays(), ammt,
									getSalaryTempList.get(i).getPresentDays(), amount_round);
							getSalaryTempList.get(i).setBasicCal(calculatedValue);
							salaryTermList.get(j).setValue(calculatedValue);

							break;
						case "A":
							double tempVal = 0;

							if (findAll == 1) {
								tempVal = calculateAllowancedata(salaryTermList.get(j).getPercentage(),
										getSalaryTempList.get(i).getSalBasis(),
										getSalaryTempList.get(i).getTotalDaysInmonth(),
										getSalaryTempList.get(i).getPayableDays(),
										getSalaryTempList.get(i).getWorkingDays(), ammt,
										getSalaryTempList.get(i).getPresentDays(), amount_round);
								getSalaryTempList.get(i).getGetAllowanceTempList().get(index)
										.setAllowanceValueCal(tempVal);
								salaryTermList.get(j).setValue(tempVal);

								System.out.println(salaryTermList.get(j).getFromColumn() + " " + tempVal + ""
										+ getSalaryTempList.get(i).getGetAllowanceTempList().get(index).getShortName());

							}

							break;
						case "S":
							double tempValNew = 0;
							tempVal = calculatePdata(salaryTermList.get(j), salaryTermList, getSalaryTempList.get(i),
									amount_round);

							if (getSalaryTempList.get(i).getPtApplicable().equalsIgnoreCase("yes")) {

								for (int k = 0; k < slabMasterList.size(); k++) {

									if (slabMasterList.get(k).getSalTermId() == salaryTermList.get(j).getSalTermId()) {

										if (tempVal >= slabMasterList.get(k).getMinVal()
												&& tempVal <= slabMasterList.get(k).getMaxVal()) {

											if (getSalaryTempList.get(i).getMonth() == 2 && slabMasterList.get(k)
													.getAmount() == febmonthptamount_condtioncheck) {
												tempValNew = febmonthptamount;
											} // $calc_month == "2" && $amt == "200"
											else {
												tempValNew = slabMasterList.get(k).getAmount();
											}
										}
									}

								}
							}

							getSalaryTempList.get(i).setPtDed(tempValNew);
							salaryTermList.get(j).setValue(tempValNew);

							break;
						case "X":
							ammt = calculatePdata(salaryTermList.get(j), salaryTermList, getSalaryTempList.get(i),
									amount_round);

							tempVal = calculateXdata(salaryTermList.get(j).getPercentage(),
									getSalaryTempList.get(i).getSalBasis(),
									getSalaryTempList.get(i).getTotalDaysInmonth(),
									getSalaryTempList.get(i).getPayableDays(),
									getSalaryTempList.get(i).getWorkingDays(), ammt,
									getSalaryTempList.get(i).getPresentDays(), amount_round);
							// getSalaryTempList.get(i).setBasicDefault(tempVal);
							salaryTermList.get(j).setValue(tempVal);

							/*
							 * System.out.println(getSalaryTempList.get(i).getEmpId() + " " + ammt +
							 * " termid " + salaryTermList.get(j).getSalTermId() + " value " +
							 * salaryTermList.get(j).getValue() + " basic" +
							 * getSalaryTempList.get(i).getBasic());
							 */
							/* System.out.println(salaryTermList); */
							break;
						case "XA":

							if (findAll == 1) {
								salaryTermList.get(j).setValue(getSalaryTempList.get(i).getGetAllowanceTempList()
										.get(index).getAllowanceValue());

								/*
								 * System.out.println( salaryTermList.get(j) + " empid " +
								 * getSalaryTempList.get(i).getEmpId());
								 */

							}
							break;
						case "P":
							double temp = calculatePdata(salaryTermList.get(j), salaryTermList,
									getSalaryTempList.get(i), amount_round);

							if (salaryTermList.get(j).getFieldName().equals("gross_salary")) {
								getSalaryTempList.get(i).setGrossSalaryDytemp(temp);
								salaryTermList.get(j).setValue(temp);
							} else if (salaryTermList.get(j).getFieldName().equals("epf_wages")) {
								getSalaryTempList.get(i).setEpfWages(temp);
								salaryTermList.get(j).setValue(temp);
							} else if (salaryTermList.get(j).getFieldName().equals("esic_wages_cal")) {
								getSalaryTempList.get(i).setEsicWagesCal(temp);
								salaryTermList.get(j).setValue(temp);
							} else if (salaryTermList.get(j).getFieldName().equals("eps_wages_cal")) {
								getSalaryTempList.get(i).setEpsWages(temp);
								salaryTermList.get(j).setValue(temp);
							} else if (salaryTermList.get(j).getFieldName().equals("esic_wages_dec_cal")) {
								getSalaryTempList.get(i).setEsicWagesDec(temp);
								salaryTermList.get(j).setValue(temp);
							} else {
								salaryTermList.get(j).setValue(temp);
							}

							break;
						case "NET":
							temp = calculatePdata(salaryTermList.get(j), salaryTermList, getSalaryTempList.get(i),
									amount_round);
							getSalaryTempList.get(i).setNetSalary(temp);
							salaryTermList.get(j).setValue(temp);
							break;
						case "Y":
							// tempVal = castNumber(ammt);

							if (salaryTermList.get(j).getFieldName().equals("performance_bouns_cal")) {
								getSalaryTempList.get(i).setPerformanceBonus(ammt);
							} else if (salaryTermList.get(j).getFieldName().equals("basic_default")) {
								getSalaryTempList.get(i).setBasicDefault(ammt);
							} else if (salaryTermList.get(j).getFieldName().equals("society_contribution")) {
								getSalaryTempList.get(i).setSocietyContributionDytemp(ammt);
							}
							salaryTermList.get(j).setValue(ammt);

							// System.out.println(salaryTermList.get(j).getFieldName()+ " " +
							// getSalaryTempList.get(i).getBasicDefault());

							/* System.out.println(salaryTermList); */
							break;

						case "C1":

							break;
						case "OT":

							ammt = calculatePdata(salaryTermList.get(j), salaryTermList, getSalaryTempList.get(i),
									amount_round);

							tempVal = otwages(salaryTermList.get(j).getPercentage(),
									getSalaryTempList.get(i).getSalBasis(),
									getSalaryTempList.get(i).getTotalDaysInmonth(),
									getSalaryTempList.get(i).getPayableDays(),
									getSalaryTempList.get(i).getWorkingDays(), salaryType.getWorkinghr(),
									getSalaryTempList.get(i).getTotOthr(), ammt, mstEmpType, amount_round);
							getSalaryTempList.get(i).setOtWages(tempVal);
							salaryTermList.get(j).setValue(tempVal);

							/*
							 * System.out.println(getSalaryTempList.get(i).getEmpId() + " " + ammt +
							 * " termid " + salaryTermList.get(j).getSalTermId() + " value " +
							 * salaryTermList.get(j).getValue() + " basic" +
							 * getSalaryTempList.get(i).getBasic());
							 */

							break;
						case "OTFD":

							break;
						case "WOP":

							ammt = calculatePdata(salaryTermList.get(j), salaryTermList, getSalaryTempList.get(i),
									amount_round);

							tempVal = otwageswo(salaryTermList.get(j).getPercentage(),
									getSalaryTempList.get(i).getSalBasis(),
									getSalaryTempList.get(i).getTotalDaysInmonth(),
									getSalaryTempList.get(i).getWeeklyOffPresent(),
									getSalaryTempList.get(i).getWorkingDays(), ammt, mstEmpType, amount_round);
							getSalaryTempList.get(i).setProductionInsentive(tempVal);
							salaryTermList.get(j).setValue(tempVal);
							// System.out.println(ammt + "oTTTTT" + tempVal);

							/*
							 * System.out.println(getSalaryTempList.get(i).getEmpId() + " " + ammt +
							 * " termid " + salaryTermList.get(j).getSalTermId() + " value " +
							 * salaryTermList.get(j).getValue() + " basic" +
							 * getSalaryTempList.get(i).getBasic());
							 */

							break;
						case "AB":

							ammt = calculatePdata(salaryTermList.get(j), salaryTermList, getSalaryTempList.get(i),
									amount_round);

							tempVal = ammt / getSalaryTempList.get(i).getTotalDaysInmonth();
							double deductValue = castNumber(
									(tempVal * getSalaryTempList.get(i).getAbsentDays() * ab_deduction), amount_round);
							// System.out.println(ammt + "oTTTTT" + tempVal);
							getSalaryTempList.get(i).setAbDeduction(deductValue);
							// assign to table Filed
							salaryTermList.get(j).setValue(deductValue);

							break;
						case "FD":
							tempVal = fundwages(salaryTermList.get(j).getPercentage(),
									getSalaryTempList.get(i).getSalBasis(), ammt, amount_round);
							salaryTermList.get(j).setValue(tempVal);
							break;
						default:

							break;

						}

					}

				}

				if (!getSalaryTempList.get(i).getMlwfApplicable().equalsIgnoreCase("yes")) {
					getSalaryTempList.get(i).setMlwf(0);
				}

				try {
					// System.out.println("PF ==== " +
					// getSalaryTempList.get(i).getPfApplicable()+"#"+getSalaryTempList.get(i).getEpfWages());
					if (getSalaryTempList.get(i).getPfApplicable().equalsIgnoreCase("yes")) {

						getSalaryTempList.get(i).setPfStatus(1);
						epf_wages_employee = getSalaryTempList.get(i).getEpfWages();
						try {
							if (getSalaryTempList.get(i).getPfType().equalsIgnoreCase("voluntary")) {
								getSalaryTempList.get(i).setEmployeePf(castNumber(
										(epf_wages_employee * getSalaryTempList.get(i).getPfEmpPer()), amount_round));
								getSalaryTempList.get(i).setEpfPercentage(getSalaryTempList.get(i).getPfEmpPer());

							} else {

								getSalaryTempList.get(i)
										.setEmployeePf(castNumber((epf_wages_employee * epf_percentage), amount_round));
								getSalaryTempList.get(i).setEpfPercentage(epf_percentage);
							}

						} catch (Exception e) {
							// e.printStackTrace();
						}

						String[] dob = getSalaryTempList.get(i).getDob().split("-");

						LocalDate birthDate = LocalDate.of(Integer.parseInt(dob[0]), Integer.parseInt(dob[1]),
								Integer.parseInt(dob[2]));
						int age = calculateAge(birthDate, LocalDate.of(Integer.parseInt(dates[0]),
								Integer.parseInt(dates[1]), Integer.parseInt(dates[2])));

						if (age <= eps_age_limit) {

							if (getSalaryTempList.get(i).getEpfWages() > employer_eps_ceiling_limit) {
								epf_wages_employeR = employer_eps_ceiling_limit;

							} else {
								epf_wages_employeR = getSalaryTempList.get(i).getEpfWages();
							}

							double employer_eps_default = getSalaryTempList.get(i).getEpfWages()
									* employer_eps_percentage;
							double employer_eps = epf_wages_employeR * employer_eps_percentage;

							// employer_eps
							getSalaryTempList.get(i).setEpsWages(castNumber(epf_wages_employeR, amount_round));
							getSalaryTempList.get(i).setEpsDefault(castNumber(employer_eps_default, amount_round));
							getSalaryTempList.get(i)
									.setEmployerEps(castNumber(castNumber(employer_eps, amount_round), amount_round));
							getSalaryTempList.get(i).setEpsEmployerPercentage(employer_eps_percentage);

							// employer_epf
							double employer_epf_default = getSalaryTempList.get(i).getEpfWages()
									* employer_epf_percentage;
							getSalaryTempList.get(i).setEpfWagesEmployer(
									castNumber(getSalaryTempList.get(i).getEpfWages(), amount_round));
							getSalaryTempList.get(i)
									.setEpmloyerEpfDefault(castNumber(employer_epf_default, amount_round));
							getSalaryTempList.get(i).setEpfEmployerPercentage(employer_epf_percentage);

							if (employer_eps < employer_eps_default) {
								getSalaryTempList.get(i).setEpmloyerEpfExtra(
										castNumber((employer_eps_default - employer_eps), amount_round));
							}
							getSalaryTempList.get(i)
									.setEmployerPf(castNumber(getSalaryTempList.get(i).getEpmloyerEpfDefault()
											+ getSalaryTempList.get(i).getEpmloyerEpfExtra(), amount_round));
						} else {
							getSalaryTempList.get(i).setEpsWages(0);
							getSalaryTempList.get(i).setEpsDefault(0);
							getSalaryTempList.get(i).setEmployerEps(0);
							getSalaryTempList.get(i).setEpsEmployerPercentage(0);

							getSalaryTempList.get(i).setEpfWagesEmployer(0);
							getSalaryTempList.get(i).setEpmloyerEpfDefault(0);
							getSalaryTempList.get(i).setEpfEmployerPercentage(0);
							getSalaryTempList.get(i).setEpmloyerEpfExtra(0);
							getSalaryTempList.get(i).setEmployerPf(0);
						}
						// employer_pf calculation

					} // strtolower($value->pf_applicable) == 'yes'
					else // $emp->pf_applicable)=='no'
					{
						getSalaryTempList.get(i).setEmployeePf(0);
						getSalaryTempList.get(i).setEpfPercentage(0);
						getSalaryTempList.get(i).setPfStatus(0);
						getSalaryTempList.get(i).setEpfWages(0);
						getSalaryTempList.get(i).setEpsEmployeePercentage(0);

						getSalaryTempList.get(i).setEpsWages(0);
						getSalaryTempList.get(i).setEpsDefault(0);
						getSalaryTempList.get(i).setEmployerEps(0);
						getSalaryTempList.get(i).setEpsEmployerPercentage(0);

						getSalaryTempList.get(i).setEpfWagesEmployer(0);
						getSalaryTempList.get(i).setEpmloyerEpfDefault(0);
						getSalaryTempList.get(i).setEpfEmployerPercentage(0);
						getSalaryTempList.get(i).setEpmloyerEpfExtra(0);
						getSalaryTempList.get(i).setEmployerPf(0);

					}
				} catch (Exception e) {
					// e.printStackTrace();
				}
				try {
					// esic cal start
					if (getSalaryTempList.get(i).getEsicApplicable().equalsIgnoreCase("no")) {
						// $records['esic_wages_cal'] = 0;

						getSalaryTempList.get(i).setEmployerEsic(0);
						getSalaryTempList.get(i).setEsic(0);
						getSalaryTempList.get(i).setEsicWagesCal(0);

						getSalaryTempList.get(i).setEsicStatus(0);
					} // strtolower($value->esic_applicable) == 'no'
					else {

						getSalaryTempList.get(i).setEsicStatus(1);

						// we have not add any code to avoid esci deduction. as it mendatory to deduct
						// esic till some month, but kishore has changed the code to cut the esic
						if (getSalaryTempList.get(i).getEsicWagesDec() >= esic_limit) {
							getSalaryTempList.get(i).setEmployerEsic(0);
							getSalaryTempList.get(i).setEsic(0);
						} else {

							getSalaryTempList.get(i)
									.setEmployerEsic(castNumber(
											(getSalaryTempList.get(i).getEsicWagesCal() * employer_esic_percentage),
											amount_round));
							getSalaryTempList.get(i)
									.setEsic(castNumber(
											(getSalaryTempList.get(i).getEsicWagesCal() * employee_esic_percentage),
											amount_round));
						}
						// System.out.println("ESIC ==== " + getSalaryTempList.get(i).getEsicWagesCal()
						// + "##" + employee_esic_percentage);

					}
				} catch (Exception e) {
					// e.printStackTrace();
				}
				getSalaryTempList.get(i).setTotPfAdminCh(castNumber(
						(getSalaryTempList.get(i).getEpsWages() * tot_pf_admin_ch_percentage), amount_round));
				getSalaryTempList.get(i).setTotEdliCh(
						castNumber((getSalaryTempList.get(i).getEpsWages() * tot_edli_ch_percentage), amount_round));
				getSalaryTempList.get(i).setTotEdliAdminCh(castNumber(
						(getSalaryTempList.get(i).getEpsWages() * tot_edli_admin_ch_percentage), amount_round));
				getSalaryTempList.get(i).setPfAdminChPercentage(tot_pf_admin_ch_percentage);
				getSalaryTempList.get(i).setEdliPercentage(tot_edli_ch_percentage);
				getSalaryTempList.get(i).setEdliAdminPercentage(tot_edli_admin_ch_percentage);
				
				getSalaryTempList.get(i).setStatusDytemp(1);
				getSalaryTempList.get(i).setNetSalary(castNumber((getSalaryTempList.get(i).getGrossSalaryDytemp()
						+ getSalaryTempList.get(i).getPerformanceBonus() + getSalaryTempList.get(i).getMiscExpAdd()
						+ getSalaryTempList.get(i).getOtWages() + getSalaryTempList.get(i).getProductionInsentive())
						- (getSalaryTempList.get(i).getAdvanceDed() + getSalaryTempList.get(i).getLoanDed()
								+ getSalaryTempList.get(i).getPayDed() + getSalaryTempList.get(i).getEsic()
								+ getSalaryTempList.get(i).getEmployeePf() + getSalaryTempList.get(i).getPtDed()
								+ getSalaryTempList.get(i).getItded() + getSalaryTempList.get(i).getTds()
								+ getSalaryTempList.get(i).getSocietyContributionDytemp()),
						amount_round));
				getSalaryTempList.get(i).setLoginName(String.valueOf(userId));
				// System.out.println(salaryTermList);
			}

			// System.out.println(getSalaryTempList);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return getSalaryTempList;
	}

	public double calculateFdata(float percentage, String salBasis, int totalDaysInMonth, float payableDays,
			float workingDays, double ammt, float presentDays, int amount_round) {

		double val = 0;

		if (percentage == 1) {
			float totalPayableDaysTemp = Math.min(payableDays, totalDaysInMonth);
			// System.out.println(ammt);
			if (salBasis.equalsIgnoreCase("monthly")) {
				val = (ammt / totalDaysInMonth) * totalPayableDaysTemp;
			} // $sal_basis == "monthly"
			else {
				// $val = ($amount / $working_days ) * $total_payable_days;
				val = (ammt) * totalPayableDaysTemp;
			}
		} // $percentage == 1
		else if (percentage == 2) {
			float totalPayableDaysTemp = Math.min(payableDays, totalDaysInMonth);
			if (salBasis.equalsIgnoreCase("monthly")) {
				val = (ammt / totalDaysInMonth) * totalPayableDaysTemp;
			} // $sal_basis == "monthly"
			else {
				// $val = ($amount / $working_days ) * $total_payable_days;
				val = (ammt / workingDays) * totalPayableDaysTemp;
			}
		} // $percentage == 2
		else if (percentage == 3) {
			float totalPayableDaysTemp = Math.min(payableDays, totalDaysInMonth);
			val = (ammt / totalDaysInMonth) * totalPayableDaysTemp;
		} // $percentage == 2
		val = castNumber(val, amount_round);
		return val;
	}

	public double calculateAllowancedata(float percentage, String salBasis, int totalDays, float totalPayableDays,
			float workingDays, double amount, float presentDays, int amount_round) {

		double val = 0;
		if (percentage == 0) {
			val = castNumber(amount, amount_round);
			// val = castNumber(amount);
		} // $percentage == "0"
		else if (percentage == 1) {
			if (salBasis.equalsIgnoreCase("monthly")) {
				val = (amount / totalDays) * totalPayableDays;
			} // $sal_basis == "monthly"
			else {
				val = amount * totalPayableDays;
			}

			// val = castNumber(val);
		} // $percentage == 1
		else if (percentage == 2) {
			if (salBasis.equalsIgnoreCase("monthly")) {
				val = (amount / totalDays) * presentDays;
			} // $sal_basis == "monthly"
			else {
				// $val = ($amount / $working_days ) * $total_payable_days;
				val = amount * totalPayableDays;
			}
			// val = castNumber(val);
		} // $percentage == 2
		else if (percentage == 3) {
			float totalPayableDaysTemp = Math.min(totalPayableDays, totalDays);
			if (salBasis.equalsIgnoreCase("monthly")) {
				val = (amount / totalDays) * totalPayableDaysTemp;
			} // $sal_basis == "monthly"
			else {
				// $val = ($amount / $working_days ) * $total_payable_days;
				val = (amount) * totalPayableDaysTemp;
			}
			// val = castNumber(val);
		} // $percentage == 3
		else if (percentage == 4) {
			float totalPayableDaysTemp = Math.min(totalPayableDays, totalDays);
			if (salBasis.equalsIgnoreCase("monthly")) {
				val = (amount / totalDays) * totalPayableDaysTemp;
			} // $sal_basis == "monthly"
			else {
				// $val = ($amount / $working_days ) * $total_payable_days;
				val = (amount / totalDays) * totalPayableDaysTemp;
			}
			// val = castNumber(val);
		} // $percentage == 4

		val = castNumber(val, amount_round);
		return val;
	}

	public double calculateXdata(float percentage, String salBasis, int totalDays, float totalPayableDays,
			float workingDays, double amount, float presentDays, int amount_round) {

		double val = 0;
		if (salBasis.equalsIgnoreCase("monthly")) {
			val = amount;
		} // $sal_basis == "monthly"
		else {
			// $val = ($amount / $working_days ) * $total_payable_days;
			val = (amount / totalDays);
		}
		val = castNumber(val, amount_round);
		return val;

	}

	public double calculatePdata(SalaryTerm salaryTerm, List<SalaryTerm> salaryTermList,
			EmpSalInfoDaiyInfoTempInfo empSalInfoDaiyInfoTempInfo, int amount_round) {

		String formula = salaryTerm.getFormula();
		String[] plus = formula.split("\\+");
		String[] minus = formula.split("\\-");

		double value = 0;

		if (plus.length > 1) {
			// System.out.println("add" + formula);
			for (int i = 0; i < plus.length; i++) {
				for (int j = 0; j < salaryTermList.size(); j++) {
					if (Integer.parseInt(plus[i]) == salaryTermList.get(j).getSalTermId()
							&& salaryTermList.get(j).getSalTypeId() == salaryTerm.getSalTypeId()) {
						value = value + salaryTermList.get(j).getValue();
						/*
						 * System.out .println(salaryTermList.get(j).getSalTermId() + " " +
						 * salaryTermList.get(j).getValue() + " " +
						 * empSalInfoDaiyInfoTempInfo.getEmpId());
						 */
						break;
					}
				}

			}
		} else if (minus.length > 1) {
			for (int i = 0; i < minus.length; i++) {
				for (int j = 0; j < salaryTermList.size(); j++) {
					if (Integer.parseInt(minus[i]) == salaryTermList.get(j).getSalTermId()
							&& salaryTermList.get(j).getSalTypeId() == salaryTerm.getSalTypeId()) {
						if (i == 0) {
							value = salaryTermList.get(j).getValue();
						} else {
							value = value - salaryTermList.get(j).getValue();
						}
						break;
					}
				}

			}
		} else if (minus.length == 1 || plus.length == 1) {
			for (int j = 0; j < salaryTermList.size(); j++) {
				if (Integer.parseInt(minus[0]) == salaryTermList.get(j).getSalTermId()) {
					value = salaryTermList.get(j).getValue();
					break;
				}
			}
		}
		value = castNumber(value, amount_round);
		return value;
	}

	public double otwages(float percentage, String salBasis, int totalDays, float totalPayableDays, float workingDays,
			double workingHour, float otHr, double ammt, MstEmpType mstEmpType, int amount_round) {

		workingHour = workingHour / 60;
		otHr = otHr / 60;
		// basic+DAy
		// metaf: amount / month_day
		double val = 0;

		double otMultiplication = 0;

		if (mstEmpType.getOtApplicable().equalsIgnoreCase("yes")) {
			otMultiplication = Integer.parseInt(mstEmpType.getOtType());

			if (salBasis.equalsIgnoreCase("monthly")) {
				val = (((ammt / totalDays) / workingHour) * otHr) * otMultiplication;
			} // $sal_basis == "monthly"
			else {
				val = ((ammt / workingHour) * otHr) * otMultiplication;
			}
			val = castNumber(val, amount_round);
		}

		return val;
	}

	public double otwageswo(float percentage, String salBasis, int totalDays, float woPresent, float workingDays,
			double ammt, MstEmpType mstEmpType, int amount_round) {

		double perDayGrossSal = (ammt / totalDays) * woPresent;

		// basic+DAy
		// metaf: amount / month_day
		double val = 0;

		if (mstEmpType.getProdIncentiveApp().equalsIgnoreCase("1")) {

			val = perDayGrossSal;
			val = castNumber(val, amount_round);
		}

		return val;
	}

	public double fundwages(float percentage, String salBasis, double ammt, int amount_round) {
		double val = 0;
		float per = percentage / 100;
		val = ammt * per;
		val = castNumber(val, amount_round);
		return val;
	}

	public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
		if ((birthDate != null) && (currentDate != null)) {
			return Period.between(birthDate, currentDate).getYears();
		} else {
			return 0;
		}
	}

	public double castNumber(double val, int amount_round) {
		switch (amount_round) {
		case 1:
			val = Math.round(val);
			break;
		case 2:
			DecimalFormat df = new DecimalFormat("#.00");
			val = Double.parseDouble(df.format(val));
			break;
		case 3:
			val = Math.ceil(val);
			break;
		case 4:
			val = Math.floor(val);
			break;
		}
		return val;
	}

	@RequestMapping(value = { "/insertFinalPayRollAndDeleteFroTemp" }, method = RequestMethod.POST)
	@ResponseBody
	public Info insertFinalPayRollAndDeleteFroTemp(@RequestBody List<EmpSalInfoDaiyInfoTempInfo> salList) {

		Info info = new Info();

		try {

			List<Integer> empIds = new ArrayList<>();
			List<Integer> detailIds = new ArrayList<>();
			int month = salList.get(0).getMonth();
			int year = salList.get(0).getYear();
			// System.out.println(salList);
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < salList.size(); i++) {

				empIds.add(salList.get(i).getEmpId());

				SalaryCalc SalaryCalc = new SalaryCalc();
				SalaryCalc.setEmpId(salList.get(i).getEmpId());
				SalaryCalc.setEmpCode(salList.get(i).getEmpCode());
				SalaryCalc.setEmpType(salList.get(i).getEmpType());
				SalaryCalc.setContractorId(salList.get(i).getContractorId());
				SalaryCalc.setDepartId(salList.get(i).getDepartId());
				SalaryCalc.setDesignationId(salList.get(i).getDesignationId());
				SalaryCalc.setLocationId(salList.get(i).getLocationId());
				SalaryCalc.setCalcMonth(salList.get(i).getMonth());
				SalaryCalc.setCalcYear(salList.get(i).getYear());
				SalaryCalc.setAttSumId(salList.get(i).getAttSumId());
				SalaryCalc.setSalTypeId(salList.get(i).getSalaryTypeId());
				SalaryCalc.setBasicCal(salList.get(i).getBasicCal());
				SalaryCalc.setPerformanceBonus(salList.get(i).getPerformanceBonus());
				SalaryCalc.setOtWages(salList.get(i).getOtWages());
				SalaryCalc.setMiscExpAdd(salList.get(i).getMiscExpAdd());
				SalaryCalc.setBonusCal(salList.get(i).getBonusCal());
				SalaryCalc.setExgretiaCal(salList.get(i).getExgretiaCal());
				SalaryCalc.setDaArreasCal(salList.get(i).getDaArreasCal());
				SalaryCalc.setIncrementArreasCal(salList.get(i).getIncrementArreasCal());
				SalaryCalc.setEpfWages(salList.get(i).getEpfWages());
				SalaryCalc.setEpfWagesEmployer(salList.get(i).getEpfWagesEmployer());
				SalaryCalc.setEsicWagesCal(salList.get(i).getEsicWagesCal());
				SalaryCalc.setGrossSalary(salList.get(i).getGrossSalaryDytemp());
				SalaryCalc.setEpsWages(salList.get(i).getEpsWages());
				SalaryCalc.setEsicWagesDec(salList.get(i).getEsicWagesDec());
				SalaryCalc.setEmployeePf(salList.get(i).getEmployeePf());
				SalaryCalc.setEmployerPf(salList.get(i).getEmployerPf());
				SalaryCalc.setEmployerEps(salList.get(i).getEmployerEps());
				SalaryCalc.setEsic(salList.get(i).getEsic());
				SalaryCalc.setEmployerEsic(salList.get(i).getEmployerEsic());
				SalaryCalc.setEsicStatus(salList.get(i).getEsicStatus());
				SalaryCalc.setPfStatus(salList.get(i).getPfStatus());
				SalaryCalc.setMlwf(salList.get(i).getMlwf());
				SalaryCalc.setTds(salList.get(i).getTds());
				SalaryCalc.setItded(salList.get(i).getItded());
				SalaryCalc.setFund(salList.get(i).getFund());
				SalaryCalc.setTotPfAdminCh(salList.get(i).getTotPfAdminCh());
				SalaryCalc.setTotEdliCh(salList.get(i).getTotEdliCh());
				SalaryCalc.setTotEdliAdminCh(salList.get(i).getTotEdliAdminCh());
				SalaryCalc.setNcpDays(salList.get(i).getNcpDaysDytemp());
				SalaryCalc.setStatus(salList.get(i).getStatusDytemp());
				SalaryCalc.setPtDed(salList.get(i).getPtDed());
				SalaryCalc.setAdvanceDed(salList.get(i).getAdvanceDed());
				SalaryCalc.setLoanDed(salList.get(i).getLoanDed());
				SalaryCalc.setMiscExpDed(salList.get(i).getMiscExpDed());
				SalaryCalc.setMiscExpDedDeduct(salList.get(i).getMiscExpDedDeduct());
				SalaryCalc.setNetSalary(salList.get(i).getNetSalary());
				SalaryCalc.setIsLocked(salList.get(i).getIsLocked());
				SalaryCalc.setLoginName(salList.get(i).getLoginName());
				SalaryCalc.setLoginTime(sf.format(date));
				SalaryCalc.setMlwfApplicable(salList.get(i).getMlwfApplicable());
				SalaryCalc.setPtApplicable(salList.get(i).getPtApplicable());
				SalaryCalc.setPayDed(salList.get(i).getPayDed());
				SalaryCalc.setCommentsForItBonus(salList.get(i).getCommentsForItBonus());
				SalaryCalc.setSocietyContribution(salList.get(i).getSocietyContributionDytemp());
				SalaryCalc.setEmpCategory(salList.get(i).getSalBasis());
				SalaryCalc.setBasicDefault(salList.get(i).getBasicDefault());
				SalaryCalc.setCmpId(salList.get(i).getCmpId());
				SalaryCalc.setAbDeduction(salList.get(i).getAbDeduction());
				SalaryCalc.setProductionInsentive(salList.get(i).getProductionInsentive());
				SalaryCalc.setPresentInsentive(salList.get(i).getPresentInsentive());
				SalaryCalc.setNightAllow(salList.get(i).getNightAllow());
				SalaryCalc.setEpfPercentage(salList.get(i).getEpfPercentage());
				SalaryCalc.setEpsEmployeePercentage(salList.get(i).getEpsEmployeePercentage());
				SalaryCalc.setEpfEmployerPercentage(salList.get(i).getEpfEmployerPercentage());
				SalaryCalc.setEpsEmployerPercentage(salList.get(i).getEpsEmployerPercentage());
				SalaryCalc.setEpsDefault(salList.get(i).getEpsDefault());
				SalaryCalc.setEpmloyerEpfDefault(salList.get(i).getEpmloyerEpfDefault());
				SalaryCalc.setEpmloyerEpfExtra(salList.get(i).getEpmloyerEpfExtra());
				SalaryCalc.setPfAdminChPercentage(salList.get(i).getPfAdminChPercentage());
				SalaryCalc.setEdliPercentage(salList.get(i).getEdliPercentage());
				SalaryCalc.setEdliAdminPercentage(salList.get(i).getEdliAdminPercentage());
				
				SalaryCalc saveres = salaryCalcRepo.save(SalaryCalc);

				List<SalAllownceCal> allowlist = new ArrayList<>();

				for (int j = 0; j < salList.get(i).getGetAllowanceTempList().size(); j++) {

					SalAllownceCal empAllowance = new SalAllownceCal();
					empAllowance.setSalaryCalcId(saveres.getId());
					empAllowance.setAllowanceId(salList.get(i).getGetAllowanceTempList().get(j).getAllowanceId());
					empAllowance.setAllowanceValue(salList.get(i).getGetAllowanceTempList().get(j).getAllowanceValue());
					empAllowance.setEmpId(salList.get(i).getGetAllowanceTempList().get(j).getEmpId());
					empAllowance.setAllowanceValueCal(
							salList.get(i).getGetAllowanceTempList().get(j).getAllowanceValueCal());
					empAllowance.setShortName(salList.get(i).getGetAllowanceTempList().get(j).getShortName());
					empAllowance.setDelStatus(1);
					detailIds.add(salList.get(i).getGetAllowanceTempList().get(j).getEmpSalAllowanceId());
					allowlist.add(empAllowance);

				}

				List<SalAllownceCal> saveAllores = salAllownceCalRepo.saveAll(allowlist);
			}

			int deleteFromTemp = salaryCalcTempRepo.deleteFromTemp(month, year, empIds);
			int deleteFromTempAll = salAllownceTempRepo.deleteFromTempAll(detailIds);

			info.setError(false);
			info.setMsg("success");

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}

		return info;
	}

	@RequestMapping(value = { "/updateIsPaidInPaydeClaimAdvLoan" }, method = RequestMethod.POST)
	@ResponseBody
	public Info updateIsPaidInPaydeClaimAdvLoan(@RequestParam("month") int month, @RequestParam("year") int year,
			@RequestParam("userId") int userId, @RequestParam("empIds") List<Integer> empIds) {

		Info info = new Info();

		try {

			int updateAdv = advanceRepo.updateAdv(month, year, empIds);
			int updateClaim = claimHeaderRepo.updateClaim(month, year, empIds);
			int updatePayde = payDeductionDetailsRepo.updatePayde(month, year, empIds);
			// List<GetPayDedList> getLoanList = getPayDedListRepo.getLoanList(year + "-" +
			// month + "-01", empIds);
			List<LoanMain> getLoanList = loanMainRepo.getLoanList(year + "-" + month + "-01", empIds);
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			List<LoanDetails> loandetaillist = new ArrayList<>();
			for (int i = 0; i < getLoanList.size(); i++) {
				LoanDetails loanDetails = new LoanDetails();
				loanDetails.setLoanMainId(getLoanList.get(i).getId());
				loanDetails.setMonths(month);
				loanDetails.setYears(year);
				loanDetails.setPayType("SP");
				loanDetails.setLoginName(String.valueOf(userId));
				loanDetails.setLoginTime(sf.format(date));

				int amt = 0;

				if (getLoanList.get(i).getCurrentOutstanding() < getLoanList.get(i).getLoanEmiIntrest()) {
					amt = getLoanList.get(i).getCurrentOutstanding();
				} else {
					amt = getLoanList.get(i).getLoanEmiIntrest();
				}

				if (getLoanList.get(i).getSkipId() == 0) {
					loanDetails.setAmountEmi(amt);
					loanDetails.setRemarks("Auto Deducted :Salary Deduction");
					getLoanList.get(i).setCurrentOutstanding(getLoanList.get(i).getCurrentOutstanding() - amt);
					getLoanList.get(i).setCurrentTotpaid(getLoanList.get(i).getCurrentTotpaid() + amt);
				} else {
					getLoanList.get(i).setSkipId(0);
					loanDetails.setAmountEmi(0);
					loanDetails.setSkippAmoount(amt);
					loanDetails.setSkippMonthYear(month + "-" + year);
				}

				if (getLoanList.get(i).getCurrentOutstanding() == 0) {
					getLoanList.get(i).setLoanStatus("Paid");
				}
				loandetaillist.add(loanDetails);
			}

			if (loandetaillist.size() > 0) {
				List<LoanDetails> res = loanDetailsRepo.saveAll(loandetaillist);
				List<LoanMain> updateRes = loanMainRepo.saveAll(getLoanList);
			}

			info.setError(false);
			info.setMsg("success");

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}

		return info;
	}

	@RequestMapping(value = { "/sendMailTest" }, method = RequestMethod.GET)
	@ResponseBody
	public Info sendMailTest() {

		Info info = new Info();

		try {
			EmailUtility.sendEmail("task.management@kppmca.com", "De@8380077223", "akshaykasar72@gmail.com",
					"Test Mail", "", "Mail Testing");
			info.setError(false);
			info.setMsg("success");

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}

		return info;
	}

	@RequestMapping(value = { "/getPayrollGenratedList" }, method = RequestMethod.POST)
	@ResponseBody
	public PayRollDataForProcessing getPayrollGenratedList(@RequestParam("month") int month,
			@RequestParam("year") int year) {

		PayRollDataForProcessing payRollDataForProcessing = new PayRollDataForProcessing();

		try {

			List<GetPayrollGeneratedList> list = getPayrollGeneratedListRepo.getPayrollGenratedList(month, year);
			List<Allowances> allowancelist = allowanceRepo.findBydelStatusAndIsActive(0, 1);
			List<SalAllownceCal> getPayrollAllownceList = salAllownceCalRepo.getPayrollAllownceList(month, year);

			for (int i = 0; i < list.size(); i++) {

				List<SalAllownceCal> assignAllownceList = new ArrayList<>();

				for (int k = 0; k < getPayrollAllownceList.size(); k++) {

					if (list.get(i).getId() == getPayrollAllownceList.get(k).getSalaryCalcId()) {
						assignAllownceList.add(getPayrollAllownceList.get(k));

					}
				}

				list.get(i).setPayrollAllownceList(assignAllownceList);
			}

			payRollDataForProcessing.setPayrollGeneratedList(list);
			payRollDataForProcessing.setAllowancelist(allowancelist);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return payRollDataForProcessing;
	}

	@RequestMapping(value = { "/getPayrollGenratedListByEmpIds" }, method = RequestMethod.POST)
	@ResponseBody
	public PayRollDataForProcessing getPayrollGenratedListByEmpIds(@RequestParam("month") int month,
			@RequestParam("year") int year, @RequestParam("empIds") List<Integer> empIds) {

		PayRollDataForProcessing payRollDataForProcessing = new PayRollDataForProcessing();

		try {

			List<GetPayrollGeneratedList> list = getPayrollGeneratedListRepo.getPayrollGenratedList(month, year,
					empIds);
			List<Allowances> allowancelist = allowanceRepo.findBydelStatusAndIsActive(0, 1);
			List<SalAllownceCal> getPayrollAllownceList = salAllownceCalRepo.getPayrollAllownceList(month, year,
					empIds);
			Setting setting = settingRepo.findByKey("ammount_format_show");
			int amount_round = Integer.parseInt(setting.getValue());
			for (int i = 0; i < list.size(); i++) {

				List<SalAllownceCal> assignAllownceList = new ArrayList<>();

				for (int k = 0; k < getPayrollAllownceList.size(); k++) {

					if (list.get(i).getId() == getPayrollAllownceList.get(k).getSalaryCalcId()) {

						getPayrollAllownceList.get(k).setAllowanceValueCal(
								castNumber(getPayrollAllownceList.get(k).getAllowanceValueCal(), amount_round));
						assignAllownceList.add(getPayrollAllownceList.get(k));

					}
				}
				list.get(i).setBasicCal(list.get(i).getBasicCal());
				list.get(i).setOtWages(castNumber(list.get(i).getOtWages(), amount_round));
				list.get(i).setPerformanceBonus(castNumber(list.get(i).getPerformanceBonus(), amount_round));
				list.get(i).setMiscExpAdd(castNumber(list.get(i).getMiscExpAdd(), amount_round));
				list.get(i).setPtDed(castNumber(list.get(i).getPtDed(), amount_round));
				list.get(i).setEmployeePf((float) castNumber(list.get(i).getEmployeePf(), amount_round));
				list.get(i).setEsic((float) castNumber(list.get(i).getEsic(), amount_round));
				list.get(i).setAdvanceDed(castNumber(list.get(i).getAdvanceDed(), amount_round));
				list.get(i).setTds(castNumber(list.get(i).getTds(), amount_round));
				list.get(i).setSocietyContribution(castNumber(list.get(i).getSocietyContribution(), amount_round));
				list.get(i).setLoanDed(castNumber(list.get(i).getLoanDed(), amount_round));
				list.get(i).setNetSalary(castNumber(list.get(i).getNetSalary(), amount_round));
				long sal = (long) list.get(i).getNetSalary();
				list.get(i).setMoneyInword(EnglishNumberToWords.convert(sal));
				list.get(i).setPayrollAllownceList(assignAllownceList);
			}

			payRollDataForProcessing.setPayrollGeneratedList(list);
			payRollDataForProcessing.setAllowancelist(allowancelist);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return payRollDataForProcessing;
	}

}
