package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.advance.repository.AdvanceRepo;
import com.ats.hrmgt.advance.repository.GetAdvanceRepo;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.LeaveType;
import com.ats.hrmgt.model.advance.Advance;
import com.ats.hrmgt.model.advance.GetAdvance;

@RestController
public class AdvanceApiController {

	@Autowired
	AdvanceRepo advanceRepo;

	@RequestMapping(value = { "/saveMstEmpAdvance" }, method = RequestMethod.POST)
	public @ResponseBody Advance saveMstEmpAdvance(@RequestBody Advance leaveType) {

		Advance save = new Advance();
		try {

			save = advanceRepo.saveAndFlush(leaveType);
			if (save == null) {

				save = new Advance();

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@Autowired
	GetAdvanceRepo getAdvanceRepo;

	@RequestMapping(value = { "/getPendingAdvance" }, method = RequestMethod.POST)
	public @ResponseBody List<GetAdvance> getPendingAdvance(@RequestParam("companyId") int companyId) {

		List<GetAdvance> list = new ArrayList<GetAdvance>();
		try {

			list = getAdvanceRepo.getSubModule(companyId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getAdvanceHistory" }, method = RequestMethod.POST)
	public @ResponseBody List<GetAdvance> getAdvanceHistory(@RequestParam("empId") int empId,
			@RequestParam("calYrId") String calYrId, @RequestParam("companyId") int companyId ) {

		List<GetAdvance> list = new ArrayList<GetAdvance>();
		try {

			if (empId == 0 && !calYrId.equals("0")) {
				list = getAdvanceRepo.getSpecYearAdv(companyId, calYrId);

			} else if (empId != 0 && calYrId.equals("0")) {
				list = getAdvanceRepo.getSpecEmpAdv(companyId, empId);

			} else if (empId != 0 && !calYrId.equals("0")) {
				list = getAdvanceRepo.getSpecAdv(companyId, empId, calYrId);

			} else {
				list = getAdvanceRepo.getAllAdv(companyId);
			}

 
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getAdvanceById" }, method = RequestMethod.POST)
	public @ResponseBody Advance getAdvanceById(@RequestParam("advId") int advId) {

		Advance list = new Advance();
		try {

			list = advanceRepo.findById(advId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/deleteAdvance" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteLMstEmpType(@RequestParam("advId") int advId) {

		Info info = new Info();

		try {

			int delete = advanceRepo.deleteAdvance(advId);

			if (delete > 0) {
				info.setError(false);
				info.setMsg("deleted");
			} else {
				info.setError(true);
				info.setMsg("failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("failed");
		}

		return info;

	}

	
	
	@RequestMapping(value = { "/updateSkipAdvance" }, method = RequestMethod.POST)
	public @ResponseBody Info updateSkipAdvance(@RequestParam("dateTimeUpdate") String dateTimeUpdate,@RequestParam("userId") int userId,@RequestParam("skipStr") String skipStr,@RequestParam("count") int count,@RequestParam("advId") int advId) {

		Info info = new Info();

		try {

			int delete = advanceRepo.skipAdvance(advId,userId,count,skipStr,dateTimeUpdate);

			if (delete > 0) {
				info.setError(false);
				info.setMsg("deleted");
			} else {
				info.setError(true);
				info.setMsg("failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("failed");
		}

		return info;

	}

	@RequestMapping(value = { "/checkCustPhone" }, method = RequestMethod.POST)
	public @ResponseBody Info checkEmployeeEmail(@RequestParam String voucherNo) {

		Info info = new Info();
		List<Advance> emp = new ArrayList<Advance>();
		try {

			emp = advanceRepo.findByVoucherNoAndDelStatus(voucherNo, 1);
			if (emp.size() > 0) {
				info.setError(true);
			} else {
				info.setError(false);

			}

		} catch (Exception e) {
			System.err.println("Exce in checkEmployeeEmail  " + e.getMessage());
		}

		return info;

	}

}