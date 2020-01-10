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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.Allowances;
import com.ats.hrmgt.model.EmpAllowanceList;
import com.ats.hrmgt.model.EmpSalAllowance;
import com.ats.hrmgt.model.EmpSalInfoDaiyInfoTempInfo;
import com.ats.hrmgt.model.EmpSalaryInfo;
import com.ats.hrmgt.model.EmpSalaryInfoForPayroll;
import com.ats.hrmgt.model.GetAdvanceList;
import com.ats.hrmgt.model.GetClaimList;
import com.ats.hrmgt.model.GetPayDedList;
import com.ats.hrmgt.model.GetSalDynamicTempRecord;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.MstEmpType;
import com.ats.hrmgt.model.PayRollDataForProcessing;
import com.ats.hrmgt.model.SalAllownceTemp;
import com.ats.hrmgt.model.SalaryCalcTemp;
import com.ats.hrmgt.model.SalaryTerm;
import com.ats.hrmgt.model.SalaryTypesMaster;
import com.ats.hrmgt.model.SampleClass;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.model.SlabMaster;
import com.ats.hrmgt.repository.AllowancesRepo;
import com.ats.hrmgt.repository.EmpSalAllowanceRepo;
import com.ats.hrmgt.repository.EmpSalInfoDaiyInfoTempInfoRepo;
import com.ats.hrmgt.repository.EmpSalaryInfoForPayrollRepository;
import com.ats.hrmgt.repository.EmpSalaryInfoRepo;
import com.ats.hrmgt.repository.GetAdvanceListRepo;
import com.ats.hrmgt.repository.GetClaimListRepo;
import com.ats.hrmgt.repository.GetPayDedListRepo;
import com.ats.hrmgt.repository.GetSalDynamicTempRecordRepository;
import com.ats.hrmgt.repository.MstEmpTypeRepository;
import com.ats.hrmgt.repository.SalAllownceTempRepo;
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
			@RequestParam("empIds") List<Integer> empIds) {

		Info info = new Info();

		try {
			List<EmpSalaryInfoForPayroll> list = empSalaryInfoForPayrollRepository
					.getEmployeeListWithEmpSalEnfoForPayRollForTempInsert(month, year, empIds);
			List<Allowances> allowancelist = allowanceRepo.findBydelStatusAndIsActive(0, 1);
			List<EmpSalAllowance> empAllowanceList = empSalAllowanceRepo.findByDelStatusAndEmpId(1, empIds);

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
			@RequestParam("year") int year, @RequestParam("empIds") List<Integer> empIds) {

		Info info = new Info();
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
			double eps_percentage = 0;
			double ceiling_limit = 0;
			double eps_age_limit = 0;
			double esic_limit = 0;
			double tot_pf_admin_ch_percentage = 0;
			double tot_edli_ch_percentage = 0;
			double tot_edli_admin_ch_percentage = 0;

			int febmonthptamount_condtioncheck = 0;
			double febmonthptamount = 0;
			int amount_round = 0;

			for (int k = 0; k < settingList.size(); k++) {
				if (settingList.get(k).getKey().equalsIgnoreCase("ceiling_limit_eps_wages")) {
					cealing_limit_eps_wages = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("eps_percentage")) {
					eps_percentage = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("epf_percentage")) {
					epf_percentage = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("ceiling_limit")) {
					ceiling_limit = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("eps_age_limit")) {
					eps_age_limit = Float.parseFloat(settingList.get(k).getValue());
				} else if (settingList.get(k).getKey().equalsIgnoreCase("esic_limit")) {
					esic_limit = Float.parseFloat(settingList.get(k).getValue());
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
				}
			}

			MstEmpType mstEmpType = new MstEmpType();
			SalaryTypesMaster salaryType = new SalaryTypesMaster();

			for (int i = 0; i < getSalaryTempList.size(); i++) {

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

						if (!salaryTermList.get(j).getFromColumn().equals("")) {
							if (salaryTermList.get(j).getFromColumn().equals("basic")) {
								ammt = getSalaryTempList.get(i).getBasic();
							} else if (salaryTermList.get(j).getFromColumn().equals("performance_bouns_cal")) {
								ammt = getSalaryTempList.get(i).getPerformanceBonus();
							} else if (salaryTermList.get(j).getFromColumn().equals("society_contribution")) {
								ammt = getSalaryTempList.get(i).getSocietyContribution();
							} else {

								for (int k = 0; k < getSalaryTempList.get(i).getGetAllowanceTempList().size(); k++) {

									if (getSalaryTempList.get(i).getGetAllowanceTempList().get(k).getShortName()
											.equals(salaryTermList.get(j).getFromColumn())) {
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
							double tempVal = calculateAllowancedata(salaryTermList.get(j).getPercentage(),
									getSalaryTempList.get(i).getSalBasis(),
									getSalaryTempList.get(i).getTotalDaysInmonth(),
									getSalaryTempList.get(i).getPayableDays(),
									getSalaryTempList.get(i).getWorkingDays(), ammt,
									getSalaryTempList.get(i).getPresentDays(), amount_round);
							getSalaryTempList.get(i).getGetAllowanceTempList().get(index).setAllowanceValueCal(tempVal);
							salaryTermList.get(j).setValue(tempVal);
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
							tempVal = calculateXdata(salaryTermList.get(j).getPercentage(),
									getSalaryTempList.get(i).getSalBasis(),
									getSalaryTempList.get(i).getTotalDaysInmonth(),
									getSalaryTempList.get(i).getPayableDays(),
									getSalaryTempList.get(i).getWorkingDays(), ammt,
									getSalaryTempList.get(i).getPresentDays(), amount_round);
							getSalaryTempList.get(i).setBasicDefault(tempVal);
							salaryTermList.get(j).setValue(tempVal);
							break;
						case "P":
							double temp = calculatePdata(salaryTermList.get(j), salaryTermList,
									getSalaryTempList.get(i), amount_round);
							if (salaryTermList.get(j).getFieldName().equals("gross_salary")) {
								getSalaryTempList.get(i).setGrossSalaryDytemp(temp);
							} else if (salaryTermList.get(j).getFieldName().equals("epf_wages")) {
								getSalaryTempList.get(i).setEpfWages(temp);
							} else if (salaryTermList.get(j).getFieldName().equals("esic_wages_cal")) {
								getSalaryTempList.get(i).setEsicWagesCal(temp);
							} else if (salaryTermList.get(j).getFieldName().equals("eps_wages_cal")) {
								getSalaryTempList.get(i).setEpsWages(temp);
							} else if (salaryTermList.get(j).getFieldName().equals("esic_wages_dec_cal")) {
								getSalaryTempList.get(i).setEsicWagesDec(temp);
							}
							salaryTermList.get(j).setValue(temp);
							break;
						case "Y":
							// tempVal = castNumber(ammt);

							if (salaryTermList.get(j).getFieldName().equals("performance_bouns_cal")) {
								getSalaryTempList.get(i).setPerformanceBonus(ammt);
							} else if (salaryTermList.get(j).getFieldName().equals("basic")) {
								getSalaryTempList.get(i).setBasicDefault(ammt);
							} else if (salaryTermList.get(j).getFieldName().equals("society_contribution")) {
								getSalaryTempList.get(i).setSocietyContributionDytemp(ammt);
							}
							salaryTermList.get(j).setValue(ammt);
							break;
						case "C1":

							break;
						case "OT":
							tempVal = otwages(salaryTermList.get(j).getPercentage(),
									getSalaryTempList.get(i).getSalBasis(),
									getSalaryTempList.get(i).getTotalDaysInmonth(),
									getSalaryTempList.get(i).getPayableDays(),
									getSalaryTempList.get(i).getWorkingDays(),
									getSalaryTempList.get(i).getTotworkingHrs(), getSalaryTempList.get(i).getTotOthr(),
									ammt, amount_round);
							getSalaryTempList.get(i).setOtWages(tempVal);
							salaryTermList.get(j).setValue(tempVal);
							break;
						case "OTFD":

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
					if (getSalaryTempList.get(i).getCeilingLimitEmpApplicable().equalsIgnoreCase("yes")
							&& getSalaryTempList.get(i).getEpfWages() > ceiling_limit) {
						epf_wages_employee = ceiling_limit;
					} else {
						epf_wages_employee = getSalaryTempList.get(i).getEpfWages();
					}
				} catch (Exception e) {
					// e.printStackTrace();
				}

				try {
					if (getSalaryTempList.get(i).getCeilingLimitEmployerApplicable().equalsIgnoreCase("yes")
							&& getSalaryTempList.get(i).getEpfWages() > ceiling_limit) {
						epf_wages_employeR = ceiling_limit;

					} else {
						epf_wages_employeR = getSalaryTempList.get(i).getEpfWages();
					}
				} catch (Exception e) {
					// e.printStackTrace();
				}
				getSalaryTempList.get(i).setEpfWagesEmployer(epf_wages_employeR);

				try {
					if (getSalaryTempList.get(i).getEpsWages() > cealing_limit_eps_wages) {
						eps_Cal = cealing_limit_eps_wages;
					} // $this->emp_sal_terms_col[$emp_id]['eps_wages'] > $sealing_limit_eps_wages
					else {
						eps_Cal = getSalaryTempList.get(i).getEpsWages();
					}
				} catch (Exception e) {
					// e.printStackTrace();
				}

				try {
					// System.out.println("age " + getSalaryTempList.get(i).getPfApplicable());
					if (getSalaryTempList.get(i).getPfApplicable().equalsIgnoreCase("yes")) {

						getSalaryTempList.get(i).setPfStatus(1);
						// ok
						// echo $value->pf_type;
						try {
							if (getSalaryTempList.get(i).getPfType().equalsIgnoreCase("statutory")) {
								getSalaryTempList.get(i)
										.setEmployeePf(castNumber((epf_wages_employee * epf_percentage), amount_round));

							} // strtolower($value->pf_type) == 'statutory'
							else if (getSalaryTempList.get(i).getPfType().equalsIgnoreCase("voluntary")) {
								getSalaryTempList.get(i).setEmployeePf(castNumber(
										(epf_wages_employee * getSalaryTempList.get(i).getPfEmpPer()), amount_round));
							} // strtolower($value->pf_type) == 'voluntary'
								// ok
								// eps_age_limit
								// $age = $this->mpayroll->calculateAge($value->dob);

						} catch (Exception e) {
							// e.printStackTrace();
						}

						String[] dob = getSalaryTempList.get(i).getDob().split("-");
						// System.out.println("dob[2]" + dob[2] +"dob[1]" + dob[1]+"dob[0]" + dob[0] );
						LocalDate birthDate = LocalDate.of(Integer.parseInt(dob[0]), Integer.parseInt(dob[1]),
								Integer.parseInt(dob[2]));
						int age = calculateAge(birthDate, LocalDate.of(Integer.parseInt(dates[0]),
								Integer.parseInt(dates[1]), Integer.parseInt(dates[2])));

						// System.out.println("age " + age);
						// int age = 60;
						if (age <= eps_age_limit) {
							// employer_eps
							double employer_eps = eps_Cal * eps_percentage;
							getSalaryTempList.get(i).setEmployerEps(castNumber(employer_eps, amount_round));
							getSalaryTempList.get(i).setEpsWages(castNumber(eps_Cal, amount_round));
							/*
							 * $this->emp_sal_terms_col[$emp_id]['employer_eps'] = round($employer_eps);
							 * $this->emp_sal_terms_col[$emp_id]['eps_wages'] = $eps_Cal;
							 */
							// ok
						} // $age <= $eps_age_limit
						else {
							getSalaryTempList.get(i).setEmployerEps(0);
							getSalaryTempList.get(i).setEpsWages(0);

						}
						// employer_pf calculation
						getSalaryTempList.get(i).setEmployerPf(castNumber(
								((epf_wages_employeR * epf_percentage) - getSalaryTempList.get(i).getEmployerEps()),
								amount_round));

					} // strtolower($value->pf_applicable) == 'yes'
					else // $emp->pf_applicable)=='no'
					{
						getSalaryTempList.get(i).setEmployerPf(0);
						getSalaryTempList.get(i).setPfStatus(0);
						getSalaryTempList.get(i).setEmployerEps(0);
						getSalaryTempList.get(i).setEmployeePf(0);
						getSalaryTempList.get(i).setEpfWages(0);
						getSalaryTempList.get(i).setEpsWages(0);
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
						getSalaryTempList.get(i).setPfStatus(0);
						getSalaryTempList.get(i).setEsicStatus(0);
					} // strtolower($value->esic_applicable) == 'no'
					else {
						getSalaryTempList.get(i).setPfStatus(1);
						getSalaryTempList.get(i).setEsicStatus(1);

						double employee_esic_percentage = getSalaryTempList.get(i).getEmployeeEsicPercentage();
						double employer_esic_percentage = getSalaryTempList.get(i).getEmployerEsicPercentage();

						// we have not add any code to avoid esci deduction. as it mendatory to deduct
						// esic till some month, but kishore has changed the code to cut the esic
						if (getSalaryTempList.get(i).getEsicWagesDec() >= esic_limit) {
							getSalaryTempList.get(i).setEmployerEsic(0);
							getSalaryTempList.get(i).setEsic(0);
						} else {

							getSalaryTempList.get(i)
									.setEmployerEsic(castNumber(
											(getSalaryTempList.get(i).getEsicWagesCal() * employee_esic_percentage),
											amount_round));
							getSalaryTempList.get(i).setEsic(castNumber(
									(getSalaryTempList.get(i).getEsicWagesCal() * employer_esic_percentage), 1));
						}

						// $records['esic_wages_dec'] = 0;
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
				getSalaryTempList.get(i).setStatusDytemp(1);
				getSalaryTempList.get(i).setNetSalary(castNumber((getSalaryTempList.get(i).getGrossSalaryDytemp()
						+ getSalaryTempList.get(i).getPerformanceBonus() + getSalaryTempList.get(i).getMiscExpAdd())
						- (getSalaryTempList.get(i).getEsic() + getSalaryTempList.get(i).getTds()
								+ getSalaryTempList.get(i).getItded() + getSalaryTempList.get(i).getPtDed()
								+ getSalaryTempList.get(i).getAdvanceDed() + getSalaryTempList.get(i).getLoanDed()
								+ getSalaryTempList.get(i).getEmployeePf() + getSalaryTempList.get(i).getPayDed()
								+ getSalaryTempList.get(i).getSocietyContributionDytemp()),
						amount_round));
			}

			// System.out.println(getSalaryTempList);

			info.setError(false);
			info.setMsg("success");
		} catch (Exception e) {
			info.setError(true);
			info.setMsg("failed");
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
				val = (float) ((ammt / totalDaysInMonth) * totalPayableDaysTemp);
			} // $sal_basis == "monthly"
			else {
				// $val = ($amount / $working_days ) * $total_payable_days;
				val = (float) ((ammt) * totalPayableDaysTemp);
			}
		} // $percentage == 1
		else if (percentage == 2) {
			float totalPayableDaysTemp = Math.min(payableDays, totalDaysInMonth);
			if (salBasis.equalsIgnoreCase("monthly")) {
				val = (float) ((ammt / totalDaysInMonth) * totalPayableDaysTemp);
			} // $sal_basis == "monthly"
			else {
				// $val = ($amount / $working_days ) * $total_payable_days;
				val = (float) ((ammt / workingDays) * totalPayableDaysTemp);
			}
		} // $percentage == 2
		val = castNumber(val, amount_round);
		return val;
	}

	public double calculateAllowancedata(float percentage, String salBasis, int totalDays, float totalPayableDays,
			float workingDays, double amount, float presentDays, int amount_round) {

		double val = 0;
		if (percentage == 0) {
			// val = castNumber(amount);
		} // $percentage == "0"
		else if (percentage == 1) {
			if (salBasis.equalsIgnoreCase("monthly")) {
				val = (float) ((amount / totalDays) * totalPayableDays);
			} // $sal_basis == "monthly"
			else {
				val = (float) (amount * totalPayableDays);
			}

			// val = castNumber(val);
		} // $percentage == 1
		else if (percentage == 2) {
			if (salBasis.equalsIgnoreCase("monthly")) {
				val = (float) ((amount / totalDays) * presentDays);
			} // $sal_basis == "monthly"
			else {
				// $val = ($amount / $working_days ) * $total_payable_days;
				val = (float) (amount * totalPayableDays);
			}
			// val = castNumber(val);
		} // $percentage == 2
		else if (percentage == 3) {
			float totalPayableDaysTemp = Math.min(totalPayableDays, totalDays);
			if (salBasis.equalsIgnoreCase("monthly")) {
				val = (float) ((amount / totalDays) * totalPayableDaysTemp);
			} // $sal_basis == "monthly"
			else {
				// $val = ($amount / $working_days ) * $total_payable_days;
				val = (float) ((amount) * totalPayableDaysTemp);
			}
			// val = castNumber(val);
		} // $percentage == 3
		val = castNumber(amount, amount_round);
		return val;
	}

	public double calculateXdata(float percentage, String salBasis, int totalDays, float totalPayableDays,
			float workingDays, double amount, float presentDays, int amount_round) {

		double val = 0;
		if (salBasis.equalsIgnoreCase("monthly")) {
			val = (float) amount;
		} // $sal_basis == "monthly"
		else {
			// $val = ($amount / $working_days ) * $total_payable_days;
			val = (float) (amount / totalDays);
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
						value = (float) (value + salaryTermList.get(j).getValue());
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
							value = (float) (salaryTermList.get(j).getValue());
						} else {
							value = (float) (value - salaryTermList.get(j).getValue());
						}
						break;
					}
				}

			}
		} else {
			for (int j = 0; j < salaryTermList.size(); j++) {
				if (Integer.parseInt(minus[0]) == salaryTermList.get(j).getSalTermId()) {
					value = (float) (salaryTermList.get(j).getValue());
					break;
				}
			}
		}
		value = castNumber(value, amount_round);
		return value;
	}

	public double otwages(float percentage, String salBasis, int totalDays, float totalPayableDays, float workingDays,
			float workingHour, float otHr, double ammt, int amount_round) {

		workingHour = workingHour / 60;
		otHr = otHr / 60;
		// basic+DAy
		// metaf: amount / month_day
		double val = 0;

		if (salBasis.equalsIgnoreCase("monthly")) {
			val = (float) ((ammt / workingDays) / (workingHour * (otHr * percentage)));
		} // $sal_basis == "monthly"
		else {
			val = (float) ((ammt / workingHour) * (otHr * percentage));
		}
		val = castNumber(val, amount_round);
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

}
