package com.ats.hrmgt.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

 

@RestController
public class ClaimReportRestController {

	/*
	 * @Autowired EmployeeInfoRepository employeeInfoRepository;
	 * 
	 * @Autowired ClaimRepository claimRepository;
	 * 
	 * @Autowired EmployeeWithClaimIdRepository employeeWithClaimIdRepository;
	 * 
	 * @Autowired GetProjectHeaderRepo getProjectHeaderRepo;
	 * 
	 * @Autowired ClientWiseClaimReportRepository clientWiseClaimReportRepository;
	 * 
	 * @RequestMapping(value = { "employeeTypeWiseClaimReport" }, method =
	 * RequestMethod.POST) public @ResponseBody List<EmployeeWithClaim>
	 * employeeTypeWiseClaimReport(
	 * 
	 * @RequestParam("locationId") List<Integer>
	 * locationId, @RequestParam("fromDate") String fromDate,
	 * 
	 * @RequestParam("toDate") String toDate) {
	 * 
	 * List<EmployeeWithClaim> reportList = new ArrayList<>();
	 * 
	 * try {
	 * 
	 * List<ClaimType> claimTypelist =
	 * claimRepository.findByDelStatusAndCompanyIdOrderByCompanyIdDesc(1, 1);
	 * List<EmployeeInfo> employeeInfo =
	 * employeeInfoRepository.getEmpInfoByLocId(locationId, 1);
	 * 
	 * List<EmployeeWithClaimId> queryList =
	 * employeeWithClaimIdRepository.employeeTypeWiseClaimReport(fromDate, toDate);
	 * 
	 * for (int i = 0; i < employeeInfo.size(); i++) {
	 * 
	 * EmployeeWithClaim employeeWithClaim = new EmployeeWithClaim();
	 * employeeWithClaim.setEmpId(employeeInfo.get(i).getEmpId()); employeeWithClaim
	 * .setEmpName(employeeInfo.get(i).getEmpFname() + " " +
	 * employeeInfo.get(i).getEmpSname());
	 * 
	 * List<TypeListWithAmt> list = new ArrayList<>();
	 * 
	 * for (int j = 0; j < claimTypelist.size(); j++) {
	 * 
	 * int flag = 0;
	 * 
	 * for (int k = 0; k < queryList.size(); k++) {
	 * 
	 * if (queryList.get(k).getEmpId() == employeeInfo.get(i).getEmpId() &&
	 * queryList.get(k).getClaimTypeId() == claimTypelist.get(j).getClaimTypeId()) {
	 * 
	 * TypeListWithAmt typeListWithAmt = new TypeListWithAmt();
	 * typeListWithAmt.setTypeId(claimTypelist.get(j).getClaimTypeId());
	 * typeListWithAmt.setTypeName(claimTypelist.get(j).getClaimTypeTitle());
	 * typeListWithAmt.setAmt(queryList.get(k).getClaimAmount());
	 * list.add(typeListWithAmt); flag = 1; break; }
	 * 
	 * }
	 * 
	 * if (flag == 0) { TypeListWithAmt typeListWithAmt = new TypeListWithAmt();
	 * typeListWithAmt.setTypeId(claimTypelist.get(j).getClaimTypeId());
	 * typeListWithAmt.setTypeName(claimTypelist.get(j).getClaimTypeTitle());
	 * typeListWithAmt.setAmt(0); list.add(typeListWithAmt); }
	 * 
	 * }
	 * 
	 * employeeWithClaim.setList(list); reportList.add(employeeWithClaim); }
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return reportList;
	 * 
	 * }
	 * 
	 * @RequestMapping(value = { "employeeProjectWiseClaimReport" }, method =
	 * RequestMethod.POST) public @ResponseBody List<EmployeeWithClaim>
	 * employeeProjectWiseClaimReport(
	 * 
	 * @RequestParam("locationId") List<Integer>
	 * locationId, @RequestParam("fromDate") String fromDate,
	 * 
	 * @RequestParam("toDate") String toDate) {
	 * 
	 * List<EmployeeWithClaim> reportList = new ArrayList<>();
	 * 
	 * try {
	 * 
	 * List<GetProjectHeader> projectlist =
	 * getProjectHeaderRepo.getProListByCompanyId(1); List<EmployeeInfo>
	 * employeeInfo = employeeInfoRepository.getEmpInfoByLocId(locationId, 1);
	 * 
	 * List<EmployeeWithClaimId> queryList =
	 * employeeWithClaimIdRepository.employeeProjectWiseClaimReport(fromDate,
	 * toDate);
	 * 
	 * for (int i = 0; i < employeeInfo.size(); i++) {
	 * 
	 * EmployeeWithClaim employeeWithClaim = new EmployeeWithClaim();
	 * employeeWithClaim.setEmpId(employeeInfo.get(i).getEmpId()); employeeWithClaim
	 * .setEmpName(employeeInfo.get(i).getEmpFname() + " " +
	 * employeeInfo.get(i).getEmpSname());
	 * 
	 * List<TypeListWithAmt> list = new ArrayList<>();
	 * 
	 * for (int j = 0; j < projectlist.size(); j++) {
	 * 
	 * int flag = 0;
	 * 
	 * for (int k = 0; k < queryList.size(); k++) {
	 * 
	 * if (queryList.get(k).getEmpId() == employeeInfo.get(i).getEmpId() &&
	 * queryList.get(k).getClaimTypeId() == projectlist.get(j).getProjectId()) {
	 * 
	 * TypeListWithAmt typeListWithAmt = new TypeListWithAmt();
	 * typeListWithAmt.setTypeId(projectlist.get(j).getProjectId());
	 * typeListWithAmt.setTypeName(projectlist.get(j).getProjectTitle());
	 * typeListWithAmt.setAmt(queryList.get(k).getClaimAmount());
	 * list.add(typeListWithAmt); flag = 1; break; }
	 * 
	 * }
	 * 
	 * if (flag == 0) { TypeListWithAmt typeListWithAmt = new TypeListWithAmt();
	 * typeListWithAmt.setTypeId(projectlist.get(j).getProjectId());
	 * typeListWithAmt.setTypeName(projectlist.get(j).getProjectTitle());
	 * typeListWithAmt.setAmt(0); list.add(typeListWithAmt); }
	 * 
	 * }
	 * 
	 * employeeWithClaim.setList(list); reportList.add(employeeWithClaim); }
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return reportList;
	 * 
	 * }
	 * 
	 * @RequestMapping(value = { "clientWiseClaimReport" }, method =
	 * RequestMethod.POST) public @ResponseBody List<ClientWiseClaimReport>
	 * clientWiseClaimReport( @RequestParam("fromDate") String fromDate,
	 * 
	 * @RequestParam("toDate") String toDate) {
	 * 
	 * List<ClientWiseClaimReport> reportList = new ArrayList<>();
	 * 
	 * try {
	 * 
	 * reportList = clientWiseClaimReportRepository.clientWiseClaimReport(fromDate,
	 * toDate);
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return reportList;
	 * 
	 * }
	 */

}
