package com.ats.hrmgt.controller;

import java.util.ArrayList;
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
	public Info calculateSalary(@RequestParam("month") int month, @RequestParam("year") int year,
			@RequestParam("empIds") List<Integer> empIds) {

		Info info = new Info();

		try {

			List<EmpSalInfoDaiyInfoTempInfo> getSalaryTempList = empSalInfoDaiyInfoTempInfoRepo.getSalaryTempList(month,
					year, empIds);
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
				getSalaryTempList.get(i).setGetAllowanceTempList(getAllowanceTempList);
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
								ammt = getSalaryTempList.get(i).getSocietyContribution();
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
							float calculatedValue = calculateFdata(salaryTermList.get(j).getPercentage(),
									getSalaryTempList.get(i).getSalBasis(),
									getSalaryTempList.get(i).getTotalDaysInmonth(),
									getSalaryTempList.get(i).getPayableDays(),
									getSalaryTempList.get(i).getWorkingDays(), ammt,
									getSalaryTempList.get(i).getPresentDays());
							getSalaryTempList.get(i).setBasicCal(calculatedValue);
							salaryTermList.get(j).setValue(calculatedValue);

							break;
						case "A":
							float tempVal = calculateAllowancedata(salaryTermList.get(j).getPercentage(),
									getSalaryTempList.get(i).getSalBasis(),
									getSalaryTempList.get(i).getTotalDaysInmonth(),
									getSalaryTempList.get(i).getPayableDays(),
									getSalaryTempList.get(i).getWorkingDays(), ammt,
									getSalaryTempList.get(i).getPresentDays());
							getSalaryTempList.get(i).getGetAllowanceTempList().get(index).setAllowanceValueCal(tempVal);
							salaryTermList.get(j).setValue(tempVal);
							break;
						case "S":
							float tempValNew = 0;
							tempVal = calculatePdata(salaryTermList.get(j), salaryTermList, getSalaryTempList.get(i));

							if (getSalaryTempList.get(i).getPtApplicable().equalsIgnoreCase("yes")) {

								for (int k = 0; k < slabMasterList.size(); k++) {

									if (slabMasterList.get(k).getSalTermId() == salaryTermList.get(j).getSalTermId()) {

										if (tempVal >= slabMasterList.get(k).getMinVal()
												&& tempVal <= slabMasterList.get(k).getMaxVal()) {

											if (getSalaryTempList.get(i).getMonth() == 2
													&& slabMasterList.get(k).getAmount() == 200) {
												tempValNew = 300;
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
									getSalaryTempList.get(i).getPresentDays());
							getSalaryTempList.get(i).setBasicDefault(tempVal);
							salaryTermList.get(j).setValue(tempVal);
							break;
						case "P":
							float temp = calculatePdata(salaryTermList.get(j), salaryTermList,
									getSalaryTempList.get(i));
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
									ammt);
							getSalaryTempList.get(i).setOtWages(tempVal);
							salaryTermList.get(j).setValue(tempVal);
							break;
						case "OTFD":

							break;
						case "FD":
							tempVal = fundwages(salaryTermList.get(j).getPercentage(),
									getSalaryTempList.get(i).getSalBasis(), ammt);
							salaryTermList.get(j).setValue(tempVal);
							break;
						default:

							break;

						}

					}

				}

			}

			// System.out.println(getSalaryTempList);

			info.setError(false);
			info.setMsg("success");
		} catch (Exception e) {
			info.setError(true);
			info.setMsg("failed");
			e.printStackTrace();
		}

		return info;
	}

	public float calculateFdata(float percentage, String salBasis, int totalDaysInMonth, float payableDays,
			float workingDays, double ammt, float presentDays) {

		float val = 0;

		if (percentage == 1) {
			float totalPayableDaysTemp = Math.min(payableDays, totalDaysInMonth);

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
			// val = castNumber(val);
		return val;
	}

	public float calculateAllowancedata(float percentage, String salBasis, int totalDays, float totalPayableDays,
			float workingDays, double amount, float presentDays) {
		float val = 0;

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

		return val;
	}

	public float calculateXdata(float percentage, String salBasis, int totalDays, float totalPayableDays,
			float workingDays, double amount, float presentDays) {

		float val = 0;
		if (salBasis.equalsIgnoreCase("monthly")) {
			val = (float) amount;
		} // $sal_basis == "monthly"
		else {
			// $val = ($amount / $working_days ) * $total_payable_days;
			val = (float) (amount / totalDays);
		}
		return val;

	}

	public float calculatePdata(SalaryTerm salaryTerm, List<SalaryTerm> salaryTermList,
			EmpSalInfoDaiyInfoTempInfo empSalInfoDaiyInfoTempInfo) {

		String formula = salaryTerm.getFormula();
		String[] plus = formula.split("\\+");
		String[] minus = formula.split("\\-");

		float value = 0;

		if (plus.length > 1) {

			for (int i = 0; i < plus.length; i++) {
				for (int j = 0; j < salaryTermList.size(); j++) {
					if (Integer.parseInt(plus[i]) == salaryTermList.get(j).getSalTermId()) {
						value = (float) (value + salaryTermList.get(j).getValue());
						break;
					}
				}

			}
		} else if (minus.length > 1) {
			for (int i = 0; i < minus.length; i++) {
				for (int j = 0; j < salaryTermList.size(); j++) {
					if (Integer.parseInt(minus[i]) == salaryTermList.get(j).getSalTermId() && i == 0) {
						value = (float) (salaryTermList.get(j).getValue());
						break;
					} else {
						value = (float) (value - salaryTermList.get(j).getValue());
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

		return value;
	}

	public float otwages(float percentage, String salBasis, int totalDays, float totalPayableDays, float workingDays,
			float workingHour, float otHr, double ammt) {

		workingHour = workingHour / 60;
		otHr = otHr / 60;
		// basic+DAy
		// metaf: amount / month_day
		float val = 0;

		if (salBasis.equalsIgnoreCase("monthly")) {
			val = (float) ((ammt / workingDays) / (workingHour * (otHr * percentage)));
		} // $sal_basis == "monthly"
		else {
			val = (float) ((ammt / workingHour) * (otHr * percentage));
		}
		// val = castNumber(val);
		return val;
	}

	public float fundwages(float percentage, String salBasis, double ammt) {
		float val = 0;
		float per = percentage / 100;
		val = (float) (ammt * per);
		// val = castNumber(val);
		return val;
	}
	/*
	 * public float castNumber(float val) { switch
	 * ($this->sallary_term_cal_amount_setting) { case 1: $number = round($number);
	 * break; case 2: $number = number_format($number, 2, '.', ''); break; case 3:
	 * $number = ceil($number); break; case 4: $number = floor($number); break; }
	 * //$this->sallary_term_cal_amount_setting return $number; }
	 */

}
