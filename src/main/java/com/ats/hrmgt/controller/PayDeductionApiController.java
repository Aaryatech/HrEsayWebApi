package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.PayDeduction;
import com.ats.hrmgt.model.PayDeductionDetails;
import com.ats.hrmgt.repository.PayDeductionDetailsRepo;
import com.ats.hrmgt.repository.PayDeductionRepo;

@RestController
public class PayDeductionApiController {

	@Autowired
	PayDeductionRepo payDeductRepo;
	
	@Autowired PayDeductionDetailsRepo detailRepo;

	@RequestMapping(value = { "/getAllPayDeduction" }, method = RequestMethod.GET)
	public List<PayDeduction> getAllPayDecuvtion() {
		List<PayDeduction> list = new ArrayList<PayDeduction>();
		try {
			list = payDeductRepo.findByDelStatusOrderByDedTypeIdDesc(1);
		} catch (Exception e) {
			System.err.println("Excep in getAllPayDecuvtion : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = { "/getPayDeductionById" }, method = RequestMethod.POST)
	public PayDeduction getPayDeductionById(@RequestParam int typeId) {
		PayDeduction pay = new PayDeduction();
		try {
			pay = payDeductRepo.findByDedTypeIdAndDelStatus(typeId, 1);
		} catch (Exception e) {
			System.err.println("Excep in getPayDeductionById : " + e.getMessage());
			e.printStackTrace();
		}

		return pay;

	}

	@RequestMapping(value = { "/deletePayDeduction" }, method = RequestMethod.POST)
	public Info deleteEmployee(@RequestParam int typeId) {

		Info info = new Info();
		try {

			int res = payDeductRepo.deleteDeductnTypeById(typeId);

			if (res > 0) {
				info.setError(false);
				info.setMsg("Sucess");
			} else {
				info.setError(true);
				info.setMsg("Fail");
			}

		} catch (Exception e) {
			System.err.println("Excep in deletePayDeduction : " + e.getMessage());
			e.printStackTrace();
		}

		return info;
	}

	@RequestMapping(value = { "/saveDeductnPaymentType" }, method = RequestMethod.POST)
	public PayDeduction saveDeductnPaymentType(@RequestBody PayDeduction pay) {
		PayDeduction savePayDeduct = new PayDeduction();
		try {
			savePayDeduct = payDeductRepo.save(pay);
		} catch (Exception e) {
			System.err.println("Excep in /saveDeductnPaymentType : " + e.getMessage());
			e.printStackTrace();
		}

		return savePayDeduct;

	}
	
	/***************************************PayDeductionDetails*************************************/
	@RequestMapping(value = { "/savePayDeductnDetail" }, method = RequestMethod.POST)
	public PayDeductionDetails saveDeductnPaymentType(@RequestBody PayDeductionDetails pay) {
		PayDeductionDetails payDeductDetail = new PayDeductionDetails();
		try {
			payDeductDetail = detailRepo.save(pay);
		} catch (Exception e) {
			System.err.println("Excep in /savePayDeductnDetail : " + e.getMessage());
			e.printStackTrace();
		}

		return payDeductDetail;

	}
	
}
